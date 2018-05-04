package design_pattern.cglib_proxy;

public class Test {
    public static void main(String[] args) {
//        CGLibProxy cgLibProxy = new CGLibProxy();
//        Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
        Hello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("CGlib");
    }
}
