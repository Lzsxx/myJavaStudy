package sword_offer;
//把只包含因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含因子7。
// 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。

// 思路：第一个丑数为1，后面每次新产生的丑数，一定是已有的丑数分别 * 2 、* 3 、* 5 后产生的。
//  至于2、3、5分别应该乘以哪个数x产生，则是通过判断，如果2*num[x - 1] <= max , 2 * num[x] > max，
// 那么x就是我们要找的那个值。但是无法确定和3、5产生的丑数哪个小，所以要都产生出来比较
public class s33_uglyNumber {
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0){
            return 0;
        }
        int[] uglyNum = new int[index];
        uglyNum[0] = 1;
        int ugly_2 = 0;
        int ugly_3 = 0;
        int ugly_5 = 0;
        int currMax = uglyNum[0];
        for (int i = 1; i < index; i++) {
            // 找到各自相乘后最接近最大值的那个下标
            while (uglyNum[ugly_2] * 2 <= currMax){
                ugly_2 ++;
            }
            while (uglyNum[ugly_3] * 3 <= currMax){
                ugly_3 ++;
            }
            while (uglyNum[ugly_5] * 5 <= currMax){
                ugly_5 ++;
            }
            int min = getMin(uglyNum[ugly_2] * 2, uglyNum[ugly_3] * 3, uglyNum[ugly_5] * 5);
            uglyNum[i] = min;
            currMax = min;
        }
        return currMax;
    }
    public int getMin(int a, int b, int c){
        int minner = a < b ? a : b;
        minner = minner < c ? minner : c;
        return minner;
    }
}
