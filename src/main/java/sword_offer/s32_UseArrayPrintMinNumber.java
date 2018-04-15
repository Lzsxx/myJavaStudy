package sword_offer;

// 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
// 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
// 思路：用前面题排列的方法，然后对每一个字符串按位比较
public class s32_UseArrayPrintMinNumber {
    public String min = "";
    public String PrintMinNumber(int [] numbers) {
        arrange(numbers, 0, numbers.length - 1);
        return min;
    }
    public void arrange(int[] numbers, int start, int end){
        if (start == end){
            // 排好了一个序列，来比较吧
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < numbers.length; j++) {
                sb.append(numbers[j]);
            }
            // min为空，或者min的位数大于sb，那么都会直接取新值 为min 【其实这里考虑多了，排列出来的位数一定相同
            if (min.length() == 0 || min.length() > sb.length()){
                min = sb.toString();
                return;
            }else {
                char[] minChs = min.toCharArray();
                for (int i = 0; i < minChs.length; i++) {
                    if (minChs[i] > sb.charAt(i)){
                        min = sb.toString();
                        return;
                    }else if (minChs[i] < sb.charAt(i)){
                        return;
                    }
                    // else == , continue
                }
            }
        }
        for (int i = start; i <= end; i++) {
            swap(numbers, i, start);    // 选定值，交换，以备子序列排列
            arrange(numbers,start + 1, end);
            swap(numbers, i, start);    // 交换回去
        }
    }
    public static void swap(int[] arr, int a, int b){
        if (a == b)
            return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
