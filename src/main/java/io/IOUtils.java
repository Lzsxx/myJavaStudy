package io;

import java.io.*;

/**
 * Created by Administrator on 2017/12/5.
 */
public class IOUtils {

    /**
    * @Description: FileInputStream
    */
    public static void testInputStream_printHex(String fileName) throws IOException{
        // 把文件作为字节流进行读操作
        /****第一种方法，一个个字节读，比较慢，但省空间***/
        long startTime = System.currentTimeMillis();    //获取开始时间

        FileInputStream fileInputStream = new FileInputStream(fileName);
        int abyte ;
        while ( (abyte = fileInputStream.read()) != -1){
            System.out.print(Integer.toHexString(abyte) + " ");
        }
        fileInputStream.close();

        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("\n一个个读取，程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间

        /****第二种方法，一次读取一个字节数组，循环读取，比较快，但占据内存空间***/
        startTime = System.currentTimeMillis();    //获取开始时间

        fileInputStream = new FileInputStream(fileName);
        byte[] buf = new byte[24 * 1024];
        int bytes = 0;
        while ((bytes = fileInputStream.read(buf,0,buf.length))!= -1){
            for (int i=0; i < bytes; i++){
                System.out.print(Integer.toHexString(buf[i] & 0xff) + " ");
            }
        }
        fileInputStream.close();

        endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("\n字节数组读取，程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间

        /****第三种方法，用Buffer***/
        startTime = System.currentTimeMillis();    //获取开始时间

        fileInputStream = new FileInputStream(fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream((fileInputStream));

        int temp;
        while ((temp = bufferedInputStream.read())!= -1){
            //System.out.print(ztemp);
        }
        fileInputStream.close();

        endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("\n缓冲区读取，程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间


    }

    /**
    * @Description: FileOutputStream   DataOutputStream
    */
    public static void testOutputStream_writeFile(String fileName){

        try {

            //如果文件不存在，则直接创建，如果存在，删除后创建
            // 如果加第2个参数为true，表示存在时在后追加，不删除
            FileOutputStream fileOutputStream = new FileOutputStream(fileName,false);

            //写入char字符的低8位
            fileOutputStream.write('A');

            /****Stream类型的API只能处理byte字节，下面的100是当做字节来处理，如果要写入数字100，
             * 要将其当做字符串写入，用Writer的相关API**/
            //写Int
            int a = 100;
            //fileOutputStream.write(a);  //只会写入int的低8位，要完整，需要手动处理如下：
            fileOutputStream.write((a >>> 24) & 0xff);
            fileOutputStream.write((a >>> 16) & 0xff);
            fileOutputStream.write((a >>> 8) & 0xff);
            fileOutputStream.write(a);


            // 写入byte数组
            byte[] gbk= "中国哈哈哈".getBytes("utf-8");
            fileOutputStream.write(gbk);

            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
    * @Description: FileWriter
    */
    public static void testWriter_writeFile(String fileName){
        try {
            FileWriter fileWriter = new FileWriter(fileName,false);
            fileWriter.write("100");//写入100
            fileWriter.write(100);  //写入d
            String hhh = "哈哈哈哈哈";//写入哈哈哈
            fileWriter.write(hhh);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
