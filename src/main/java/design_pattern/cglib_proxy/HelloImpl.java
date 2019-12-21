package design_pattern.cglib_proxy;

public class HelloImpl implements Hello {
    @Override
    public void say(String name) {
        System.out.println("Hello! " + name);
    }

    public void goodMorning(String name) {
        System.out.println("good morning! " + name);
    }
}
