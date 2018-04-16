package sword_offer;

// 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。

// 思路：先部分翻转，后全部翻转，或者先全部，后部分，关键是要控制好low和high


public class s44_ReverseSentence {
    public String ReverseSentence(String str) {
        char[] chs = str.toCharArray();
        String[] strings = str.split(" ");
        // 分开调整
        int indexStart = 0;
        for (int i = 0; i < strings.length; i++) {
            int indexEnd = indexStart + strings[i].length() - 1;
            reverse(chs, indexStart, indexEnd);
            indexStart = indexEnd + 2 ;
        }
        // 全部调整
        reverse(chs, 0, chs.length - 1);
        return String.valueOf(chs);
    }
    public char[] reverse(char[] chs, int low, int high){
        while (low < high){
            char temp = chs[low];
            chs[low] = chs[high];
            chs[high] = temp;
            low ++;
            high --;
        }
        return chs;
    }
}
