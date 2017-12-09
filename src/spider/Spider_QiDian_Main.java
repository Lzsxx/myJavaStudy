package spider;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/12/9.
 */
public class Spider_QiDian_Main {
    public static void main(String[] args) {
        /** 
        * @Description: 本程序用于爬取起点中文网上的小说的免费部分，
         * 只需传入想要下载的小说点击“免费试读”后的URL，就能下载全部免费章节
        */        
        // 爬取参数
        String baseUrl = "https://read.qidian.com/chapter/rJgN8tJ_cVdRGoWu-UQg7Q2/eSlFKP1Chzg1";
        // html里小说名字的标志
        String novelMark = "<a.*id=\"bookImg\".*?>(.*?)</a>";       //第一个？必须，这样才能非贪心匹配
        // html里章节标题的标志
        String titleMark = "<h3 class=\"j_chapterName\">(.*?)</h3>";
        // html里正文的标志
        String contentMark = "<div class=\"read-content j_readContent\">(.*?)</div>";
        // html里下一章链接的标志
        String nextChapterMark = "<a id=\"j_chapterNext\" href=\"(.*?)\" data-eid";
        // 网页编码
        Charset charsetName = Charset.forName("utf-8");
        // 存储地址
        String destFilePath = "E:\\Code\\JavaStudy\\novel";


        try {
            File testPath = new File(destFilePath);
            if ( !testPath.exists()){
                testPath.mkdirs();
            }
            String fileName = "\\" + getRandomName() + ".txt";
            File destFile = new File(destFilePath + fileName);
            // 如果目标文件不在则删除
            if ( !destFile.exists() ) {
                destFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(destFilePath + fileName, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            /*****开始调用spider爬取内容，结束后更改正确的书名*****/

            System.out.println("开始爬取数据...");
            long startTime = System.currentTimeMillis();
            // 判断是否是第一次调用spider，如果是，则需要获取并写入书名
            boolean first = true;
            String returnNovelName = spiderGo(first, baseUrl, bufferedWriter,novelMark, titleMark, contentMark,nextChapterMark, charsetName);
            // 由于不能重复关闭，所以不能在递归函数中关闭，只好移到最外层函数结束之后再关闭
            bufferedWriter.close();
            //爬取完成后重命名整理，有同样名称时失败，会保持原名
            destFile.renameTo(new File(destFilePath + "\\" + returnNovelName + ".txt"));

            long endTime = System.currentTimeMillis();
            System.out.println("用时 " + (endTime - startTime) / 1000 + "秒...");

            /*****  The End  *****/

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
    * @Description: 开始爬取内容，并写入txt文件
    * @param: 是否第一次调用，爬取文章的URL地址，写入流，标题标志，内容标志，下一章链接标志，字符编码集
    * @return:  void
    */

    public static String spiderGo(Boolean first, String baseUrl, BufferedWriter bufferedWriter, String novelMark,
                                  String titleMark, String contentMark , String nextChapterMark, Charset charsetName) {
        String returnNovelName = "novel";
        try {

            /*******爬取内容,存储在stringBuffer中******/
            StringBuffer stringBuffer = new StringBuffer();
            String realUrl = baseUrl;
            // 获取目标url的response
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(realUrl).openStream(),charsetName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();

            /*****对整个网页内容进行筛选，写入文件*****/
            String resultContent = stringBuffer.toString();

            // 如果是第一次调用，就获取书名并写入
            if (first){
                // 书名匹配
                Pattern novelPat = Pattern.compile(novelMark);
                Matcher matcherNovel = novelPat.matcher(resultContent);
                if(matcherNovel.find()) {
                    returnNovelName = matcherNovel.group(1);
                    bufferedWriter.newLine();
                    bufferedWriter.write(returnNovelName);
                    bufferedWriter.newLine();
                }
            }


            // 标题正则匹配
            Pattern titlePat = Pattern.compile(titleMark);
            Matcher matcherTitle = titlePat.matcher(resultContent);
            if(matcherTitle.find()) {
                String title = matcherTitle.group(1);
                bufferedWriter.newLine();
                bufferedWriter.write(title);
                bufferedWriter.newLine();
            }

            // 正文正则匹配表达式
            Pattern contentPat = Pattern.compile(contentMark);
            Matcher matcher = contentPat.matcher(resultContent);
            if (matcher.find()) {
                String[] contentArr = matcher.group(1).split("\\s*<p>\\s*");
                for (String  paragraph : contentArr) {
                    bufferedWriter.write(paragraph);
                    bufferedWriter.newLine();
                }
            }

            //匹配下一章链接
            Pattern nextPat = Pattern.compile(nextChapterMark);
            Matcher matcherNext = nextPat.matcher(resultContent);
            if (matcherNext.find()) {
                String nextHref = matcherNext.group(1);
                String vipMark =  nextHref.split("\\.")[0];
                System.out.println(vipMark);
                // 当链接是以read开头时，表示下一章是普通章节，继续抓取，否则是vip章节，放弃抓取
                if (vipMark.equals("//read")){
                    // 传入false表示不是第一次调用，以后不需要再获取书名
                    spiderGo(false,"https:"+nextHref, bufferedWriter , novelMark,titleMark, contentMark,nextChapterMark, charsetName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnNovelName;
    }

    public static String getRandomName(){
        long t = System.currentTimeMillis();
        Random rd = new Random(t);//作为种子数传入到Random的构造器中
        int randomInt = rd.nextInt();//生成随即整数

        return "novel" + Integer.toString(randomInt);

    }
}
