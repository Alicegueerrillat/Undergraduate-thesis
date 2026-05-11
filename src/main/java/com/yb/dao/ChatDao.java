package com.yb.dao;

import com.yb.entity.ChatEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.ChatVO;
import com.yb.entity.view.ChatView;


/**
 * AI助手
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface ChatDao extends BaseMapper<ChatEntity> {
	
	List<ChatVO> selectListVO(@Param("ew") QueryWrapper<ChatEntity> wrapper);
	
	ChatVO selectVO(@Param("ew") QueryWrapper<ChatEntity> wrapper);
	
	List<ChatView> selectListView(@Param("ew") QueryWrapper<ChatEntity> wrapper);

	List<ChatView> selectListView(Page page,@Param("ew") QueryWrapper<ChatEntity> wrapper);

	
	ChatView selectView(@Param("ew") QueryWrapper<ChatEntity> wrapper);
	

}
