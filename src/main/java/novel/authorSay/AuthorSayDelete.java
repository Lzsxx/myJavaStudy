package novel.authorSay;

import io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;

public class AuthorSayDelete {
    public static void main(String[] args) {
        String fileDir = "D:\\BaiduNetdiskDownload";
        String sourceFile = "tianjiao.txt";
        String targetFile = "tianjiao-del.txt";
        String beginPtn = ".*作者有话要说.*";
        String endPtn = ".*☆、第.*";
        boolean writeFlag = true;   // 默认读到的每一行都会写，直到读到作者有话要说，改为false，后续行都不写，读到下一段的开头，又改为true继续写
        Charset charset = Charset.forName("GBK");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileDir, sourceFile)), charset));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileDir,targetFile)),charset));){
            String line = null;
            while ( (line= reader.readLine()) != null) {
                if (line.matches(beginPtn)) {
                    writeFlag = false;
                } else if (line.matches(endPtn)) {
                    writeFlag = true;
                }

                if (writeFlag) {
                    out.write(line);
                    out.write("\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
