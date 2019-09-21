package GenericsType;

import java.awt.image.ShortLookupTable;
import java.util.ArrayList;

/**
 * @Description: JavaStudy
 * @author: lzs
 * @date:: 2019-05-26 19:19
 */

public class Son extends Parent<String>  {

    public void setName(String name) {
        System.out.println("son:" + name);
    }


    public static void main(String[] args) {
        Son son=new Son();
        son.setName("abc");
//        son.setName(new Object());//The method setName(String) in the type Son is not applicable for the arguments (Object)
    }


}


