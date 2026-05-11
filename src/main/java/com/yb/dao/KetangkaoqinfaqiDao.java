package com.yb.dao;

import com.yb.entity.KetangkaoqinfaqiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.KetangkaoqinfaqiVO;
import com.yb.entity.view.KetangkaoqinfaqiView;


/**
 * 课堂考勤发起
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface KetangkaoqinfaqiDao extends BaseMapper<KetangkaoqinfaqiEntity> {
	
	List<KetangkaoqinfaqiVO> selectListVO(@Param("ew") QueryWrapper<KetangkaoqinfaqiEntity> wrapper);
	
	KetangkaoqinfaqiVO selectVO(@Param("ew") QueryWrapper<KetangkaoqinfaqiEntity> wrapper);
	
	List<KetangkaoqinfaqiView> selectListView(@Param("ew") QueryWrapper<KetangkaoqinfaqiEntity> wrapper);

	List<KetangkaoqinfaqiView> selectListView(Page page,@Param("ew") QueryWrapper<KetangkaoqinfaqiEntity> wrapper);

	
	KetangkaoqinfaqiView selectView(@Param("ew") QueryWrapper<KetangkaoqinfaqiEntity> wrapper);
	

}
