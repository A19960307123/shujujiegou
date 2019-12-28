package tree;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname ClueBinaryTree
 * @Description 线索二叉树（可以有效利用所有的空间（叶子结点的left和right））
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

    public int getLeftType() {
        return leftType;
    }

    public int getRightType() {
        return rightType;
    }

    @Override
    public String toString() {
        return "ClueHeroNode [no = "+no+",name ="+name+"]";
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

    public void ClueNodes(){
        ClueNodes(root);
    }

    //遍历线索化二叉树的方法
    public void clueList(){
        ClueHeroNode node =root;
        while (node != null){
            /*
            * 找到以node为根节点的树里，中序遍历的起始点，输出它
            * */
            while (node.getLeftType() ==0){
                node =node.getLeft();
            }
            System.out.println(node);

            //如果node.rightType是1，则node.right代表的是后继结点，可以直接进行访问输出
            //如果node.rightType是0，就说明node.right不是下一个访问的结点
            //node = node.right，然后进入循环，重新去找中序遍历的第一个访问点
            //所有的有两个分支的非叶子结点，都会存在一个叶子结点的后驱指向它
            while (node.getRightType()==1){
                node = node.getRight();
                System.out.println(node);
            }
            node =node.getRight();
        }
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
