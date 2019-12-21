package design_pattern.jdk_proxy;

import com.sun.java.browser.plugin2.liveconnect.v1.BridgeFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler{
    private Object target;  // 这是要被代理的类的实例对象

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }


    /**
     * proxy是动态生成的类
     * method是Hello接口里的方法
     * args是方法参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);    //通过反射调用方法
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
