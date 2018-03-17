package LintCode;

public class MinStack_12 {
    private int[] items;
    private int size;
    private static final int DEFAULT_SIZE = 5;
    private int min = 0;    //默认第一个元素最小
    private int current = -1;   //

    public MinStack_12() {
        // do intialization if necessary
        doClear();
    }
    public void doClear(){
        size = 0;
        ensureCapacity(DEFAULT_SIZE);
    }
    public void ensureCapacity(int wantSize){
        if (wantSize < size){
            return;
        }
        int[] old = items;
        items = new int[wantSize];
        for (int i = 0; i < size; i++) {
            items[i] = old[i];
        }
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        // write your code here
        if (size >= items.length){
            ensureCapacity(2 * size + 1);
        }
        current ++; //指向要添加的位置
        items[current] = number;
        if (items[min] > items[current]){
            min = current;
        }
        size ++;
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        if (size <= 0){
            return -1;
        }
        size --;

        if ( current == min ){        //current指向当前要删除的元素
            min = 0;
            if (size > 1){
                for (int i = 1; i < size; i++) {
                    if (items[min] > items[i]){
                        min = i;
                    }
                }
            }
        }
        int temp = items[current -- ];
        System.out.println(temp);
        return temp;
    }

    /*
     * @return: An integer
     */
    public int min() {
        // write your code here
        if (size <= 0){
            return -1;
        }
        int temp = items[min];
        System.out.println(temp);
        return temp;
    }

    public void test(){
        push(152);
        pop();
        push(163);
        min();
        push(177);
        min();
        pop();
        min();
        push(178);
        min();
        push(154);
        pop();
        pop();
        min();
        push(167);
        min();
        push(171);
        min();
        push(160);
        min();
        push(171);
        min();
        pop();
        pop();
        pop();
        pop();
        pop();
        push(157);
        min();
        push(169);
        min();
        push(156);
        min();
        push(160);
        min();
        push(162);
        min();
        pop();
        pop();
        pop();
        min();
        push(173);
        min();
        push(158);
        min();
        push(171);
        min();
        push(172);
        min();
        push(176);
        min();
        push(180);
        pop();
        min();
        push(152);
        min();
        push(156);
        min();
        push(166);
        pop();
        min();
        pop();
        min();
        push(171);
        pop();
        min();
        push(166);
        pop();
        min();
        pop();
        min();
        pop();
        pop();
        pop();
        min();
        push(174);
        min();
        push(166);
        min();
        pop();
        min();
        push(175);
        min();
        pop();
        pop();
        min();
        push(169);
        min();
        push(170);
        pop();
        min();
        push(165);
        min();
        push(159);
        min();
        push(177);
        pop();
        min();
        pop();
        pop();
        min();
        push(153);
        min();
        push(180);
        min();
        pop();
        min();
        push(171);
        min();
        push(177);
        min();
        push(180);
        min();
        push(177);
        min();
        pop();
        pop();
        pop();
        min();
        push(154);
        pop();
        min();
        push(153);
        min();
        push(158);
        min();
        pop();
        min();
        push(172);
        min();
        push(159);
        pop();
        min();
        push(151);
        pop();
        min();
        push(154);
        min();
        push(163);
        min();
        push(111);
    }

    public static void main(String[] args) {
        MinStack_12 minStack = new MinStack_12();
        minStack.test();
    }
}
