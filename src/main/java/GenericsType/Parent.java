package GenericsType;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-05-26 19:18
 */
public class Parent<T>{

    public void setName(T name) {
        System.out.println("Parent:" + name);
    }
}

