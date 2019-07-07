package com.example.demo.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BuildingDao;
import com.example.demo.pojo.Building;
import com.example.demo.service.BuildingService;
@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private BuildingDao us;

	@Override
	public List<Building> findBuilding(Building bd) {
		// TODO Auto-generated method stub
		return us.findBuilding(bd);
	}
}
