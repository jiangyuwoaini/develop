package com.example.demo.service;


import java.util.List;

import com.example.demo.pojo.Building;

public interface BuildingService {
	//查询用户的详细数据
	List<Building> findBuilding(Building bd);
}
