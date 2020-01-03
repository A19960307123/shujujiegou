package tree;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname AVLTreeDemo
 * @Description 平衡二叉树
 * @Date 2020/1/3 16:04
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        int[] arr={1,2,3,4};
        for (int a:arr){
            avlTree.add(new AVLNode(a));
        }
//        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
    }
}

class AVLTree {
    private AVLNode root;

    public AVLNode getRoot() {
        return root;
    }

    public void add(AVLNode node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("树为空");
        } else {
            root.infixOrder();
        }
    }

    public AVLNode searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public void delAVLNode(int value) {//对根节点的删除要单独写情况
        if (root == null) {
            return;
        }
        AVLNode searchTheParent;//searchTheParent表示要删除的结点的父节点
        AVLNode search;//search表示要删除的结点
        int mark;//1表示要删除结点为左节点，2表示要删除结点为右节点
        if (root.value == value) {//恰好删除根节点
            searchTheParent = new AVLNode(-1);//创建一个结点的左子结点指向root
            searchTheParent.left = root;
        } else {
            searchTheParent = searchParent(value);
        }
        if (searchTheParent == null) {
            System.out.println("未找到要删除的结点");
            return;
        } else if (searchTheParent.left.value == value) {
            search = searchTheParent.left;
            mark = 1;
        } else {
            search = searchTheParent.right;
            mark = 2;
        }
        //2.要删除的结点有两个子结点
        if (search.left != null && search.right != null) {
            AVLNode middle = search.left;
            AVLNode middleParent = search;
            mark = 1;
            while (middle.right != null) {//找到search左子树下的最大值及其父节点
                middleParent = middle;
                middle = middle.right;//middle只可能是叶子结点或只有一个子结点
                mark = 2;
            }
            search.value = middle.value;//把middle的值赋给search
            //之后的工作变成了删除middle
            search = middle;
            searchTheParent = middleParent;
        }
        //1.要删除的结点为叶子结点
        if (search.left == null && search.right == null) {
            if (search == root) {//如果要删除的是根结点
                root = null;
                return;
            }
            if (mark == 1) {
                searchTheParent.left = null;
            } else {
                searchTheParent.right = null;
            }
            return;//结束
        }

        //3.要删除的结点只有一个子结点
        if (search == root) {
            root = searchTheParent.left;
        }
        if (mark == 1) {
            if (search.left == null) {
                searchTheParent.left = search.right;
            } else {
                searchTheParent.left = search.left;
            }
        } else {
            if (search.left == null) {
                searchTheParent.right = search.right;
            } else {
                searchTheParent.right = search.left;
            }
        }
    }
}
class AVLNode{
    int value;
    AVLNode left;
    AVLNode  right;

    public AVLNode(int value) {
        this.value = value;
    }

    /**
     * 返回当前结点的高度，以该节点为根节点的高度
     * @return 以该节点为根节点的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 返回左子树的高度
     * @return 左子树的高度
     */
    public int leftHeight(){
        if (left == null){
            return  0;
        }else{
            return left.height();
        }
    }

    /**
     * 返回右子树的高度
     * @return 右子树的高度
     */
    public int rightHeight(){
        if (right == null){
            return 0;
        }else{
           return right.height();
        }
    }

    /**
     * 添加结点
     * @param node 要添加的结点
     */
    public void add(AVLNode node){
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
        /*
        * 迭代，每次个经过的结点都会判断依次，所以每一个子树都是AVL树
        * */
        if (rightHeight()-leftHeight()>1){
            /*
            * 左旋转之后左子树的高度= 原本右子树的左子树+1
            * 左旋转之后右子树的高度= 原本右子树的右子树
            * 如果右子树的左子树高度大于右子树的右子树高度
            * 旋转过后 leftHeight()-rightHeight()>1  依然存在
            * */

            if (right!=null && right.leftHeight()>right.rightHeight()){
                right.rightRotate();
            }
            leftRotate();
            return;
        }
        if (leftHeight()-rightHeight()>1){
            if(left!=null && left.rightHeight()>left.leftHeight()){
                left.leftRotate();
            }
            rightRotate();
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
        return "AVLNode{" +
                "value=" + value +
                '}';
    }

    /**
     * 查找等于value的结点的父节点
     * @param value 要查找的值
     * @return 找到就返回父节点，否则就返回null
     */
    public AVLNode searchParent(int value){
        if ((this.left != null && this.left.value == value)
                ||(this.right != null && this.right.value == value)){
            return this;
        }else if (this.value>value && this.left != null){
            return this.left.searchParent(value);
        }else if (this.value<=value && this.right != null){
            return this.right.searchParent(value);
        }else {
            return null;
        }
    }

    /**
     * 左旋转
     */
    public void leftRotate(){
        //1.创建一个新的结点，等于当前结点的值
        AVLNode newNode = new AVLNode(value);
        //2.把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        //3.把更新的结点的右子树设置成过去结点的右子树的左子树
        newNode.right = right.left;
        //4.把当前结点的值替换成右子结点的值
        value = right.value;
        //5.把当前结点的右子树设置成当前结点的右子树的右子树
        right =right.right;
        //把当前结点的左子树设置为新的结点
        left = newNode;
    }

    /**
     * 右旋转
     */
    public void rightRotate(){
        //与左旋转类似
        AVLNode newNode = new AVLNode(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right =newNode;
    }
}
