package com.yb.dao;

import com.yb.entity.QingjiaxinxiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.QingjiaxinxiVO;
import com.yb.entity.view.QingjiaxinxiView;


/**
 * 请假信息
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface QingjiaxinxiDao extends BaseMapper<QingjiaxinxiEntity> {
	
	List<QingjiaxinxiVO> selectListVO(@Param("ew") QueryWrapper<QingjiaxinxiEntity> wrapper);
	
	QingjiaxinxiVO selectVO(@Param("ew") QueryWrapper<QingjiaxinxiEntity> wrapper);
	
	List<QingjiaxinxiView> selectListView(@Param("ew") QueryWrapper<QingjiaxinxiEntity> wrapper);

	List<QingjiaxinxiView> selectListView(Page page,@Param("ew") QueryWrapper<QingjiaxinxiEntity> wrapper);

	
	QingjiaxinxiView selectView(@Param("ew") QueryWrapper<QingjiaxinxiEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") QueryWrapper<QingjiaxinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") QueryWrapper<QingjiaxinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") QueryWrapper<QingjiaxinxiEntity> wrapper);



}
