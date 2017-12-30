package Algorithm.ReplaceBlank;


import sun.text.resources.cldr.ti.FormatData_ti;

//题目描述
// 请实现一个函数，将一个字符串中的空格替换成“%20”。
// 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。



public class ReplaceBlank {
    public static void main(String[] args) {

        StringBuffer oldString = new StringBuffer("We are friends!");
        String str = "We are friends!";
        // 要在调用之前输出oldString，否则调用以后，由于传入了oldString对象，属于引用调用，会改变外面的oldString的值
        System.out.println("The old String is : " + oldString);
        String newString = replaceBlank(oldString);
        System.out.println("The new String is : " + newString);
        //如果允许用标准库，直接调用replace
        System.out.println("IF use std replace: "+ str.replaceAll(" ","%20"));
    }
    // 思路：
    //首先要搞明白，要求是可以生成新字符串，还是要在原字符串上面做移动（以下是在原字符串上移动的实现
    //在原字符串上，每替换一个空格为%20，会多加两个字符，
    // 如果从前到后，每次添加都要将后面的内容后移，开销大
    // 如果从后往前，则每个字符只需要移动一次，但需要先遍历原字符串有多少空格，计算新字符串所占用的char数组大小
    private static String replaceBlank(StringBuffer oldString){
        int count = 0;
        // 计算空格数目
        for (int i = 0; i < oldString.length(); i++) {
            if (oldString.charAt(i) == ' '){
                count ++;
            }
        }
        int oldLength = oldString.length();
        int newLength = oldLength + count * 2;
        oldString.setLength(newLength);
        for (int i = newLength-1, j = oldLength-1; i >=0 && j >=0; j--) {
            if( oldString.charAt(j) == ' ' ){
                oldString.setCharAt(i--,'0');
                oldString.setCharAt(i--,'2');
                oldString.setCharAt(i--,'%');
            }else {
                oldString.setCharAt(i, oldString.charAt(j));
                i--;
            }
        }
        return oldString.toString();
    }
}
