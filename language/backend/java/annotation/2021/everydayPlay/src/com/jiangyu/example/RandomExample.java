package com.jiangyu.example;

import java.util.Random;

/**  
 * @ClassName: RandomExample
 * @Description: 三种随机数实现练习
 * @author Jy
 * @date 2021-01-21 12:07:18 
*/  
//第一种：new Random()
//第二种：Math.random()
//第三种：currentTimeMillis()
public class RandomExample {
	public static void main(String[] args) {
//		demo01();
//		demo02();
		//至于第三种方法虽然不常用，但是也是一种思路。方法返回从1970年1月1日0时0分0秒（这与UNIX系统有关）
		//到现在的一个long型的毫秒数，取模之后即可得到所需范围内的随机数。
		int max = 100,min=1;
		long randomNum = System.currentTimeMillis();
		int ran = (int)(randomNum%(max - min) +min);
		System.out.println(ran);
	}
	//而第二种方法返回的数值是[0.0,1.0）的double型数值，由于double类数的精度很高，可以在一定程度下看做随机数，
	//借助（int）来进行类型转换就可以得到整数随机数了，代码如下。
	private static void demo02() {
		int max =100,min=1;
		int ran = (int)(Math.random() * (max -min)+min);
		System.out.println(ran);
	}
	//第一种需要借助java.util.Random类来产生一个随机数发生器，也是最常用的一种，构造函数有两个，
	//Random()和Random(long seed)。第一个就是以当前时间为默认种子，第二个是以指定的种子值进行。
	//产生之后，借助不同的语句产生不同类型的数。种子就是产生随机数的第一次使用值,机制是通过一个函数,
	//将这个种子的值转化为随机数空间中的某一个点上,并且产生的随机数均匀的散布在空间中。
	//以后产生的随机数都与前一个随机数有关。以代码为例。
	private static void demo01() {
		Random r = new Random();
		for(int i =0;i<5; i++) {
			int nextInt = r.nextInt(100);
			System.out.println(nextInt);
		}
	}
}
