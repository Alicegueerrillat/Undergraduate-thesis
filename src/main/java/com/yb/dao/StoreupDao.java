package com.yb.dao;

import com.yb.entity.StoreupEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.StoreupVO;
import com.yb.entity.view.StoreupView;


/**
 * 收藏表
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface StoreupDao extends BaseMapper<StoreupEntity> {
	
	List<StoreupVO> selectListVO(@Param("ew") QueryWrapper<StoreupEntity> wrapper);
	
	StoreupVO selectVO(@Param("ew") QueryWrapper<StoreupEntity> wrapper);
	
	List<StoreupView> selectListView(@Param("ew") QueryWrapper<StoreupEntity> wrapper);

	List<StoreupView> selectListView(Page page,@Param("ew") QueryWrapper<StoreupEntity> wrapper);

	
	StoreupView selectView(@Param("ew") QueryWrapper<StoreupEntity> wrapper);
	

}
