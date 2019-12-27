package sort;


import java.util.Arrays;

import static sort.TestSort.*;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname HillSort
 * @Description 希尔排序
 * @Date 2019/12/23 20:12
 */
public class HillSort {
    public static void main(String[] args) {
        boolean b = false;
        try {
            b = testCheck(HillSort.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(b);

//        int[] arr ={8,5,7,24,86,764,864,65,6};
//        sort(arr);
//        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[] arr) {
        int temp;
    /*    for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp=arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
        }*/
    //移位法
        for (int gap = arr.length/2; gap < arr.length; gap /= 2) {
            for (int i = gap; i < arr.length; i-=gap) {
                int j =i;
                temp = arr[j];
                if (arr[j]<arr[j-gap]){
                    while (j-gap>=0 && temp<arr[j-gap]){
                        arr[j] =arr[j-gap];
                        j-=gap;
                    }
                    arr[j] =temp;
                }
            }
        }
    }
}
