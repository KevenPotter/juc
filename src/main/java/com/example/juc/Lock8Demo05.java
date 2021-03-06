package com.example.juc;

import java.util.concurrent.TimeUnit;

class Phone {
    public static synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("*****sendEmail");
    }

    public synchronized void sendSMS() throws Exception {
        System.out.println("*****sendSMS");
    }

    public void sayHello() throws Exception {
        System.out.println("*****sayHello");
    }
}

/**
 * 1.标椎访问,请问先打印邮件还是短信       Thread.sleep(100);的话先打印邮件后发短信
 * 2.暂定4秒钟在邮件方法,请问先打印邮件还是短信 TimeUnit.SECONDS.sleep(4); 先打印邮件后发短信
 * 3.新增普通sayHello方法,请问先打印sayHello还是hello 先打印sayHello后发sendEmail
 * 4.两部手机,先打印邮件还是短信
 * 5.两个静态同步方法,同一部手机,请问先打印邮件还是短信
 * 6.两个静态同步方法,两部手机,请问先打印邮件还是短信
 * 7.一个静态同步方法,一个普通同步方法,同一部手机,请问先打印邮件还是短信
 * 8.一个静态同步方法,一个普通同步方法,两部手机,请问先打印邮件还是短信
 * <p>
 * 一个对象里面如果有多个synchronized方法,某一个时刻内,只要一个线程去调用其中的一个synchronized方法了,其他的线程都只能等待.
 * 换句话说,某一个时刻内,只能由唯一一个线程去访问这些synchronized方法.锁的是当前对象this,被锁定后,其他的线程都不能进入到当前
 * 对象的其他synchronized方法.
 * <p>
 * 加个普通方法后发现和同步锁无关
 * <p>
 * 换成两个对象后,不是同一把锁了,情况立刻发生变化
 * <p>
 * synchronized实现同步的基础:Java中的每一个对象都可以作为锁.
 * 具体表现为以下3中形式:
 * 1.对于普通同步方法,锁是当前实例对象;
 * 2.对于同步方法块,锁是synchronized括号里配置的对象;
 * 3.对于静态同步方法,锁是当前类的Class对象
 * <p>
 * 当一个线程试图访问同步代码块时,它首先必须得到锁,退出或抛出异常时必须释放锁.也就是说如果一个实例对象的非静态同步方法获取锁后,
 * 该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁,可是,别的实例对象的非静态同步方法因为跟该实例对象的非静
 * 态同步方法用的是不同的锁,所以无需等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁.
 * <p>
 * 所有的静态同步方法用的也是同一把锁——类对象Class本身.这两把锁是两个不同的对象,所以静态同步方法与非静态同步方法之间是不会有竞争条
 * 件的.但是一旦一个静态同步方法获取锁后,其他的静态同步方法都必须等待该方法释放锁后才能获取锁,而不管是同一个实例对象的静态同步
 * 方法之间,还是不同的实例对象的静态同步方法之间,只要他们同一个类的实例对象.
 */
public class Lock8Demo05 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone1.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
//                phone1.sendSMS();
//                phone1.sayHello();
                phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
