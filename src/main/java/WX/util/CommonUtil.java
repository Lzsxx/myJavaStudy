package WX.util;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 类名: CommonUtil </br>
 * 描述: 通用工具类  </br>
 */
public class CommonUtil {
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    public static void writeToFile(String fileDir, String fileName, String content) throws FileNotFoundException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileDir,fileName), true)));

        String time = getTime();

        try {
            out.write(content);
            out.write("," + time);
            out.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        IOUtils.closeQuietly(out);
    }

    public static void writeToFileCover(String fileDir, String fileName, String content) throws FileNotFoundException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileDir,fileName), false)));

        try {
            out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        IOUtils.closeQuietly(out);
    }

    public static String readFile(String fileDir, String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileDir, fileName))));

        String line = reader.readLine();

        IOUtils.closeQuietly(reader);

        return line.trim();
    }

    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String time = sdf.format(date);
        return time;
    }



    /**
     * URL编码（utf-8）
     *
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据内容类型判断文件扩展名
     *
     * @param contentType 内容类型
     * @return
     */
    public static String getFileExt(String contentType) {
        String fileExt = "";
        if ("image/jpeg".equals(contentType))
            fileExt = ".jpg";
        else if ("audio/mpeg".equals(contentType))
            fileExt = ".mp3";
        else if ("audio/amr".equals(contentType))
            fileExt = ".amr";
        else if ("video/mp4".equals(contentType))
            fileExt = ".mp4";
        else if ("video/mpeg4".equals(contentType))
            fileExt = ".mp4";
        return fileExt;
    }
}