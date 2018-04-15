package sword_offer;

// 求整数1 ~ n中1出现的次数，如：1~13中包含1的数字有1、10、11、12、13因此共出现6次,
// 思路：
public class s31_appear_times_of_1_in_intNumber {
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        for (int i = 1; i <= n ; i++) {
            int temp = i;
            while (temp != 0){
                if ( temp % 10 == 1){
                    count ++;
                }
                temp = temp / 10;
            }
        }
        return count;
    }
}
