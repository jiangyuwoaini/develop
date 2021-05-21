package com.lblz.nio.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

/*一、通道(Channel) :用于源节点与目标节点的连接。在Java NIO中负责缓冲区中数据的传输。Channel本身不存储数据,因此需要配合缓冲区进行传输.
 *二、通道的主要实现类 
 * 	 java.nio.channels.Channel接口:
 * 		|-Filechannel
 * 		|-SocketChannel 
 * 		|-ServerSocketChannel
 * 		|-DatagramChannel
 *三、获取通道
 *	 1.java针对支持通道类提供了getChannel()方法
 *		本地IO:
 *		FileInputStream/FileOutputStream
 *		RandomAccessFile
 *		网络IO:
 *		SocketServer
 *		SocketDatagram
 *		Socket
 *	 2,在JDK 1.7中的NIO.2针对各个通道提供了静态方法open()"
 *	 3,在JDK 1.7中的NIO.2的Files工具类的newByteChannel()
 *四、通道之间的数据传输
 *	 transferFrom()
 *	 transferTo()
 *无、分散(Scatter)与聚集(Gather) 
 *	 分散读职(Scattering Reads) :将通道中的数据分散到多个缓冲区中
 *	 聚集写入(Gathering Writes) :将多个缓冲区中的数据聚集到通道中
 *六、字符集:Charset 
 *	 编码:字符串->字节数组
 *	 解码:字节数组->字符串
 * */
public class TestChannel_One {
	public static void main(String[] args) throws Exception {
//		test_One();
//		test_Two();
//		test_Three();
//		test_Four();
//		test_Five();
		test_Six();
		
	}
	
	/**
	 * @Title: test_Six
	 * @Description: TODO(nio的编码和解码)
	 * @throws CharacterCodingException 参数
	 * @return void 返回类型
	 * @throws
	 */
	public static void test_Six() throws CharacterCodingException {
		Charset csOne = Charset.forName("GBK");
		CharsetEncoder ceEncoder = csOne.newEncoder();//获取编码器
		CharsetDecoder cdDecoder = csOne.newDecoder();//获取解码器
		CharBuffer charBuffer = CharBuffer.allocate(1024); //缓冲区？ 我也不确定... 是的 是缓冲区
		charBuffer.put("我会为自己感到骄傲嘛？我会一直这样萎靡下去嘛？我会不会为成为我自己想成为的人？我能不能控制好我自己？");
		charBuffer.flip();//切换为写模式
		ByteBuffer byteBuffer = ceEncoder.encode(charBuffer);//编码
		for(int i = 0; i < 100; i++) {
			System.out.println(byteBuffer.get());
		}
		byteBuffer.flip();
		CharBuffer charBuffer2 = cdDecoder.decode(byteBuffer);//解码
		System.out.println(charBuffer.toString());
		System.out.println("-------------------------------------------");
		Charset csTwo = Charset.forName("GBK"); //用gdb编码用utf-8解码
		byteBuffer.flip();//搞不得为什么写两次
		CharBuffer decode = csTwo.decode(byteBuffer); //為什麼CharBuffer也能调用decode? 看源码
		System.out.println(decode.toString());
	}
	
	/**
	 * @Title: test_Five
	 * @Description: TODO(获取所有编码解码格式)
	 * @param  参数
	 * @return void 返回类型
	 * @throws
	 */
	public static void test_Five() {
		SortedMap<String, Charset> map = Charset.availableCharsets(); //获取所有可编码解码的格式
		Set<Entry<String, Charset>> set = map.entrySet();
		for (Entry<String, Charset> entry : set) {
			System.out.println(entry.getKey() + " = "+entry.getValue());
		}
	}

	/**
	 * @Title: test_four
	 * @Description: TODO(分散和聚集)
	 * @param @throws Exception 参数
	 * @return void 返回类型
	 * @throws
	 */
	private static void test_Four() throws Exception {
		RandomAccessFile inRaf = new RandomAccessFile("笔记.txt", "rw");
		
		//1.获取通道
		FileChannel channel = inRaf.getChannel();
		//2.分配指定大小的缓冲区 非直接缓冲区
		ByteBuffer buffOne = ByteBuffer.allocate(1024); //设置缓冲区的字节码大小
		ByteBuffer buffTwo = ByteBuffer.allocate(1024);//设置缓冲区的字节码大小
		//3.分散读取
		ByteBuffer[] bufs = {buffOne,buffTwo};
		channel.read(bufs);//通道对缓冲区数据进行读取
		for (ByteBuffer byteBuffer : bufs) {
			byteBuffer.flip(); //切换成写模式
		}
		System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
		System.out.println("----------------------------------");
		System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));
		//4.聚集写入
		RandomAccessFile rafs = new RandomAccessFile("笔记本1.txt", "rw");
		FileChannel fileChannel = rafs.getChannel(); //再次获取通道
		fileChannel.write(bufs); //从通道写入缓冲区数据
	}

	
	/**
	 * @throws IOException 
	 * @Title: test_three
	 * @Description: TODO(通道之间的数据传输)
	 * @param  参数
	 * @return void 返回类型
	 * @throws
	 */
	private static void test_Three() throws IOException{
		//通道相当于是io流,可获得文件的流,写操作要用缓冲区
		FileChannel inChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.READ);//获取写通道 
		FileChannel outChannel = FileChannel.open(Paths.get("4.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE); //设置写通道读写模式 ,图片设置创建
		inChannel.transferTo(0, inChannel.size(), outChannel);
		
		inChannel.close();
		outChannel.close();
	}

	/**
	 * @Title: test_two
	 * @Description: TODO(使用过直接缓冲区完成文件的复制(内存映射文件))
	 * @param  IOException 参数
	 * @return void 返回类型
	 * @throws
	 */
	private static void test_Two() throws IOException {
		//通道相当于是io流,可获得文件的流,写操作要用缓冲区
		FileChannel inChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.READ);//获取写通道 
		FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE); //设置写通道读写模式 ,图片设置创建
		//内存杨舍文件 只有myteBuffer支持
		MappedByteBuffer inMappBuffer = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMappBuffer = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size()); //设置读写模式
		//直接对缓冲区进行数据的读写操作
		byte[] dst = new byte[inMappBuffer.limit()]; 
		inMappBuffer.get(dst); //读缓冲区获取字节流
		outMappBuffer.put(dst); //将字节流加入写缓冲区去
		inChannel.close(); //关闭读通道
		outChannel.close(); //关闭写通道
	}
	
	/**
	 * @Title: test_one
	 * @Description: TODO(非直接缓冲区完成图片复制)
	 * @param  FileNotFoundException
	 * @param  IOException 参数
	 * @return void 返回类型
	 * @throws
	 */
	private static void test_One() throws FileNotFoundException, IOException {
		//1.利用通道完成稿文件的复制
		FileInputStream fis = new FileInputStream("1.jpg");
		FileOutputStream fos = new FileOutputStream("2.jpg");
		//2.获取通道
		FileChannel fisChannel = fis.getChannel();
		FileChannel fosChannel = fos.getChannel();
		//3.分配指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		//4.将通道中的数据存入到缓冲区中
		while(fisChannel.read(buf) != -1) {
			buf.flip();//切换读数据模式
			//5.将缓冲区的数据写入到通道中
			fosChannel.write(buf);
			buf.clear();
		}
		fosChannel.close();
		fisChannel.close();
		fos.close();
		fis.close();
	}
}
