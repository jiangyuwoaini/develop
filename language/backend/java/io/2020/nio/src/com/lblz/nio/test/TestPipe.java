package com.lblz.nio.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;

import org.junit.Test;

/**
 * @ClassName: TestPipe
 * @Description: TODO(测试管道)
 * @author lblz
 * @date 2021年4月4日
 *
 */
public class TestPipe {
	/**
	 * @Title: test1
	 * @Description: TODO(单向通道测试)
	 * @param @throws IOException 参数
	 * @return void 返回类型
	 * @throws
	 */
	@Test
	public void test1() throws IOException {
		Pipe pipe = Pipe.open();//获取管道
		ByteBuffer buf = ByteBuffer.allocate(1024);
		SinkChannel sinkChannel = pipe.sink();
		buf.put("通过单向管道发送数据".getBytes());
		buf.flip();
		sinkChannel.write(buf); //缓存写入通道
		//读取缓存中大数据
		SourceChannel source = pipe.source(); //接收
		buf.flip();
		int len = source.read(buf);
		System.out.println(new String(buf.array(),0,len));
		source.close();
		sinkChannel.close();
	}
}
