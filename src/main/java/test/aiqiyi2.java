package test;

import LintCode.CoinChange_669;

import java.util.Arrays;
import java.util.Scanner;

// 思路:首先取最小的数+2，直到大于等于中间大的数，如果等于，那么两者一起+1，到等于最大的数
// 如果最小的数+2之后，大于了中间数，那么对最小的数和最大的数同时+1，然后
public class aiqiyi2 {
    public static int count = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        in.close();
        String[] strs = input.split(" ");
        int[] arr = new int[3];
        arr[0] = Integer.parseInt(strs[0]);
        arr[1] = Integer.parseInt(strs[1]);
        arr[2] = Integer.parseInt(strs[2]);

//        int[] arr = {2,5,4};
        Arrays.sort(arr);   // 首先排序
        processMin(arr);
        System.out.println(count);
    }
    public static void processMin(int[] arr){  // 最小值的下标
        if (arr[0] == arr[1] && arr[1] == arr[2]){
            return;
        }
        boolean stop = false;
        while ( !stop ){
           while (arr[0] < arr[1]) {
               arr[0] += 2;
               count ++;
           }
//         离开以后判断，如果两个小的能够相等，那么直接都加1即可
           Arrays.sort(arr);
           if (arr[0] == arr[1] && arr[1] == arr[2]){
               stop = true;
               return;
           }else if (arr[0] == arr[1]){   // 两小一大
               while (arr[0] < arr[2]){
                   arr[0] ++;
                   arr[1] ++;
                   count ++;
               }
               stop = true;
               return;
           }else if (arr[1] == arr[2]){    // 两大一小
                int gap = arr[1] - arr[0];
                if (gap % 2 == 1){//相差奇数倍，那么两大先加1，再在小的上面加
                    arr[1] ++;
                    arr[2] ++;
                    gap ++;
                    count ++;
                }
               // 现在相差一个偶数倍，那么直接在小的上面加
               for (int i = 0; i < gap / 2; i++) {
                   arr[0] += 2;
                   count ++;
               }
           }
//           else {  //没有相等关系

//           }
       }
   }
}
