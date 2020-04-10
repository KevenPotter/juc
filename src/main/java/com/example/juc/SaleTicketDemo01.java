package com.example.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket { // 资源类 = 实例变量 + 实例方法

    private int number = 30;
    // List list = new ArrayList();

    Lock lock = new ReentrantLock(); // 可重入锁

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第: " + (number--) + "\t 还剩下: " + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicketDemo01 {

    /**
     * 题目: 三个售票员卖出30张票
     * 笔记: 如何编写企业级的多线程代码——固定的编程套路+模板
     * 1.在高内聚低耦合的前提下,线程 操作  资源类
     * 1.1 一言不合,先创建一个资源类
     */
    public static void main(String[] args) {

    }
}
