package com.jianyu.test;
/**  
 * @ClassName: TestMybatis
 * @Description: 测试mybatis的使用
 * 				 方式1:直接调用嘛  方式2:调用接口方法 方式三
 * @author Jy
 * @date 2021-02-02 01:48:36 
*/

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;import org.apache.ibatis.type.LocalDateTimeTypeHandler;
import org.junit.Test;

import com.jianyu.entity.Person;
import com.jianyu.mapper.PersonMapper;
import com.jianyu.param.CustomPerson;
import com.jianyu.vo.PersonVo;

import sun.util.resources.LocaleData;


public class TestMybatis {
	public SqlSessionFactory getFactory() {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}
	@Test
	public void testInsert() {
		SqlSessionFactory sqlSessionFactory = this.getFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);//利用mapped接口的方式去做crud
		Person person = new Person();
		person.setSex(true);
		person.setAge(20);
		person.setName("功成名就21111111");
		person.setAddress("world2111111111111");
		person.setBirthday(LocalDateTime.now());
		//int insert = sqlSession.insert("personMapper.insertPerson",person);
		personMapper.insertPerson(person);
		System.out.println("id:"+person.getId());
		sqlSession.commit();
		sqlSession.close();
	}
	@Test
	public void testQueryPersonById() {
		Person person = new Person();
		person.setId(13477945);
//		person.setName("功成名就21111111");
		SqlSessionFactory sqlSessionFactory = this.getFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Person Person = sqlSession.selectOne("com.jianyu.mapper.PersonMapper.queryPersonById",person);
		System.out.println(Person);
	}
	@Test
	public void testDeletePersonById() {
		SqlSessionFactory sqlSessionFactory = this.getFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int Person = sqlSession.delete("com.jianyu.mapper.PersonMapper.deleteById",1);
		System.out.println(Person);
		sqlSession.commit();
		sqlSession.close();
	}
	@Test
	public void testUpdatePersonById() {
		SqlSessionFactory sqlSessionFactory = this.getFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Person person = new Person();
		person.setId(2);
		person.setSex(true);
		person.setAge(20);
		person.setName("功成名就22222");
		person.setAddress("world222222222222");
		person.setBirthday(LocalDateTime.now());
		int Person = sqlSession.delete("com.jianyu.mapper.PersonMapper.updatePersonById",person);
		System.out.println(Person);
		sqlSession.commit();
		sqlSession.close();
	}
	@Test 
	public void testQueryPersonVo() {
		PersonVo personVo = new PersonVo();
		CustomPerson customPerson = personVo.getCustomPerson();
		if(customPerson == null) {
			customPerson = new CustomPerson();
		}
		customPerson.setName("功成名就2");
		customPerson.setAddress("world2");
		SqlSessionFactory sqlSessionFactory = this.getFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
		List<Person> queryPersonVo = personMapper.queryPersonVo(personVo);
		System.out.println(queryPersonVo);
		sqlSession.commit();
		sqlSession.close();
	}
}
