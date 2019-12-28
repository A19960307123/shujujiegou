package sort;

import java.util.Arrays;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname HeapSort
 * @Description TODO
 * @Date 2019/12/28 19:54
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr={1,6,4,5,67,3,1,5};
        sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int arr[],int left,int right){
        maxHeapify(arr);
        System.out.println(Arrays.toString(arr));
        int temp;
        for (int i = right; i >left; i--) {
            temp = arr[i];
            arr[i]=arr[left];
            arr[left] =temp;
            heapDown(arr, i-left, left);
        }
    }

    /**
     * 功能描述:数组大根堆化
     * @return void
     * @auther mada
     * @param arr 传入数组
     * @date 2019/12/28 20:47
     */
     private static void maxHeapify(int[] arr){
        if (arr.length<=1)
            return;
        for (int i = arr.length/2-1; i >=0; i--) {//arr[i]代表的是非叶子结点
            heapDown(arr, arr.length, i);
        }
    }

    /**
     * 将堆顶arr[index]向下沉
     * @param arr 模拟堆的数组
     * @param length 堆的大小
     * @param index 索引
     */
    public static void heapDown(int[] arr,int length,int index){
        int f =index;
        int temp;
        while (f <= length/2-1) {//如果此时arr[index]依然为非叶子结点
            int kid=2*f+1;//此时k代表arr[i]结点的左子树结点
            if (kid + 1 < length && arr[kid] < arr[kid + 1])
                kid++;//k代表子树结点里面大的那个
            if (arr[f] < arr[kid]) {//如果子结点大于父节点,交换子节点和父节点的数据
                temp = arr[f];
                arr[f] = arr[kid];
                arr[kid] = temp;
                f = kid;//调整kid为根的堆
            } else {//如果父节点大于最大的子节点，就直接跳出，因为是从后往前进行调整，下方的堆已经是大根堆了
                break;
            }
        }
    }

    public static void heapUp(int[] arr,int index){
        int kid=index;
        int temp;
        while (kid>0){
            int father = (kid-1)/2;
            if (arr[father]<arr[kid]){
                temp = arr[father];
                arr[father] =arr[kid];
                arr[kid]=temp;
                kid =father;
            }else
                break;
        }
    }

    public static void delHeap(int[] arr,int index,int length){
        int temp;
        temp = arr[index];
        arr[index] =arr[length-1];
        arr[length-1]=temp;//交换index和堆最后一个数
        heapDown(arr, length-1, index);
    }

    public static void modifyHeap(int[] arr,int index ,int lentgth,int modifyNum){
        if (arr[index]==modifyNum){
            return;
        }
        arr[index]=modifyNum;
        heapUp(arr, index);
        heapDown(arr, lentgth, index);
    }
}
