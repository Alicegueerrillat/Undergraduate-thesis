package com.yb.dao;

import com.yb.entity.KechengxinxiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.KechengxinxiVO;
import com.yb.entity.view.KechengxinxiView;


/**
 * 课程信息
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface KechengxinxiDao extends BaseMapper<KechengxinxiEntity> {
	
	List<KechengxinxiVO> selectListVO(@Param("ew") QueryWrapper<KechengxinxiEntity> wrapper);
	
	KechengxinxiVO selectVO(@Param("ew") QueryWrapper<KechengxinxiEntity> wrapper);
	
	List<KechengxinxiView> selectListView(@Param("ew") QueryWrapper<KechengxinxiEntity> wrapper);

	List<KechengxinxiView> selectListView(Page page,@Param("ew") QueryWrapper<KechengxinxiEntity> wrapper);

	
	KechengxinxiView selectView(@Param("ew") QueryWrapper<KechengxinxiEntity> wrapper);
	

}
