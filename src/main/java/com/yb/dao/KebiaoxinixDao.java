package com.yb.dao;

import com.yb.entity.KebiaoxinixEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.KebiaoxinixVO;
import com.yb.entity.view.KebiaoxinixView;


/**
 * 课表信息
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface KebiaoxinixDao extends BaseMapper<KebiaoxinixEntity> {
	
	List<KebiaoxinixVO> selectListVO(@Param("ew") QueryWrapper<KebiaoxinixEntity> wrapper);
	
	KebiaoxinixVO selectVO(@Param("ew") QueryWrapper<KebiaoxinixEntity> wrapper);
	
	List<KebiaoxinixView> selectListView(@Param("ew") QueryWrapper<KebiaoxinixEntity> wrapper);

	List<KebiaoxinixView> selectListView(Page page,@Param("ew") QueryWrapper<KebiaoxinixEntity> wrapper);

	
	KebiaoxinixView selectView(@Param("ew") QueryWrapper<KebiaoxinixEntity> wrapper);
	

}
