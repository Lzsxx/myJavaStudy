package Thread.thread_pool;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-09-19 09:33
 */
public interface ThreadPool<T extends Runnable> {
    void execute(T job);

    void shutdown();

    void addWorkers(int num);

    void removeWorkers(int num);

    int getJobSize();
}
