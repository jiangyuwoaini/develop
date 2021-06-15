package com.lblz.struts.action.ognl;

import java.util.Comparator;

/**
 * @author lblz
 * @deacription 对对象进行排序
 * @date 2021/6/2 20:33
 **/
public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
