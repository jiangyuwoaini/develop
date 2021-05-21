package com.lblz.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

/*
 * 	一、使用NIO完成网络通信的三个核心:
 * 		1,通道(Channel) :负责连接
 * 			java.nio.channels.Channel接口:
 * 				|--SelectableChannel    //ctp
 * 					|--SocketChannel		//ctp
 * 					|--ServerSocketChanne	//ctp
 * 					|--DatagramChannel		//ctp
 * 				
 * 					|--Pipe.SinkChannel		//udp
 * 					|--Pipe.SourceChannel	//udp
 * 	二、缓冲区(Buffer) :负责数据的存取
 * 	三、选择器(Selector) :是SelectableChannel的多路复用器。用于监控SelectableChannel的IO状况
 */
/**
 * @ClassName: TestBlockingNIO_One
 * @Description: TODO(非阻塞式)
 * @author lblz
 * @date 2021年4月3日
 *
 */
public class TestNonBlockingNIO_Two {
	
	/**
	 * @throws IOException 
	 * @Title: client
	 * @Description: TODO(客户端)
	 * @param  参数
	 * @return void 返回类型
	 * @throws
	 */
	@Test
	public void send() throws IOException {
		DatagramChannel dc = DatagramChannel.open(); //文件通道
		dc.configureBlocking(false); //切换为非阻塞
		ByteBuffer buf = ByteBuffer.allocate(1024); //分配缓存大小
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) { 
			String str = scan.next();
			buf.put((new Date().toString() + ":\n"+str).getBytes());
			buf.flip();

			dc.send(buf, new InetSocketAddress("127.0.0.1",9898));
			buf.clear();
		}
		//关闭通道
		dc.close();
	}
	/**
	 * @throws IOException 
	 * @throws IOException 
	 * @Title: client
	 * @Description: TODO(服务端)
	 * @param  参数
	 * @return void 返回类型
	 * @throws
	 */
	@Test
	public void receive() throws IOException {
		DatagramChannel dc = DatagramChannel.open(); //1.获取通道
		dc.configureBlocking(false);		//2.切换非阻塞模式
		dc.bind(new InetSocketAddress(9898));//3.绑定连接 默认应该是本地ip
		//4.获取选择器
		Selector selector = Selector.open();
		//5.将通道注册到选择器上,并指定"监听接收事件"
		dc.register(selector, SelectionKey.OP_READ);
		//6.轮询式的获取选择器上已经"准备就绪"的
		while(selector.select() > 0) {
			 Iterator<SelectionKey> it = selector.selectedKeys().iterator(); //7.获取当前选择器所有注册的选择键 选择键的意思就是已就绪的监听事件
			//8.获取准备就绪的事件
				while (it.hasNext()) {
					SelectionKey sk = it.next();
					//9.判断具体是什么事件准备就绪
					if(sk.isReadable()){ 
						ByteBuffer buf = ByteBuffer.allocate(1024);
						dc.receive(buf);
						buf.flip();
						System.out.println(new String(buf.array(),0,buf.limit()));
						buf.clear();
				}
			}
			//取消键选择器SelectionKey
			it.remove();
		}
	}
}
