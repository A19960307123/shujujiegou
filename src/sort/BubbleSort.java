package sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname BubbleSort
 * @Description 冒泡排序
 * @Date 2019/12/23 17:09
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3,9,-1,10,-2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[] arr){
        int temp;
        boolean mark = false;
        for (int i = 0; i <arr.length-1 ; i++) {
            if (mark){
                break;
            }
            mark =true;
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    mark =false;
                }
            }
        }
    }
}
