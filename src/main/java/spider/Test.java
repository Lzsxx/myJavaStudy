package spider;


import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
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



        } catch (IOException e) {
            e.printStackTrace();
        }



        try {
            httpClientGetWebPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void httpClientGetWebPage() {

        try {
            // 工厂模式获得httpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 创建httpget.
            HttpGet httpget = new HttpGet("http://www.jjwxc.net/onebook.php?novelid=3024023&chapterid=18");
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpClient.execute(httpget);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            // 打印响应状态
            System.out.println(response.getStatusLine());
            if (entity != null) {
                // 打印响应内容
                System.out.println("Response content: " + EntityUtils.toString(entity, Charset.forName("gb2312")));
            }

            response.close();
            // 关闭连接,释放资源
            httpClient.close();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
