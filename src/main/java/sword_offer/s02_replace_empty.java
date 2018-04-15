package sword_offer;

import java.util.LinkedList;
import java.util.Queue;

// 题目：替换字符串中的空格为%20
class replaceSpace {
    public String replaceSpace(StringBuffer str) {
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ( ch == ' '){
                queue.offer('%');
                queue.offer('2');
                queue.offer('0');
            }else {
                queue.offer(ch);
            }
        }
        char[] result = new char[queue.size()];
        int j = 0;
        for(char temp : queue){
            result[j] = temp;
            j ++;
        }
        return String.valueOf(result);
    }
}