package com.dwt;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Package: com.dwt
 * @Description:
 * @Author: Sammy
 * @Date: 2020/10/25 10:53
 */

public class ReentrantReadWriteLockTest {
	private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
	private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
	private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

	public static void read(){
		readLock.lock();
		try {
            System.out.println(Thread.currentThread().getName() + "获取读锁，开始执行");
            Thread.sleep(20);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放读锁");
        }
	}

	public static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取写锁，开始执行");
            Thread.sleep(40);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放写锁");
        }
    }

     public static void read1() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取读锁，开始执行");
            Thread.sleep(20);
            System.out.println(Thread.currentThread().getName()+ "尝试升级读锁为写锁");
            //读锁升级为写锁(失败)
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() +"读锁升级为写锁成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放读锁");
        }
    }

    public static void write1() {
        System.out.println(Thread.currentThread().getName() + "开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取写锁，开始执行");
            Thread.sleep(40);
            System.out.println(Thread.currentThread().getName() +"尝试降级写锁为读锁");
            //写锁降级为读锁（成功）
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+ "写锁降级为读锁成功");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放写锁");
            writeLock.unlock();
            readLock.unlock();
        }
    }


    //  public static void main(String[] args) {
    //     new Thread(() -> read(), "Thread1").start();
    //     new Thread(() -> read(), "Thread2").start();
    //     new Thread(() -> write(), "Thread3").start();
    //     new Thread(() -> write(), "Thread4").start();
    // }

    public static void main(String[] args) {
        new Thread(() -> write(), "Thread1").start();
        new Thread(() -> read(), "Thread2").start();
        new Thread(() -> read(), "Thread3").start();
        new Thread(() -> write(), "Thread4").start();
        new Thread(() -> read(), "Thread5").start();
        new Thread(() -> {
            Thread[] threads = new Thread[100];
            for (int i = 0; i < 100; i++) {
                threads[i] = new Thread(() -> read(), "子线程创建的Thread" + i);
            }
            for (int i = 0; i < 100; i++) {
                threads[i].start();
            }
        }).start();
    }

    //  public static void main(String[] args) {
    //     new Thread(() -> write1(), "Thread1").start();
    //     new Thread(() -> read1(), "Thread2").start();
    // }



}
