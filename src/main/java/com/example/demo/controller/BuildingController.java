package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.HyperLogLogCommands;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


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
	
/*	@RequestMapping("/Test2/updateh1/{id}")
	public String updateUsertwo(Building user,@PathVariable("id") Integer id,Model mo) {
		user.setId(id);
		int usee = us.updateUser(user);
		if(usee > 0) {
			mo.addAttribute("msg", "用户修改成功~");
			List<Building> use = us.findUser(user);
			mo.addAttribute("list", use);
			return "NewFile";
		}else {
			mo.addAttribute("msg", "要命啦,出bug啦，救命啊");
			return "error";
		}
	}*/
}
