package ztest;

import java.util.Scanner;

class Main {
        //     第3题
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLong()){
            long input = in.nextLong();
            if (input == 0){
                System.out.println("1");
                continue;
            }else if (input == 1){
                System.out.println("2");
                continue;
            }
            int count = 0;
            String str = Long.toBinaryString(input);
            char[] chs = str.toCharArray();
            for (int j = 1; j < chs.length; j++) {  // 将二进制数小于Length的都统一先处理了，到与length相同的那一级，再一个个处理
                count += getCount(j);
            }
            long remain = 1 << (chs.length - 1);
            for (long i = remain; i <= input; i++) {
                str = Long.toBinaryString(i);
                chs = str.toCharArray();
                int low = 0;
                int high = chs.length - 1;  // 根据length应该可以优化一下
                while (low < high){
                    if (chs[low] == chs[high]){
                        low ++;
                        high --;
                    }else {
                        break;
                    }
                }
                if (low >= high){   // 走完全部循环的
                    count ++;
                }
            }
            System.out.println(count);
        }
        in.close();
    }
    public static int getCount(int length){
        int result = 0;
        if (length == 1){
            result = 2;
        }else if (length > 1){
            int temp = length - 2;
//            temp = (int) Math.ceil(temp / 2);
            temp = (temp+1) / 2;
            result = (int) Math.pow(2, temp);
        }
        return result;
    }

}

// 第2题
//    public static void main(String args[])
//    {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextLine()){
//            int input = in.nextInt();
//            if (input == 1){
//                System.out.println("0");
//                continue;
//            }
//            int[] result = new int[input];
//            int count = 0;
//            int k_idx = 0;
//            for (int num = 1; num <= input; num++) {   // num输入从1开始
//                int min = Integer.MAX_VALUE;
//                for (int i = 0; i < count; i++) {
//                    int k = 1;
//                    while ( Math.pow(result[i], k) < num ){
//                        k ++;
//                    }
//                    // 离开循环的时候，Math.pow(result[i], k)是刚好大于num的那个值
//                    int temp = (int) Math.pow(result[i], k);
//                    if (min < temp){
//                        min = temp;
//                    }
//                }
//                if (min < num){
//                    result[count++] = min;
//                }else {
//                    if (isSushu(num)){
//                        result[count++] = num;
//
//                    }
//                }
//
//            }
//            System.out.println(count);
//        }
//        in.close();
//    }
//
//    public static boolean isSushu(int num){
//        int temp = (int) Math.sqrt(num);
//        for (int j = 2; j <= temp; j++) {// 把Math.sqrt(i)转换为int类形
//            if (num % j == 0) {
//                return false;
//            }
//        }
//        return true;
//    }


//     // 第1题
//     public static void main(String args[])
//     {
//         Scanner in = new Scanner(System.in);
//         while (in.hasNextLine()){
//             String input = in.nextLine();
//             String[] strings = input.split(" ");
//             int a = Integer.parseInt(strings[0]);
//             int b = Integer.parseInt(strings[1]);
//             int c = Integer.parseInt(strings[2]);
//             int count = (b - c - 1 ) + 2;   // 本来已经a > b ，肯定会带来2个1， 那么就要看b和c的差距，相减之后会带来多少个1，找规律即可
//             System.out.println(count);
//         }
//         in.close();
//     }


