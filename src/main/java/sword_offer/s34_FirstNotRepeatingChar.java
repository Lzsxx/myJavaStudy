package sword_offer;

import java.util.HashSet;

//在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置
// 思路：首先，只出现一次的字符，可以通过两个set的组合来找。一个set存储全部数字，另一个set存储出现过两次以上的
// 然后再遍历一次，如果一个在singleSet中，但不在repeat中，就说明是第一个出现的
public class s34_FirstNotRepeatingChar {
    public int FirstNotRepeatingChar(String str) {
        HashSet<Character> allSet = new HashSet<>();
        HashSet<Character> repeatSet = new HashSet<>();
        char[] chs = str.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (!allSet.contains(chs[i])){  //第一次出现
                allSet.add(chs[i]);
            }else { //如果出现了多次
                repeatSet.add(chs[i]);
            }
        }
        for (int i = 0; i < chs.length; i++) {
            if ( allSet.contains(chs[i]) && !repeatSet.contains(chs[i])){
                return i;
            }
        }
        return -1;
    }
}
