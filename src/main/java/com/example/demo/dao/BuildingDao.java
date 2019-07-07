package com.example.demo.dao;

import java.util.List;

import com.example.demo.pojo.Building;



public interface BuildingDao{
	//查询用户的详细数据
	List<Building> findBuilding(Building bd);
	
}
