package com.yueny.fw.practice.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程分别占用 south 锁和 north 锁，并同时请求对方占用的锁，导致死锁
 *
 * @author yueny09 <deep_blue_yang@163.com>
 * @DATE 2019/6/5 下午3:46
 */
public class Deadlock extends Thread {
    protected Object myDirect;
    static ReentrantLock south = new ReentrantLock();
    static ReentrantLock north = new ReentrantLock();

    public Deadlock(){
        //.
    }

    public Deadlock(Object obj){
        this.myDirect = obj;
        if(myDirect==south){
            this.setName("south");
        }else{
            this.setName("north");
        }
    }

    @Override
    public void run(){
        if(myDirect==south){
            try{
                north.lockInterruptibly();//占用 north
                try{
                    Thread.sleep(500);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                south.lockInterruptibly();
                System.out.println("car to south has passed");
            }catch(InterruptedException ex){
                System.out.println("car to south is killed");
                ex.printStackTrace();
            }finally{
                if(north.isHeldByCurrentThread()){
                    north.unlock();
                }
                if(south.isHeldByCurrentThread()){
                    south.unlock();
                }
            }
        }
        if(myDirect==north){
            try{
                south.lockInterruptibly();//占用 south
                try{
                    Thread.sleep(500);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                north.lockInterruptibly();
                System.out.println("car to north has passed");
            }catch(InterruptedException ex){
                System.out.println("car to north is killed");
                ex.printStackTrace();
            }finally{
                if(north.isHeldByCurrentThread()){
                    north.unlock();
                }
                if(south.isHeldByCurrentThread()){
                    south.unlock();
                }
            }
        }

    }
    public static void main(String[] args) throws InterruptedException{
        Deadlock car2south = new Deadlock(south);
        Deadlock car2north = new Deadlock(north);
        car2south.start();
        car2north.start();
    }
}
