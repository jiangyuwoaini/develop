package com.lblz.dao;



import java.util.List;

import com.lblz.bean.User;
import com.lblz.dao.base.Dao;
import com.lblz.orm.DBSessionFactory;
import com.lblz.orm.DBsession;

/**
 * @ClassName: UserDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author J y
 * @date 2021年3月7日
 *
 */
public class UserDao implements Dao<User>{
	private DBsession session;
	
	public UserDao(DBsession session) {
		this.session = session;
	}
	
	
	public User login(String name,String pass) {
		User us = new User();
		us.setUserName(name);
		us.setPassword(pass);
		us = session.find(us);
		return us;
	}


	@Override
	public int save(User obj){
		if(obj!=null) {
			try {
				return session.save(obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}


	@Override
	public int update(User obj) {
		if(obj!=null) {
			try {
				return session.update(obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}


	@Override
	public int delete(User obj) {
		if(obj!=null) {
			try {
				return session.delete(obj.getClass(),obj.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}


	@Override
	public List<User> findAll() {
		try {
			return session.list(User.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
