package com.lblz.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

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
 * @ClassName: TestBlockingNIO_Two
 * @Description: TODO(非阻塞式 传送数据)
 * @author lblz
 * @date 2021年4月3日
 *
 */
public class TestNonBlockingNIO_One {
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
		scChannel.configureBlocking(false);		//2.切换非阻塞模式
		ByteBuffer buf = ByteBuffer.allocate(1024); //3.分配缓存大小
		buf.put(new Date().toString().getBytes());//4.发送数据给服务端
		buf.flip();
		scChannel.write(buf);
		buf.clear();
		scChannel.close();//5.关闭通道
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
	public void server() throws IOException {
		ServerSocketChannel ssChannel = ServerSocketChannel.open(); //1.获取通道
		ssChannel.configureBlocking(false);		//2.切换非阻塞模式
		ssChannel.bind(new InetSocketAddress(9898));//3.绑定连接 默认应该是本地ip
		//4.获取选择器
		Selector selector = Selector.open();
		//5.将通道注册到选择器上,并指定"监听接收事件"
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		//6.轮询式的获取选择器上已经"准备就绪"的
		while(selector.select() > 0) {
			 Iterator<SelectionKey> it = selector.selectedKeys().iterator(); //7.获取当前选择器所有注册的选择键 选择键的意思就是已就绪的监听事件
			//8.获取准备就绪的事件
				while (it.hasNext()) {
					SelectionKey sk = it.next();
					//9.判断具体是什么事件准备就绪
					if(sk.isAcceptable()) {
						//10.若"接收就绪",获取客户端连接
						SocketChannel sChannel = ssChannel.accept();
						//11.切换非阻塞模式
						sChannel.configureBlocking(false);
						//12.将该通道注册到选择器上
						sChannel.register(selector, SelectionKey.OP_READ);
					}else if(sk.isReadable()){ //13.获取当前选择器上"读就绪"状态的通道
						SocketChannel sChannel = (SocketChannel)sk.channel();
						//14.读取数据
						ByteBuffer buf = ByteBuffer.allocate(1024);
						int len = 0;
						while((len = sChannel.read(buf)) != -1) {
							buf.flip();
							System.out.println(new String(buf.array(),0,len));
							buf.clear();
						}
					}
					//取消键选择器SelectionKey
					it.remove();
			}
		}
	}
}
