package ztest;

import design_pattern.jdk_proxy.Test;

/**
 * @author lzs
 * @date 2018/9/10 20:55
 */
public class JD_choose3 {
    public static void main(String[] args) {
        System.out.println(Test2.s);
    }


}
class Test2 {
    static {
        System.out.println("OK");
    }

    public static  String s = "JD";
//    public static final String s = new String("JD");
}