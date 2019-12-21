package design_pattern.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class HelloProxy implements MethodInterceptor {
    /***** 修改为单例模式 *****/
    private static HelloProxy instance = new HelloProxy();
    private HelloProxy() {

    }
    public static HelloProxy getInstance() {
        return instance;
    }
    /***** 修改完毕 *****/

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    /**
     * Object o 是动态生成的代理类 HelloImpl$$EnhancerByCGLIB$$fe1ccb9c，say()方法被重写过
     * Method method 是HelloImpl里的方法say()
     * Object[] objects 是方法参数
     * MethodProxy methodProxy 是对应method的代理方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);          // 正常情况
//        Object result = methodProxy.invoke(new HelloImpl(), objects); // 也能运行，但是要指定具体的Hello接口的实现类，失去了灵活性，变成了静态代理
//        Object result = method.invoke(new HelloImpl(), objects);      // 情况同上

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
