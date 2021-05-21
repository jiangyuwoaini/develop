package com.lblz.study;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: TestReadWriteLock
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author lblz
 * @date 2021年3月27日
 *
 */
/*	1. ReadwriteLock :读写锁 
 * 		写写/读写需要“互斥”
 * 		读读不需要互斥
 * */
public class TestReadWriteLock {
	public static void main(String[] args) {
		TestReadWriteLock tr = new TestReadWriteLock();
		new Thread(()->{ //多个读操作
			for (int i = 0;i < 100; i++) {
				tr.get();
			}
		}).start();
		new Thread(()->{  //一个写操作
			tr.set(18);
		}).start();
	}
	private int number = 0 ;
	
	private int i = 0;
	
	private ReadWriteLock lock = new ReentrantReadWriteLock(); //创建读写对象
	
	
	//读操作
	public void get() {
		lock.readLock().lock();//上锁
		try {
			System.out.println(Thread.currentThread().getName()+" number: "+number+",i:"+i++);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.readLock().unlock();//释放锁
		}
	}
	
	//读操作
	public void set(int number) {
		lock.writeLock().lock();//上锁
		try {
			System.out.println(Thread.currentThread().getName());
			this.number = number;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.writeLock().unlock();//释放锁
		}
	}
}
