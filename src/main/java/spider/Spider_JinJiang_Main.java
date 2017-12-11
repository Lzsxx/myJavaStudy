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
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/12/9.
 */
public class Spider_JinJiang_Main {
    public static void main(String[] args) {
        /**
         * @Description: 本程序用于爬取Jinjiang小说的免费部分，
         * 只需传入想要下载的小说的封面URL，就能下载全部免费章节
         */
        // 爬取参数
        String baseUrl = "http://www.jjwxc.net/onebook.php?novelid=2766439";
        // html里小说名字的标志
        String novelMark = "<span itemprop=\"articleSection\">(.*?)</span>";
        // html里章节标题的标志
        String titleMark = "<option value=\"\">(.*?)</option>";
        // html里正文的标志
        String contentMark = "<div style=\"clear:both;\"></div>\\s*?(.*?)\\s*?<div";
        // html里爬取到VIP章节的标志
        String stopMark = "<a itemprop=\"url\" style=\"cursor:pointer\" onclick=\"(vip_buy\\('vip_\\d+'\\))\" ";
        // 网页编码
        Charset charsetName = Charset.forName("gb2312");
        // 存储地址
        String destFilePath = "E:\\Code\\JavaStudy\\novel_Jinjiang";
        // 如果存在同名，是否覆盖？默认不覆盖
        Boolean coverOld = true;
        int chapterCount = 0;
        int vipBegin = 0;

        try {
            //如果目录文件夹不存在，则创建
            File testPath = new File(destFilePath);
            if ( !testPath.exists()){
                testPath.mkdirs();
            }
            //如果文件不存在，则创建
            String fileName = "\\" + getRandomName() + ".txt";
            File destFile = new File(destFilePath + fileName);
            if ( !destFile.exists() ) {
                destFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(destFilePath + fileName, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            /*****开始调用spider爬取内容，结束后更改正确的书名*****/

            System.out.println("开始爬取数据...");
            long startTime = System.currentTimeMillis();

            // 判断是否是第一次调用spider，如果是，则需要获取并写入书名
            String returnNovelName = spiderGo_HttpClient(chapterCount,vipBegin, baseUrl, bufferedWriter,novelMark, titleMark, contentMark,stopMark, charsetName);
            // 由于不能重复关闭，所以不能在递归函数中关闭，只好移到最外层函数结束之后再关闭
            bufferedWriter.close();
            // 重命名之前，如果已有同名文件，则判断配置，如果要求覆盖生成，则先删除之前的文件
            String formalFileName = destFilePath + "\\" + returnNovelName + ".txt";
            if (coverOld){
                String alreayExistFileName = formalFileName;
                File alreayExistFile = new File(alreayExistFileName);
                if ( alreayExistFile.exists() ) {
                    alreayExistFile.delete();
                    System.out.println("Delete Old File " + alreayExistFileName);
                }
            }
            //爬取完成后重命名整理，有同样名称时失败，会保持原名
            destFile.renameTo(new File(formalFileName));

            long endTime = System.currentTimeMillis();
            System.out.println("用时 " + (endTime - startTime) / 1000 + "秒...");

            /*****  The End  *****/

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * @Description: 开始爬取内容，并写入txt文件
     * @param: 当前章节计数，VIP章节开始的index，书名url,写入流，标题标志，内容标志，遇到vip停止标志，字符编码集
     * @return:  void
     */

    public static String spiderGo_HttpClient(int chapterCount,int vipBegin, String baseUrl, BufferedWriter bufferedWriter, String novelNameMark,
                                  String titleMark, String contentMark , String stopMark, Charset charsetName) {
        System.out.println("chapter:"+chapterCount);
        String returnNovelName = "novel";
        try {
            /*******爬取内容,存储在stringBuffer中******/

            String  resultContent = httpClientGetWebPage(baseUrl, charsetName);
//            System.out.println(resultContent);

            /*****对整个网页内容进行筛选，写入文件，并且需要判断VIP章节数*****/
            // 如果是第一次调用，就获取书名并写入
            if (chapterCount == 0){
                // 书名匹配
                Pattern novelNamePat = Pattern.compile(novelNameMark);
                Matcher matcherNovel = novelNamePat.matcher(resultContent);
                if(matcherNovel.find()) {
                    returnNovelName = matcherNovel.group(1);
                    bufferedWriter.newLine();
                    bufferedWriter.write(returnNovelName);
                    bufferedWriter.newLine();
                }

                /*****判断VIP章节数，确定结束爬取的位置*****/
                Pattern stopPat = Pattern.compile(stopMark);
                Matcher matcherStop = stopPat.matcher(resultContent);
                // 如果是入V章节，则有Vip标志，只爬取免费章节
                if (matcherStop.find()) {
                    String vipStr = matcherStop.group(1).split("'")[1].split("_")[1];
                    vipBegin = Integer.valueOf(vipStr);
                    System.out.println(vipStr);
                }else {
                    // 如果没有vip标志，可能是全文免费，此时要自己计数章节数目，爬取全部章节
                    Pattern chapterCountPat = Pattern.compile("<span itemprop=\"headline\">\\s*?<div style=\"float:left\">\\s*<a itemprop=\"url\"\\s*");
                    Matcher matcherChapterCount = chapterCountPat.matcher(resultContent);
                    while (matcherChapterCount.find()) {
                        vipBegin ++;
                    }
                    System.out.println(vipBegin + "  Chapter All Free!");
                    vipBegin ++;
                }
            }

            /*** 标题正则匹配***/
            Pattern titlePat = Pattern.compile(titleMark);
            Matcher matcherTitle = titlePat.matcher(resultContent);
            if(chapterCount > 0 && matcherTitle.find()) {
                String title = matcherTitle.group(1);
                bufferedWriter.newLine();
                bufferedWriter.write(title);
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }

            /*** 正文正则匹配表达式***/
            Pattern contentPat = Pattern.compile(contentMark);
            Matcher matcher = contentPat.matcher(resultContent);
            if (chapterCount > 0 && matcher.find()) {
                String[] contentArr = matcher.group(1).split("(\\s*<br>\\s*)+");
                for (String  paragraph : contentArr) {
                    paragraph = paragraph.trim();

                    if(paragraph.length() <= 2){
                        continue;       //由于作者会多加空行，这里是希望去除多余空行，不写入文件
                    }

                    bufferedWriter.write(paragraph);
                    bufferedWriter.newLine();

                }
            }

            /***匹配下一章链接***/
            // 传入false表示不是第一次调用，以后不需要再获取书名
            if(chapterCount != -1 && chapterCount < vipBegin){
                chapterCount ++ ;
                spiderGo_HttpClient(chapterCount,vipBegin, baseUrl + "&chapterid="+ Integer.toString(chapterCount), bufferedWriter , novelNameMark,titleMark, contentMark,stopMark, charsetName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnNovelName;
    }

    public static String httpClientGetWebPage( String baseUrl, Charset charsetName) {
        String htmlPage = "";
        try {
            // 工厂模式获得httpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // 创建httpget.
            HttpGet httpget = new HttpGet(baseUrl);
//            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpClient.execute(httpget);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            // 打印响应状态
//            System.out.println(response.getStatusLine());
            if (entity != null) {
                htmlPage = EntityUtils.toString(entity, charsetName);
                // 打印响应内容
//                System.out.println("Response content: " + htmlPage);
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

        return htmlPage;
    }

    public static String getRandomName(){
        long t = System.currentTimeMillis();
        Random rd = new Random(t);//作为种子数传入到Random的构造器中
        int randomInt = rd.nextInt();//生成随即整数

        return "novel" + Integer.toString(randomInt);

    }
}
