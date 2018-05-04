package sword_offer;

/**
* @Description: 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
*/
 class Find {
    public boolean Find(int target, int [][] array) {
        // 每一行从左到右递增
        // 每一列从上到下递增
        // 但本行第一个数和上一行最后一个数之间没有确定的大小关系，所以
        // 之前用的横向和竖向二分的方法已经无效，只能采用横向二分，竖向顺序

        if (array.length < 1){
            return false;
        }

        int rowNum = array.length;
        int colNum = array[0].length;

        for (int i = 0; i < rowNum; i++) {
            int low = 0;
            int high = colNum - 1;
            while (low <= high){
                int mid = low + (high - low) / 2;
                if (array[i][mid] == target){
                    return true;
                }else if (array[i][mid] > target){
                    high = mid - 1;
                }else {
                    low = mid + 1;
                }
            }
        }
        return false;
    }
}