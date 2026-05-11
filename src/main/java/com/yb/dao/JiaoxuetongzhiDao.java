package com.yb.dao;

import com.yb.entity.JiaoxuetongzhiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.JiaoxuetongzhiVO;
import com.yb.entity.view.JiaoxuetongzhiView;


/**
 * 教学通知
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface JiaoxuetongzhiDao extends BaseMapper<JiaoxuetongzhiEntity> {
	
	List<JiaoxuetongzhiVO> selectListVO(@Param("ew") QueryWrapper<JiaoxuetongzhiEntity> wrapper);
	
	JiaoxuetongzhiVO selectVO(@Param("ew") QueryWrapper<JiaoxuetongzhiEntity> wrapper);
	
	List<JiaoxuetongzhiView> selectListView(@Param("ew") QueryWrapper<JiaoxuetongzhiEntity> wrapper);

	List<JiaoxuetongzhiView> selectListView(Page page,@Param("ew") QueryWrapper<JiaoxuetongzhiEntity> wrapper);

	
	JiaoxuetongzhiView selectView(@Param("ew") QueryWrapper<JiaoxuetongzhiEntity> wrapper);
	

}
