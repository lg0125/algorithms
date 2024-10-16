package com.chris.algorithms.templates.structure.test20241004;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPool implements Executor {

    private final AtomicInteger ctl = new AtomicInteger(0);

    private volatile int corePoolSize;

    private volatile int maximumPoolSize;

    private final BlockingQueue<Runnable> workQueue;

    public MyThreadPool(
            int corePoolSize,
            int maximumPoolSize,
            BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
    }

    @Override
    public void execute(Runnable task) {
        int workerCount = ctl.get();
        if(workerCount < corePoolSize) {
            if(!addWorker(task))
                reject();
            return;
        }

        if(!workQueue.offer(task)) {
            // workerCount >= corePoolSize && workQueue is Full
            if(!addWorker(task))
                reject();
        }
    }

    private boolean addWorker(Runnable firstTask) {
        int workerCount = ctl.get();
        if(workerCount >= maximumPoolSize)
            return false;

        Worker worker = new Worker(firstTask);
        worker.start();

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
            this.thread = new Thread(this);
            this.firstTask = firstTask;
        }

        @Override
        public void run() {
            Runnable task = firstTask;
            try {
                while (task != null || (task = getTask()) != null) {
                    task.run();

                    int workerCount = ctl.get();
                    if(workerCount > maximumPoolSize)
                        break;

                    task = null;
                }
            } finally {
                ctl.decrementAndGet();
            }
        }

        private Runnable getTask() {
            while (true) {
                try {
                    return workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void start() {
            thread.start();
        }
    }
}
