package com.yb.dao;

import com.yb.entity.XueyuanEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.XueyuanVO;
import com.yb.entity.view.XueyuanView;


/**
 * 学院
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface XueyuanDao extends BaseMapper<XueyuanEntity> {
	
	List<XueyuanVO> selectListVO(@Param("ew") QueryWrapper<XueyuanEntity> wrapper);
	
	XueyuanVO selectVO(@Param("ew") QueryWrapper<XueyuanEntity> wrapper);
	
	List<XueyuanView> selectListView(@Param("ew") QueryWrapper<XueyuanEntity> wrapper);

	List<XueyuanView> selectListView(Page page,@Param("ew") QueryWrapper<XueyuanEntity> wrapper);

	
	XueyuanView selectView(@Param("ew") QueryWrapper<XueyuanEntity> wrapper);
	

}
