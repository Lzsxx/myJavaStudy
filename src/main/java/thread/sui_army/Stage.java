package thread.sui_army;

public class Stage extends Thread {
    @Override
    public void run() {

        System.out.println("欢迎观看隋唐演义");
        try {
            Thread.sleep(1000);     // 暂缓执行，此时没有其他线程争CPU，所以只是为了延缓节奏，没有其他逻辑效果
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("大幕徐徐拉开");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("话说隋朝末年...");

        ArmyRunnable armyTaskOfSuiDynasty = new ArmyRunnable();
        ArmyRunnable armyTaskOfRevolt = new ArmyRunnable();

        Thread armyOfSuiDynasty = new Thread(armyTaskOfSuiDynasty, "隋军");
        Thread armyOfRevolt = new Thread(armyTaskOfRevolt, "农民起义军");

        // 启动线程，让军队开始作战
        armyOfSuiDynasty.start();
        armyOfRevolt.start();

        // 作战过程中，线程休眠，大家专心观看军队厮杀
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 拼杀了50毫秒后，关键人物登场
        System.out.println("正当双飞激战正酣，半路杀出了个程咬金");
        Thread mrCheng = new KeyPersonThread();
        mrCheng.setName("程咬金");
        System.out.println("程咬金的理想就是结束战争，使百姓安居乐业！");

        /*** 至此，armyTaskOfRevolt 和 armyTaskOfSuiDynasty 都还没有用false停下，所以Stage线程在执行过程中，还是会被打断 ，
         * 即：会在程咬金半路杀出来以后，以及理想宣告之前，出现双方军队继续厮杀的提示***/

//        农民起义军进攻对方[1]
//        隋军进攻对方[1]
//        正当双飞激战正酣，半路杀出了个程咬金
//        隋军进攻对方[1]
//        隋军进攻对方[1]
//        农民起义军进攻对方[1]
//        程咬金的理想就是结束战争，使百姓安居乐业！
//        程咬金开始了战斗！
//        程咬金左突右击，攻击隋军...
//        程咬金左突右击，攻击隋军...

        // 历史大戏留给关键人物，双方厮杀的小兵要停止战争
        armyTaskOfRevolt.keepRunning = false;
        armyTaskOfSuiDynasty.keepRunning = false;   // 如果相应Thread里的run是for循环语句，这里设置以后，其实并不能及时终止

        try {
            Thread.sleep(2000); // 加停顿是为了观众观看效果
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关键人物出场
        mrCheng.start();

        // 万众瞩目，所有线程等待程先生完成历史使命
        try {
            mrCheng.join(); // 调用了join方法，会使其他线程（包括父进程和兄弟进程）等待这个线程执行完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("战争结束，人民安居乐业，程先生实现了自己的梦想");
        System.out.println("谢谢观看隋唐演义，再见！");

    }

    public static void main(String[] args) {
        new Stage().start();
    }
}
