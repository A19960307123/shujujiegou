package haosong;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname CircleList
 * @Description 环形链表
 * @Date 2019/12/20 21:08
 */
public class CircleList {
    private Boy first ;

    public static void main(String[] args) {
        CircleList circleList = new CircleList();
        circleList.countBoy(1, 2, 5);
    }
//    添加小孩节点，构建成一个环形的链表
    public void  addBoy(int nums){
        if (nums<1){
            System.out.println("Nums的值不正确");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <=nums ; i++) {
//            根据编号创建小孩节点
            Boy boy = new Boy(i);
            if (i==1){
                first = boy;
                first.setNext(first);
                curBoy = boy;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

//    遍历当前列表
    public void showBoy(){
        if (first ==null){
            System.out.println("链表为空");
            return;
        }
        Boy curBoy =first;
        while (true){
            System.out.println(curBoy);
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 包含了添加小孩的操作
     * @param startId 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初由多少个小孩在圈中
     */

    public  void  countBoy(int startId,int countNum ,int nums ){
        if (nums<1 || startId>nums || startId <1){
            System.out.println("输入有误");
            return;
        }
        addBoy(nums);
//        helper辅助用来删除链表的节点
        Boy helper = first;
        while (helper.getNext()!=first){
            helper=helper.getNext();
        }
//        先移动startId-1次,使first指向startId这个小孩
        for (int i = 0; i <startId-1 ; i++) {
            helper = helper.getNext();
        }
        while (helper.getNext() !=helper ){
            for (int i = 0; i <countNum-1 ; i++) {
                helper = helper.getNext();
            }
            System.out.println(helper.getNext());
            helper.setNext(helper.getNext().getNext());
        }
        System.out.println("最后的胜利者是： "+ helper.getId()+"号小孩");
    }
}
class Boy{
    private int id;
    private Boy next;

    public Boy(int id, Boy next) {
        this.id = id;
        this.next = next;
    }

    public Boy(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public Boy getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "id=" + id +
                '}';
    }
}
