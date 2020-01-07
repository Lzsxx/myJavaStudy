package ztest;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-12-30 09:30
 */
public class Test{
    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();
    public static String getDbType() {
        return HOLDER.get();
    }

    public static void setDbType(String dbType) {
        HOLDER.set(dbType);
    }

    public static void cleanDbHolder() {
        HOLDER.remove();
    }
    public static void main(String[] args) {
        final char c1 = '\u00A0';
        System.out.println("\'" + c1 + "\'");
        System.out.println(StringUtils.isBlank(Character.toString(c1)));

        final char c2 = '\u0020';
        System.out.println("\'" + c2 + "\'");
        System.out.println(StringUtils.isBlank(Character.toString(c2)));

    }
}