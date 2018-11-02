package excel;

import java.io.*;

/**
 * @author lzs
 * @date 2018/9/17 4:07
 */
public class DeleteFakeAE {
    public static void main(String[] args) {
        String inputFile = "E:\\Sensorless_drive_diagnosis_excel\\AE\\AE原始记录+Net预测.csv";
        String outputFile = "E:\\Sensorless_drive_diagnosis_excel\\AE\\剔除非对抗样本.csv";

        deleteFakeAE(inputFile, outputFile);

    }

    public static void deleteFakeAE(String inputFile, String outputFile) {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            // 输入变量有48个，所以每隔48个分一行
            int count = 0;

            /* 写入Txt文件 */
            File writename = new File(outputFile); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename, false));

            /* 读入TXT文件 */
            String pathname = inputFile; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename), "GBK"); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] strings = line.split(",");
                if (!strings[48].equals(strings[50])) {
                    out.write(line + "\n");
                }
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
