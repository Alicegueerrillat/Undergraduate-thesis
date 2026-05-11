package com.yb.entity.model;

import com.yb.entity.KebiaoxinixEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 课表信息
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public class KebiaoxinixModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 节数
	 */
	
	private String sectionnum;
		
	/**
	 * 星期
	 */
	
	private Integer week;
		
	/**
	 * 班级
	 */
	
	private String banji;
		
	/**
	 * 地点
	 */
	
	private String didian;
		
	/**
	 * 教师账号
	 */
	
	private String jiaoshizhanghao;
				
	
	/**
	 * 设置：节数
	 */
	 
	public void setSectionnum(String sectionnum) {
		this.sectionnum = sectionnum;
	}
	
	/**
	 * 获取：节数
	 */
	public String getSectionnum() {
		return sectionnum;
	}
				
	
	/**
	 * 设置：星期
	 */
	 
	public void setWeek(Integer week) {
		this.week = week;
	}
	
	/**
	 * 获取：星期
	 */
	public Integer getWeek() {
		return week;
	}
				
	
	/**
	 * 设置：班级
	 */
	 
	public void setBanji(String banji) {
		this.banji = banji;
	}
	
	/**
	 * 获取：班级
	 */
	public String getBanji() {
		return banji;
	}
				
	
	/**
	 * 设置：地点
	 */
	 
	public void setDidian(String didian) {
		this.didian = didian;
	}
	
	/**
	 * 获取：地点
	 */
	public String getDidian() {
		return didian;
	}
				
	
	/**
	 * 设置：教师账号
	 */
	 
	public void setJiaoshizhanghao(String jiaoshizhanghao) {
		this.jiaoshizhanghao = jiaoshizhanghao;
	}
	
	/**
	 * 获取：教师账号
	 */
	public String getJiaoshizhanghao() {
		return jiaoshizhanghao;
	}
			
}
