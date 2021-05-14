package com.jiangyu.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jiangyu.beans.Employee;
import com.jiangyu.mapper.EmployeeMapper;

public class TestMp {
	private final static Logger logger = LoggerFactory.getLogger(TestMp.class.getName());
	private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
	EmployeeMapper employeeMapper = ioc.getBean("employeeMapper",EmployeeMapper.class);
	public static void main(String[] args){
		TestMp mp = new TestMp();
//		System.out.println(mp.testCommontInsert());
//		System.out.println(mp.TestCommonUpdate());
//		mp.testCommonSelect();
		mp.testEntityWrapperSelect();
	}
	//条件构造器 修改操作
	@Test
	public void testEntityWrapperUpdate() {
		Employee employee = new Employee();
		employee.setAge(18);
		employee.setLastName("jiangjun");
		employee.setGender(0);
		employeeMapper.update(employee, new EntityWrapper<Employee>()
				.eq("last_name", "tom")
				.eq("age", 44));
	}
	@Test
	public void testEntityWrapperDelete() {
		employeeMapper.delete(new EntityWrapper<Employee>()
				.eq("last_name", "Tom")
				.eq("age", 22)
				);
	}
	//条件构造器 查询操作
	@Test
	public void testEntityWrapperSelect() {
		//我们需要分页查询tbl_employee表中,年龄在18~50之间且性别为男且姓名为mp的所有用户
//		List<Employee> emps = employeeMapper.selectPage(new Page<Employee>(1,2),new EntityWrapper<Employee>()
//				.between("age", 1, 30)
//				.eq("last_name", "mp")
//				.eq("gender", 1)
//				);
//		System.out.println(emps);
		//使用Condition查询与上面一样的效果
		List<Employee> emps = employeeMapper.selectPage(new Page<Employee>(1,2),
				Condition.create()
				.between("age", 1, 30)
				.eq("last_name", "mp")
				.eq("gender", 1)
				);
       	System.out.println(emps);
		//查询tbl_employee表中 性别为女,且名字带有'老师' 或者邮箱带有'a'
//		employeeMapper.selectList(new EntityWrapper<Employee>()
//				.eq("gender", 0)
//				.like("last_name", "老师")
//				//.or()//sql:(last_name like ?or meail like ?)
//				.orNew() //sql:(last_name like?) or (email like ?)
//				.like("email", "a"));
		
		//查询性别为女的,根据age进行排序(asc/desc),简单分页
//		employeeMapper.selectList(new EntityWrapper<Employee>()
//				.eq("gender", 0)
//				.orderBy("age")
//				//.orderDesc(Arrays.asList(new String[] {"age"})) //倒序排序
//				.last("desc limit 1,3")//末尾拼接
//				);
	}
	@Test
	public void testCommonitDelete() {
		//1.根据id进行删除
//		Integer result = employeeMapper.deleteById(13);
//		System.out.println(result);
		//2.根据条件进行删除
//		Map<String, Object> map = new HashMap<>();
//		map.put("last_name", "Mp");
//		Integer result = employeeMapper.deleteByMap(map);
//		System.out.println(result);
		//3.批量删除
		ArrayList<Integer> idList = new ArrayList<Integer>();
		idList.add(1);
		idList.add(2);
		Integer result  = employeeMapper.deleteBatchIds(idList);
		System.out.println(result);
	}
	@Test
	public int testCommontInsert()  {
		Integer result = 0;
		try {
			
			Employee employee = new Employee();
			employee.setLastName("mp");
			employee.setEmail("1824@163.com");
			employee.setGender(1);
			employee.setAge(22);
			//result = employeeMapper.insert(employee);
			result = employeeMapper.insertAllColumn(employee);
			//获取当前数据在数据库中主键值
			Integer id = employee.getId();
			System.out.println("key:"+id);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Test
	public void testCommonSelect() {
		//初始化对象
//		Employee employee = employeeMapper.selectById(7);
//		System.out.println(employee);
		//2.用过多个列进行查询 id + lastName
//		Employee employee = new Employee();
//		employee.setId(6);
//		employee.setLastName("马里亚老师");
//		Employee result = employeeMapper.selectOne(employee);
//		System.out.println(result);
		//3.通过多个id进行查询
//		List<Integer> idList = new ArrayList<Integer>();
//		idList.add(4);
//		idList.add(5);
//		idList.add(7);
//		idList.add(1);
//		List<Employee> emps = employeeMapper.selectBatchIds(idList);
//		System.out.println(emps);
		//通过Map封装条件查询
//		Map<String,Object> columnMap = new HashMap<String, Object>();
//		columnMap.put("last_name","Tom");
//		columnMap.put("gender", 1);
//		List<Employee> empList = employeeMapper.selectByMap(columnMap);
//		logger.info("查询成功");
//		System.out.println(empList);
		//分页查询
		List<Employee> emps = employeeMapper.selectPage(new Page<Employee>(3,2), null);
		for (Employee employee : emps) {
			System.out.println(employee.getLastName());
		}
	}
	@Test
	public int TestCommonUpdate() {
		Integer result = 0;
		try {
			//初始化对象
			Employee employee = new Employee();
			employee.setId(6);
			employee.setLastName("马里亚老师");
			employee.setEmail("mly@sina.com");
			employee.setAge(33);
			//result = employeeMapper.updateById(employee);
			  result = employeeMapper.updateAllColumnById(employee);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Test
	public void testDatasource() throws SQLException {
		DataSource ds = ioc.getBean("dataSource",DataSource.class);
		Connection conn = ds.getConnection();
		System.out.println(conn);
	}
}
