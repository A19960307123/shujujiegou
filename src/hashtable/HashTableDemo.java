package hashtable;

import javax.swing.*;

/**
 * @author 宋浩
 * @version 1.0
 * @Classname HashTableDemo
 * @Description TODO
 * @Date 2019/12/25 16:59
 */
public class HashTableDemo {
    public static void main(String[] args) {
    }
}

//创建HashTable管理多条链表
class HashTab{
    private EmpLinkList[] empLinkLists ;
    private int size;

    public HashTab(int size){
        this.size =size;
        empLinkLists =new EmpLinkList[size];
        //只分配空间不能直接使用，还需要对每个空间进行初始化
        for (int i = 0; i < size; i++) {
            empLinkLists[i] = new EmpLinkList();
        }
    }

    public void add(Emp emp){
        //1.根据员工ID判读加到哪个链表
        int empLinkListNo = hashFun(emp.id);
        empLinkLists[empLinkListNo].add(emp);
    }

    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkLists[i].list();
        }
    }

    //编写一个散列函数，使用一个简单的取模法
    public int hashFun(int id){
        return id%size;
    }

    public void findEmpById(int id ){
        int listId=hashFun(id);
        Emp emp =empLinkLists[listId].findEmpById(id);
        if (emp ==null ){
            System.out.println("未查找到");
        }else {
            System.out.println("name = "+emp.name +"  ;  id = "+emp.id);
        }
    }
}

class Emp {//链表用来存储数据
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

}

class EmpLinkList{
    private Emp head;//链表头

    public void add(Emp emp){
        if (head ==null){
            head =emp;
        }
        Emp temp = head;
        while (temp.next != null){
            temp =temp.next;
        }
        temp.next = emp;
    }

    public void list(){
        if (head ==null){
            System.out.println("当前链表为空");
        }
        System.out.println("当前链表信息为：");
        Emp temp =head;
        while (temp!=null){
            System.out.println("name = "+temp.name +"  ;  id = "+temp.id);
            temp=temp.next;
        }
    }

    public Emp findEmpById(int id){
        if (head ==null){
            System.out.println("链表为空");
            return null;
        }
        Emp temp =head;
        while (temp!=null){
            if (temp.id ==id){
                return temp;
            }
            temp =temp.next;
        }
        return null;
    }
}

