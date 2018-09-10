package ztest;

/**
 * @author lzs
 * @date 2018/9/10 19:31
 */
public class JD_choose {
    public static void main(String[] args) {
        System.out.println("A");
        new JD_choose();
        new JD_choose();
    }

    public JD_choose() {
        System.out.println("B");
    }
    {
        System.out.println("C");
    }
    static {
        System.out.println("D");
    }
    // DA CB CB
}
