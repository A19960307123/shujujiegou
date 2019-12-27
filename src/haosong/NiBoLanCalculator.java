package haosong;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname NiBoLanCalculator
 * @Description 模拟逆波兰计算器
 * @Date 2019/12/22 20:38
 */
public class NiBoLanCalculator {
    public static void main(String[] args) {

        String exp ="1+((2+3)*4)-5";
//        List<String> zhongZhuiExp = toZhongZhuiExp(exp);
////        List<String> toHou = zhongToHou(zhongZhuiExp);
        System.out.println(calculateZhong(exp));
        /*String suffixExpression = "3 4 + 5 * 6 -";
        int res = calculate(getListString(suffixExpression));
        System.out.println("计算结果是:"+res);*/
//        为了方便，逆波兰表达式的数和符号用空格隔开

    }
    public static List<String>  getListString(String suff){
        String[] split = suff.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for(String ele:split){
            list.add(ele);
        }
        return list;
    }

    /**
     * 功能描述:计算后缀表达式
     * @return: int
     * @auther: mada
     * @date: 2019/12/22 21:31
     */
    public static int calculate(List<String> list){
        /*思路：
         * 1.将"3 4 + 5 x 6 -"放入到ArrayList中
         * 2.将Arrayl传递给一个方法，遍历ArrayList配合栈完成计算
         *
         * */
        Stack<String> stacks = new Stack<>();
        for(String item: list){
//            用正则表达式进行匹配
            if(item.matches("\\d+")){
                stacks.push(item);
            }else {
//                pop出两个数进行运算
                int num1 = Integer.parseInt(stacks.pop());
                int num2 = Integer.parseInt(stacks.pop());
                int res = 0;
                if (item.equals("+")){
                    res = num2+num1;
                }else if (item.equals("-")){
                    res = num2-num1;
                }else if (item.equals("*")){
                    res = num2*num1;
                }else if (item.equals("/")){
                    res = num2/num1;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stacks.push(""+res);
            }
        }
        return Integer.parseInt(stacks.pop());
    }

    /**
     * 功能描述:将表达式转化为中缀表达式的List
     * @return: java.util.List<java.lang.String>
     * @auther: mada
     * @date: 2019/12/22 21:19
     */
    public static List<String> toZhongZhuiExp(String s){
        List<String> ls =new ArrayList<>();
        String str="";
        char c;
        for (int j = 0; j <s.length() ; j++) {
            if ((c =s.charAt(j))<48 ||(c=s.charAt(j))>57){
                if (str.length()>0)
                    ls.add(str);
                ls.add(""+c);
                str="";
            }else{
                str+=String.valueOf(s.charAt(j));
            }
        }
        ls.add(str);
        return ls;
    }

    /**
     * 功能描述:优先级判断
     * @return: int
     * @auther: mada
     * @date: 2019/12/22 22:05
     */
    public static int prority(String c){
        if (c.equals("*") || c.equals("/")){
            return 1;
        }else if (c.equals("+") || c.equals("-")){
            return 0;
        }else if (c.equals("(") || c.equals(")")){
            return -1;
        }else {
            return -1;
        }
    }

    /**
     * 功能描述:中缀表达式转换为后缀表达式
     * @return: java.util.List<java.lang.String>
     * @auther: mada
     * @date: 2019/12/22 22:06
     */
    public static List<String> zhongToHou(List<String> zhongList){
        ArrayList<String> houList = new ArrayList<>();//存储结果
        Stack<String> operStack = new Stack<>();//符号栈
        for (String item:zhongList){
            if(item.matches("\\d+")){
                houList.add(item);
            }else {
                if (item.equals("(")||operStack.empty()){
                    operStack.add(item);
                }else if (item.equals(")")){
                    do {
                        houList.add(operStack.pop());
                    } while (!operStack.peek().equals("("));
                    operStack.pop();
                }else {
                    while (prority(item)<=prority(operStack.peek())){
                        houList.add(operStack.pop());
                        if (operStack.empty())
                            break;
                    }
                    operStack.add(item);
                }
            }
        }
        while (!operStack.isEmpty()){
            houList.add(operStack.pop());
        }
        return houList;
    }

    /**
     * 功能描述:暴露在外的计算函数，用于计算中缀表达式
     * @return: int
     * @auther: mada
     * @date: 2019/12/22 22:10
     */
    public static int calculateZhong(String exp){
       return calculate(zhongToHou(toZhongZhuiExp(exp)));
    }
}
