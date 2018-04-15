package bishi_template;

import java.util.Scanner;

public class Scanner_withoutEndSignal {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            int n = Integer.parseInt(in.nextLine());
            System.out.println(n);
        }


    }
}
