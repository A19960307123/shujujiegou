package sort;

import java.util.Arrays;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname MergeSort
 * @Description 归并排序
 * @Date 2019/12/24 19:27
 */
public class MergeSort {

    /**
     * 功能描述:合并
     * @return void
     * @auther mada
     * @date 2019/12/24 19:42
     */
    public static void merge(int[] arr,int left,int mid,int right){
        int left1 = left;
        int left2 = mid+1;
        int[] temp =new int[right-left+1];
        int t = 0;
        while (left1<=mid && left2<= right){
            if (arr[left1]<=arr[left2]){
                temp[t++]=arr[left1++];
            }else {
                temp[t++]=arr[left2++];
            }
        }
        while (left2 <= right) {
            temp[t++] = arr[left2++];
        }
        while (left1 <= mid) {
            temp[t++] = arr[left1++];
        }

        t=0;
        int t2=left;
        while (t2<=right){
            arr[t2++]=temp[t++];
        }
    }

    public static void sort(int[] arr,int left,int right){
        if (left>=right)
            return;
        int mid = (right+left)/2;
        sort(arr, left, mid);
        sort(arr, mid+1,right);
        merge(arr, left, mid, right);
    }
}
