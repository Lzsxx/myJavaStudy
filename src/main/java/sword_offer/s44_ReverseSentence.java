package sword_offer;

// 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。

// 思路：先部分翻转，后全部翻转，或者先全部，后部分，关键是要控制好low和high


public class s44_ReverseSentence {
    public String ReverseSentence(String str) {
//     “student. a am I” “I am a student.”
        char[] chs = str.toCharArray();
        int low = 0;
        int high = -1;
        boolean lastIsEmpty = false;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == ' ') {
                reverse(chs, low, high);
                lastIsEmpty = true;
            }else {
                if (lastIsEmpty) {
                    low = i;
                    high = i - 1;
                    lastIsEmpty = false;
                }
                high ++;
            }
        }
        // 如果最后离开的时候，没有经过空格，就要在进行一次补充的翻转
        if (!lastIsEmpty) {
            reverse(chs, low, high);
        }
        reverse(chs, 0, chs.length - 1);
        return new String(chs);
    }

    public void reverse(char[] chs, int low, int high) {
        while (low < high) {
            char temp = chs[low];
            chs[low] = chs[high];
            chs[high] = temp;
            low ++;
            high--;
        }
    }

//    public String ReverseSentence(String str) {
//        char[] chs = str.toCharArray();
//        String[] strings = str.split(" ");
//        // 分开调整
//        int indexStart = 0;
//        for (int i = 0; i < strings.length; i++) {
//            int indexEnd = indexStart + strings[i].length() - 1;
//            reverse2(chs, indexStart, indexEnd);
//            indexStart = indexEnd + 2 ;
//        }
//        // 全部调整
//        reverse2(chs, 0, chs.length - 1);
//        return String.valueOf(chs);
//    }
//    public char[] reverse2(char[] chs, int low, int high){
//        while (low < high){
//            char temp = chs[low];
//            chs[low] = chs[high];
//            chs[high] = temp;
//            low ++;
//            high --;
//        }
//        return chs;
//    }
}
