package tree;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname ArrBinaryTreeDemo
 * @Description 实现顺序存储二叉树遍历(就是用数组来模拟完全二叉树的结构)
 * @Date 2019/12/27 16:04
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr ={1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
    }
}
class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void  preOrder(){
        this.preOrder(0);
    }

    /**
     * @param index 数组的下标
     */
    public void preOrder(int index){
        if (arr==null || arr.length ==0)
            System.out.println("数组为空");
        //输出当前这个元素
        System.out.println(arr[index]);
        if (index*2+1<arr.length){
            preOrder(index*2+1);
        }
        if (index*2+2<arr.length){
            preOrder(index*2+2);
        }
    }
}
