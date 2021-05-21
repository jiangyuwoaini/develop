package com.lblz.nio.test;

import java.nio.ByteBuffer;

import org.junit.Test;

/* 一、缓冲区(Buffer) :在Java NIO中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据
 * 根据数据类型不同(boolean除外) ,提供了相应类型的缓冲区:
 * 		|-ByteBuffer
 * 		|-CharBuffer
 * 		|-ShortBuffer
 * 		|-IntBuffer
 * 		|-LongBuffer
 * 		|-FloatBuffer
 * 		|-DoubleBuffer
 * 		上述缓冲区的管理方式几乎一致,通过allocate()获职缓冲区
 * 二、缓冲区存取数据的两个核心方法
 *		put():存入数据到缓冲区中
 *		get():获取缓冲区的数据
 * 三、缓冲区的四个核心属性
 * 		capacity :容量,表示缓冲区中最大存储数据的容量。一旦声明不能改变。
 * 		limit :界限,表示缓冲区中可以操作数据的大小。(limit后数据不能进行读写)
 * 		position :位置,表示缓冲区中正在操作数据的位置。
 * 		mark :标记,表示记录当前position的位置。可以通过reset()恢复到mark的位置
 *		0 <= mark <= position <= limit <= capacity
 * 四、直接缓冲区与非直接缓冲区:
 * 		非直接缓冲区:通过allocate()方法分配缓冲区,将缓冲区建立在JVM的内存中
 * 		直接缓冲区:通过allocateDirect()方法分配直接缓冲区,将缓冲区建立在物理内存中
 */
public class TestBuffer_Oner {
	public static void main(String[] args) {
		new TestBuffer_Oner().testThree();
	}
	
	@Test
	public void testThree() {
		//分配直接缓冲区
		ByteBuffer buf = ByteBuffer.allocateDirect(1025);
		System.out.println(buf.isDirect()); //判断是否是直接缓冲区
	}
	@Test
	public void testTwo() {
		String str = "abcde";
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.put(str.getBytes());
		buf.flip();//切换到读
		byte[] dts = new byte[str.length()];
		buf.get(dts,0,2);
		System.out.println(new String(dts,0,2));
		System.out.println(buf.position()); //在哪个位置?
		//mark() 标记
		buf.mark();
		buf.get(dts,2,2);
		System.out.println(new String(dts,2,2));
		System.out.println(buf.position()); //在哪个位置?
		
		//reset() :恢复到mark的位置
		buf.reset();
		System.out.println(buf.position());
		//判断缓冲区中是否还有剩余数据
		if(buf.hasRemaining()) {
			//获取缓冲区中可以操作的数量
			System.out.println(buf.remaining());
		}
		
	}

	@Test
	private static void testOne() {
		String str = "abcde";
		//一、分配一个指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		System.out.println("-----------------allocate()---------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		//二、利用put()存入数据缓冲区
		buf.put(str.getBytes());
		System.out.println("-----------------put()---------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		//三、切换读取数据的模式
		buf.flip();
		System.out.println("-----------------flip()---------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		//四、利用get()读取缓冲区的数据
		byte[] dts = new byte[buf.limit()];
		buf.get(dts);
		System.out.println(new String(dts,0,dts.length));
		System.out.println("-----------------get()---------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		//五、rewind 可重复读
		buf.rewind();
		System.out.println("-----------------rewind()---------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		//6、clear 清空缓冲区 虽然清空缓冲区 但是缓冲区数据还在,只是处于被遗忘状态
		buf.clear();
		System.out.println("-----------------clear()---------------------");
		System.out.println(buf.position());
		System.out.println(buf.limit());
		System.out.println(buf.capacity());
		System.out.println((char)buf.get());
	}
}
