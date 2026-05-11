package com.yb.dao;

import com.yb.entity.ZhuanyeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ZhuanyeVO;
import com.yb.entity.view.ZhuanyeView;


/**
 * 专业
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface ZhuanyeDao extends BaseMapper<ZhuanyeEntity> {
	
	List<ZhuanyeVO> selectListVO(@Param("ew") QueryWrapper<ZhuanyeEntity> wrapper);
	
	ZhuanyeVO selectVO(@Param("ew") QueryWrapper<ZhuanyeEntity> wrapper);
	
	List<ZhuanyeView> selectListView(@Param("ew") QueryWrapper<ZhuanyeEntity> wrapper);

	List<ZhuanyeView> selectListView(Page page,@Param("ew") QueryWrapper<ZhuanyeEntity> wrapper);

	
	ZhuanyeView selectView(@Param("ew") QueryWrapper<ZhuanyeEntity> wrapper);
	

}
