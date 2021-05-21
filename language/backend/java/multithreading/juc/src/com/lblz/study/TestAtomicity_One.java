package com.lblz.study;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;

/*	一、i++的原子性问题:
 * 	    i++的操作实际上分为三个步骤：
 * 				"读-改-写"
 * 					int i = 10;
 * 					i = i++; // 10; //i = i + 1; 但是 i + 1这步操作是在执行后加的
 * 
 * 	二、原子变量: jdk1.5后java.util.concurrent.atomic包下提供了常用的原子变量:
 * 		1.atomic包下源码都用了volatile关键字保证可见性
 * 		2.atomic包下也用了CAS算法 什么是CAS?
 * 				CAS全面(compare-And-Swap)算法保证数据的原子性
 *  			CAS算法是硬件对于并发操作共享数据的支持
 *  			内存值V
 *  			预存之A
 *  			更新至B
 *  			只有当V == A,V == B 才做更新,否则不做更新
 */
public class TestAtomicity_One {
	public static void main(String[] args) {
		AtomicDemo ad = new AtomicDemo();

		for(int i = 0; i<10; i++) {
			new Thread(ad).start();
		}
	}
}
@Data
class AtomicDemo implements Runnable{
	//private volatitle int serialNumber = 0; //volatitle能够保证内存可见证 但是无法做到原子性操作
	
	private AtomicInteger serialNumber = new AtomicInteger();
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(getSerialNumber());
	}
	public int getSerialNumber() {
		return serialNumber.getAndIncrement(); //对值进行原子性+1; 
	}
}