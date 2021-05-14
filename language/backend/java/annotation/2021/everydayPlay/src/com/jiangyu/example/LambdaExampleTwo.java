package com.jiangyu.example;

/**  
 * @ClassName: LambdaExampleTwo
 * @Description: lambda表达式
 * @author Jy
 * @date 2021-01-21 11:33:44 
*/  
public class LambdaExampleTwo{
	public static void main(String[] args) {
		LambdaClass lambdaClass = new LambdaClass();
		lambdaClass.run();
	}
}
interface LambdaExampleThree {
	String eat(String str);
}
class LambdaClass{
	/*
	* lambda使用条件；
	* 1.使用lambda需要有接口，并且接口只有一个抽象方法。
	* 2.必须要有上下文环境，才能推导出类型。
	*
	*
	* lambda和匿名内部类区别：
	* 所需类型不同
	* 匿名内部类：可以是接口，也可以是抽象类，还可以是具体类
	* Lambda表达式：只能是接口
	* 使用限制不同
	* 如果接口中有且仅有一个抽象方法，可以使用Lambda表达式，也可以使用匿名内部类
	* 如果接口中多于一个抽象方法，只能使用匿名内部类，而不能使用Lambda表达式
	* 实现原理不同
	* 匿名内部类：编译之后，产生一个单独的.class字节码文件
	* Lambda表达式：编译之后，没有一个单独的.class字节码文件。对应的字节码会在运行的时候动态生成
	* */

	public void run() {
		//匿名内部类简写法
		useEnable(new LambdaExampleThree() {
			@Override
			public String eat(String str) {
				// TODO Auto-generated method stub
				System.out.println(str+"匿名内部类吃的方法");
				return str;
			}
		},"苹果");
		//lambda简写
		useEnable((String str)->{
			System.out.println(str+"匿名内部类吃的方法");
			return str;
		},"苹果");
		
		useEnable(s->{
			System.out.println(s+"匿名内部类吃的方法");
			return s;
		},"苹果");
		//去掉 类型、括号、中括号写法
		LambdaExampleThree e=s->s ;
		useEnable(e, "橘子");
	}
	public void useEnable(LambdaExampleThree e,String s) {
		String ss = e.eat(s);
		System.out.println(ss);
	}
}