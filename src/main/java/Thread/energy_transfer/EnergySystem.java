package Thread.energy_transfer;

public class EnergySystem {
    private final double[] energyBoxes;
    private final Object lockObj = new Object();    // 一个普通的java对象，本身已经具备了wait()和notify（）等方法

    public EnergySystem(int n, double initialEnergy) {
        energyBoxes = new double[n];
        for (int i = 0; i < energyBoxes.length; i++) {
            energyBoxes[i] = initialEnergy;
        }
    }

    public void transfer(int from, int to, double amount) {
        synchronized (lockObj) {
            // 当资源不足时，被while循环放到等待队列里等待，而不会去竞争CPU资源
            while (energyBoxes[from] < amount) {
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.print(Thread.currentThread().getName());
            energyBoxes[from] -= amount;
            System.out.printf("从%d转移%10.2f单位能量到%d", from, amount, to);
            energyBoxes[to] += amount;
            System.out.printf("能量总和：%10.2f%n", getTotalEnergies());
            // 执行完毕，退出临界区，退出前要唤醒其他等待线程
            lockObj.notifyAll();
        }
    }

    public double getTotalEnergies() {
        double sum = 0;
        for (double amount : energyBoxes) {
            sum += amount;
        }
        return sum;
    }

    public int getBoxAmount() {
        return energyBoxes.length;
    }
}
