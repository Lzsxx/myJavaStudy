package thread.thread_pool;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-09-19 09:43
 */
public class DefaultThreadPool implements ThreadPool<WaitExecJob> {
    private static final int MAX_THRED_NUM = 10;   //最大线程数
    private static final int DEF_THRED_NUM = 5;    //默认
    private static final int MIN_THRED_NUM = 1;    //最小

    private final LinkedList<WaitExecJob> waitExecJobList = new LinkedList<>();    //待完成的工作列表
    private final List<WorkerRunner> workerRunners = Collections.synchronizedList(new ArrayList<>());     //工作者列表

    private int workerNum = DEF_THRED_NUM;     //工作线程的数量
    private AtomicLong threadNum = new AtomicLong();    //线程编号生成

    // 构造函数初始化
    public DefaultThreadPool() {
        createWorkers(DEF_THRED_NUM);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_THRED_NUM ? MAX_THRED_NUM : num < MIN_THRED_NUM ? MIN_THRED_NUM : num;
        createWorkers(workerNum);
    }

    // 创建线程，并添加到线程池中，初始创建不需要加锁，后续add要加锁，在addWorker里面加锁
    private void createWorkers(int num) {
        for (int i = 0; i < num; i++) {
            WorkerRunner workerRunner = new WorkerRunner();
            workerRunners.add(workerRunner);
            Thread thread = new Thread(workerRunner, "ThreadPool-work-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    // 执行任务，实际上只是向队列中新增任务，然后通知有任务到来，如果有线程是等待状态，则等待线程会被唤醒，
    // 否则不会有效，只能等待其他线程完成任务后来取这个任务
    @Override
    public void execute(WaitExecJob waitExecJob) {
        if (waitExecJob != null) {
            synchronized (waitExecJobList) {
                waitExecJobList.addLast(waitExecJob);
                waitExecJobList.notify();
            }
        }
    }

    @Override
    public int getJobSize() {
        return waitExecJobList.size();
    }

    @Override
    public void addWorkers(int num) {
        // 新增的时候要互斥访问，不能被其他remove的方法进入，也不能被分配任务的execute进入
        synchronized (waitExecJobList) {
            // 如果新增后的线程数大于允许的最大数量，就只能增加到最大数量之前的空额
            if (num + workerNum > MAX_THRED_NUM) {
                num = MAX_THRED_NUM - workerNum;
            }
            createWorkers(num);
            workerNum += num;
        }
    }

    @Override
    public void removeWorkers(int num) {
        synchronized (waitExecJobList) {
            if (num > workerNum) {
                throw new IllegalArgumentException("数量超出");
            }
            int count = 0;
            while (count < num) {
                // ArrayList可以通过count来访问,
                WorkerRunner workerRunner = workerRunners.get(count);
                // 如果移除成功
                if (workerRunners.remove(workerRunner)) {
                    workerRunner.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public void shutdown() {
        for (WorkerRunner workerRunner : workerRunners) {
            workerRunner.shutdown();
        }
    }


    public class WorkerRunner implements Runnable{

        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                WaitExecJob waitExecJob = null;
                synchronized (waitExecJobList) {
                    // 如果工作者列表是空的，就等待
                    while (waitExecJobList.isEmpty()) {
                        try {
                            waitExecJobList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            // 感知到外部对WorkerThread的中断，就返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    // 退出等待后，一定能取到一个，因为notify只会唤醒一个
                    waitExecJob = waitExecJobList.removeFirst();
                }

                // 释放锁，然后开始执行具体的任务
                // waitExecJob的run()并不会新起一个线程，只是单纯地执行
                // 也就是说，当Runnable不被传入Thread时，它的的run方法只是一个普通方法
                if (waitExecJob != null) {
                    waitExecJob.run();
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }

}
