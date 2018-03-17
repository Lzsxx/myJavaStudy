package LintCode;

import java.util.Stack;

class IsValidParentheses {

    public static void main(String[] args) {
        IsValidParentheses isValidParentheses = new IsValidParentheses();
        System.out.println(isValidParentheses.isValidParentheses("["));
    }

     public boolean isValidParentheses(String s) {
         // write your code here
         Stack<Character> stack = new Stack<>();
         char[] chars = s.toCharArray();
         for (int i = 0; i < chars.length; i++) {
             if ( '(' == chars[i] ||  '[' == chars[i] || '{' == chars[i]){
                stack.push(chars[i]);
             }
             else if (')' == chars[i] || ']' == chars[i] || '}' == chars[i] ){
                 if (stack.isEmpty()){
                     return false;
                 }
                 char temp = stack.pop();
                 if (')' == chars[i] && '(' != temp
                         || ']' == chars[i] && '[' != temp
                         || '}' == chars[i] && '{' != temp){
                     return false;
                 }
             }
         }
         if (stack.isEmpty()){
             return true;
         }else {
             return false;
         }
     }
}
