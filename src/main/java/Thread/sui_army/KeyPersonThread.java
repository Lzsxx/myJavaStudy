package Thread.sui_army;

public class KeyPersonThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始了战斗！");
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "左突右击，攻击隋军...");
        }
        System.out.println(Thread.currentThread().getName() +"退出了战斗！");
    }
}
