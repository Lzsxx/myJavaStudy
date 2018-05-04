package design_pattern.static_proxy;

public class HelloProxy implements Hello {
    private Hello hello;

    public HelloProxy() {
        this.hello = new HelloImpl();
    }

    @Override
    public void say(String name) {
        before();
        hello.say(name);
        after();
    }

    public void before() {
        System.out.println("Before proxy");
    }

    public void after() {
        System.out.println("After proxy");
    }
}
