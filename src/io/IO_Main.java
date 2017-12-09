package io;

/**
 * Created by Administrator on 2017/12/7.
 */
public class IO_Main {
    public static void main(String[] args) {
        /**
         * @Description: 调用FileOutputStream向txt文件写数据，但只能按照byte来写，
         * 写Int会出乱码，不知道为啥，
         * 写中文的话，弄好编码类型即可
         */
        try {
            IOUtils.testOutputStream_writeFile("E:\\Code\\JavaStudy\\fileOutputStream.txt");
            IOUtils.testWriter_writeFile("E:\\Code\\JavaStudy\\fileWriter.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
