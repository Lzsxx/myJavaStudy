package ztest;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author lzs
 * @date 2018/9/15 19:41
 */
public class weipinhui_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] strings = line.split(" ");
        int kth = Integer.parseInt(strings[0]);
        int n = Integer.parseInt(strings[1]);

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            line = in.nextLine();
            strings = line.split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(strings[j]);
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }


        // 第kth大元素肯定在前 range 行内产生
        int range =(int) Math.ceil((double) (kth) / n);
        // 肯定不会在下标小于等于exclude的方阵内产生
        int exclude = (int) Math.floor(Math.sqrt(kth - 1));
        int gap = kth - (int)Math.pow(exclude, 2);  // 在剩余的探测范围内找第gap大的数

//        对探测范围内的数采用插入排序
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < range; i++) {
            for (int j = 0; j < n; j++) {
                // 左上角方阵内
                if (i < exclude && j < exclude) {
                    continue;
                }else {
                    list.add(matrix[i][j]);
                }

            }
        }
        // 有时候刚好方阵右下角是，需要补充进去
        list.add(matrix[exclude - 1][exclude - 1]);
        gap = gap + 1;
        // 排序
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        System.out.println(list.get(gap - 1));
    }
}
