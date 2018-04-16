package sword_offer;

// 一个整型数组里除了两个数字之外，其他的数字都出现了两次。
// 请写程序找出这两个只出现一次的数字。

// 思路：由于x^y^y = x，那么将数组里所有的数字都进行一次异或，最后得到的是那两个只出现一次的
// 数据的异或，然后将这个数字从右边开始找为1的位置，两个数字不同是从这里开始的（从右往左）
// 分为一个为1，一个为0，并以此位置的标准，再次遍历原数组，将里面这个地方的数字都按照0或1为标准分隔开
// 然后将得到的两个数字各自异或，最后得到的就是要找的两个数
public class s40_FindNumsAppearOnce {
    public static void main(String[] args) {
        int[] array = {2,4,3,6,3,2,5,5};
        int[] num1 = new int[array.length];
        int[] num2 = new int[array.length];
        s40_FindNumsAppearOnce test = new s40_FindNumsAppearOnce();
        test.FindNumsAppearOnce(array, num1, num2);
        System.out.println(num1[0]);
        System.out.println(num2[0]);
    }
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int exclusive = 0;
        for (int k = 0; k < array.length; k++) {
            exclusive = exclusive ^ array[k];
        }
        int index = 0;
        while ((exclusive & 1) == 0){
            exclusive = exclusive >> 1; // 如果&1为0，那么这一位是1，右移一位判断，注意更新index
            index ++;
        }
        // 退出循环时的index，是从右往左，以0开始的一个位置
        // 再次遍历，这次直接右移动index位，判断那一位是1还是0，1放在num1，0放在num2
        int i = 0;
        int j = 0;
        for(int a : array){
            int temp = a;
            temp = temp >> index;
            if ((temp & 1) == 1){
                num1[0] ^= a;
            }else {
                num2[0] ^= a;
            }
        }
        // 下面对num1和num2再次遍历做异或操作，得到的结果存放在下标0处
//        for (int k = 1; k < i; k++) {
//            num1[0] = num1[0] ^ num1[i];
//        }
//        for (int k = 1; k < j; k++) {
//            num2[0] = num2[0] ^ num2[i];
//        }
    }
}
