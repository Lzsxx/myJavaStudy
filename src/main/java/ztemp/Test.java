package ztemp;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-06-03 15:17
 */

public class Test
{
    public static ExecutorService executorService = Executors.newFixedThreadPool(3);
    public static void main(String[] args) throws InterruptedException, IOException {

        String strPath = "C:\\a\\aa\\aaa.txt";
        File file = new File(strPath);
        File fileParent = file.getParentFile();
        System.out.println("parentPath:" + fileParent.getName());
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        file.createNewFile();

//        for (int i = 0; i < 20; i++) {
//            executorService.submit(() -> {
//                System.out.println(Thread.currentThread().getName());
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//
//        }
//        System.out.println("开始等待");
//        executorService.shutdown();
//        boolean b = executorService.awaitTermination(300, TimeUnit.SECONDS);
//        System.out.println("等待完毕: " + b);
//        if(b){
//            System.out.println("分线程已经结束");
//        }

        /* 循环遍历一连串文件信息，它们的文件名可能相同，也可能不同，相同的经过排序连在一起 */
        String newName = "";
        String oldName = "";
        for (int i = 0; i < 9999; i++) {
            // 1、获取新的文件名
            newName = Integer.toString(i);

            // 2、判断新旧文件名是否相同，以此来确定是直接写当前文件，还是创建新文件再写
            if (!oldName.equals(newName)) {
                // 3、如果文件名不同，表示在处理一个新文件

                // 6、判断切换文件之前是否有旧文件在处理
                if (!"".equals(oldName)) {
                    // 7、如果有，修正首行记录，关闭上一个文件的处理
                }


                // 4（8）、切换处理下一个文件
                oldName = newName;

                // 4（8）、创建文件，写入初始文件头，并更新oldName，表示当前正在处理的文件

            }
            // 如果旧文件名已经等于新文件名，表示还是在处理同一个文件
            // 或者文件名不同，但新文件已经创建好，可以开始后续步骤

            // 5（9）、写入文件体记录，同时累计文件头需要的数据
        }
        // 10、处理完所有文件之后，对最后一个文件进行首行记录的修正，关闭最后一个文件的处理



    }

}