package sort;

import java.util.Arrays;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname HeLanFlag
 * @Description TODO
 * @Date 2019/12/24 10:55
 */
public class HeLanFlag {

    public static void main(String[] args) {
        int[] arr= {-5,4,6,4,69,7,2,4,68,5,3,7};
        sort(arr, 0,11,2);
        System.out.println(Arrays.toString(arr));
    }
    /**
     * 功能描述:将arr数组分成三个部分，分别是大于arr[i]，等于arr[i]，小于arr[i]；
     * @param arr 要处理的数组
     * @param i 以数值中第i个数为基准
     * @param start 需要排序的起始位置
     * @param end 需要排序的结束位置
     * @return void
     * @auther mada
     * @date 2019/12/24 10:58
     */
    public static void sort(int[] arr,int start,int end,int i){
        if (i>end ||i<start){
            System.out.println("基准位置超过数组范围");
            return;
        }
        if ((end-start)==1){
            return;
        }
        int markNum = arr[i];
        int lo =start;//第一个等于markNum数的位置
        int la =end;//最后一个等于markNum数的位置
        int current = start;
        int temp;
        while (current<=la){
            if (arr[current]>markNum){
                //如果arr[current]大于markNum，就交换current和la位置上的值，la向前移动一位,current不动
                temp = arr[current];
                arr[current]=arr[la];
                arr[la] = temp;
                la--;
            }else if (arr[current]<markNum){
                //如果arr[current]小于markNum，就交换current和lo位置上的值，lo向后移动一位,current不动
                if (current!=lo) {
                    temp = arr[current];
                    arr[current] = arr[lo];
                    arr[lo] = temp;
                }
                lo++;
                current++;
            }else {
                current++;
            }
        }
    }
}
