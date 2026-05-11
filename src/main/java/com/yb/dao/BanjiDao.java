package com.yb.dao;

import com.yb.entity.BanjiEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.BanjiVO;
import com.yb.entity.view.BanjiView;


/**
 * 班级
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface BanjiDao extends BaseMapper<BanjiEntity> {
	
	List<BanjiVO> selectListVO(@Param("ew") QueryWrapper<BanjiEntity> wrapper);
	
	BanjiVO selectVO(@Param("ew") QueryWrapper<BanjiEntity> wrapper);
	
	List<BanjiView> selectListView(@Param("ew") QueryWrapper<BanjiEntity> wrapper);

	List<BanjiView> selectListView(Page page,@Param("ew") QueryWrapper<BanjiEntity> wrapper);

	
	BanjiView selectView(@Param("ew") QueryWrapper<BanjiEntity> wrapper);
	

}
