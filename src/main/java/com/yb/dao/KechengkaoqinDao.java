package com.yb.dao;

import com.yb.entity.KechengkaoqinEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.KechengkaoqinVO;
import com.yb.entity.view.KechengkaoqinView;


/**
 * 课程考勤
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface KechengkaoqinDao extends BaseMapper<KechengkaoqinEntity> {
	
	List<KechengkaoqinVO> selectListVO(@Param("ew") QueryWrapper<KechengkaoqinEntity> wrapper);
	
	KechengkaoqinVO selectVO(@Param("ew") QueryWrapper<KechengkaoqinEntity> wrapper);
	
	List<KechengkaoqinView> selectListView(@Param("ew") QueryWrapper<KechengkaoqinEntity> wrapper);

	List<KechengkaoqinView> selectListView(Page page,@Param("ew") QueryWrapper<KechengkaoqinEntity> wrapper);

	
	KechengkaoqinView selectView(@Param("ew") QueryWrapper<KechengkaoqinEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") QueryWrapper<KechengkaoqinEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") QueryWrapper<KechengkaoqinEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") QueryWrapper<KechengkaoqinEntity> wrapper);



}
