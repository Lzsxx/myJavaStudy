package Algorithm.ArraysFindTarget;

import java.util.Arrays;

// 题目描述
// 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
// 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

public class ArraysFindTarget {
    public static void main(String[] args) {
        int [] array_1D = {1,2,3,4};
        int[][] array_2D = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
//        System.out.println(Arrays.toString(array_1D));
//        System.out.println(Arrays.deepToString(arrays_2D));
//        System.out.println(array_1D.length);
//        System.out.println(array_2D[0].length);
        int target = 13;
        int[] index = findTarget(array_2D,target);
        System.out.println("Target's indexs are: " + Arrays.toString(index));
        System.out.println("Target is: " + target);
        System.out.println("The find value is: " + array_2D[index[0]][index[1]]);
    }
    private static int[] findTarget(int[][] array_2D, int target){
        int rowLength = array_2D[0].length;
        int colLength = array_2D.length;
        int i = 0;
        int j = colLength - 1;
        while ( i <= colLength-1 && j >= 0 ){
            if (target == array_2D[i][j]){
                return new int[]{i,j};
            } else if (target < array_2D[i][j]){    //小于往左走
                j--;
            }else if(target > array_2D[i][j]){      //大于往下走
                i++;
            }
        }
        return  new int[]{-1,-1};
    }
}
