package com.yb.dao;

import com.yb.entity.UsersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.UsersVO;
import com.yb.entity.view.UsersView;


/**
 * 管理员
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface UsersDao extends BaseMapper<UsersEntity> {
	
	List<UsersVO> selectListVO(@Param("ew") QueryWrapper<UsersEntity> wrapper);
	
	UsersVO selectVO(@Param("ew") QueryWrapper<UsersEntity> wrapper);
	
	List<UsersView> selectListView(@Param("ew") QueryWrapper<UsersEntity> wrapper);

	List<UsersView> selectListView(Page page,@Param("ew") QueryWrapper<UsersEntity> wrapper);

	
	UsersView selectView(@Param("ew") QueryWrapper<UsersEntity> wrapper);
	

}
