package design_pattern.static_proxy;

import java.io.File;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
        HelloProxy helloProxy = new HelloProxy();
        helloProxy.say("Tongji~");
    }

    /**
     * 只有当存在的file是文件夹，或不存在文件夹而创建成功时，才能正常结束，否则都会抛出异常
     * @param fileDir
     * @throws Exception
     */

    public static void creatDir(String fileDir) throws Exception {
        File file = new File(fileDir);
        if (file.exists()) {
            //如果已存在的文件不是文件夹，此时无法创建同名文件夹，抛出异常
            if (!file.isDirectory()) {
                throw new Exception("存在同名文件，无法创建文件夹");
            }
        } else {
            //尝试创建文件夹，如果创建失败，则抛出异常
            if (!file.mkdirs()) {
                throw new Exception("创建文件夹失败");
            }
        }
    }
}
