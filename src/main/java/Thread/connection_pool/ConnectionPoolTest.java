package Thread.connection_pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-09-18 21:53
 */
public class ConnectionPoolTest {
    static int threadCount = 30;   /* 线程数 */
    static int repeatCount = 20;   /* 线程里重复连接的次数 */
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end = new CountDownLatch(threadCount);
    static ConnectionPool connectionPool = new ConnectionPool(10);


    public static void main(String[] args) throws InterruptedException {
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notgot = new AtomicInteger();

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(repeatCount, got, notgot), "ConnectionRunner-" + i);
            thread.start();
        }

        start.countDown();
        end.await();

        System.out.println("尝试获取连接的次数：" + (threadCount * repeatCount));
        System.out.println("获取成功次数：" + got);
        System.out.println("获取失败次数：" + notgot);

    }

    /* 线程类 */
    private static class ConnectionRunner implements Runnable {
        int repeatCount;
        AtomicInteger got;
        AtomicInteger notgot;

        public ConnectionRunner(int repeatCount, AtomicInteger got, AtomicInteger notgot) {
            this.repeatCount = repeatCount;
            this.got = got;
            this.notgot = notgot;
        }

        @Override
        public void run() {
            /* 开启一个线程后，先阻塞，等待同一时刻发令后统一执行 */
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            System.out.println("跳过阻塞开始执行：" + Thread.currentThread().getName());

            /* 跳过阻塞后，开始正式执行 */
            while (repeatCount > 0) {
                Connection connection = null;
                try {
                    // 尝试取连接
//                    System.out.println("尝试取连接：" + Thread.currentThread().getName());
                    connection = connectionPool.fetchConnection(1000);

                    // 如果获取成功
                    if (connection != null) {
                        System.out.println("超时之前获取连接成功: " + Thread.currentThread().getName());
                        try {
                            connection.createStatement();
                            connection.commit();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally {
                            //只要获取连接成功了，无论执行如何，都要释放连接
                            connectionPool.releaseConnection(connection);
                            got.incrementAndGet();
                        }

                    } else {
                        // 获取失败
                        System.out.println("获取连接失败: " + Thread.currentThread().getName());
                        notgot.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    repeatCount--;
                }
            }
            /* 终止计数-1，当为0时，唤醒对应的await() */
            end.countDown();
        }
    }



}
