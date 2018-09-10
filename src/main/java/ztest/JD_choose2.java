package ztest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzs
 * @date 2018/9/10 19:37
 */
public class JD_choose2 {
    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        Thread t1 = new JD_choose2().new MyThread();
        Thread t2 = new Thread(new JD_choose2().new MyRunnable());

        t1.setPriority(3);
        t1.setPriority(8);

        t1.start();
        t2.start();
        t2.join();

        for (int i = 0; i < 100000; i++) {
            i++;
        }

        list.add("main");
        t1.join();

        for (String s : list) {
            System.out.println(s);
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                i ++;
            }
            list.add("Thread 1");
        }
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                Thread.yield();
                i++;
            }
            list.add("Thread 2");
        }
    }
}
