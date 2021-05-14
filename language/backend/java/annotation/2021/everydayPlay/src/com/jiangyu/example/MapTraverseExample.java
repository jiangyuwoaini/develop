package com.jiangyu.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**  
 * @ClassName: MapTraverseExample
 * @Description: Map遍历的四种方式
 * @author Jy
 * @date 2021-01-20 11:03:45 
*/  
public class MapTraverseExample {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "孙建军");
		map.put("password", "1314");
//		mapExampleOne(map); //用fore去遍历
//		mapExampleTwo(map); //while循环
//		mapExampleThree(map);
//		mapExampleFour(map);
	}

	private static void mapExampleFour(Map<String, String> map) {
		Iterator iterator = map.entrySet().iterator();
		System.out.println(map.entrySet().size());
		String key;
		String value;
		while(iterator.hasNext()) {
			Map.Entry entry = (Map.Entry)iterator.next();
			key = entry.getKey().toString();
			value = entry.getValue().toString();
			System.out.println("key:"+key+"  value:"+value);
		}
	}

	private static void mapExampleThree(Map<String, String> map) {
		Iterator it = map.keySet().iterator();
		while(it.hasNext()) {
			String key;
			String value;
			key = it.next().toString();
			value = map.get(key);
			System.out.println("key:"+key+"  value:"+value);
		}
	}

	private static void mapExampleTwo(Map<String, String> map) {
		Set set  = map.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>)i.next(); //将i.next强转
			System.out.println("key:"+entry.getKey()+"  value:"+entry.getValue());
		}
	}

	private static void mapExampleOne(Map<String, String> map) {
		for (Map.Entry<String,String> entry : map.entrySet()) {
			System.out.println("key:"+entry.getKey()+"  value:"+entry.getValue());
		}
	}
}
