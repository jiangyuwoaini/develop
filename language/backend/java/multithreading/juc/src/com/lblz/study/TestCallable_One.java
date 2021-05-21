package com.lblz.study;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: TestCallable_One
 * @Description: TODO()
 * @author lblz
 * @date 2021年3月27日
 *
 */
/*
 * 一、创建执行线程的方式三:实现Callable接口
 *    区别:相较于Runnable接口方式,方法有返回值,并且可以抛出异常
 * 二、执行callable方式,需要FutureTask实现类的支持,用于接收运算结果。FutureTask是Future接口的实现类
 * 
 * */
public class TestCallable_One {
	public static void main(String[] args) {
		ThreadDemos td = new ThreadDemos();
		//1·执行callable方式,需要FutureTask实现类的支持,用于接收运算结果。
		FutureTask<Integer> result = new FutureTask<>(td);
		new Thread(result).start();
		try {
			//2.接收线程运算后的结果
			Integer sum = result.get(); //当线程结果执行完之后 才会执行这段代码 /也可以用于闭锁的操作
			System.out.println(sum);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class ThreadDemos implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int i = 0; i< 10000000 ;i++) {
			sum += i;
		}
		return sum;
	}
	
}