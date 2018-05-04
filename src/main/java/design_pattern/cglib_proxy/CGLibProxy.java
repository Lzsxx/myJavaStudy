package design_pattern.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CGLibProxy implements MethodInterceptor {
    /***** 修改为单例模式 *****/
    private static CGLibProxy instance = new CGLibProxy();
    private CGLibProxy() {

    }
    public static CGLibProxy getInstance() {
        return instance;
    }
    /***** 修改完毕 *****/

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }
    public void before() {
        System.out.println("Before proxy");
    }

    public void after() {
        System.out.println("After proxy");
    }
}
