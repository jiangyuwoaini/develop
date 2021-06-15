package com.lblz.struts.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lblz
 * @deacription
 * @date 2021/6/7 23:31
 **/
public class Dao {
    private static Map<Integer,Employee> emps =new HashMap<>();
    static {
        emps.put(1001,new Employee(1001,"AA","aa","lblz1@163.com"));
        emps.put(1002,new Employee(1002,"AA","aa","lblz2@163.com"));
        emps.put(1003,new Employee(1003,"AA","aa","lblz3@163.com"));
        emps.put(1004,new Employee(1004,"AA","aa","lblz4@163.com"));
        emps.put(1005,new Employee(1005,"AA","aa","lblz5@163.com"));
    }

    public List<Employee> getEmployees(){
        return new ArrayList<>(emps.values());
    }

    public void deleted(Integer deletedId){
        emps.remove(deletedId);
    }

    public void save(Employee emp){
        long timeMillis = System.currentTimeMillis();
        emp.setEmployeeId((int)timeMillis);
        emps.put(emp.getEmployeeId(),emp);
    }

    public Employee edit(Integer deletedId){
        Employee employee = emps.get(deletedId);
        return employee;
    }

    public void update(Employee emp){
        emps.put(emp.getEmployeeId(),emp);
    }
}
