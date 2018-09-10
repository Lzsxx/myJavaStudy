package excel;

import java.awt.geom.Dimension2D;
import java.io.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 使用注意：1、设置读取和输出的路径
// 2、 注意设置每读取多少个number就换行

public class ExtractAE {
    public static void main(String[] args) {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            // 输入变量有48个，所以每隔48个分一行
            final int VarNum = 48;
            int count = 0;

            /* 写入Txt文件 */
            File writename = new File("E:\\Sensorless_drive_diagnosis_excel\\AE\\ae_drive_relu_normalize.csv"); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename, true));
//            BufferedWriter out = new BufferedWriter(new FileWriter(writename));


            /* 读入TXT文件 */
            String pathname = "E:\\Sensorless_drive_diagnosis_excel\\AE\\ae_original.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

//            String inputMark = "input\\[\\d+?\\]\\s=\\s(.*)\\.";
            String inputMark = "input\\[\\d+?\\]\\s=\\s(.*)\\.\\sNormalized";
            ArrayList<String> strList = new ArrayList<>();
            Pattern titlePat = Pattern.compile(inputMark);

            String line = "";
            line = br.readLine();
            while (line != null) {
                System.out.println(line);
                Matcher matcherTitle = titlePat.matcher(line);
                if( matcherTitle.find()) {
                    String digital = matcherTitle.group(1);
                    out.write(digital+",");
                    count ++;
                    if (count >= VarNum) {
                        out.write("\n");
                        count = 0;
                    }
                    System.out.println("digital: "+  digital);
                }
                line = br.readLine(); // 一次读入一行数据
            }
            reader.close();
            br.close();

            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
