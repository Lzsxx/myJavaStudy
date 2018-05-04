package design_pattern.static_proxy;

public class Test {
    public static void main(String[] args) {
        HelloProxy helloProxy = new HelloProxy();
        helloProxy.say("Tongji~");
    }
}
