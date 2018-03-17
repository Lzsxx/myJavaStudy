package LintCode;

import java.util.Stack;
import java.util.regex.Pattern;

class SimplifyPath {
    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();
        System.out.println(simplifyPath.simplifyPath("/..."));
    }

     public String simplifyPath(String path) {
         // write your code here
         Stack<String> stack = new Stack<>();
         String[] strings = path.split("/");

         for ( String str : strings ) {
             if (str.equals("..") ){
                 if ( !stack.isEmpty() ){
                     stack.pop();
                 }
                 continue;
             }else if (str.matches("(\\s*)|(\\.)") || str.equals("")){
                continue;
             }
             stack.push("/"+str);
         }

         if (stack.isEmpty()){
             stack.push("/");
         }

         //形成字符串
         StringBuffer stringBuffer = new StringBuffer();
         for (int i = 0; i < stack.size(); i++) {
             stringBuffer.append(stack.get(i));
         }
         return new String(stringBuffer);
     }

}
