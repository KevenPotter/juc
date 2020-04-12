package com.example.juc;

import java.util.concurrent.TimeUnit;

class Phone {
    public synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("*****sendEmail");
    }

    public synchronized void sendSMS() throws Exception {
        System.out.println("*****sendSMS");
    }
}

/**
 * 1.标椎访问,请问先打印邮件还是短信       Thread.sleep(100);的话先打印邮件后发短信
 * 2.暂定4秒钟在邮件方法,请问先打印邮件还是短信
 */
public class Lock8Demo05 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        Thread.sleep(100);
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
