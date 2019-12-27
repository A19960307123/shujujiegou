package sort;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname InsertSort
 * @Description TODO
 * @Date 2019/12/23 18:57
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {3,9,-1,10,-2};
//        sort(arr);
//        System.out.println(Arrays.toString(arr));
        testTime();
    }


    public static void sort(int[] arr){
        for (int i = 1; i <arr.length ; i++) {
            int markNum =arr[i];//记录下当前值
            int j=i-1;
            for (; j >=0 && arr[j]>markNum; j--) {
                arr[j+1]=arr[j];
            }
            arr[j+1]=markNum;
        }
    }

    private static void testTime(){
        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i]= random.nextInt(80000);
        }
        System.out.println("排序前的时间：");
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date1);
        System.out.println(format);
        sort(arr);
        System.out.println("排序后的时间：");
        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println(format2);
    }
}
