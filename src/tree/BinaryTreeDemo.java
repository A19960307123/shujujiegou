package tree;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname BinaryTreeDemo
 * @Description TODO
 * @Date 2019/12/26 17:18
 */
public class BinaryTreeDemo {
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public  void preOrder(){
        if (this.root == null){
            System.out.println("二叉树为空");
        }else
            this.root.preOrder();
    }

    public  void infixOrder(){
        if (this.root == null){
            System.out.println("二叉树为空");
        }else
            this.root.infixOrder();
    }

    public  void postOrder(){
        if (this.root == null){
            System.out.println("二叉树为空");
        }else
            this.root.postOrder();
    }
}

class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode [no = "+no+",name ="+name+"]";
    }

    /**
     * 功能描述:前序遍历
     * @return void
     * @auther mada
     * @date 2019/12/26 17:25
     */
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null)
            this.left.preOrder();
        if (this.right!= null)
            this.right.preOrder();
    }

    /**
     * 功能描述:中序遍历
     * @return void
     * @auther mada
     * @date 2019/12/26 17:28
     */
    public void infixOrder(){
        if (this.left!=null)
            this.left.infixOrder();
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 功能描述:后序遍历
     * @return void
     * @auther mada
     * @date 2019/12/26 17:34
     */
    public void postOrder(){
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }
}
