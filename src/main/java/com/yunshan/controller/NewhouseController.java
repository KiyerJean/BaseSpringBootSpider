package com.yunshan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunshan.model.Newhouse;
import com.yunshan.service.NewhouseService;
@EnableAutoConfiguration
@Controller
@RequestMapping(value="/newhouse")
public class NewhouseController extends BaseController{

	@Autowired
	private NewhouseService newhouseService;
	
	
	//跳转加入我们志愿者页面
	@RequestMapping(value="/newhouse",method=RequestMethod.GET)
	public String newhouse(ModelMap map){
		return "admin/newhouse";
	}

	//ajax添加房子
	@RequestMapping(value="/ajaxInsertNewhouse",method=RequestMethod.POST)
	public void ajaxVolunteerPeopleList(Newhouse record,HttpServletRequest request,HttpServletResponse response){
		newhouseService.insert(record);
		toJson(0, "success", null, response);
	}
	
	
}
