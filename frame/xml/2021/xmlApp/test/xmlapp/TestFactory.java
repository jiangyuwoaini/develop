package xmlapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.jiangyu.pojo.User;
import com.jiangyu.xmlparser.BeanInfo;
import com.jiangyu.xmlparser.FactoryBuilder;
import com.jiangyu.xmlparser.FactoryBuilder.Factory;





public class TestFactory {

	
	@Test
	public void test() {
		//String xmlPath = TestFactory.class.getClassLoader().getResource("beans.xml").getFile();
		String xmlPath = "D:\\Program Files\\eclipse\\CodePath\\xmlApp\\resource\\beans.xml";
		FactoryBuilder builder = FactoryBuilder.getInstance(xmlPath);
		Factory factory = builder.newFactory();
		User user = (User) factory.getBean("bossId");
		System.out.println(user);
	}
}
