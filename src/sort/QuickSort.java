package sort;

import java.util.Arrays;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname QuickSort
 * @Description TODO
 * @Date 2019/12/24 17:23
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {45,55,68,3,4,35,2,6,42,4,54,65,3,6,13,5,33,53,5,3,54,36,13,5,2};
        sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[] arr,int left ,int right){
        if ((right-left)<1){
            return;
        }
        int l =left;
        int temp;
        int current = left;
        int r = right;
        int p =(left+right)/2;
        int mark =arr[p];
        while (current<=r){
            if (arr[current]<mark){
                if (current != l){
                temp =arr[current];
                arr[current]=arr[l];
                arr[l]=temp;
                }
                l++;
                current++;
            }else if (arr[current]>mark){
                temp =arr[current];
                arr[current]=arr[r];
                arr[r]=temp;
                r--;
            }else current++;
        }
        sort(arr, left, l-1);
        sort(arr, r+1, right);
    }
}
