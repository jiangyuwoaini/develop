package com.lblz.study;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @ClassName: TestForkJoinPool_One
 * @Description: TODO()
 * @author lblz
 * @date 2021年3月27日
 *
 */
public class TestForkJoinPool_One {
	public static void main(String[] args) {
		Instant start = Instant.now();
		
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L,100000000L);
		Long sum = pool.invoke(task);
		System.out.println(sum);
		
		Instant end = Instant.now();
		System.out.println("消耗时间为:"+Duration.between(start, end).toMillis());
	}
	//@Test
	public  void test1() {
		Instant start = Instant.now();
		long sum = 0L;;
		for(long i = 0L; i <= 100000000; i++) {
			sum += i;
		}
		System.out.println(sum);
		Instant end = Instant.now();
		System.out.println("消耗时间为:"+Duration.between(start, end).toMillis());
	}
	//@Test
	public  void test2() {
		Instant start = Instant.now();
		Long sum = LongStream.rangeClosed(0L, 100000000L)
				.parallel()
				.reduce(0L,Long ::sum);
		System.out.println(sum);
		Instant end = Instant.now();
		System.out.println("消耗时间为:"+Duration.between(start, end).toMillis());
	}
}
class ForkJoinSumCalculate extends RecursiveTask<Long>{

	/**
	 * @Fields field:field:{todo}(序列号)
	 */
	private static final long serialVersionUID = 1L;
	private Long start;
	private Long end;
	
	private static final long THURSHOLD = 10000L; //临界值
	public ForkJoinSumCalculate(Long start,Long end) {
		this.start = start;
		this.end = end;
	}
	
	
	@Override
	protected Long compute() {
		long length = end - start;
		
		if(length <= THURSHOLD) {
			long sum = 0L;
			
			for(long i = start; i <= end; i++) {
				sum += i;
			}
			return sum;
		}else {
			long middle = (start + end) / 2; 
			ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
			left.join();//进行拆分,同时压入线程队列
			ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1, end);
			right.fork();
			return left.join() + right.join();
		}
	}
	
}