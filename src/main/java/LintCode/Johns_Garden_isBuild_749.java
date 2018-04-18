package LintCode;

// 约翰想在他家后面的空地上建一个后花园，现在有两种砖，一种3 dm的高度，7 dm的高度。约翰想围成x dm的墙。
// 如果约翰能做到，输出YES，否则输出NO

// 思路：其实只需要分成3种情况讨论，一种全3，一种全7，一种3、7混合，
// 全3和全7的情况，直接取模%为0，就可判断出来
// 3、7混合的情况，可以每次-3、-7，然后再次进入函数，采用递归的方式来判断，总有一刻，只剩下3，或者只剩下7，或者
// 如果是3、7对半分的情况的话，0取模任何数都是0，还是能够正确退出

public class Johns_Garden_isBuild_749 {
    /**
     * @param x: the wall's height
     * @return: YES or NO
     */
    public String isBuild(int x) {
        // write you code here
        if (process(x)) {
            return "YES";
        }
        return "NO";
    }
    public boolean process(int x){
        if (x < 0){
            return false;
        }
        if (x % 3 == 0 || x % 7 == 0){
            return true;
        }else {
            int a = x - 3;
            int b = x - 7;
            return process(a) || process(b);
        }
    }
}
