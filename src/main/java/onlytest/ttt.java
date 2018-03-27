package onlytest;

public class ttt {
    public static void main(String[] args) {
        Bear bear = new Bear(); // call Father constructor:kind:bear
        BlackBear blackBear = new BlackBear();  // call Father constructor:kind:bear, color:null
        System.out.println(blackBear.toString());   // kind:bear, color:black

        Bear up = new BlackBear();
        BlackBear down = (BlackBear) up;

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