package com.chris.algorithms.templates.structure.test20240916;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPoolExecutor implements Executor {
    private volatile int corePoolSize;
    private volatile int maximumPoolSize;
    private final BlockingQueue<Runnable> workQueue;

    private final AtomicInteger ctl = new AtomicInteger(0);

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize       = corePoolSize;
        this.maximumPoolSize    = maximumPoolSize;
        this.workQueue          = workQueue;
    }

    @Override
    public void execute(Runnable task) {
        int cnt = ctl.get();

        if(cnt < corePoolSize) {
            if(!addWorker(task))
                reject();
            return;
        }

        if(!workQueue.offer(task))
            if(!addWorker(task))
                reject();
    }

    private boolean addWorker(Runnable firstTask) {
        int cnt = ctl.get();
        if(cnt >= maximumPoolSize)
            return false;

        Worker worker = new Worker(firstTask);
        worker.thread.start();

        ctl.incrementAndGet();

        return true;
    }

    private void reject() {
        throw new RuntimeException(
                "Error！ctl.count：" + ctl.get() +
                        " workQueue.size：" + workQueue.size()
        );
    }

    public final class Worker implements Runnable {
        final Thread thread;

        Runnable firstTask;

        public Worker(Runnable firstTask) {
            this.thread     = new Thread(this);
            this.firstTask  = firstTask;
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
            for(; ;) {
                try {
                    return workQueue.take();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
