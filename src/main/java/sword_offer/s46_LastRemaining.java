package sword_offer;

//每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
// HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
// 然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,
// 然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....
// 直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
// 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)

// 思路：用链表模拟循环链表来做吧

import java.util.LinkedList;

public class s46_LastRemaining {
    // 临场发挥递归版，更容易理解
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0 || m == 0) {
            return -1;
        }

        LinkedList<Integer> list = new LinkedList();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        return remove(list, 0, m); // 从0开始，移除第m个数
    }

    public int remove(LinkedList<Integer> list,int start, int removeNode) {
        if (list.size() == 1) {
            return list.get(0);
        }
        int idx = (removeNode - 1 + start) % list.size();
        list.remove(idx);
        return remove(list, idx, removeNode);   // 移除后，下一个起始点就是idx指向的点
    }

//    临时发挥版
public int LastRemaining_Solution2(int n, int m) {
    if (n == 0 || m == 0) {
        return -1;
    }
    LinkedList<Integer> list = new LinkedList<>();
    for (int i = 0; i < n; i++) {
        list.add(i);
    }
    int count = 0;
    int lastIndex = 0;
    while (list.size() > 1) {
        // 首先 (m - 1) % list.size() 表示以下标0为数数0开始时要提出的下标，但如果之前已经踢出过，
        // 那么实际下标0是从lastIndex即被踢出的下一个数开始，由于那个数被踢出了，所以0跳lastIndex次后的下标，就是起始下标
        // 而本轮该踢出的下标，则是(m - 1) % list.size() + lastIndex)，后面再加一个% list.size()，是解决循环的问题
        // 由于list.size（）是动态提取，所以已经考虑到踢出数据后size的变化
        int index = ((m - 1) % list.size() + lastIndex) % list.size();   // n-count是当前的长度，用lastIndex表示起点，加上当前长度，是模拟循环链表取模
        list.remove(index);
        lastIndex = index;
    }
    return list.get(0);
}


//    public int LastRemaining_Solution(int n, int m) {
//        if (n == 0 || m == 0){
//            return -1;
//        }
//        // n是编号数，第m-1唱歌
//        LinkedList<Integer> list = new LinkedList<>();
//        for (int i = 0; i < n; i++) {   // 0 ~ n - 1
//            list.add(i);
//        }
//        // 开始踢出
//        int index = (m - 1) % list.size();
//        while (list.size() > 1){
//            list.remove(index);
//            index = (index + m - 1) % list.size();
//        }
//        return list.get(0);
//    }
}
