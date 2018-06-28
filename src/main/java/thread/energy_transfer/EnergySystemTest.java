package thread.energy_transfer;

public class EnergySystemTest {
    // 将要构建的能量世界中能量盒子的总数
    public static final int BOX_AMOUNT = 100;
    // 每个盒子的初始容量
    public static final double INITIAL_ENERGY = 1000;

    public static void main(String[] args) {
        EnergySystem eng = new EnergySystem(BOX_AMOUNT, INITIAL_ENERGY);
        for (int i = 0; i < BOX_AMOUNT; i++) {
            EnergyTransferTask task = new EnergyTransferTask(eng, i, INITIAL_ENERGY);
            Thread t = new Thread(task, "TransferThread_" + i);
            t.start();
        }
    }
}
