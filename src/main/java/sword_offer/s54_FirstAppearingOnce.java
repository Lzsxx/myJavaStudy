package sword_offer;

import java.util.HashMap;
import java.util.LinkedHashMap;

//请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，
// 第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。

// 思路：根据描述，每个字符与其出现次数是有关联的，那么用map，还要从输入顺序来判断读取，最直观的就是用LinkedHashMap

public class s54_FirstAppearingOnce {
    LinkedHashMap<Character, Integer> linkedHashMap = new LinkedHashMap();
    //Insert one char from stringstream
    public void Insert(char ch)
    {
        if (linkedHashMap.containsKey(ch)){ // 已经存在
            int count = linkedHashMap.get(ch);
            count ++;
            linkedHashMap.put(ch, count);
        }else {
            linkedHashMap.put(ch, 1);
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        for (HashMap.Entry entry : linkedHashMap.entrySet()){
            int count = (int) entry.getValue();
            if (count == 1){
                return (char) entry.getKey();
            }
        }
        return '#';
    }
}
