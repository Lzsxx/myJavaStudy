package sword_offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
//输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,
// 则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。

// 思路：递归；每次交换一个字符作为第一个数，然后进入下一个递归，
// 递归出来后，将数组恢复原状，等下一次循环来改变。
public class s27_String_list_all_serial {
    public static void main(String[] args) {
        String sss = "ab";
        s27_String_list_all_serial test = new s27_String_list_all_serial();
        test.Permutation(sss);
    }
    public ArrayList<String> list = new ArrayList<>();
    public HashSet<String> set = new HashSet<>();
    public ArrayList<String> Permutation(String str) {
        if (str.length() == 0){
            return list;
        }
        char[] chs = str.toCharArray();
        Permutation(chs, 0, chs.length-1);
        Collections.sort(list);
        return list;
    }
    public void Permutation(char[] ch, int start, int end) {
        if (start == end){  //等于时，此时形成的字符串就是当前排列的结果
            String str = String.valueOf(ch);
            if (!set.contains(str)){
                set.add(str);
                list.add(str);
            }
            return;
        }
        for (int i = start; i <= end ; i++) {   // 每次循环是选定第一个起始的字符
            swap(ch, i, start); // 交换，选定当前起始的字符是i
            Permutation(ch, start+1, end);  // 对start后的子串继续循环，确定位置
            swap(ch, start, i); //复原
        }
    }
    public static void swap(char[] arr, int a, int b){
        if (a == b)
            return;
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
