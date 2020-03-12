package com.yunshan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.yunshan.util.CommonUtil;



public class BaseController {
	
	 public final Logger logger =LoggerFactory.getLogger(BaseController.class);


	public Integer GetRequestInteger(HttpServletRequest request,String name,Integer dafault){
		String value=request.getParameter(name);
		if(value!=null&&CommonUtil.isInt(value)){
			return Integer.parseInt(value);
		}else{
			return dafault;
		}
	}
	
	public String GetRequestString(HttpServletRequest request,String name,String dafault){
		String value=request.getParameter(name);
		if(value!=null&&!"".equals(value.trim())){
			return value;
		}else{
			return dafault;
		}
	}
	
	public Integer GetSessionInteger(HttpServletRequest request,String name,Integer dafault){
		HttpSession session=request.getSession();
		String value=session.getAttribute(name)!=null?session.getAttribute(name).toString():null;
		if(value!=null&&CommonUtil.isInt(value)){
			return Integer.parseInt(value);
		}else{
			return dafault;
		}
	}
	
	public String GetSessionString(HttpServletRequest request,String name,String dafault){
		HttpSession session=request.getSession();
		String value=session.getAttribute(name)!=null?session.getAttribute(name).toString():null;
		if(value!=null&&!"".equals(value.trim())){
			return value;
		}else{
			return dafault;
		}
	}
	
    /** 
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
     * 
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
     * 
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
     * 192.168.1.100 
     * 
     * 用户真实IP为： 192.168.1.110 
     * 
     * @param request 
     * @return 
     */
    public String getIpAddress(HttpServletRequest request) { 
      String ip = request.getHeader("x-forwarded-for"); 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
        ip = request.getHeader("Proxy-Client-IP"); 
      } 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
        ip = request.getHeader("WL-Proxy-Client-IP"); 
      } 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
        ip = request.getHeader("HTTP_CLIENT_IP"); 
      } 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
        ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
      } 
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
        ip = request.getRemoteAddr(); 
      } 
      return ip; 
    } 
	
	public void toJson(int status,Object data,String url,HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status", status);
		map.put("url", url);
		if(data != null && data instanceof String){
			map.put("msg",data);
		}else{
			map.put("data",data);
		}
		PrintWriter pw=null;
		
		try {
			pw=response.getWriter();
			pw.write(JSONObject.toJSONString(map));
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pw!=null){
				pw.close();
			}
		}
		return;
	}
	public void toError(HttpServletRequest request,String code,String msg) {
		request.setAttribute("code", code);
		request.setAttribute("msg", msg);
	}
	
	
}
