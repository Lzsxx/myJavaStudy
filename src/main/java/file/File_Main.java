package file;

import java.io.File;
import java.io.IOException;

public class File_Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        /**
        * @Description: 调用FileUtils的方法进行遍历文件夹及文件操作
        */
        try {
            file.FileUtils.listAllFiles(new File("E:\\Code\\JavaStudy"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
        * @Description: 调用IOUtils的方法读取文件，并将每个字节按照十六进制格式打印出来，
         * 只能读取字节，不是读取字符
        */
        try {
            io.IOUtils.testInputStream_printHex("E:\\Code\\JavaStudy\\wukongzhuan.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }





    }
}
