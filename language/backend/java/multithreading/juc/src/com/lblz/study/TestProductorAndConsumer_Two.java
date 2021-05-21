package com.lblz.study;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: TestProductorAndConsumer
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author lblz
 * @date 2021年3月27日
 *
 */
public class TestProductorAndConsumer_Two {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Productor pro = new Productor(clerk);
		Consumer cus = new Consumer(clerk);
		
		new Thread(pro,"生产者A").start();
		new Thread(cus,"消费者B").start();
	
		new Thread(pro,"生产者C").start();
		new Thread(cus,"消费者D").start();
	}
}
class Clerks{
	private int product = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	//进货
	public synchronized void get() {
		while(product >= 1) { //为了避免虚假唤醒问题,应该总是使用在循环中
			System.out.println("产品已满！");
			try {
				condition.await(); //代替Object的awit
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			System.out.println(Thread.currentThread().getName()+" : "+ ++product);
			condition.signalAll();//代替Object的notifyAll
	}
	
	//卖货
	public synchronized void sale() {
		while(product <= 0) {
			System.out.println("缺货");
			try {
				condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
			System.out.println(Thread.currentThread().getName()+" : "+ --product);
			condition.signalAll();
	}
}
//生产者
class Productors implements Runnable{
	private Clerks clerks;
	
	public Productors(Clerks clerks) {
		this.clerks = clerks;
	}
	
	@Override
	public void run() {
		for(int i = 0; i<20; i++) {
			clerks.get();
		}
	}
	
}
//消费者
class Consumers implements Runnable{
	private Clerks clerks;
	public Consumers(Clerks clerks) {
		this.clerks = clerks;
	}
	@Override
	public void run() {
		for(int i = 0; i<20; i++) {
			clerks.sale();
		}
	}
	
}