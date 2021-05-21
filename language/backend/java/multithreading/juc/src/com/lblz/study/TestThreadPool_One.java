package com.lblz.study;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: TestThreadPool
 * @Description: TODO(线程池)
 * @author lblz
 * @date 2021年3月27日
 *
 */
/*	一、线程池:
 * 		提供了一个线程队列,队列中保存着所有等待状态的线程。避免了创建与销毁额外开销,提高了响应的速度。
 * 	二、线程池的体系结构:
 * 		java.util.concurrent.Executor :负责线程的使用与调度的根接口
 * 			|-ExecutorService子接口:线程池的主要接口
 * 				|--ThreadPoolExecutor实现类。
 * 				|--ScheduledExecutorService子接口:负责线程的调度
 * 					|--ScheduledThreadPoolExecutor实现类 继承了ThreadPoolExecutor 实现了ScheduledExecutorService
 * 	三、工具类:Executors
 * 	   ExecutorService newFixedThreadPool():创建固定大小的线程池
 * 	   ExecutorService newCachedThreadPool():缓存线程池,线程池的数量不固定,可以根据需求自动的更改数据。
 * 	   ExecutorService newSingleThreadExecutor():创建单个线程池,线程池只有一个线程
 * 
 * ScheduledExecutorService newScheduledThreadPool():创建固定大小的线程,可以延迟或定时的执行任务
 */
public class TestThreadPool_One {
	public static void main(String[] args) {
		ThreadPoolDemo td = new ThreadPoolDemo();
		//一、创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(5);
		//二、为线程池中的线程分配任务
		for(int i =0; i < 10; i++) {
			pool.submit(td);
		}
		//三、关闭线程池
		pool.shutdown();
	}
}
class ThreadPoolDemo implements Runnable{
	private int i = 0;

	@Override
	public void run() {
		for(int i = 0;i<100; i++) {
			System.out.println(Thread.currentThread().getName()+"                 i = i + 1"+i++);
		}
	}
}