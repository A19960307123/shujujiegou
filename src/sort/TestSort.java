package sort;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname TestSort
 * @Description 选择排序
 * @Date 2019/12/23 19:29
 */
public class TestSort {
    public static void main(String[] args) {
        try {
           boolean b = testCheck(HeapSort.class);
           System.out.println(b);
//            testTime(QuickSort.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 功能描述:产生随机数组
     * @param num 要求的数组的长度
     * @return int[]
     * @auther: mada
     * @date: 2019/12/23 19:33
     */
    public static int[] productNum(int num){
        int[] arr = new int[num];
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            arr[i]= random.nextInt(num);
        }
        return arr;
    }

    /**
     * 功能描述:验证排序算法是否正确，要求排序算法的名称和参数是sort(int[] arr,int,int)；
     * @return boolean 排序方法正确正确就返回true
     * @param testClass 要测试的类
     * @auther mada
     * @date 2019/12/23 20:01
     */
    public static boolean testCheck(Class testClass) throws Exception {
        int num = 80000;
        int[] arr = productNum(num);
        int[] arr2 = new int[num];
        System.arraycopy(arr,0, arr2,0 , arr.length);
        Arrays.sort(arr);
        Method sort = testClass.getMethod("sort",int[].class,int.class,int.class);
        sort.invoke(testClass.getDeclaredConstructor().newInstance(), arr2,0,arr2.length-1);
        for (int i = 0; i < num; i++) {
            if (arr[i]!=arr2[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * 功能描述:测试排序方法的运行时间
     * @return void
     * @param testClass 要测试的类名
     * @auther: mada
     * @date: 2019/12/23 20:09
     */
    public static void testTime(Class testClass) throws Exception {
        int num = 80000;
        int[] arr = productNum(num);
        Method sort = testClass.getMethod("sort",int[].class,int.class,int.class);
        System.out.println("排序前的时间：");
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date1);
        System.out.println(format);
        sort.invoke(testClass.getDeclaredConstructor().newInstance(), arr,0,arr.length-1);
        System.out.println("排序后的时间：");
        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println(format2);
    }
}


