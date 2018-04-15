package sword_offer;

// 变态跳台阶：一次可以跳1~n个台阶，总共有多少种方案
// 分析：1阶时，1种
// 2阶时，2种
// n >= 3阶时，如果第一次跳1阶，剩下的F(n-1)种；如果一次跳2阶，剩下F（n-2)种，。。。一次跳n-1阶，剩下F(n-n+1)=F(1)种
// 所以，总计：F(n) = F(n-1) + F(n-2)+...+F(1)
// F(n-1) = F(n-2)+F(n-3)+...F(1)
// F(n) - F(n-1) = F(n-1)
// 所以：F(n) = 2 * F(n-2)

public class s09_BT_JumpFloor {
    public int JumpFloorII(int target) {
        if (target <= 0){
            return 0;
        }
        if (target == 1){
            return 1;
        }
        int before = 1;
        int result = 0;
        for (int i = 2; i <= target; i++) {
            result = 2 * before;
            before = result;
        }
        return result;
    }
}
