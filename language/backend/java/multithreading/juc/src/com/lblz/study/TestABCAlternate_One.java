package com.lblz.study;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*编写一个程序,开启3个线程,这三个线程的ID分别为A、B、C,每个线程将自己的ID在屏幕上打印10遍,要求输出的结果必须按顺序显示。
 * 	如: ABCABCAB C.次递归
 */
public class TestABCAlternate_One {
	public static void main(String[] args) {
		Thread_t thread_t = new Thread_t();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i<20; i++) {
					thread_t.loopA(i);
				}
			}
		},"A").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i<20; i++) {
					thread_t.loopB(i);
				}
			}
		},"B").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i<20; i++) {
					thread_t.loopC(i);
					System.out.println("--------------------------------"); //一轮结束
				}
			}
		},"C").start();
	}
}
class Thread_t{
	private int number = 1; //当前正在执行线程的标记
	private Lock lock = new ReentrantLock();
	private Condition conditionA = lock.newCondition();  
	private Condition conditionB = lock.newCondition();  
	private Condition conditionC = lock.newCondition(); 
	
	public void loopA(int totalLoop) {
		lock.lock();
		try {
			//1.判断
			if(number != 1) {
				try {
					conditionA.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//2.打印
			System.out.println(Thread.currentThread().getName() +"-"+totalLoop);
			//3.唤醒
			number = 2;
//			conditionB.signal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void loopB(int totalLoop) {
		lock.lock();
		try {
			//1.判断
			if(number != 2) {
				try {
					conditionB.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//2.打印
			System.out.println(Thread.currentThread().getName() +"-"+totalLoop);
			//3.唤醒
			number = 3;
//			conditionC.signal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void loopC(int totalLoop) {
		lock.lock();
		try {
			//1.判断
			if(number != 3) {
				try {
					conditionC.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//2.打印
			System.out.println(Thread.currentThread().getName() +"-"+totalLoop);
			//3.唤醒
			number = 1;
//			conditionA.signal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			lock.unlock();
		}
	}
}