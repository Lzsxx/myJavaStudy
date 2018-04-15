package sword_offer;


//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
// 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。

// 思路：后续遍历，最后一个数一定是树根
// 而根据二叉搜索树的特性，左支都小于树根，右支都大于树根，所以可以遍历前面的数据
// 以第一个比根大的地方为分割点，如果后面都比大，那就继续比较
// 如果后面有大有小，那就fail
public class s23_VerifySquenceOfBST {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length < 1){
            return false;
        }
        return VerifySquenceOfBST(sequence, 0, sequence.length - 1);
    }
    public boolean VerifySquenceOfBST(int [] sequence, int start, int end) {
        if (start >= end){  //如果分到最后只剩一个，则返回。//递归出口
            return true;
        }
        int root = sequence[end];
        int pivot = -1;
        boolean findPivot = false;
        for (int i = start; i < end; i++) {
            if ( sequence[i] < root){
                if (!findPivot){
                    continue;   //在没有转折点之前，都继续进行，否则，
                }else {
                    return false;
                }
            }
            if (sequence[i] > root ){
                findPivot = true;
                pivot = i;
            }
        }
        if (!findPivot){    //那么就是都小于咯，都是左支,那么下一个分割点是end-1
            return VerifySquenceOfBST(sequence, start, end - 1);
        }else { //找到了分割点，可能是中间，或者都是右支
            if (pivot == end - 1){  //都是右支
                return VerifySquenceOfBST(sequence, start, end - 1);
            }
            // 如果走到这里，那就分割数组，进入递归
            return VerifySquenceOfBST(sequence, start, pivot - 1) && VerifySquenceOfBST(sequence,pivot, end);
        }
    }
}
