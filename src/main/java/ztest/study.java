package ztest;

/**
 * @author lzs
 * @date 2018/10/18 15:51
 */
public class study {
   public static void main(String[] args) {
                   StringBuilder param = new StringBuilder("123");
                   String str = getString(param);
                  System.out.println(str);
              }

           private static String getString(StringBuilder pa){
                  pa.append(34);
                 System.out.println(11);
                 return "33";
              }
}
