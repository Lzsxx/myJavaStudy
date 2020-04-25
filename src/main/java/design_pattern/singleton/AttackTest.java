package design_pattern.singleton;

import design_pattern.singleton.InnerClassSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-12-28 17:17
 */
public class AttackTest {
    public static void main(String[] args) {
        attack();
    }

    public static void attack() {
        Class<?> InnerClassSingleton = design_pattern.singleton.InnerClassSingleton.class;
        try {
            Constructor<?>[] constructors = InnerClassSingleton.getDeclaredConstructors();
            constructors[0].setAccessible(true);
            InnerClassSingleton instance1 = (InnerClassSingleton) constructors[0].newInstance();
            InnerClassSingleton instance2 = (InnerClassSingleton) constructors[0].newInstance();
            if (instance1 == instance2) {
                System.out.println("attack failed,攻击Sinleton5失败");
            } else {
                System.out.println("attack success!攻击Sinleton5成功，创建了多个实例");
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
