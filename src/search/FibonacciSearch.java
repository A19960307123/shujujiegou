package search;

import java.util.Arrays;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname FibonacciSearch
 * @Description 裴波那契查找算法
 * @Date 2019/12/25 10:46
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] data ={1,3,5,7,8,9,11,16,19,25,31,35,37,39,40,42,44,46};
        System.out.println(search(data, 0, data.length-1, 46));
    }
    public static int maxSize =20;
    public static int[] fib(){
        int[] f = new int[maxSize];//存放斐波那契数列
        f[0]=1;
        f[1]=1;
        for (int i = 2; i < maxSize; i++) {
            f[i] =f[i-1]+f[i-2];
        }
        return  f;
    }

    public static int search(int[] arr, int left, int right, int value) {
        int k = 0;
        int mid;
        int[] f = fib();
        while (right - left > f[k] - 1) {
            k++;
        }
        int[] arr2 = Arrays.copyOf(arr, f[k]);
        for (int i = right - left; i < f[k]; i++) {
            arr2[i] = arr[right];
        }
        int le = 0;
        mid = le + f[--k] - 1;
        while (true) {
            if (arr2[mid] > value) {
                if (k < 1) {
                    return -1;
                }
                mid = le + f[--k] - 1;
            } else if (arr2[mid] < value) {
                if (k < 2) {
                    return -1;
                }
                le = mid + 1;
                mid = le + f[k - 2] - 1;
                k = k - 2;
            } else {
                if (mid < right - left)
                    return left + mid;
                else
                    return right;
            }
        }
    }
}
