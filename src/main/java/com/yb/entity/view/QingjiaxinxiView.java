package com.yb.entity.view;

import com.yb.entity.QingjiaxinxiEntity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.yb.utils.EncryptUtil;
 

/**
 * 请假信息
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
@TableName("qingjiaxinxi")
public class QingjiaxinxiView  extends QingjiaxinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public QingjiaxinxiView(){
	}
 
 	public QingjiaxinxiView(QingjiaxinxiEntity qingjiaxinxiEntity){
 	try {
			BeanUtils.copyProperties(qingjiaxinxiEntity,this);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
