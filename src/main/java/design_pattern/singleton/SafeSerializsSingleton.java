package design_pattern.singleton;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2020-04-22 22:29
 */
public class SafeSerializsSingleton {
    public static void main(String[] args) throws Exception {
        Single instance = Single.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(copyInstance(instance).hashCode());

        //使用反射方式直接调用私有构造器
        Class<Single> clazz = (Class<Single>)Class.forName("com.lebron.test.single.Single");
        Constructor<Single> con = clazz.getDeclaredConstructor(null);
        con.setAccessible(true);//绕过权限管理，即在true的情况下，可以通过构造函数新建对象
        Single instance1 = con.newInstance();
        Single instance2 = con.newInstance();
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());

    }

    private static Single copyInstance(Single instance) throws Exception{
        FileOutputStream fos = new FileOutputStream("d:/a.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(instance);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/a.txt"));
        Single single2 = (Single)ois.readObject();
        oos.close();
        ois.close();
        return single2;
    }
}

class Single implements Serializable{
    private static final long serialVersionUID = 1L;

    // 设置flag初始为false，只能设置一次true，如果被反射破坏，多次创建，就抛出异常
    private static boolean flag = false;
    private Single(){
        synchronized (Single.class) {
            if (!flag) {
                flag = true;
            } else {
                throw new RuntimeException("单例模式被侵犯！");
            }

        }
    }

    private static Single single;

    public static Single getInstance(){
        if ( single == null ) {
            synchronized (Single.class) {
                if ( single == null ) {
                    single = new Single();
                }
            }

        }
        return single;
    }

    //
    private Object readResolve() {
        return single;
    }
}
