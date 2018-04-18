package sword_offer;

//输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
// 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
public class s13_reOrderArray_oddFront_evenEnd {
    public static void main(String[] args) {
        int arr[] = {2,4,6,1,3,5,7};
        reOrderArray(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    public static void reOrderArray(int [] array) {
        int low = 0;
        int n = array.length - 1;   //最后一个下标的位置
        int record = 0;
        while (low <= n){
            while (low <= n && array[low] % 2 == 1){  //从前往后遍历，遇到奇数就一直挪，直到偶数
                low ++;
            }
            if ( low >= n){  // 移动完全部，发现都是奇数
                break;
            }
            // 出来时,如果low没越界，且low指向的数为偶数
            // 向后遍历看是否有奇数，如果有，就交换
            int i;
            for ( i = low + 1; i <= n; i++) {
                if (array[i] % 2 == 1){
                    if (i > low + 1){  //为了保证偶数与偶数的位置不变，在与奇数交换的时候需要先判断，如果中间有隔着别的偶数，要依次挪动
                        int temp = array[i];
                        for (int j = i; j >= low + 1; j--) {    //取到Low+1,要把low的位置空出来
                            array[j] = array[j - 1];
                        }
                        array[low] = temp;
                    }else {
                        swap(array, low, i);
                    }
                    low ++;
                    break;
                }
            }
            if ( i > n){    // 如果i因为大于n而退出，则表示后面的都是偶数，要结束最外层循环
                break;
            }
        }
    }
    public static void swap(int[] arr, int i, int j){
        if (i == j){
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
