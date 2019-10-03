package Thread.sui_army;

public class ArmyRunnable implements Runnable {
    // volatile保证可见性
    volatile boolean keepRunning = true;

    @Override
    public void run() {
        while (keepRunning) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "进攻对方[" + 1 + "]");
                Thread.yield(); // 让出CPU
            }
        }
        System.out.println(Thread.currentThread().getName() + "退出了战斗！");
    }
}