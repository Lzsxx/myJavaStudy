package sword_offer;

// 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
// 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。

// 先分别计算上三角和下三角，然后将上三角和下三角相乘

public class s51_multiply_array {
    // 临场发挥版
    public int[] multiply(int[] A) {
        // 分成上下三角
        int length = A.length;
        int[] downArr = new int[length];
        int[] upArr = new int[length];
        int sum = 1;
        for (int i = 0; i < length; i++) {
            downArr[i] = sum;
            sum *= A[i];    //  累乘
        }
        sum = 1;
        for (int i = length - 1; i >= 0; i--) {
            upArr[i] = sum;
            sum *= A[i];
        }
        int[] B = new int[length];
        for (int i = 0; i < length; i++) {
            B[i] = downArr[i] * upArr[i];
        }
        return B;
    }
//    public int[] multiply(int[] A) {
//        int length = A.length;
//        int[] B = new int[length];
//        if (length < 1){    // A为空的话
//            return B;
//        }
//
//        // 计算上三角
//        B[0] = 1;
//        for (int i = 1; i < length; i++) {
//            B[i] = B[i - 1] * A[i - 1];
//        }
//        // 计算下三角
//        int temp = 1;   // 假装这是最后一行的1
//        for (int i = length - 2; i >= 0; i--) { // 这里是因为，在length-1的那一行，已经计算完，不再需要计算了
//            temp = temp * A[i+1];
//            B[i] = B[i] * temp;
//        }
//        return B;
//    }
}
