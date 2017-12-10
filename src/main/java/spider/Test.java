package spider;

//import sun.net.www.http.HttpClient;
import org.apache.commons.httpclient.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2017/12/10.
 */
public class Test {
    public static void main(String[] args) {
        try {

            File file = new File("E:\\Code\\JavaStudy\\novelTest.txt");
            if ( !file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);


            /*******爬取内容,存储在stringBuffer中******/

            StringBuffer stringBuffer = new StringBuffer();
            String realUrl = "http://www.jjwxc.net/onebook.php?novelid=3024023&chapterid=2";
            // 获取目标url的response
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(realUrl).openStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(realUrl).openStream(), Charset.forName("gb2312")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            fileWriter.write(stringBuffer.toString());
            bufferedReader.close();

            String test = stringBuffer.toString();
//            byte[] bytes = test.getBytes("utf-8");
            String utf8 = new String(test.getBytes("gb2312"), "gb2312");
            System.out.println(utf8);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testGet() throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://www.hao123.com");
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();
            int l;
            byte[] tmp = new byte[2048];
            while ((l = instream.read(tmp)) != -1) {
                System.out.println(new String(tmp, 0, l, "utf-8"));
            }
        }
    }
}
