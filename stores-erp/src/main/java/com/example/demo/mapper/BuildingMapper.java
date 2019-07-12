package com.example.demo.mapper;


import java.util.List;

import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.Building;

@Mapper
public interface BuildingMapper {
	//对建筑数据进行展示
	List<Building> findBuilding(Building bd);
	//根据条件查询自己想要的数据
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
