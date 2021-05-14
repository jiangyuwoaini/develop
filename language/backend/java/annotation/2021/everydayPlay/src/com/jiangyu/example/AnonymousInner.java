package com.jiangyu.example;


import java.text.Annotation;



/**  
 * @ClassName: AnonymousInner
 * @Description: TODO(匿名内部类练习:)
 * @author Jy
 * @date 2021-01-18 01:31:08 
*/  
public class AnonymousInner {
	interface HelloWorld{
		public void greet();
		public void greetSomeone(String someone);
	}
	//案例一，实现接口的匿名类：
	public void sayHello() {
		//局部类EnglishGreeting实现HelloWorlde接口
		class EngLiGreeting implements HelloWorld{
			String name = "world";
			@Override
			public void greet() {
				System.out.println("world");
			}

			@Override
			public void greetSomeone(String someone) {
				name =someone;
				System.out.println("Hello"+name);
			}
		}
		HelloWorld englishGreeting = new EngLiGreeting();
		//2.匿名内部类实现helloWorld接口
		HelloWorld frenchGreeting = new HelloWorld() {
			String name ="tout le monde";
			@Override
			public void greetSomeone(String someone) {
				// TODO Auto-generated method stub
				name = someone;
				System.out.println("salut" + name);
			}
			
			@Override
			public void greet() {
				// TODO Auto-generated method stub
				greetSomeone("tou le monde");
			}
		};
		//3.匿名类实现HelloWorld接口
		HelloWorld spanishGreeting = new HelloWorld() {
			String name = "mundo";
			@Override
			public void greetSomeone(String someone) {
				// TODO Auto-generated method stub
				name = someone;
				System.out.println("Hola, "+name);

			}
			
			@Override
			public void greet() {
				// TODO Auto-generated method stub
				greetSomeone("mundo");
			}
		};//匿名类是一个表达式，因此在定义的最后用分号";"结束。
		englishGreeting.greet();
		frenchGreeting.greetSomeone("Fred");
		spanishGreeting.greet();
	}
	//案例二，匿名子类（继承父类）：
static class AnimalTest {
	private final String ANIMAL ="动物";
	public void accessTest() {
		System.out.println("匿名内部类访问其他方法");
	}
	class Animal{ //搞不懂为啥用不了 extends AnimalTest
		private String name;
		public Animal(String name) {
			this.name = name;
		}
		public void printAnimalName() {
			System.out.println(name);
		}
		//鸟类,匿名子类,继承自Animal类,可以覆写父类方法
	}
	Animal bird = new Animal("不顾鸟") {

		@Override
		public void printAnimalName() {
			accessTest(); //访问外类成员
			System.out.println(ANIMAL);//访问外部final修饰变量
			super.printAnimalName();
		}
	};
	public void print() {
		bird.printAnimalName();
	}
}
//案例一，内嵌类的属性屏蔽：
//3.访问作用域内的局部变量、定义和访问匿名内部类成员
//static class ShadowTest {
//	public int x = 0;
//	class FirstLevel{
//		public int x = 1;
//		void methodFirstLevel(int x) {
//			System.out.println("x ="+x);
//			System.out.println("this.x ="+this.x);
//			System.out.println("ShadowTest.this.x ="+ShadowTest.this.x);
//		}
//	}
//}
//案例二，匿名内部类的属性屏蔽：
//(5)、匿名内部类可以有常量属性（final修饰的属性）；
//(6)、匿名内部内中可以定义属性，如上面代码中的代码:private int x = 1;
//(7)、匿名内部内中可以可以有额外的方法（父接口、类中没有的方法）;
//(8)、匿名内部内中可以定义内部类；
//(9)、匿名内部内中可以对其他类进行实例化。
static class ShadowTest{
	public int x = 0;
	interface FirstLevel{
		void methodInFirstLevel(int x);
	}
	FirstLevel firstLevel = new FirstLevel() {
		public int x = 1;
		
		//public static String  str = "Hello World";//编译错误
		//public static void ad() {}//编译错误
		public static final String finalStr ="Hello world";//正常
		public void extranMethod() {}//正常
		@Override
		public void methodInFirstLevel(int x) {
			// TODO Auto-generated method stub

		}
	};
}
//public class AnonymousInnerOne extends Application {
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Hello World!");
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();
//    }
//}



//public class CustomTextFieldSample extends Application {
//
//    final static Label label = new Label();
//
//    @Override
//    public void start(Stage stage) {
//        Group root = new Group();
//        Scene scene = new Scene(root, 300, 150);
//        stage.setScene(scene);
//        stage.setTitle("Text Field Sample");
//
//        GridPane grid = new GridPane();
//        grid.setPadding(new Insets(10, 10, 10, 10));
//        grid.setVgap(5);
//        grid.setHgap(5);
//
//        scene.setRoot(grid);
//        final Label dollar = new Label("$");
//        GridPane.setConstraints(dollar, 0, 0);
//        grid.getChildren().add(dollar);
//
//        final TextField sum = new TextField() {
//            @Override
//            public void replaceText(int start, int end, String text) {
//                if (!text.matches("[a-z, A-Z]")) {
//                    super.replaceText(start, end, text);
//                }
//                label.setText("Enter a numeric value");
//            }
//
//            @Override
//            public void replaceSelection(String text) {
//                if (!text.matches("[a-z, A-Z]")) {
//                    super.replaceSelection(text);
//                }
//            }
//        };
//
//        sum.setPromptText("Enter the total");
//        sum.setPrefColumnCount(10);
//        GridPane.setConstraints(sum, 1, 0);
//        grid.getChildren().add(sum);
//
//        Button submit = new Button("Submit");
//        GridPane.setConstraints(submit, 2, 0);
//        grid.getChildren().add(submit);
//
//        submit.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                label.setText(null);
//            }
//        });
//
//        GridPane.setConstraints(label, 0, 1);
//        GridPane.setColumnSpan(label, 3);
//        grid.getChildren().add(label);
//
//        scene.setRoot(grid);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
interface Printable{
	void print();
}

	public static void main(String[] args) {
		//案例一，实现接口的匿名类：
//		AnonymousInner anonymousInner = new AnonymousInner();
//		anonymousInner.sayHello();
		//输出 hello/salut Fred/Hola mundo
		//案例二，匿名子类（继承父类）：
//		AnimalTest animalTest = new AnimalTest();
//		animalTest.print();
		//输出 匿名内部类访问其他方法/动物/不顾鸟  (为什么不是两个布谷鸟,是因为printAnimalName只是调用了这个方法)
		/*
		 * 3.访问作用域内的局部变量、定义和访问匿名内部类成员 匿名内部类与局部类对作用域内的变量拥有相同的的访问权限。
		 * (1)、匿名内部类可以访问外部内的所有成员；
		 * (2)、匿名内部类不能访问外部类未加final修饰的变量（注意：JDK1.8即使没有用final修饰也可以访问）；
		 * (3)、属性屏蔽，与内嵌类相同，匿名内部类定义的类型（如变量）会屏蔽其作用域范围内的其他同名类型（变量）：
		 */
		// 案例一，内嵌类的属性屏蔽：
//		ShadowTest sTest = new ShadowTest();
//		ShadowTest.FirstLevel f1 = sTest.new FirstLevel();
//		f1.methodFirstLevel(23);
//		//输出:x = 23 this.x = 1 ShadowTest.this.x = 0
		// 案例二，匿名内部类的属性屏蔽：
		
		//============================================================================
		//与类不同，无法直接 new 创建接口对象，但是可以声明接口类型的变量，引用实现了接口的类对象。
		Printable obj = new Printable() { //其实这就是创建了个匿名内部类
			
			@Override
			public void print() {
				// TODO Auto-generated method stub
				System.out.println("hello");
			}
		};
		obj.print();
	}
}
