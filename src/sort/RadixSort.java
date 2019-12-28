package sort;



/**
 * @author 宋浩
 * @version 1.0
 * @Classname RadixSort
 * @Description 基数排序
 * @Date 2019/12/24 20:06
 */
public class RadixSort {
    public static void sort(int[] arr, int left, int right) {
        int[][] bucket = new int[10][arr.length];//十个桶
        int[] bucketElementCounts = new int[10];//一维数组用来存放桶内数的个数
        int max = arr[0];//最大值用来判断放入桶的次数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        int maxWei = ("" + max).length();//最大位数
        for (int times = 0,dividend= 1; times < maxWei; times++,dividend*=10) {//dividend被除数用于取元素
            for (int i = 0; i < arr.length; i++) {
//            取出各个位的元素
                int digitOfElement = (arr[i] / dividend) % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]++] = arr[i];
            }
            int index = 0;
            for (int i = 0; i < bucketElementCounts.length; i++) {//十个桶取数据
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index++] = bucket[i][j];
                }
                bucketElementCounts[i] = 0;
            }
        }
    }
}


