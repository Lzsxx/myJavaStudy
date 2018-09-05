package ztest;

import java.util.Scanner;
import java.util.Stack;
//输入为一个算数表达式的字符串。输出它经过计算之后的结果。如果该字符串不满足算数表达式则输出字符串Error。
//        注意：
//        0. 输入中的数只有非负整数。小数、负数等均需要输出Error。
//        1. 需要支持运算符加、减、乘以及括号。
//        2. 运算符的优先级为：括号>加=减>乘。
//        3. 支持数与操作符、操作符与操作符之间有任意空格。
//        3. 输入和运算过程中不需要考虑整数溢出，用32位的int即可。
//        输入描述:
//        输入1：123
//        输入2：1 23
//        输入3：1 + 2 * 3
//        输入4：1+(2*3)
//        输出描述:
//        输出1：123
//        输出2：Error
//        输出3：9
//        输出4：7

//示例1
//        输入
//        1 + 2 * 3 - (4*5)
//        输出
//        -51
//        说明
//        1 + 2 * 3 - (4*5)   =>  1 + 2 * 3 - 20   => 3 * 3 - 20  =>  3 * -17  =>  -51

public class Paypal {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String string = in.nextLine();
        in.close();
//        String str = string.replace(" ","");
        char[] symbol = string.toCharArray();

        Stack<Character> operatorStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        boolean lastIsNum = false;
        boolean carefulIfNextIsNumber = false;
        boolean isFail = false;

        for (int i = 0; i < symbol.length; i++) {
            // 遇到左括号，入栈
            if (symbol[i] == '(') {
                operatorStack.push(symbol[i]);
                lastIsNum = false;
                carefulIfNextIsNumber = false;
            // 数字0~9，遇到数字就入栈，可以处理连续数字
            }else if (symbol[i] >= 48 && symbol[i] <= 57){
                if (carefulIfNextIsNumber){     //如果前面两个分别是数字、空格，这里又遇到数字，则失败
                    isFail = true;
                    break;
                }
                int realValue = symbol[i] - 48;
                if (lastIsNum){
                    int temp = numStack.pop();
                    temp = temp * 10 + realValue;
                    numStack.push(temp);
                }else {
                    numStack.push(realValue);
                }
                lastIsNum = true;
                carefulIfNextIsNumber = false;
                // 遇到右括号
            }else if (symbol[i] == ')'){
                if (operatorStack.isEmpty()){
                    isFail = true;
                    break;
                }
                while (!operatorStack.isEmpty() && operatorStack.peek() != '('){
                    char op = operatorStack.pop();
                    if (numStack.size() < 2){
                        isFail = true;
                        break;
                    }
                    int a = numStack.pop();
                    int b = numStack.pop();
                    int result = 0;

                    if (op == '+'){
                        result = b + a;
                    }else if (op == '-'){
                         result = b - a;
                     }else if (op == '*'){
                         result = b * a;
                    }
                    numStack.push(result);
                }
                if (operatorStack.isEmpty()){
                    isFail = true;
                    break;
                }else {
                    operatorStack.pop();    // (出栈
                }
                lastIsNum = false;
                carefulIfNextIsNumber = false;
            }else if (symbol[i] == '*'){
                if (numStack.size() < 2){
                    isFail = true;
                    break;
                }
                if (!operatorStack.isEmpty()){
                    if (operatorStack.peek() == '-'){
                        int a = numStack.pop();
                        int b = numStack.pop();
                        int result = b - a;
                        numStack.push(result);
                        operatorStack.pop();
                    }else if (operatorStack.peek() == '+'){
                        int a = numStack.pop();
                        int b = numStack.pop();
                        int result = b + a;
                        numStack.push(result);
                        operatorStack.pop();
                    }
                }
                operatorStack.push(symbol[i]);
                lastIsNum = false;
                carefulIfNextIsNumber = false;
            }else if (symbol[i] == '+'){
                operatorStack.push(symbol[i]);

                lastIsNum = false;
                carefulIfNextIsNumber = false;
            }else if (symbol[i] == '-'){
                operatorStack.push(symbol[i]);

                lastIsNum = false;
                carefulIfNextIsNumber = false;
            }else if (symbol[i] == ' '){    //遇到空格
                if (lastIsNum){
                    carefulIfNextIsNumber = true;
                }else {
                    carefulIfNextIsNumber = false;
                }
                lastIsNum = false;
            } else if (symbol[i] == '.'){   // 有小数或者负数，输出错误，结束
                isFail = true;
                break;
            }else {
                isFail = true;
                break;
            }
            // 遗留，负数的情况怎么判断？
         }

        if (isFail){
            System.out.println("Error");
            return;
        }

         while ( !operatorStack.isEmpty() ){
             if (numStack.size() < 2){  //这里可以确定出去以后opstack一定为empty，否则就是操作数不匹配，从这里推出
                 System.out.println("Error");
                 return;
             }
             char op = operatorStack.pop();
             int a = numStack.pop();
             int b = numStack.pop();
             int result = 0;

             if (op == '+'){
                 result = b + a;
             }else if (op == '-'){
                 result = b - a;
             }else if (op == '*'){
                 result = b * a;
             }
             numStack.push(result);
         }

         if (numStack.size() != 1){
             System.out.println("Error");
         } else{
             System.out.println(numStack.peek());
         }
    }
}