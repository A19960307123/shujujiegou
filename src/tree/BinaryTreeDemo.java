package tree;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname BinaryTreeDemo
 * @Description 模拟二叉树
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

    public HeroNode preOrderSearch(int no){
        if (root !=null)
            return  root.preOrderSearch(no);
        else
            return null;
    }

    public HeroNode infixOrderSearch(int no){
        if (root !=null)
            return  root.infixOrderSearch(no);
        else
            return null;
    }

    public HeroNode postOrderSearch(int no){
        if (root !=null)
            return  root.postOrderSearch(no);
        else
            return null;
    }

    public void delNode(int no){
        if (root != null){
            if (root.getNo() == no){
                root =null;
            }else
                root.delNode(no);
        }
        else
            System.out.println("树为空");
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

    /**
     * 功能描述:前序遍历查找(通过id查找)
     * @return tree.HeroNode
     * @param no 要查找的值
     * @auther mada
     * @date 2019/12/27 10:56
     */
    public HeroNode preOrderSearch(int no){
        if (this.no == no) {
            return this;
        }
        HeroNode result =null;
        if (this.left != null)
            result = this.left.preOrderSearch(no);
        if (result != null)
            return result;
        if (this.right != null)
            result =this.right.preOrderSearch(no);
        return result;
    }

    /**
     * 功能描述:中序遍历查找
     * @return tree.HeroNode
     * @auther mada
     * @date 2019/12/27 10:59
     */
    public HeroNode infixOrderSearch(int no){
        HeroNode result = null;
        if (this.left != null)
            result = this.left.infixOrderSearch(no);
        if (result != null)
            return result;
        if (this.no == no)
            return this;
        if (this.right!=null)
            result =this.right.infixOrderSearch(no);
        return result;
    }

    /**
     * 功能描述:后序遍历查找
     * @return tree.HeroNode
     * @auther mada
     * @date 2019/12/27 11:03
     */
    public HeroNode postOrderSearch(int no){
        HeroNode result = null;
        if (this.left != null)
            result = this.left.postOrderSearch(no);
        if (result != null)
            return result;
        if (this.right!=null) {
            result =this.right.postOrderSearch(no);
        }
        if (result != null)
            return result;
        if (this.no == no) {
            return this;
        }
        return result;
    }

    /**
     * 功能描述:删除子树
     * @return void
     * @auther mada
     * @date 2019/12/27 14:54
     */
    public void delNode(int no){
        if (this.left != null &&this.left.no == no){
            this.left = null;
            return;
        }
        if (this.right!= null && this.right.no == no){
            this.right = null;
            return;
        }
        if (this.left != null)
            this.left.delNode(no);
        if (this.right!=null)
            this.right.delNode(no);
    }
}
