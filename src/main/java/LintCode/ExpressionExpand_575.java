package LintCode;

import javax.naming.ldap.SortResponseControl;
import javax.xml.stream.FactoryConfigurationError;
import java.util.Stack;

class ExpressionExpand {
    public static void main(String[] args) {
        ExpressionExpand expressionExpand = new ExpressionExpand();
        String sss = expressionExpand.expressionExpand("3[2[ad]3[pf]]xyz");
        System.out.println(sss);
    }
     public String expressionExpand(String s) {
         // write your code here
         Stack<String> bracketsStack = new Stack<>();
         Stack<Integer> countStack = new Stack<>();
         StringBuffer buffer = new StringBuffer();
         boolean lastIsNum = false;
         final String FALSE = "false";

         char[] str = s.toCharArray();
         for (int i = 0; i < str.length; i++) {
             // 遇到字母，如果count栈为空，就直接加入Buffer，否则就入栈
             if (str[i] >= 65 && str[i] <= 90 || str[i] >= 97 && str[i] <= 122 ){   //字母a~z的大小写范围
                 if (lastIsNum){
                     return FALSE;  //字母紧跟在数字后面会出错
                 }
                 lastIsNum = false;
                 if ( !countStack.isEmpty() ){
                     bracketsStack.push(String.valueOf(str[i]));
                 }else{
                     buffer.append(str[i]);
                 }
                 // 数字0~9，遇到数字就入栈
             } else if (str[i] >= 48 && str[i] <= 57){
                 int realValue = str[i] - 48;
                 if (lastIsNum){
                     int temp = countStack.pop();
                     temp = temp * 10 + realValue;
                     countStack.push(temp);
                 }else {
                     countStack.push(realValue);
                 }
                 lastIsNum = true;
                 // 遇到左括号，入栈
             } else if ( str[i] == '['){
                 if ( !lastIsNum){  //[前面如果不是数字，就出错
                     return FALSE;
                 }
                 bracketsStack.push(String.valueOf(str[i]));
                 lastIsNum = false;
             }  else if ( str[i] == ']'){
                 if (lastIsNum){
                     return FALSE;
                 }
                 // 遇到一个]，意味着要出栈一堆字母，先取数字，再取字母，然后重复
                 int repeatCount  = countStack.pop();
                 Stack<String> waitForRepeatStr_Stack = new Stack<>();
                 StringBuffer waitForRepeatStr_Buffer = new StringBuffer();
                 while ( !bracketsStack.isEmpty() && !bracketsStack.peek().equals("[")){
                     waitForRepeatStr_Stack.push(bracketsStack.pop());
                 }
                 if ( !bracketsStack.isEmpty() ){
                     bracketsStack.pop();   // 将[出栈，但是不入tempStr
                 }else {
                     return FALSE;  // 如果是因为栈空而退出循环，则根本没有找到配对的[,报错
                 }
                 // 拼接出栈的字符成字符串
                 while ( !waitForRepeatStr_Stack.isEmpty() ){
                     waitForRepeatStr_Buffer.append(waitForRepeatStr_Stack.pop());
                 }
                 String repeatStr = waitForRepeatStr_Buffer.toString();
                 waitForRepeatStr_Buffer = new StringBuffer();
                 for (int j = 0; j < repeatCount; j++) {
                     waitForRepeatStr_Buffer.append(repeatStr);
                 }
                 //出栈了[以后依然不为空，新匹配好的字符串要继续入栈
                 if ( !bracketsStack.isEmpty() ){
                     bracketsStack.push(waitForRepeatStr_Buffer.toString());
                 }else {    // 否则，就可以直接加入输出序列
                     buffer.append(waitForRepeatStr_Buffer);
                 }
                 lastIsNum = false;
             }else {
                 return FALSE;
             }
         }
         return buffer.toString();
     }
}
