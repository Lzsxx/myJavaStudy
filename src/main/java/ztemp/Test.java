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

    }
}