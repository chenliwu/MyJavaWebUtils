package com.chenlw.java.web.utils.java8.thread;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-14 14:03
 */
public class ThreadTester {

    public static void main(String[]args){
        test1();
    }

    /**
     * 使用JAVA8 Lambda表达式使用Thread
     */
    public static void test1(){
        Thread thread = new Thread(()->{
            System.out.println("使用JAVA8 Lambda表达式使用Thread");
        },"thread");
        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("new Runnable()使用Thread");
            }
        },"thread1");
        thread1.start();
    }

}
