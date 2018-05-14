package excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class ProcessMyData_MinMaxAvg {
    private static final Map<String, String> originalDataMap = new HashMap<>();
    static {
        originalDataMap.put("1", "error_1_foreignBody");
        originalDataMap.put("2", "error_2_suddenlyStop");
        originalDataMap.put("3", "error_3_zigzagCurrent");
        originalDataMap.put("4", "error_4_lineBreak");
        originalDataMap.put("5", "error_5_delay");
        originalDataMap.put("6", "error_6_overproof");
        originalDataMap.put("7", "error_7_muddledness");
        originalDataMap.put("8", "error_8_notFlexible");
    }

    /***** 可配置项 *****/
    private static  String categoryFlag = "1";
    private static  int[] interval = {1, 2, 9, 10, 11, 20}; //6个阶段
    private static  String rootPath = "E:\\excel\\";
    private static  String filePath = rootPath + originalDataMap.get(categoryFlag);
    private static  String targetFile = rootPath + "target\\target.csv";

    boolean debugFlag = false;


    public static void main(String[] args) {
        ProcessMyData_MinMaxAvg data = new ProcessMyData_MinMaxAvg();
        for (int i = 1; i <= originalDataMap.size(); i++) {
            categoryFlag = String.valueOf(i);
            filePath = rootPath + originalDataMap.get(categoryFlag);
            data.getAllExcel();
        }
//        data.readExcel(new File("E:\\excel\\1,+0.1.xls"));

    }
    public void getAllExcel(){
        File file = new File(filePath);
        if (file.isDirectory()){
            File[] listFiles = file.listFiles();
            for (File f : listFiles){
                if (f.getName().endsWith(".xls")){
                    System.out.println(f.getName());
                    if (f.getName().equalsIgnoreCase("0.4,0.6.xls")){
                        debugFlag = true;
                    }
                    readExcel(f);
                }
            }
        }
    }

    public void writeExcel(Map<Integer, Double> minMap, Map<Integer, Double> maxMap, Map<Integer, Double> avgMap){
        ArrayList<ArrayList<String>> alldata=new ArrayList<ArrayList<String>>();
        ArrayList<String> list = new ArrayList<>();
        NumberFormat numberFormat = NumberFormat.getNumberInstance();        //digits 显示的数字位数 为格式化对象设定小数点后的显示的最多位,显示的最后位是舍入的
        numberFormat.setMaximumFractionDigits(15);

        for (int i = 0; i < interval.length; i++) {
            if (minMap.get(i) == null && maxMap.get(i) == null && avgMap.get(i) == null){ // 如果对应下标的map没有值，那么就用0填充
                list.add("0");
                list.add("0");
                list.add("0");
            }else {
                list.add(numberFormat.format(minMap.get(i)));
                list.add(numberFormat.format(maxMap.get(i)));
                list.add(numberFormat.format(avgMap.get(i)));
            }
        }

        list.add(categoryFlag);
        alldata.add(list);
        Array2CSV(alldata, targetFile);
    }

    public void readExcel(File file){


        Map<Integer, List<Double>> valueMap = new LinkedHashMap<>();  // 先存放分割后的数据

        File xlsFile = file;
        // 获得工作簿对象
        Workbook workbook = null;
        try {
            workbook = Workbook.getWorkbook(xlsFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        // 获得所有工作表
        Sheet[] sheets = workbook.getSheets();
        boolean stopFirstLayer = false;
        boolean stopSecondLayer = false;
        // 遍历工作表
        if (sheets != null)
        {
            for (Sheet sheet : sheets)
            {
                // 获得行数
                int rows = sheet.getRows();
                int lastIndex = Integer.MIN_VALUE;
                // 读取数据
                for (int row = 0; row < rows; row++)
                {
                    try {
// 读取每一行数据，一行里面从左到右一列列读
                        double time = Double.parseDouble(sheet.getCell(0, row).getContents());
                        double value = Double.parseDouble(sheet.getCell(1, row).getContents());
                        int index = getIndex(time);

                        if (index != lastIndex){    //由于时间数据是有序的，如果当前index与上一刻的index不同，那么就要新建一个list
                            List<Double> doubleList = new ArrayList<>();
                            valueMap.put(index, doubleList);
                            lastIndex = index;
                        }

                        List<Double> list = valueMap.get(index);
                        list.add(value);
                    }catch (NumberFormatException e){   // 如果读到非数值的数据，丢弃不管，处理下一行
                        continue;
                    }
                }
                // 遍历完一个sheet的所有行数且分类完毕，此时来做特征值计算,并写入一行到表格中
                computeSpecialValue(valueMap);

                break; // 只处理第一个sheet
            }
        }
        workbook.close();
    }

    public void computeSpecialValue(Map<Integer, List<Double>> valueMap){
        Map<Integer, Double> minMap = new HashMap<>();    // 保存最小值
        Map<Integer, Double> maxMap = new HashMap<>();    // 保存最大值
        Map<Integer, Double> avgMap = new HashMap<>();    // 保存平均值

        if (debugFlag){
            System.out.println("Stop");
        }

        for (int i = 0; i < interval.length; i++) {
            // 有多少个interval，那么最多会有多少个对于与map的list，但是有的list可能没有值，为null,所以这里要退出本次循环
            List<Double> list = valueMap.get(i);
            if (list == null){
                continue;
            }

            double minValue = Double.MAX_VALUE;
            double maxValue = Double.MIN_VALUE;
            double avgValue = 0;
            double sum = 0;

            for (double v : list){
                if (v < minValue){
                    minValue = v;
                }
                if (v > maxValue){
                    maxValue = v;
                }
                sum += v;
            }

            avgValue = sum / (double) list.size();
            System.out.println("平均值是："+avgValue);

            minMap.put(i, minValue);
            maxMap.put(i, maxValue);
            avgMap.put(i, avgValue);
        }

        // 最后调用write，将特征值写入文件
        writeExcel(minMap, maxMap, avgMap);
    }

    public int getIndex(double time) {
        if (time < 0){
            System.out.println("Error");
            return -1;
        }
        for (int i = 0; i < interval.length; i++) {
            if (time <= interval[i]){
                return i;
            }
        }
        return interval.length - 1;     // 超过20秒的，都放到20秒那个格子里
    }

    public static void Array2CSV(ArrayList<ArrayList<String>> data, String path) {
        try {
            BufferedWriter out =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,true),"UTF-8"));
            for (int i = 0; i < data.size(); i++)
            {
                ArrayList<String> onerow=data.get(i);
                for (int j = 0; j < onerow.size(); j++)
                {
//                    out.write(DelQuota(onerow.get(j)));
                    out.write(onerow.get(j));
                    out.write(",");
                }
                out.newLine();
            }
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String DelQuota(String str) {
        String result = str;
        String[] strQuota = { "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "`", ";", "'", ",", ".", "/", ":", "/,", "<", ">", "?" };
        for (int i = 0; i < strQuota.length; i++)
        {
            if (result.indexOf(strQuota[i]) > -1)
                result = result.replace(strQuota[i], "");
        }
        return result;
    }

}
