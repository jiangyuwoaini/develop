package com.example.demo.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.pojo.Building;

@Mapper
public interface BuildingMapper {
	//对建筑数据进行展示
	List<Building> findBuilding(Building bd);


}
