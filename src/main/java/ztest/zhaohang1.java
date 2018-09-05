package ztest;

import java.util.Scanner;

/**
 * @author lzs
 * @date 2018/8/30 20:24
 */
public class zhaohang1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        char[] chs = line.toCharArray();
        char[] chs_reverse = line.toCharArray();

        int count = 0;
        int count_reverse = 0;

        int n = chs.length;
        int flag = n;
        while (flag > 0){
            flag = 0;
            for (int j = 1; j < n; j++){
                if (chs[j] < chs[j - 1]){
                    count++;
                    swap(chs, j , j-1);
                    flag = j;
                }
            }
            n = flag;
        }

        n = chs_reverse.length;
        flag = n;
        while (flag > 0) {
            flag = 0;
            for (int j = 1; j < n; j++) {
                if (chs_reverse[j] > chs_reverse[j - 1]) {
                    count_reverse++;
                    swap(chs_reverse, j, j - 1);
                    flag = j;
                }
            }
            n = flag;
        }
        int min = count > count_reverse ? count_reverse : count;
        System.out.println(min);

    }

    public static void swap(char[] chs, int a, int b){
        if (a == b)
            return;
        char temp = chs[a];
        chs[a] = chs[b];
        chs[b] = temp;
    }
}
