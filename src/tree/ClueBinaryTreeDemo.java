package tree;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname ClueBinaryTree
 * @Description 线索二叉树
 * @Date 2019/12/27 16:39
 */
public class ClueBinaryTreeDemo {
    public static void main(String[] args) {

    }
}
class ClueHeroNode{
    //如果leftType == 0表示指向左子树，如果是1，表示指向前驱节点
    //如果leftType == 0表示指向右子树，如果是1，表示指向后继节点
    private int leftType;
    private int rightType;
    private int no;
    private String name;
    private ClueHeroNode left;
    private ClueHeroNode right;

    public ClueHeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClueHeroNode getLeft() {
        return left;
    }

    public void setLeft(ClueHeroNode left) {
        this.left = left;
    }

    public ClueHeroNode getRight() {
        return right;
    }

    public void setRight(ClueHeroNode right) {
        this.right = right;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}

class ClueBinaryTree {
    //为了实现线索化，需要参加一个指向当前结点的前驱结点的指针
    //在递归进行线索化时，pre总是保留一个结点
    private ClueHeroNode pre =null;
    private ClueHeroNode root;

    public void setRoot(ClueHeroNode root) {
        this.root = root;
    }

    /**
     * 中序线索二叉树
     * @param node 当前需要线索化的结点
     */
    public void ClueNodes(ClueHeroNode node){
        if (node == null){
            return;
        }
        //(一)先线索化左子树
        ClueNodes(node.getLeft());
        //（二）线索化当前指针
        //修改本结点的左结点
        if (node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //修改前驱结点的右结点
        if (pre != null && pre.getRight()== null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        //(三)线索化右子树
        ClueNodes(node.getRight());
    }
}
