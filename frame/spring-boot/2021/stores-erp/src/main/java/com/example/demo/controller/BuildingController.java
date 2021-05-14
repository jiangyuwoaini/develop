package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.pojo.Building;
import com.example.demo.service.BuildingService;

@Controller
public class BuildingController {
	@Autowired
	private BuildingService bs;
	
	@RequestMapping("/wjy")
	public String HyperLogLogCommands() {
		return "redirect:/Test2/findListBs";
	}
	@RequestMapping("/Test2/findListBs")
	public String findBuilding(Building building,Model mo) {
		List<Building> blist = bs.findBuilding(building);
		if(blist!=null) {
			mo.addAttribute("msg", "仓库数据查询成功~");
			mo.addAttribute("list", blist);
			return "NewFile";
		}else {
			mo.addAttribute("msg", "要命啦,出bug啦，救命啊");
			return "error";
		}
	}
	
	@RequestMapping("/Test2/findListBsones")
	public String findBuildingJus(Building building,Model mo,@RequestParam("bid") String bid,@RequestParam("bwidth")
	String bwidth,@RequestParam("bsid") String bsid,@RequestParam("btime") String btime) {
		building.setBid(bid);
		building.setBsid(bsid);
		building.setBwidth(bwidth);
		building.setBtime(btime);
		List<Building> blist = bs.findBuildingone(building);
		if(blist!=null) {
			mo.addAttribute("msg", "仓库数据查询成功~");
			mo.addAttribute("list", blist);
			return "NewFile";
		}else {
			mo.addAttribute("msg", "要命啦,出bug啦，救命啊");
			return "error";
		}
	}
	@RequestMapping("/Test2/deleteBuilding/{id}")
	public String delBuilding(Model mo,@PathVariable("id")int id) {
		int deleteBuilding = bs.deleteBuilding(id);
		if(deleteBuilding>0) {
			mo.addAttribute("msg","数据删除成功~");
			return "redirect:/Test2/findListBs";
		}else {
			mo.addAttribute("msg", "要命啦,出bug啦，救命啊");
			return "error";
		}
	}
	
	@RequestMapping("/Test2/finBuildingos/{id}")
	public String findBuildingOs(Building bd,@PathVariable("id")int id,Model mo) {
		bd.setId(id);
		Building bds = bs.findBuildings(bd);
		if(bds!=null) {
			mo.addAttribute("use",bds);
			return "upPersonal2";
		}else {
			mo.addAttribute("msg", "要命啦,出bug啦，救命啊");
			return "error";
		}
	}
	
	@RequestMapping("/Test2/updateBuilding/{id}")
	public String updateUsertwo(Building bd,@PathVariable("id") int id,Model mo) {
		bd.setId(id);
		int usee = bs.updateBuilding(bd);
		if(usee > 0) {
			mo.addAttribute("msg", "信息修改成功~");
			return "redirect:/Test2/findListBs";
		}else {
			mo.addAttribute("msg", "要命啦,出bug啦，救命啊");
			return "error";
		}
	}
	@RequestMapping("/Test2/hello")
	public String helo(){
		return "upPersonal";
	}
	@RequestMapping("/Test2/insertBuilding")
	public String insertBuilding(Building bd,Model mo){
		int insertBuilding = bs.insertBuilding(bd);
		if(insertBuilding>0) {
			mo.addAttribute("msg", "信息添加成功~");
			return "redirect:/Test2/findListBs";
		}else {
			mo.addAttribute("msg", "要命啦,出bug啦，救命啊");
			return "error";
		}
	}
}
