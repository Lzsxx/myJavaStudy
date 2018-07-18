package ztest;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

class ttt {
    public static void main(String[] args) {
        Bear bear = new Bear(); // call Father constructor:kind:bear
        BlackBear blackBear = new BlackBear();  // call Father constructor:kind:bear, color:null，
        //因为子类在构造的时候，默认会调用父类的无参构造函数super()，如果父类没有无参的构造函数，就会出错。
        //解决办法：在子类构造方法里手动调用其他有参的父类构造函数super(args)，或者在父类里添加一个无参构造函数
        System.out.println(blackBear.toString());   // kind:bear, color:black
    }
}
class Bear {
    private String kind = "bear";
    @Override
    public String toString() {
        return "kind:" + kind;
    }
    public Bear(){
        System.out.println("call Father constructor:" + toString());
    }
}
class BlackBear extends Bear{
    private String color = "black";
    @Override
    public String toString() {
        return super.toString() + ", color:" + color;
    }
}