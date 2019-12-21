package design_pattern.cglib_proxy;

import net.sf.cglib.core.DebuggingClassWriter;

public class Test {
    public static void main(String[] args) {
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\my_code\\JavaStudy\\src\\main\\java\\design_pattern\\cglib_proxy\\classFile");
//
        Hello helloProxy = HelloProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("CGlib");

    }
}
