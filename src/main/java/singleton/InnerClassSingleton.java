package singleton;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-12-28 17:16
 */
public class InnerClassSingleton {
    private static class SingletonHolder {
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }
    private InnerClassSingleton (){}
    public static final InnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
