package datastructure.queue;

import java.util.Scanner;

/**
 * @author Xinyu Zhang
 * @version 1.0
 * @desc
 * @date 2021 2021/6/14 22:29
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        //接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            //接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                //取出数据
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                //查看队列头的数据
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                //退出
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

class ArrayQueue {
    /**
     * 表示数组的最大容量
     */
    private int maxSize;

    /**
     * 队列头
     */
    private int front;

    /**
     * 队列尾
     */
    private int rear;

    /**
     * 模拟队列的数组
     */
    private int[] arr;

    /**
     * 创建队列的构造器
     *
     * @param arrMaxSize
     */
    public ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.arr = new int[this.maxSize];
        //指向队列头部，分析出front是一个指向队列头的前一个位置
        this.front = -1;
        //指向队列尾，指向队列尾的数据（即就是队列的最后一个数据）
        this.rear = -1;
    }

    /**
     * 判断队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return this.rear == this.maxSize - 1;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.rear == this.front;
    }

    /**
     * 添加数据到队列
     *
     * @param n
     */
    public void addQueue(int n) {
        //判断队列是否已满
        if (this.isFull()) {
            System.out.println("队列已满！");
            return;
        }
        //rear后移
        this.rear++;
        //把n赋值给最后一个下标
        this.arr[this.rear] = n;
    }

    /**
     * 获取数据
     *
     * @return
     */
    public int getQueue() {
        //判断队列是否为空
        if (this.isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        //front后移
        this.front++;
        return this.arr[front];
    }

    public void showQueue() {
        //判断队列是否为空
        if (this.isEmpty()) {
            System.out.println("队列为空！");
            return;
        }
        for (int i = 0; i < this.arr.length; i++) {
            System.out.println("arr[" + i + "] = " + this.arr[i]);
        }
    }

    public int headQueue() {
        //判断队列是否为空
        if (this.isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        return this.arr[this.front + 1];
    }
}
