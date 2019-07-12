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

	@Override
	public List<Building> findBuildingone(Building bd) {
		// TODO Auto-generated method stub
		return us.findBuildingone(bd);
	}

	@Override
	public int deleteBuilding(int id) {
		// TODO Auto-generated method stub
		return us.deleteBuilding(id);
	}

	@Override
	public Building findBuildings(Building building) {
		// TODO Auto-generated method stub
		return us.findBuildings(building);
	}

	@Override
	public int updateBuilding(Building building) {
		// TODO Auto-generated method stub
		return us.updateBuilding(building);
	}

	@Override
	public int insertBuilding(Building building) {
		// TODO Auto-generated method stub
		return us.insertBuilding(building);
	}
}
