package com.yb.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import com.baomidou.mybatisplus.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;


/**
 * 课表时间
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
@TableName("scheduletime")
public class ScheduletimeEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ScheduletimeEntity() {
		
	}
	
	public ScheduletimeEntity(T t) {
		try {
			BeanUtils.copyProperties(t,this);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 节数
	 */
					
	private Integer sectionnum;
	
	/**
	 * 开始时间
	 */
					
	private String starttime;
	
	/**
	 * 结束时间
	 */
					
	private String endtime;
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：节数
	 */
	public void setSectionnum(Integer sectionnum) {
		this.sectionnum = sectionnum;
	}
	/**
	 * 获取：节数
	 */
	public Integer getSectionnum() {
		return sectionnum;
	}
	/**
	 * 设置：开始时间
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	/**
	 * 获取：开始时间
	 */
	public String getStarttime() {
		return starttime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	/**
	 * 获取：结束时间
	 */
	public String getEndtime() {
		return endtime;
	}
}
