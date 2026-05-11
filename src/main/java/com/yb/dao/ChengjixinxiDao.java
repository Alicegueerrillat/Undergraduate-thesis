package com.yb.dao;

import com.yb.entity.ChengjixinxiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ChengjixinxiVO;
import com.yb.entity.view.ChengjixinxiView;


/**
 * 成绩信息
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface ChengjixinxiDao extends BaseMapper<ChengjixinxiEntity> {
	
	List<ChengjixinxiVO> selectListVO(@Param("ew") QueryWrapper<ChengjixinxiEntity> wrapper);
	
	ChengjixinxiVO selectVO(@Param("ew") QueryWrapper<ChengjixinxiEntity> wrapper);
	
	List<ChengjixinxiView> selectListView(@Param("ew") QueryWrapper<ChengjixinxiEntity> wrapper);

	List<ChengjixinxiView> selectListView(Page page,@Param("ew") QueryWrapper<ChengjixinxiEntity> wrapper);

	
	ChengjixinxiView selectView(@Param("ew") QueryWrapper<ChengjixinxiEntity> wrapper);
	

}
