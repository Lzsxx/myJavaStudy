package Thread.connection_pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-09-18 21:36
 */
public class ConnectionDriver {
    private static class ConnectionProxy implements InvocationHandler {
        //代理Connection里的方法，但什么也不做
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            TimeUnit.MILLISECONDS.sleep(500);
            return null;
        }

        public static Object getInstance() {
            return Proxy.newProxyInstance(ConnectionProxy.class.getClassLoader(), new Class[]{Connection.class}, new ConnectionProxy());
        }
    }

    public static Connection createConnection() {
        return (Connection) ConnectionProxy.getInstance();
    }
}
