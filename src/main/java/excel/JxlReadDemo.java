package excel;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * jxl读excel
 *
 * @author jianggujin
 *
 */
public class JxlReadDemo
{
    public static void main(String[] args) throws BiffException, IOException
    {
        File xlsFile = new File("E:\\excel\\1,+0.1.xls");
        // 获得工作簿对象
        Workbook workbook = Workbook.getWorkbook(xlsFile);
        // 获得所有工作表
        Sheet[] sheets = workbook.getSheets();
        // 遍历工作表
        if (sheets != null)
        {
            for (Sheet sheet : sheets)
            {
                // 获得行数
                int rows = sheet.getRows();
                // 获得列数
                int cols = sheet.getColumns();
                // 读取数据
                for (int row = 0; row < rows; row++)
                {
                    for (int col = 0; col < cols; col++)
                    {
                        // 读取每一行数据，一行里面从左到右一列列读
                        System.out.printf("%10s", sheet.getCell(col, row).getContents());
                    }
                    System.out.println();
                }
            }
        }
        workbook.close();
    }
}