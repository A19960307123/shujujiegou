package digui;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname Queue8
 * @Description 递归八皇后问题
 * @Date 2019/12/23 14:51
 */
public class Queue8 {
    int max =8;
    int[] array = new int[max];
    static int count = 0;
    static int countnum = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println(count);
        System.out.println(countnum);
    }

    private void check(int n){
        if(n==max){
//            print();
            count++;
            return;
        }
        for (int i = 0; i <max ; i++) {
            array[n]=i;
            if (judge(n)){
                check(n+1);
            }
        }
    }

    /**
     * 功能描述:判断该皇后与之前的皇后是否冲突
     * @param n 代表第n个皇后
     * @return: boolean
     * @auther: mada
     * @date: 2019/12/23 15:23
     */
    private boolean judge(int n){
        countnum++;
        for (int i = 0; i <n ; i++) {
            if (array[i]==array[n] || Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    //打印结果
    private void print(){
        for (int i = 0; i <array.length ; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
