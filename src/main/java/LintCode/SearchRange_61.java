package LintCode;

 class SearchRange {
    public static void main(String[] args) {
        SearchRange searchRange = new SearchRange();
        int[] A = {-1,0,1,2,2,2,3,3,3,4,4,4,5,5,6,90,92,93,101};
        int[] re = searchRange.searchRange( A ,2);
        for ( int a :re) {
            System.out.println(a);
        }
    }
//    public int[] searchRange(int[] A, int target) {
//        // write your code here
//        int low = 0;
//        int high = A.length - 1;
//        int[] result = {-1,-1};
//
//        while ( low <= high){
//            int mid = low + (high - low) / 2;
//            if (A[mid] < target){
//                low = mid + 1;
//            }else if (A[mid] > target){
//                high = mid - 1;
//            }else {
//                int tempLow = mid;
//                int tempHigh = mid;
//                while ( tempLow > 0 && A[tempLow - 1] == target){
//                    tempLow --;
//                }
//                while ( tempHigh < A.length - 1 && A[tempHigh + 1] == target){
//                    tempHigh ++;
//                }
//                result[0] = tempLow;
//                result[1] = tempHigh;
//                return result;
//            }
//        }
//        return result;
//    }

     /***** 版本2 *****/
     public int[] searchRange(int[] A, int target) {
         // write your code here
         int low = -1;
         int high = A.length;
         int[] result = {-1,-1};

         while ( (low+1) != high){
             int mid = low + (high - low) / 2;
             if (A[mid] < target){
                 low = mid;
             }else {
                 high = mid;
             }
         }
         if (high >= A.length || A[high] != target){
             return result;
         }
         int tempLow = high;
         int tempHigh = high;
         while ( tempLow > 0 && A[tempLow - 1] == target){
             tempLow --;
         }
         while ( tempHigh < A.length - 1 && A[tempHigh + 1] == target){
             tempHigh ++;
         }
         result[0] = tempLow;
         result[1] = tempHigh;
         return result;
     }
}
