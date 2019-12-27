package haosong;

import java.util.Stack;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname Calculatior
 * @Description 用Stack进行简单计算器的模拟
 * @Date 2019/12/22 19:00
 */
public class Calculatior {
    public static void main(String[] args) {
        String expression = "33*2*6-2";
        CalStack<Integer> numStack = new CalStack<>();
        CalStack<Character> operStack = new CalStack<>();

        int num1 = 0;
        int num2 = 0;
        char oper = 0;
        int res = 0;
        char ch=  ' ';
        String num = "";
        for (int i = 0; i <expression.length() ; i++) {
//            依次得到表达式的每一个字符
            ch=expression.substring(i,i+1).charAt(0);
//            判断ch是数字还是符号
            if(operStack.isOper(ch)){
                numStack.add(Integer.parseInt(num));
                num = "";
                if (!operStack.isEmpty()){
                    /*
                    * 如果符号栈有操作符，就进行比较，如果当前的操作符的优先级低于栈顶的符号优先级，就从
                    * 数字栈中pop出两个数，符号栈中pop出一个符号进行运算，得到结果，将数入栈，然后再次进行比较
                    * 行比较，将当前操作符入符号栈
                    *
                    * 本质就是遇到一个低优先级的符号，就将之前的高优先级的
                    * 表达式运算掉，最后保证符号栈里的符号的优先级是递增的
                    * 最后就可以依次出栈，计算，不会出错
                    * */

//                    while (operStack.prority(ch)<operStack.prority(operStack.peek())){
                    if (operStack.prority(ch)<=operStack.prority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res =numStack.cal(num1, num2, oper);
                        numStack.add(res);
//                        if (operStack.isEmpty()){
//                            break;
//                        }
                    }
                    operStack.add(ch);
                }else{
                    operStack.add(ch);
                }
            }else {
//                如果是数字，就加到num中
                num =num+String.valueOf(ch);
            }
        }
//        最后一个数也要加到num中去
        numStack.add(Integer.parseInt(num));

//        处理剩余同一优先级的式子
        while (!operStack.isEmpty()){
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res =numStack.cal(num1, num2, oper);
            numStack.add(res);
        }
        System.out.println(numStack.pop());
    }
}
class CalStack<T> extends Stack<T>{

    /**
     * 功能描述:判断运算符的优先级
     * @return: int
     * @auther: mada
     * @date: 2019/12/22 19:07
     */
    public int prority(char oper){
        if (oper == '*'|| oper == '/'){
            return 1;
        }else if (oper == '+'|| oper == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }

    public int cal(int num1, int num2, char oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
