package tree;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname BinarySortTreeDemo
 * @Description TODO
 * @Date 2020/1/3 9:53
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr ={7,3,10,12,5,1,9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.infixOrder();
    }
}

class BinarySortTree{
    private Node root;

    public void add(Node node){
        if (root == null){
            root =node;
        }else{
            root.add(node);
        }
    }

    public void infixOrder(){
        if (root ==null){
            System.out.println("树为空");
        }else{
            root.infixOrder();
        }
    }
}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 添加结点
     * @param node 要添加的结点
     */
    public void add(Node node){
        if (node == null){
            return;
        }
        if (node.value<this.value){//添加的结点值小于当前结点的值
            if (this.left==null){
                this.left = node;
            }else{
                this.left.add(node);
            }
        }else {//添加的结点值大于等于当前结点的值
            if (this.right == null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
