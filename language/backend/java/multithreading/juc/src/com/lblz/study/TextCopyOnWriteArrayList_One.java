package com.lblz.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;



/**
 * @ClassName: TextCopyOnWriteArrayList_One
 * @Description: TODO(CopyOnWriteArrayList 写入并复制,添加多时效率低,每次添加时都会进行复制 开销大。)
 * @author lblz
 * @date 2021年3月27日
 *
 */
public class TextCopyOnWriteArrayList_One {
	public static void main(String[] args) {
		HelloThread helloThread = new HelloThread();
		for (int i = 10; i > 0 ; i--) {
			new Thread(helloThread).start();
		}
	}
}
class HelloThread implements  Runnable{
	//private static List<String> list = Collections.synchronizedList(new ArrayList<String>()); //会出现并发修改异常
	private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();//每次写入时都会复制出一个新的列表 效率低 适合并发迭代 不适合添加多 或者修改多
	
	static {
		list.add("aa");
		list.add("bb");
		list.add("cc");
	}

	@Override
	public void run() {
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
			list.add("aa"); 
		}
	}
}
