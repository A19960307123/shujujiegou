package search;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname InsertSearch
 * @Description TODO
 * @Date 2019/12/25 10:30
 */
public class InsertSearch {
    public static int search(int[] arr, int left, int right, int value) {
        if (right < left) {
            return -1;
        }
        int mid =left+(right -left)*(value-arr[left])/(arr[right]-arr[left]);
        if (arr[mid] == value)
            return mid;
        else if (arr[mid] > value)
            return search(arr, left, mid - 1, value);
        else
            return search(arr, mid + 1, right, value);
    }
}
