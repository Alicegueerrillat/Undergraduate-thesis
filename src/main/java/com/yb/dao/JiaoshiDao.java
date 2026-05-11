package com.yb.dao;

import com.yb.entity.JiaoshiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.JiaoshiVO;
import com.yb.entity.view.JiaoshiView;


/**
 * 教师
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface JiaoshiDao extends BaseMapper<JiaoshiEntity> {
	
	List<JiaoshiVO> selectListVO(@Param("ew") QueryWrapper<JiaoshiEntity> wrapper);
	
	JiaoshiVO selectVO(@Param("ew") QueryWrapper<JiaoshiEntity> wrapper);
	
	List<JiaoshiView> selectListView(@Param("ew") QueryWrapper<JiaoshiEntity> wrapper);

	List<JiaoshiView> selectListView(Page page,@Param("ew") QueryWrapper<JiaoshiEntity> wrapper);

	
	JiaoshiView selectView(@Param("ew") QueryWrapper<JiaoshiEntity> wrapper);
	

}
