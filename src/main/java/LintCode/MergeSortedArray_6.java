package LintCode;

 class MergeSortedArray {

     public int[] mergeSortedArray(int[] A, int[] B) {
         // write your code here
         int[] C = new int[A.length + B.length];
         int i = 0;
         int j = 0;
         int k = 0;
         while ( i < A.length && j < B.length){
             if ( A[i] <=  B[j]){
                 C[k] = A[i];
                 i++;
             }else {
                 C[k] = B[j];
                 j++;
             }
             k ++;
         }
         while ( i < A.length){
             C[k++] = A[i++];
         }
         while ( j < B.length){
             C[k++] = B[j++];
         }
         return C;
     }
}
