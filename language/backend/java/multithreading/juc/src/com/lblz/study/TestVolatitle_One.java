package com.lblz.study;

import lombok.Data;

/**
 * @ClassName: TestVolatitle_One
 * @Description: TODO(Volatitle关键字练习)
 * @author lblz
 * @date 2021年3月27日
 *
 */
//一、volatile关键字:当多个线程进行操作共享数据时,可以保证内存中的数据可见。相较于synchronized是一种较为轻量级的同步策略。
//注意：
	//1. volatile不具备"互斥性"
	//2. volatile不能保证变量的"原子性”
public class TestVolatitle_One {
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		new Thread(td).start();
		while(true) {//while等于true线程就会一直执行
//			synchronized (td) { //加锁会让线程有序的执行,并且会刷新锁范围内的最新数据,如果内存可见性问题,可以用volitile代替
				if(td.isFlag()) {
					System.out.println("---------------------");
					break; //跳出循环
//				}
			}
		}
	}
}
@Data
class ThreadDemo implements Runnable{
	private volatile boolean flag = false;
	@Override
	public void run() {
		try {
			Thread.sleep(2000);//等待两秒
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag = true;
		System.out.println("flag="+isFlag());
	}
}