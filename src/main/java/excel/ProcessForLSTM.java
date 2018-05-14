package excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ProcessForLSTM {
    private static final Map<String, String> originalDataMap = new HashMap<>();
    static {
        originalDataMap.put("0", "normal");
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
    private static  String categoryFlag = "0";
    private static  String rootPath = "E:\\excel\\";
    private static  String filePath = rootPath + originalDataMap.get(categoryFlag);
    private static  String targetFile = rootPath + "target\\target.csv";

    boolean debugFlag = false;


    public static void main(String[] args) {
        ProcessForLSTM data = new ProcessForLSTM();
        for (int i = 0; i < originalDataMap.size(); i++) {
            categoryFlag = String.valueOf(i);
            filePath = rootPath + originalDataMap.get(categoryFlag);
            data.getAllExcel();
        }
//        data.readExcel(new File("E:\\excel\\normal.xls"));

    }
    public void getAllExcel(){
        File file = new File(filePath);
        if (file.isDirectory()){
            File[] listFiles = file.listFiles();
            for (File f : listFiles){
                if (f.getName().endsWith(".xls")){
                    System.out.println(f.getName());
                    if (f.getName().equalsIgnoreCase("0.4,0.6.xls") || f.getName().equalsIgnoreCase("1.3,1.45.xls")){
                        debugFlag = true;
                    }
                    readExcel(f);
                }
            }
        }
    }

    public void writeExcel(ArrayList<String> list){
        ArrayList<ArrayList<String>> alldata=new ArrayList<ArrayList<String>>();
//        NumberFormat numberFormat = NumberFormat.getNumberInstance();        //digits 显示的数字位数 为格式化对象设定小数点后的显示的最多位,显示的最后位是舍入的
//        numberFormat.setMaximumFractionDigits(15);

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
                ArrayList<String> stringList = new ArrayList<>();
                stringList.add(String.valueOf(categoryFlag));
                for (int row = 0; row < rows; row++)
                {
                    try {
                        // 读取每一行的电流数据
                        String value = sheet.getCell(1, row).getContents();
                        if (value.equals("Y")) {
                            continue;
                        }
                        stringList.add(value);
                    }catch (NumberFormatException e){   // 如果读到非数值的数据，丢弃不管，处理下一行
                        continue;
                    }
                }
                // 遍历完一个sheet的所有行数写入excel
                writeExcel(stringList);
                break; // 只处理第一个sheet
            }
        }
        workbook.close();
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
