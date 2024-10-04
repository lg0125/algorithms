package com.chris.algorithms.templates.structure;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/*
核心流程，包括：

1 有n个一直在运行的线程，相当于我们创建线程池时允许的线程池大小。
2 把线程提交给线程池运行。
3 如果运行线程池已满，则把线程放入队列中。
4 最后当有空闲时，则获取队列中线程进行运行。
 */
public class MyThreadPoolExecutor implements Executor {
    private final AtomicInteger ctl = new AtomicInteger(0);


    private volatile int corePoolSize;
    private volatile int maximumPoolSize;

    private final BlockingQueue<Runnable> workQueue;

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
    }

    @Override
    public void execute(Runnable command) {
        int c = ctl.get();
        if (c < corePoolSize) {
            if (!addWorker(command))
                reject();
            return;
        }

        if (!workQueue.offer(command))
            if (!addWorker(command))
                reject();
    }

    private boolean addWorker(Runnable firstTask) {
        if (ctl.get() >= maximumPoolSize)
            return false;

        Worker worker = new Worker(firstTask);
        worker.thread.start();

        ctl.incrementAndGet();

        return true;
    }

    private final class Worker implements Runnable {

        final Thread thread;
        Runnable firstTask;

        public Worker(Runnable firstTask) {
            this.thread = new Thread(this);
            this.firstTask = firstTask;
        }

        @Override
        public void run() {
            Runnable task = firstTask;
            try {
                while (task != null || (task = getTask()) != null) {
                    task.run();
                    if (ctl.get() > maximumPoolSize)
                        break;
                    task = null;
                }
            } finally {
                ctl.decrementAndGet();
            }
        }

        private Runnable getTask() {
            for (; ; ) {
                try {
                    return workQueue.take();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void reject() {
        throw new RuntimeException("Error！ctl.count：" + ctl.get() + " workQueue.size：" + workQueue.size());
    }
}
