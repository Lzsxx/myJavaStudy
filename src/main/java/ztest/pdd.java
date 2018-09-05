package ztest;

import java.util.HashSet;
import java.util.Scanner;

class Pdd {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] inputArr = input.split(" ");
        int n = Integer.parseInt(inputArr[0]);
        int m = Integer.parseInt(inputArr[1]);
        int k = Integer.parseInt(inputArr[2]);

//         int n = 2;
//         int m = 2;
//         int k = 6;

        char[] chs = new char[m + n];
        for (int i = 0; i < n; i++) {
            chs[i] = 'a';
        }
        for (int i = 0; i < m; i++) {
            chs[n + i] = 'z';
        }
        for (int i = n - 1; i >= 0 ; i--) {
            currTrun_InitialAindex = i;
            arrange(chs, i, k);
        }
    }
    private static int currTrun_InitialAindex;
    private static boolean flag = false;
    private static HashSet<String> hashSet = new HashSet<>();
    public static void arrange(char[] chs, int lastA, int target){
        if (flag || lastA < -1) {
            return;
        }
        hashSet.add(new String(chs));
        if (hashSet.size() == target) {
            System.out.println(new String(chs));
            flag = true;
            return;
        }
        int nextZindex = findNextZindex(chs, lastA);
        if (nextZindex > -1) {
            swap(chs, lastA, nextZindex);    // A 和最近的一个Z交换。
            arrange(chs, nextZindex, target);  // 下次开始时，A的起始点是Z，如果没有Z会返回-1，结束递归
            swap(chs, lastA, nextZindex);    // 交换回去
        }else {
            int nextA = findNextAindex(chs, currTrun_InitialAindex, lastA);
            if (nextA > -1){
                lastA = nextA;
                nextZindex = findNextZindex(chs, lastA);
                if (nextZindex > -1) {
                    swap(chs, lastA, nextZindex);    // A 和最近的一个Z交换。
                    arrange(chs, nextZindex, target);  // 下次开始时，A的起始点是Z，如果没有Z会返回-1，结束递归
                    swap(chs, lastA, nextZindex);    // 交换回去
                }
            }
        }
    }

   public static int findNextAindex(char[] chs, int from, int exclude) {
        int i ;
       for ( i = from; i < chs.length; i++) {
           if (chs[i] == 'a') {
               break;
           }
       }
       return i == exclude ? - 1 : i;
   }

   public static int findNextZindex(char[] chs, int from) {
       for (int i = from; i < chs.length; i++) {
           if (chs[i] == 'z') {
               return i;
           }
       }
       return - 1;
   }
    public static void swap(char[] arr, int a, int b){
        if (a == b || a < 0 || b < 0)
            return;
        char temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
