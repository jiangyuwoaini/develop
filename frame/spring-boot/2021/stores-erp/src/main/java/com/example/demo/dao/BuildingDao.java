package com.example.demo.dao;

import java.util.List;

import com.example.demo.pojo.Building;



public interface BuildingDao{
	//查询用户的详细数据
	List<Building> findBuilding(Building bd);
	//根据条件查询数据
	List<Building> findBuildingone(Building bd);
	//删除一条数据
	int deleteBuilding(int id);
	//查询用户的详细信息
	Building findBuildings(Building building);
	//修改用户信息
	int updateBuilding(Building building);
	//添加用户
	int insertBuilding(Building building);
}
