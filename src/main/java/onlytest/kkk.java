package onlytest;

import javax.swing.*;
import java.util.Scanner;

 class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x;
        int [][] axis = new int[n * 2][4];
        for(int i = 0; i < n * 2; i++){
            for(int j = 0; j < 4; j++){
                x = sc.nextInt();
                axis[i][j] = x;
            }
        }
        process(axis);
    }
    public static void process(int[][] axis){
        for (int i = 0; i < axis.length; i += 2) {
           int x1 = axis[i][0];
           int y1 = axis[i+1][0];

            int x2 = axis[i][1];
            int y2 = axis[i+1][1];

            int x3 = axis[i][2];
            int y3 = axis[i+1][2];

            int x4 = axis[i][3];
            int y4 = axis[i+1][3];

            double edge1 = (x1 - x2)^2 + (y1 - y2)^2;
            double edge2 = (x2 - x3)^2 + (y2 - y3)^2;
            double edge3 = (x3 - x4)^2 + (y3 - y4)^2;
            double edge4 = (x4 - x1)^2 + (y4 - y1)^2;

            if (String.valueOf(edge1).equals(String.valueOf(edge2))){
                if (String.valueOf(edge2).equals(String.valueOf(edge3))){
                    if (String.valueOf(edge3).equals(String.valueOf(edge4))){
                        if (String.valueOf(edge4).equals(String.valueOf(edge1))){
                            System.out.println("Yes");
                            continue;
                        }
                    }
                }
            }
            System.out.println("No");
        }
    }
}