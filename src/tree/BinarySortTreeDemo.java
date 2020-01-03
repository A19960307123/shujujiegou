package tree;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname BinarySortTreeDemo
 * @Description 二叉排序树
 * @Date 2020/1/3 9:53
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr ={9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int value : arr) {
            binarySortTree.add(new Node(value));
        }
        binarySortTree.delNode(9);
        binarySortTree.delNode(2);
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

    public Node searchParent(int value){
        if (root==null){
            return  null;
        }else{
            return root.searchParent(value);
        }
    }

    /*
    * 1.找到要删除的结点 node1和它的父节点 nodeParent（如果要删除结点为根结点，就创建一个结点，左子结点指向根节点）
    * 2.记录要删除结点是其父节点的左子结点还是右子结点 mark1
    * 3.分情况考虑
    *      3.1 若要删除的结点有左右子结点
    *           找出它左子树下最大的值（最下的右子结点）或是右子树下最小的值（最下的左子结点） nodeMark
    *           同时记录这个标记结点的父节点 nodeMarkParent 和左右子结点信息 mark2
    *           将node1的值记为nodeMark的值
    *           之后删除nodeMark结点即可（此时nodeMark一定是下面两个情况）
    *           node1 = nodeMark ,nodeParent =nodeMarkParent,mark1 = mark2
    *       3.2 若要删除的结点是叶子结点
    *           父节点对应的子结点置位 null即可
    *       3.3 若要删除的结点有一个子结点
    *           父节点对应的子结点 设置为 被删除结点唯一的子结点 即可
    * 4.专门讨论删除的结点为root结点的情况
    *       4.1 若要删除的结点有左右子结点
    *           不需要额外处理，root结点会被赋予新值，删除的结点并不是root
    *       4.2 若要删除的结点是叶子结点
    *           root = null 即可
    *       4.3 若要删除的结点有一个子结点
    *           root = 唯一的子结点即可
    * */
    public void delNode(int value){//对根节点的删除要单独写情况
        if (root == null){
            return;
        }
        Node searchTheParent;//searchTheParent表示要删除的结点的父节点
        Node search ;//search表示要删除的结点
        int mark;//1表示要删除结点为左节点，2表示要删除结点为右节点
        if (root.value==value){//恰好删除根节点
            searchTheParent = new Node(-1);//创建一个结点的左子结点指向root
            searchTheParent.left =root;
        }else{
            searchTheParent  = searchParent(value);
        }
        if (searchTheParent == null){
            System.out.println("未找到要删除的结点");
            return;
        }else if(searchTheParent.left.value == value){
            search = searchTheParent.left;
            mark = 1;
        }else {
            search = searchTheParent.right;
            mark = 2;
        }
        //2.要删除的结点有两个子结点
        if (search.left !=null && search.right != null){
            Node middle = search.left;
            Node middleParent = search;
            mark = 1;
            while (middle.right !=null){//找到search左子树下的最大值及其父节点
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
        if (search.left ==null && search.right == null){
            if (search == root){//如果要删除的是根结点
                root = null;
                return;
            }
            if (mark ==1){
                searchTheParent.left =null;
            }else {
                searchTheParent.right = null;
            }
            return;//结束
        }

        //3.要删除的结点只有一个子结点
        if (search ==root){
            root = searchTheParent.left;
        }
        if (mark ==1){
            if (search.left ==null){
                searchTheParent.left =search.right;
            }else {
                searchTheParent.left =search.left;
            }
        }else {
            if (search.left ==null){
                searchTheParent.right =search.right;
            }else {
                searchTheParent.right =search.left;
            }
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

    /**
     * 查找等于value的结点的父节点
     * @param value 要查找的值
     * @return 找到就返回父节点，否则就返回null
     */
    public Node searchParent(int value){
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
}
