package Thread.connection_pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-09-18 21:33
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    /* 按照指定大小，构造连接数 */
    public ConnectionPool(int initialSize) {
        for (int i = 0; i < initialSize; i++) {
            pool.addLast(ConnectionDriver.createConnection());
        }
    }

    /* 获取连接 */
    public  Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            /* 如果超时限制的时间是一个无效值，就不采用超时限制 */
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remain = mills;
                while (remain > 0 && pool.isEmpty()) {
                    pool.wait();
                    remain = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }

    }

    /* 释放连接 */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notify();
            }
        }
    }

}
