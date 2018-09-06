package sword_offer;

//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
// 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007

// 思路：利用归并排序，按从大到小的顺序排列，那么每次数组A和数组B比较时，如果A中的某数大于B中的某数，
// 则先被移走的是A，在B后面的数都会小于A中的这个数，则后面的都是逆序数，用count += high - j + 1;计算，可以免除暴力算法中
// n*n的复杂度
// 如果按照从小到大的顺序排列，A组在前，B组在后，如果A>B当前数，则先被移走的是B，而A中后面的数都大于A当前数，肯定也大于B当前数，
// 所以count += mid - i + 1

public class s35_InversePairs {
    public static void main(String[] args) {
        s35_InversePairs test = new s35_InversePairs();
        int[] arr = {7, 5, 6, 4};
        int result = test.InversePairs(arr);
        System.out.println(result);
    }
    public long count = 0;
    public int InversePairs(int [] array) {
        mergeSort_split(array, 0, array.length - 1);
        return (int) (count % 1000000007);
    }
    public  void mergeSort_split(int[] arr, int low, int high){
        int mid = (low + high) / 2;
        if (low < high){
            mergeSort_split(arr, low, mid);
            mergeSort_split(arr, mid+1, high);
            merge(arr,low,high);
        }
        /***** 隐含的递归出口 *****/
//        else {
//            return;
//        }
    }
    public  void merge(int[] arr, int low, int high){
        // 传入进来的数组arr实际上包含了要被merge的两个数组，以mid为分界线
        // 第一个数组[low, mid]
        // 第二个数组[mid+1, high]
        int mid = (low + high) / 2;
        int sum = high - low + 1;
        int[] temp = new int[sum];

        int i = low;    // 第一个数组的起始指针
        int j = mid + 1;    // 第二个数组的起始指针
        int k = 0;
        while ( i <= mid && j <= high){
            if (arr[i] > arr[j]){
                count += mid - i + 1;  // 如果第一组的当前数大于第二组的当前数，紧接着第二组这个较小的数将被移走，那么第一组当前及后面的数，都是大于第二组当前数的，都要统计
                temp[k] = arr[j];   // 按从小到大的顺序排列
                j++;
            }else {
                temp[k] = arr[i];
                i++;
            }
            k++;
        }
        while ( i <= mid){
            temp[k] = arr[i];
            i++;
            k++;
        }
        while ( j <= high){
            temp[k] = arr[j];
            j++;
            k++;
        }

        for (int m = 0; m < sum; m++){
            arr[low + m] = temp[m];
        }
    }
}
