package com.lblz.study;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: TestCoutnDownLatch_One
 * @Description: TODO(CountDownLatch :闭锁,在完成某些运算是,只有其他所有线程的运算全部完成,当前运算才继续执行)
 * @author lblz
 * @date 2021年3月27日
 *
 */
public class TestCoutnDownLatch_One {
	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(10);//给个线程值,假如有10个线程 每一个线程执行完 就会进行减-,直到为0才能往下继续执行,
		LatchDemo ld = new LatchDemo(latch);
		Long l_start = System.currentTimeMillis(); 
		for(int i =0; i < 10; i++) { 
			new Thread(ld).start();
		}
		try {
			latch.await(); //先等上面10个线程执行完毕 再进行执行
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long l_end = System.currentTimeMillis();
		System.out.println("耗时间为:"+(l_end - l_start));
	}
}
class LatchDemo implements Runnable{
	private CountDownLatch latch;
	
	public  LatchDemo(CountDownLatch latch) {
		this.latch = latch;
	}
	@Override
	public void run() {
		try {
			synchronized (this) {
				for(int i = 0; i < 50000; i++) {
					if(i % 2 == 0) {
						System.out.println(i);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			latch.countDown();//给个线程值,假如有10个线程 每一个线程执行完 就会进行减-,直到为0才能往下继续执行,
		}
	}
}