package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname BinarySearch
 * @Description 二分查找(数组必须有序)
 * @Date 2019/12/24 21:07
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr ={1,4,6,7,9,11,61,364,367,400,400,400,400,400,531,644,2343};
        System.out.println(searchAll(arr, 0, arr.length-1, 400));
    }
    public static int search(int[] arr, int left, int right, int value) {
        if (right < left) {
            return -1;
        }
        int mid =(right+left)/2;
        if (arr[mid] == value)
            return mid;
        else if (arr[mid] > value)
            return search(arr, left, mid - 1, value);
        else
            return search(arr, mid + 1, right, value);
    }
    public static List<Integer> searchAll(int[] arr, int left, int right, int value) {
        if (right < left) {
            return new ArrayList<>();
        }
        int mid =(left+right)/2;
        if (arr[mid] == value){
            ArrayList<Integer> integers = new ArrayList<>();
            int temp =mid-1;
            integers.add(mid);
            while (arr[temp]==value && temp>=left){
                integers.add(temp--);
            }
            temp =mid+1;
            while (arr[temp]==value && temp<=right){
                integers.add(temp++);
            }
            integers.sort((o1, o2) -> o1-o2);
            return integers;
        } else if (arr[mid] > value)
            return searchAll(arr, left, mid - 1, value);
        else
            return searchAll(arr, mid + 1, right, value);
    }
}
