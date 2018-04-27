package excel;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvWriteDemo {
    public static void main(String[] arges){
        //自定义数组
        ArrayList<ArrayList<String>> alldata=new ArrayList<ArrayList<String>>();
        alldata.add(new ArrayList<String>(Arrays.asList("1","11","111")));  //添加一行
        alldata.add(new ArrayList<String>(Arrays.asList("2","22","222")));  //添加一行
        alldata.add(new ArrayList<String>(Arrays.asList("3","33","333")));  //添加一行
        //保存成csv文件
        Array2CSV(alldata,"E:\\excel\\target\\target.csv");
    }
    //导出到csv文件
    public static void Array2CSV(ArrayList<ArrayList<String>> data, String path)
    {
        try {
            BufferedWriter out =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,true),"UTF-8"));
            for (int i = 0; i < data.size(); i++)
            {
                ArrayList<String> onerow=data.get(i);
                for (int j = 0; j < onerow.size(); j++)
                {
                    out.write(DelQuota(onerow.get(j)));
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
    public static String DelQuota(String str)
    {
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
