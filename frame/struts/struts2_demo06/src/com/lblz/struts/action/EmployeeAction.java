package com.lblz.struts.action;

import com.lblz.struts.pojo.Dao;
import com.lblz.struts.pojo.Employee;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.interceptor.RequestAware;

import java.util.Map;

/**
 * @author lblz
 * @deacription
 * @date 2021/6/7 23:35
 **/
public class EmployeeAction implements RequestAware, ModelDriven<Employee>, Preparable { //PreparableIntercptor拦截器
    private Dao dao = new Dao();
    private Map<String,Object> requestMap;
    private Employee employee;

    public String list(){
        requestMap.put("emps",dao.getEmployees());
        return "list";
    }

    public String deleted(){
        dao.deleted(employee.getEmployeeId());
        return "success";
    }

    public String edit(){
        Employee employee = dao.edit(this.employee.getEmployeeId());
        employee.setEmail(employee.getEmail());
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        return "edit";
    }

    public String update(){
        Employee employee = dao.edit(this.employee.getEmployeeId());
        employee = this.employee;
        return "update";
    }

    public String save(){
//        dao.save(new Employee(null,employee.getFirstName(),employee.getLastName(),employee.getEmail()));
        dao.save(employee);
        return "success";
    }

    public void prepareSave(){
        employee = new Employee();
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        requestMap = map;
    }

    @Override
    public Employee getModel() {
        return employee = new Employee();
    }

    @Override
    public void prepare() throws Exception {
        System.out.println("prepare...");
    }
}
