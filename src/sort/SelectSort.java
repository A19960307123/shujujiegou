package sort;

import java.util.Arrays;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname SelectSort
 * @Description 选择排序
 * @Date 2019/12/23 18:41
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {3,9,-1,10,-2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 功能描述:选择排序
     * @return: void
     * @auther: mada
     * @date: 2019/12/23 18:48
     */
    public static void sort(int[] arr){
        int temp;
        for (int i = 0; i <arr.length-1 ; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i]>arr[j]){
                    temp = arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
    }
}
