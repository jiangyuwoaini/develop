package com.jiangyu.example;




/**  
 * @ClassName: otherExample
 * @Description: 其他的练习
 * @author Jy
 * @date 2021-01-21 10:35:23 
*/  
public class otherExample {
	public static void main(String[] args) {
//		example01();
		printArray(47,(float)3.14,11.11);
		printArray("one","tow","three");
		printArray(new otherExample(),new otherExample(),new otherExample());
		f(1,"str","srt");
		
		
	}
	

	/**  
	 * @Title: f
	 * @Description: TODO(可变参数) 可变参数必须只有一个
	 * @param required
	 * @param trailing
	 * @author Jy
	 * @date 2021-02-22 06:15:44 
	 */  
	private static void f(int required, String... trailing) {
        System.out.print("required: " + required + " ");
        for (String s: trailing) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
	/**  
	 * @Title: printArray
	 * @Description: TODO(可变参数)
	 * @param args
	 * @author Jy
	 * @date 2021-02-22 05:24:13 
	 */  
	private static void printArray(Object... args) {
		for (Object object : args) {
			System.out.println(object +" ");
		}
		System.out.println();
	}
	
	
	/**  
	 * @Title: example01
	 * @Description: TODO(java位运算符:
	 * 								     位与运算符解释->
	 * 											两个数都转为二进制，然后从高位开始比较，如果两个数都为1则为1，否则为0
	 * 								     位或运算符解释->
	 * 											两个位只要有一个为1，那么结果就是1，否则就为0，下面看一个简单的例子。
	 * 								     位非运算符解释->			
	 * 											如果位为0，结果是1，如果位为1，结果是0. 一般是需要补码的
	 * 								     位异或运算符解释->
	 * 											两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1。
	 * )		
	 * @author Jy
	 * @date 2021-02-20 09:26:22 
	 */  
	private static void example01() {
		int a = 121;
		System.out.println("A的值:"+Integer.toBinaryString(a));
		int b = 122;
		System.out.println("B的值:"+Integer.toBinaryString(b));
		System.out.println("位与运算符:"+(a&b));//1111000
		System.out.println("位与运算符二进制转换十进制数:"+Integer.parseInt("1111000",2));
		System.out.println("位或运算符:"+(a|b));//1111011
		System.out.println("位或运算符二进制转换十进制数:"+Integer.parseInt("1111011",2));
		System.out.println("位非运算符:"+(~a));//00000000 00000000 00000000 00000101
		System.out.println("位非运算符二进制转换十进制数:"+Integer.parseInt("011001",2));
		System.out.println("位异或运算符:"+(a^b));//0000011
		System.out.println("位异或运算符二进制转换十进制数:"+Integer.parseInt("0000011",2));
	}
}
