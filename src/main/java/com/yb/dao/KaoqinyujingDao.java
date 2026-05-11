package com.yb.dao;

import com.yb.entity.KaoqinyujingEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.KaoqinyujingVO;
import com.yb.entity.view.KaoqinyujingView;


/**
 * 考勤预警
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface KaoqinyujingDao extends BaseMapper<KaoqinyujingEntity> {
	
	List<KaoqinyujingVO> selectListVO(@Param("ew") QueryWrapper<KaoqinyujingEntity> wrapper);
	
	KaoqinyujingVO selectVO(@Param("ew") QueryWrapper<KaoqinyujingEntity> wrapper);
	
	List<KaoqinyujingView> selectListView(@Param("ew") QueryWrapper<KaoqinyujingEntity> wrapper);

	List<KaoqinyujingView> selectListView(Page page,@Param("ew") QueryWrapper<KaoqinyujingEntity> wrapper);

	
	KaoqinyujingView selectView(@Param("ew") QueryWrapper<KaoqinyujingEntity> wrapper);
	

}
