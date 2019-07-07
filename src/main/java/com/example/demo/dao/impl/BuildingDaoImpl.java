package com.example.demo.dao.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.example.demo.dao.BuildingDao;
import com.example.demo.mapper.BuildingMapper;
import com.example.demo.pojo.Building;
@Repository
public class BuildingDaoImpl  implements BuildingDao{
	@Autowired
	private BuildingMapper us;

	@Override
	public List<Building> findBuilding(Building bd) {
		// TODO Auto-generated method stub
		return us.findBuilding(bd);
	}
}
