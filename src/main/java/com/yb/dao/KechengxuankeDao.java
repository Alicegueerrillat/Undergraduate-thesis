package com.yb.dao;

import com.yb.entity.KechengxuankeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.KechengxuankeVO;
import com.yb.entity.view.KechengxuankeView;


/**
 * 课程选课
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface KechengxuankeDao extends BaseMapper<KechengxuankeEntity> {
	
	List<KechengxuankeVO> selectListVO(@Param("ew") QueryWrapper<KechengxuankeEntity> wrapper);
	
	KechengxuankeVO selectVO(@Param("ew") QueryWrapper<KechengxuankeEntity> wrapper);
	
	List<KechengxuankeView> selectListView(@Param("ew") QueryWrapper<KechengxuankeEntity> wrapper);

	List<KechengxuankeView> selectListView(Page page,@Param("ew") QueryWrapper<KechengxuankeEntity> wrapper);

	
	KechengxuankeView selectView(@Param("ew") QueryWrapper<KechengxuankeEntity> wrapper);
	

}
