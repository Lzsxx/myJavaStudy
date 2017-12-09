package file;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/5.
 */
public class FileUtils {
    private File file;
    public FileUtils(File file) {
        this.file = file;
    }

    /** 
    * @Description: 列出路径下所有的文件夹和文件 
    * @param: 
    * @return:     
    */
    public static void listAllFiles(File dir) throws IOException{
        if( !dir.exists()){
            throw new IllegalArgumentException("not exist");
        }

        if (dir.isFile()){
            System.out.println("A file : "+dir.getName());
        }else if (dir.isDirectory()){
            FileUtils.traverseDirectory(dir);
        }
    }
    /** 
    * @Description: 专门用于遍历文件夹里面的所有文件和文件夹,不对外暴露
    * @param: The name or path of the Dir
    * @return:  none
    */
    private static void traverseDirectory(File dir){
        if (dir.isDirectory()){
            System.out.println(dir.getName());
            File[] fileList = dir.listFiles();
            for (File fileName : fileList) {
                if (fileName.isFile()){
                    System.out.println("A file : "+fileName.getName());
                }else if (fileName.isDirectory()){
                    FileUtils.traverseDirectory(fileName);
                }
            }
        }
    }
}
