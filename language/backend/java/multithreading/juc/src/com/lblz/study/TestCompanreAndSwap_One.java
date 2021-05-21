package com.lblz.study;

/**
 * @ClassName: TestCompanreAndSwap_One
 * @Description: TODO(模拟CAS算法)
 * @author lblz
 * @date 2021年3月27日
 *
 */
public class TestCompanreAndSwap_One{
	public static void main(String[] args) {
		CompareAndSwap cas = new CompareAndSwap();
		for(int i = 0; i< 10; i++) {
			new Thread(()->{
				int expectedValue = cas.get();//先得初始值
				System.out.println(cas.compareAndSet(expectedValue, (int)(Math.random() * 101)));
			}).start();
		}
	}
}

class CompareAndSwap{
	private int value;
	
	public synchronized int get() { //获取内存值
		return value;
	}
	
	
	public synchronized int companreAndSwap(int expecteValue,int newValue) { //比较
		int oldValue = value;
		if(oldValue == expecteValue) { //判断oldValue的值等不等于预估的值
			this.value = newValue;
		}
		return oldValue;
	}
	
	
	public synchronized boolean compareAndSet(int expectedValue,int newValue) { //如果预估值 == value值的话 则返回true
		return expectedValue == companreAndSwap(expectedValue, newValue);
	}
}