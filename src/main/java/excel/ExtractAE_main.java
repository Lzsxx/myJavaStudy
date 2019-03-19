package excel;

import java.awt.geom.Dimension2D;
import java.io.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 使用注意：1、设置读取和输出的路径
// 2、 注意设置每读取多少个number就换行

public class ExtractAE_main {
    public static void main(String[] args) {
        String statsFile = "C:\\my_code\\processData\\3_1&2_0.03_checkmynet_stats.txt";
        String middleFile = "C:\\my_code\\processData\\stats_to_summary.txt";
        String outputFile = "C:\\my_code\\processData\\AE原始记录.csv";
        /** change!  **/
        final int outputNum = 3;

        boolean hasNormalize = true;   // 数据是否分为正则化和非正则化
        boolean getNormalize = false;    // 只有当正则化时才有效
        /** change end **/

        ExtractSummary.extractSummary(statsFile, middleFile);
        extractAE(middleFile, outputFile, outputNum, hasNormalize, getNormalize);
    }
    public static void extractAE(String inputFile,String outputFile, int outputNum, boolean hasNormalize, boolean getNormalize) {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            int count = 0;

            /* 写入Txt文件 */
            File writename = new File(outputFile); // 相对路径，如果没有则要建立一个新的output。txt文件
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename, false));

//            先写入一个[，配合后续
            out.write("[");

            /* 读入TXT文件 */
            String pathname = inputFile; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

            String inputMark = "";
            if (!hasNormalize) {
                // 无Non-Normalized的版本
                inputMark = "input\\[\\d+?\\]\\s=\\s(.*)\\.";
            }else{
                if (getNormalize) {
                    inputMark = "input\\[\\d+?\\]\\s=\\s(.*)\\.\\sNon-Normalized";   // 取Normalized的版本
                } else{
                    inputMark = "input\\[\\d+?\\]\\s=\\s(.*)\\.\\sNon-Normalized:\\s(.*)\\.";  // 取Non-Normalized的版本

                }
            }
            ArrayList<String> strList = new ArrayList<>();
            Pattern titlePat = Pattern.compile(inputMark);

            String beginMark = "Output using nnet.cpp";
            String outputMark = "output\\[\\d+?\\]\\s=\\s(.*)\\.";
            Pattern beginPat = Pattern.compile(beginMark);
            Pattern outputPat = Pattern.compile(outputMark);
            ArrayList<Double> outList = new ArrayList<>();
            boolean outputFlag = false;
            int outputCount = 0;

            String dividMark = "^Test\\sfor\\spoint\\s(\\d+?),";
            Pattern dividPat = Pattern.compile(dividMark);

            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                Matcher matcherBegin = beginPat.matcher(line);
                if (matcherBegin.find()) {
                    // 如果发现了开始输出output，做标记
                    outputFlag = true;
                    continue;
                }
                if (outputFlag) {
                    Matcher matcherOutput = outputPat.matcher(line);
                    if( matcherOutput.find()) {
                        String digital = matcherOutput.group(1);
                        outList.add(Double.parseDouble(digital));
                    }
                    outputCount++;
                    if (outputCount >= outputNum) {
                        //当分类输出的结果为10个分类，如果到第10个，就该统计大小了
                        double max = Double.MIN_VALUE;
                        double secondMax = max;
                        int maxIndex = -1;
                        int secongMaxIndex = maxIndex;

                        for (int i = 0; i < outList.size(); i++) {
                            double temp = outList.get(i);
                            if (max < temp) {
                                max = temp;
                                maxIndex = i;
                            } else if (max == temp) {    // 有时候会有两个最大值的情况
                                secondMax = temp;
                                secongMaxIndex = i;
                            }
                        }
                        // 统计完后比较，如果secondMax不是最小值，且等于max，则写入两个，否则，写入一个数
                        if (secondMax != Integer.MIN_VALUE && secondMax == max) {
                            out.write(secongMaxIndex+ "" + maxIndex + "],\n[");
                        }else {
                            out.write( maxIndex + "],\n[");
                        }
                        outputFlag = false;
                        outputCount = 0;
                        outList.clear();
                    }
                    continue;
                }

                // 如果不是output行，就开始判断是否是input行
                Matcher matcherTitle = titlePat.matcher(line);
                if( matcherTitle.find()) {
                    String digital = "";
                    if (hasNormalize && !getNormalize) {
                        digital = matcherTitle.group(2);
                    }else {
                        digital = matcherTitle.group(1);
                    }
                    out.write(digital+",");
                    System.out.println("digital: "+  digital);
                    continue;
                }

                // 再匹配一种类型的分隔符
                Matcher matcherDivid = dividPat.matcher(line);
                if( matcherDivid.find()) {
                    String point = matcherDivid.group(0);
                    out.write(point+",\n[");
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
