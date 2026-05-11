package com.yb.dao;

import com.yb.entity.KechengleixingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.KechengleixingVO;
import com.yb.entity.view.KechengleixingView;


/**
 * 课程类型
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface KechengleixingDao extends BaseMapper<KechengleixingEntity> {
	
	List<KechengleixingVO> selectListVO(@Param("ew") QueryWrapper<KechengleixingEntity> wrapper);
	
	KechengleixingVO selectVO(@Param("ew") QueryWrapper<KechengleixingEntity> wrapper);
	
	List<KechengleixingView> selectListView(@Param("ew") QueryWrapper<KechengleixingEntity> wrapper);

	List<KechengleixingView> selectListView(Page page,@Param("ew") QueryWrapper<KechengleixingEntity> wrapper);

	
	KechengleixingView selectView(@Param("ew") QueryWrapper<KechengleixingEntity> wrapper);
	

}
