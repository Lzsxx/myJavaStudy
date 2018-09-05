package ztest;

import java.util.ArrayList;
import java.util.List;

public class HW {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
////        String str = sc.nextLine();
//        String str = "FE81:0001:0000:0000:FF01:0203:0405:0607";
//        String[] strArr = str.split(":");
//        String result = "";
//
//        //检查错误
//        if (str.length() != 39 ){
//            System.out.println("Error");
//            return;
//        }
//        for (int i = 0; i < strArr.length; i++) {
//            if (strArr[i].length() != 4){
//                System.out.println("Error");
//                return;
//            }
//            char[] ch = strArr[i].toCharArray();
//            for (int j = 0; j < ch.length; j++) {
//                if (ch[i] >= '0' && ch[i] <= '9' || ch[i] >= 'A' && ch[i] <='F'){
//                    continue;
//                }else {
//                    System.out.println("Error");
//                    return;
//                }
//            }
//        }
//        char[] chars = strArr[0].toCharArray();
//
//        //组播地址
//        if (strArr[0].equals("FF00")){
//            result = "Multicast";
//        }
//        //链路本地
//        else if (chars[0] == 'F' && chars[1] == 'E' && chars[2] == '8'){
//            result = "LinkLocal";
//        }
//        //站点本地
//        else if (chars[0] == 'F' && chars[1] == 'E' && chars[2] == 'C'){
//            result = "SiteLocal";
//        }else {
//            result = "GlobalUnicast";   //全球单播
//        }
//        // 还回地址
//        // 未指定地址
//
//
//        System.out.println(result);
//    }
public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//    String t = sc.nextLine();
//    String f = sc.nextLine();
//    String m = sc.nextLine();
//
//    // transform data
//    int total = Integer.parseInt(t);
//    String[] f_arr = f.split(" ");
//    String[] m_arr = m.split(" ");
//    int[] flow = new int[f_arr.length];
//    int[] money = new int[m_arr.length];
//    for (int i = 0; i < f_arr.length; i++) {
//        flow[i] = Integer.parseInt(f_arr[i]);
//    }
//    for (int i = 0; i < m_arr.length; i++) {
//        money[i] = Integer.parseInt(m_arr[i]);
//    }

        int total = 12;
        int[] money = {10,40,30,50};
        int[] flow = {5,4,6,3};

    // begin find
    int n = flow.length;
    int[][] countMax = new int[n + 1][total + 1];
    int[][] path = new int[n + 1][total + 1];
    //initial
    for (int i = 0; i < countMax.length; i++) {
        countMax[i][0] = 0;
    }
    for (int i = 0; i < countMax[0].length; i++) {
        countMax[0][i] = 0;
    }
    //compute
    for (int i = 1; i < countMax.length; i++) {
        for (int j = 1; j < countMax[0].length; j++) {
            if (flow[i - 1] > j)
                countMax[i][j] = countMax[i - 1][j];
            else {
                if (countMax[i - 1][j] < countMax[i - 1][j - flow[i - 1]] + money[i - 1]) {
                    countMax[i][j] = countMax[i - 1][j - flow[i - 1]] + money[i - 1];
                    path[i][j] = 1;
                } else {
                    countMax[i][j] = countMax[i - 1][j];
                }
            }
        }
    }

    for (int i = 0; i < path.length; i++) {
        for (int j = 0; j < path[0].length; j++) {
            System.out.print(path[i][j]+"\t");
        }
        System.out.println();
    }

    List<Integer> list = new ArrayList<>();
    int i = countMax.length - 1;
    int j = countMax[0].length - 1;
    while (i > 0 && j > 0) {
        if (path[i][j] == 1) {
            list.add(i);
            j -= flow[i - 1];
        }
        i--;
    }

    StringBuffer buffer = new StringBuffer();
    for (int k = list.size() - 1; k >= 0; k--) {
        if (k == 0) {
            buffer.append(list.get(k));
        } else {
            buffer.append(list.get(k) + " ");
        }
    }
    System.out.println(buffer.toString());

}
}







//        Scanner sc = new Scanner(System.in);
//        String s = sc.next();
//        char[] str = s.toCharArray();
//        int count = 0;
//        int max = 0;
//
//        for (int i = 0; i < str.length; i++) {
//            int low = i;
//            int high = str.length - 1;
//            while (low < high) {
//                if (str[low] == str[high]) {
//                    low++;
//                    high--;
//                    count++;
//                } else {
//                    low = i;
//                    high--;
//                    count = 0;
//                }
//            }
//            if (count > 0 && low == high) {   //停在一个单独的数
//                count = count * 2 + 1;
//            } else {
//                count = 2 * count;
//            }
//            if (max < count) {
//                max = count;
//            }
//            count = 0;
//        }
//        System.out.println(max);
