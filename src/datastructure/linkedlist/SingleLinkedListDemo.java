package datastructure.linkedlist;

/**
 * 单链表
 *
 * @author ZhangXinYu
 * @date 2021/7/2
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1, "小红");
        Node node2 = new Node(2, "小明");
        Node node3 = new Node(3, "老王");
        Node node4 = new Node(4, "老李");
        Node node5 = new Node(5, "老张");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList.add(node1);
        singleLinkedList.add(node5);
        singleLinkedList.add(node2);
        singleLinkedList.add(node4);
        singleLinkedList.add(node3);
        singleLinkedList.list();
        System.out.println("==========");
        singleLinkedList1.addByNo(node1);
        singleLinkedList1.addByNo(node5);
        singleLinkedList1.addByNo(node5);
        singleLinkedList1.addByNo(node2);
        singleLinkedList1.addByNo(node4);
        singleLinkedList1.addByNo(node3);
        singleLinkedList1.list();
        System.out.println("==========");
        //修改链表某个节点
        singleLinkedList1.update(new Node(1,"xinyu"));
        //删除链表某个节点
        singleLinkedList1.delete(new Node(5,null));
        singleLinkedList1.list();
    }
}

/**
 * 定义一个管理节点Node的SingleLinkedList
 */
class SingleLinkedList {
    //定义一个头节点，头节点只是标识他是一个链表，不作任何操作也不存储数据更不可以做任何操作，因为一旦对头结点做了操作，后续的节点都会变化。
    public Node head = new Node(0, "");

    //添加节点到链表最后，没有考虑编号，只考虑顺序加（顺序插入单链表）
    public void add(Node node) {
        //头结点不能动
        Node temp = head;
        while (true) {
            if (temp.next == null) {
                //链表为空
                break;
            }
            //不为空就后移找到空位置
            temp = temp.next;
        }
        //循环出来temp.next一定为空
        temp.next = node;
    }

    /**
     * 按编号顺序添加
     * 1、首先找到新添加的节点的位置 通过next和遍历完成
     * 2、新的节点的next=前面节点的next
     * 3、前面节点的next=新添加的节点
     *
     * @param node
     */
    public void addByNo(Node node) {
        //头结点不能动,所以我们还需要一个辅助指针(变量)来帮助找到添加的位置
        //因为是单链表，所以我们找的temp是位于添加位置的前一个节点，否则加不进去
        Node temp = head;
        //添加的标识，如果要添加的no已存在就不能添加了
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                //说明已经在链表的最后了
                break;
            }
            if (temp.next.no > node.no) {
                //链表的位置找到了，就在temp后面插入
                break;
            } else if (temp.next.no == node.no) {
                //说明要添加的编号已经存在了
                flag = true;
                break;
            }
            //条件不满足，后移
            temp = temp.next;
        }
        if (flag) {
            //编号存在，不能添加
            System.out.println("准备插入的编号" + node.no + "已存在，不能添加！");
        } else {
            //插入到链表中
            node.next = temp.next;
            temp.next = node;
        }
    }

    //修改链表某个节点的数据
    public void update(Node node){
        Node temp = head.next;
        //是否找到标志
        boolean flag = false;
        while (true) {
            if (temp == null) {
                //全部循环完成标志
                break;
            }
            if (temp.no == node.no){
                //找到数据标志
                flag = true;
                break;
            }
            //没找到数据，节点后移
            temp = temp.next;
        }
        if(flag){
            temp.data = node.data;
        } else {
            System.out.println("您输入的no" + node.no + "不存在！");
        }
    }

    //删除链表某个节点的数据
    public void delete(Node node){
        Node temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.next.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = node.next;
        } else {
            System.out.println("您输入的no" + node.no + "不存在！");
        }
    }

    //遍历链表
    public void list() {
        //头结点不能动
        Node temp = head.next;
        if (temp == null) {
            return;
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
        //老师写法
//        if(head.next == null){
//            return;
//        }
//        Node temp = head.next;
//        while (true) {
//            if(temp == null){
//                break;
//            }
//            System.out.println(temp);
//            temp = temp.next;
//        }
    }
}

/**
 * 定义节点 Node
 */
class Node {
    public int no;
    public String data;
    //指向下一个节点
    public Node next;

    public Node(int no, String data) {
        this.no = no;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", data='" + data + "'" +
                '}';
    }
}
