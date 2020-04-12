package com.example.juc;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 1. 故障现象
 * java.util.ConcurrentModificationException
 * 2. 导致原因
 * 线程不安全
 * 3. 解决方法
 * Vector
 * Collections.synchronizedList
 * CopyWriteArrayList
 * 4. 优化建议
 * CopyOnWrite容器即写时复制的容器.往一个容器添加元素的时候,不直接往当前容器Object[]添加,而是现将当前容器Object[]进行copy,
 * 复制出一个新的容器Object[] newElements,然后新的容器Object[] newElements里面添加元素,添加完元素之后,再讲原先容器的引用
 * 指向新的容器setArray(newElements); 这样做的好处是可以对CopyOnWrite容器进行并发的读,而不需要加锁,因为当前容器不会添加任
 * 何元素.所以CopyOnWrite容器也是一种读写分离的思想,读和写不同的容器
 */
public class NotSafeDemo03 {

    public static void main(String[] args) {

    }

    public static void mapNotSafe() {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    public static void setNotSafe() {
        Set<String> list = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    public static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
