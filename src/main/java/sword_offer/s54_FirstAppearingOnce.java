package sword_offer;

import java.util.HashMap;
import java.util.LinkedHashMap;

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
