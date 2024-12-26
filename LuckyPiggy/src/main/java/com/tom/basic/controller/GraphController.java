package com.tom.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tom.basic.entity.GraphEntity;
import com.tom.basic.model.GraphVO;
import com.tom.basic.repository.GraphRepo;

import jakarta.servlet.http.HttpSession;

//@Controller
public class GraphController {
//	@Autowired
//	GraphRepo repo;
//	
//	@GetMapping("/countTurtle")
//	public String CountTurtle(String mb_id, String pos_type, Model model) {
//		GraphEntity turtle = repo.findPostureByMbIdAndPosType(mb_id, pos_type);
//		
//		System.out.println("가져온 값은" + turtle.getPosCount()+turtle.getMbId());
//		model.addAttribute("turtle",turtle);
//		
//		return "graph";
//	}
	
}
