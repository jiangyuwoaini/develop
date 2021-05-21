package com.lblz.study;

/**
 * @ClassName: TestThread8Monitor
 * @Description: TODO(线程八锁)
 * @author lblz
 * @date 2021年3月27日
 *
 */
/*	judge print is one or two?
 * 	1、两个普通同步方法,两个线程,标准打印,打印? //one twol
 *  2、新增Thread.sleep()给getOne() ,打印? //one two
 * 	3、新增普通方法getThree() ,打印? //three onetwo
 *  4、两个普通同步方法,两Number象,打印? //two one
 *  5、修改getone()为静态同步方法,打印? //twoone
 *  6、修改两个方法均为静态同步方法,一个Number对象? // one two
 *  7、一个静态同步方法,一个非静态同步方法,两个Number对象? //two one
 *  8、两个静态同步方法,两个Number对象? //one two
 * */

 /*		线程八锁的关键:
  * 		@非静态方法的锁默认为this, 静态方法的锁为对应的Class买例
  * 		@某一个时刻内,只能有一个线程持有锁,无论几个方法。
  */
public class TestThread8Monitor {
	public static void main(String[] args) {
		Number number = new Number();
		Number number2 = new Number();
 		new Thread(new Runnable() {
			@Override
			public void run() {
//				number.getOne();
//				number.getOne1();
//				number.getOne2();
				number.getOne3();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
//				number.getTwo();
//				number.getTwo1();
//				number2.getTwo2();
//				number.getTwo4();
//				number2.getTwo3();
				number2.getTwo4();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
//				number.getThree();
			}
		}).start();
	}
}
class Number{
	public synchronized void getOne() {
		System.out.println("one");
	}
	public synchronized void getOne1() {
		try {
			Thread.sleep(3000);
			System.out.println("one");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public  void getOne2() {
		try {
			Thread.sleep(3000);
			System.out.println("one");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public static synchronized void getOne3() {
		try {
			Thread.sleep(3000);
			System.out.println("one");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void getThree() {
		System.out.println("Three");
	}
	public synchronized void getTwo() {
		System.out.println("tow");
	}
	public synchronized void getTwo1() {
		System.out.println("tow");
	}
	public  void getTwo2() {
		System.out.println("tow");
	}
	public synchronized void getTwo3() {
		System.out.println("tow");
	}
	public static synchronized void getTwo4() {
		System.out.println("tow");
	}
}