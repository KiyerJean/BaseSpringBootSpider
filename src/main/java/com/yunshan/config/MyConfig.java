package com.yunshan.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix="my")
@PropertySource(value="classpath:my.properties")
public class MyConfig implements Serializable{

	private static final long serialVersionUID = 8849024999748998487L;
	
	//从spring配置文件application.properties读取配置参数
	private String img_pref;
	private String cover_pref;
	private String no_img;
	private String upload_path;
	private String upload_video_path;
	private String baidu_ak;
	public String getImg_pref() {
		return img_pref;
	}
	public void setImg_pref(String img_pref) {
		this.img_pref = img_pref;
	}
	
	public String getCover_pref() {
		return cover_pref;
	}
	public void setCover_pref(String cover_pref) {
		this.cover_pref = cover_pref;
	}
	public String getNo_img() {
		return no_img;
	}
	public void setNo_img(String no_img) {
		this.no_img = no_img;
	}
	public String getUpload_path() {
		return upload_path;
	}
	public void setUpload_path(String upload_path) {
		this.upload_path = upload_path;
	}
	
	public String getUpload_video_path() {
		return upload_video_path;
	}
	public void setUpload_video_path(String upload_video_path) {
		this.upload_video_path = upload_video_path;
	}
	public String getBaidu_ak() {
		return baidu_ak;
	}
	public void setBaidu_ak(String baidu_ak) {
		this.baidu_ak = baidu_ak;
	}
	
}
