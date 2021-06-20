package datastructure.queue;

import java.util.Scanner;

/**
 * @author Xinyu Zhang
 * @version 1.0
 * @desc
 * @date 2021 2021/6/20 13:29
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试：创建一个环形队列，设置最大长度为4，但实际上最大长度为3，因为最后一个是空的。
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4);
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
                    circleArrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    circleArrayQueue.addQueue(value);
                    break;
                //取出数据
                case 'g':
                    try {
                        int res = circleArrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                //查看队列头的数据
                case 'h':
                    try {
                        int res = circleArrayQueue.headQueue();
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

class CircleArrayQueue {
    /**
     * 表示数组的最大容量
     */
    private int maxSize;

    /**
     * 队列头
     * 指向队列头部第一个数据
     * 默认0
     */
    private int front;

    /**
     * 队列尾
     * 指向队列尾，指向队列尾的后一个位置
     * 默认0
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
    public CircleArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        this.arr = new int[this.maxSize];
        //front和rear默认为0，就不用了初始化赋值了
    }

    /**
     * 判断队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return (this.rear + 1) % this.maxSize == this.front;
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
        //直接把数组下标为rear赋值为n
        this.arr[this.rear] = n;
        //再将rear后移,这里必须考虑rear取模
        this.rear = (this.rear + 1) % this.maxSize;
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
        //之前我们定义的front指向的是数组的第一个元素，那么我们就要先把它保存到一个临时变量中，之后再后移front，再返回这个临时变量
        int res = this.arr[front];
        this.front = (this.front + 1) % maxSize;
        return res;
    }

    public void showQueue() {
        //判断队列是否为空
        if (this.isEmpty()) {
            System.out.println("队列为空！");
            return;
        }
        /**
         * 遍历队列的思路:
         *
         */
        for (int i = this.front; i < this.front + this.getSize(); i++) {
            System.out.println("arr[" + i % this.maxSize + "] = " + this.arr[i % this.maxSize]);
        }
    }

    /**
     * 求出当前有效数据的个数
     *
     * @return
     */
    public int getSize() {
        return (this.rear + this.maxSize - this.front) % this.maxSize;
    }

    public int headQueue() {
        //判断队列是否为空
        if (this.isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        return this.arr[this.front];
    }
}
