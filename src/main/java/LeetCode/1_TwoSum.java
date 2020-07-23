package LeetCode;

//leetcode submit region begin(Prohibit modification and deletion)
class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int end = -1;
        int begin = -1;
        int[] result = {-1, -1};
        for (int i = 0; i < nums.length - 1; i++) {
            int temp = target - nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if (temp == nums[j]) {
                    result[0] = j;
                    result[1] = i;
                    return result;
                }
            }
        }
        return result;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
