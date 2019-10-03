package design_pattern.cglib_proxy;

import net.sf.cglib.core.DebuggingClassWriter;

public class Test {
    public static void main(String[] args) {
//        CGLibProxy cgLibProxy = new CGLibProxy();
//        Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\my_code\\JavaStudy\\src\\main\\java\\design_pattern\\cglib_proxy\\classFile");

        Hello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("CGlib");

    }
}
