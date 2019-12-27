package search;



/**
 * @author 宋浩
 * @version 1.0
 * @Classname SeqSearch
 * @Description 线性查找
 * @Date 2019/12/24 20:56
 */
public class SeqSearch {
    public static int search(int[] arr,int value){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==value) {
                return i;
            }
        }
        return -1;
    }
}
