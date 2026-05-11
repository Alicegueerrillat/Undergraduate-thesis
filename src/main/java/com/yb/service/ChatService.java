package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ChatEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ChatVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ChatView;


/**
 * AI助手
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface ChatService extends IService<ChatEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChatVO> selectListVO(QueryWrapper<ChatEntity> wrapper);
   	
   	ChatVO selectVO(@Param("ew") QueryWrapper<ChatEntity> wrapper);
   	
   	List<ChatView> selectListView(QueryWrapper<ChatEntity> wrapper);
   	
   	ChatView selectView(@Param("ew") QueryWrapper<ChatEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ChatEntity> wrapper);

   	

}

