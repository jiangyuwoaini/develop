package com.lblz.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/*
 * 	一、使用NIO完成网络通信的三个核心:
 * 		1,通道(Channel) :负责连接
 * 			java.nio.channels.Channel接口:
 * 				|--SelectableChannel    //ctp
 * 					|--SocketChannel		//ctp
 * 					|--ServerSocketChanne	//ctp
 * 					|--DatagramChannel
 * 				
 * 					|--Pipe.SinkChannel		//udp
 * 					|--Pipe.SourceChannel	//udp
 * 	二、缓冲区(Buffer) :负责数据的存取
 * 	三、选择器(Selector) :是SelectableChannel的多路复用器。用于监控SelectableChannel的IO状况
 */
/**
 * @ClassName: TestBlockingNIO_One
 * @Description: TODO(阻塞式 传送图片,先启动server方法,再次启动client方法)
 * @author lblz
 * @date 2021年4月3日
 *
 */
public class TestBlockingNIO_One {
	
	/**
	 * @throws IOException 
	 * @Title: client
	 * @Description: TODO(客户端)
	 * @param  参数
	 * @return void 返回类型
	 * @throws
	 */
	@Test
	public void client() throws IOException {
		SocketChannel scChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9898)); //1.获取通道
		FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ); //文件通道
		ByteBuffer buf = ByteBuffer.allocate(1024); //2.分配缓存大小
		while(inChannel.read(buf) != -1) { //3.读取本地文件,并发布到服务端
			buf.flip(); //切换读写模式
			scChannel.write(buf);
			buf.clear();
		}
		//4.关闭通道
		inChannel.close();
		scChannel.close();
	}
	@Test
	/**
	 * @throws IOException 
	 * @throws IOException 
	 * @Title: client
	 * @Description: TODO(服务端)
	 * @param  参数
	 * @return void 返回类型
	 * @throws
	 */
	public void server() throws IOException {
		ServerSocketChannel ssChannel = ServerSocketChannel.open(); //1.获取通道
		FileChannel outFileChannel = FileChannel.open(Paths.get("52.jpg"), StandardOpenOption.CREATE,StandardOpenOption.WRITE);
		ssChannel.bind(new InetSocketAddress(9898));//2.绑定连接 默认应该是本地ip
		SocketChannel sChannel = ssChannel.accept(); //3.获取客户端连接的通道
		ByteBuffer buf = ByteBuffer.allocate(1024); //4.分配指定大小缓冲区
		while (sChannel.read(buf) != -1) { //5.接收客户端的数据,并保存到本地
			buf.flip();
			outFileChannel.write(buf);
			buf.clear();
		}
		sChannel.close();//6.关闭通道
		outFileChannel.close();
		ssChannel.close();
	}
}
