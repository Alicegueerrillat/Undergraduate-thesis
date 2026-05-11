package com.yb.dao;

import com.yb.entity.ScheduletimeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ScheduletimeVO;
import com.yb.entity.view.ScheduletimeView;


/**
 * 课表时间
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface ScheduletimeDao extends BaseMapper<ScheduletimeEntity> {
	
	List<ScheduletimeVO> selectListVO(@Param("ew") QueryWrapper<ScheduletimeEntity> wrapper);
	
	ScheduletimeVO selectVO(@Param("ew") QueryWrapper<ScheduletimeEntity> wrapper);
	
	List<ScheduletimeView> selectListView(@Param("ew") QueryWrapper<ScheduletimeEntity> wrapper);

	List<ScheduletimeView> selectListView(Page page,@Param("ew") QueryWrapper<ScheduletimeEntity> wrapper);

	
	ScheduletimeView selectView(@Param("ew") QueryWrapper<ScheduletimeEntity> wrapper);
	

}
