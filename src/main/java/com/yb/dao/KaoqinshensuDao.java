package com.yb.dao;

import com.yb.entity.KaoqinshensuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.KaoqinshensuVO;
import com.yb.entity.view.KaoqinshensuView;


/**
 * 考勤申诉
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface KaoqinshensuDao extends BaseMapper<KaoqinshensuEntity> {
	
	List<KaoqinshensuVO> selectListVO(@Param("ew") QueryWrapper<KaoqinshensuEntity> wrapper);
	
	KaoqinshensuVO selectVO(@Param("ew") QueryWrapper<KaoqinshensuEntity> wrapper);
	
	List<KaoqinshensuView> selectListView(@Param("ew") QueryWrapper<KaoqinshensuEntity> wrapper);

	List<KaoqinshensuView> selectListView(Page page,@Param("ew") QueryWrapper<KaoqinshensuEntity> wrapper);

	
	KaoqinshensuView selectView(@Param("ew") QueryWrapper<KaoqinshensuEntity> wrapper);
	

}
