package com.jianyu.mapper;

import java.util.List;

import com.jianyu.entity.Person;
import com.jianyu.vo.PersonVo;
/**  
 * @ClassName: PersonMapper
 * @Description: 用接口Mapper的方式去实现CRUD
 * @author Jy
 * @date 2021-02-04 10:05:38 
*/  
public interface PersonMapper {
	Person queryPersonById(Person person);
	void insertPerson(Person person);
	void deleteById(Integer id);
	void updatePersonById(Person person);
	List<Person> queryPersonVo(PersonVo personVo);
}
