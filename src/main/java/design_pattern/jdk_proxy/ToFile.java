package design_pattern.jdk_proxy;

import sun.misc.OSEnvironment;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-12-20 16:12
 */
public class ToFile {

    public static void main(String[] args) throws IOException {
        Hello hello = (Hello) new DynamicProxy(new HelloImpl()).getProxy();
        hello.say("hello");

        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Hello.class});
        FileOutputStream os = new FileOutputStream("C:\\my_code\\JavaStudy\\src\\main\\java\\design_pattern\\jdk_proxy\\classFile\\$Proxy0.class");
        os.write(bytes);

        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
