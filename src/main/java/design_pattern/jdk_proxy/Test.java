package design_pattern.jdk_proxy;


import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
//        Hello hello = new HelloImpl();
//        DynamicProxy dynamicProxy = new DynamicProxy(hello);

//        Hello helloProxy = (Hello) Proxy.newProxyInstance(hello.getClass().getClassLoader(),
//                hello.getClass().getInterfaces(), dynamicProxy);

        Hello helloProxy = new DynamicProxy(new HelloImpl()).getProxy();
        helloProxy.say("jdk Dynamic");
    }
}
