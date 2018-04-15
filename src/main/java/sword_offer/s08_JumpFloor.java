package sword_offer;


// 题目：一只青蛙一次可以跳1或2级台阶，求跳上n级台阶一共有多少种方法
// 分析：当n>=3时，若一次跳1级，则剩下n-1级有F（n-1）种跳法
// 若一次跳2级，剩下n-1级有F（n-2）种跳法
public class s08_JumpFloor {
    public int JumpFloor(int target) {
        if (target <= 0){
            return 0;
        }
        if (target == 1){
            return 1;   //1级只有一种跳法
        }
        if (target == 2){
            return 2;   // 2级可以一次跳2级，也可以一次一级跳两次，所以是2种跳法。如果是3级，
        }
        // 递归版
//        return JumpFloor(target - 1) + JumpFloor(target - 2);

        // 非递归版
        int first = 1;
        int second = 2;
        int sum = -1;
        for (int i = 3; i <= target; i++) {
            sum = first + second;
            first = second;
            second = sum;
        }
        return sum;
    }
}
