package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ChatDao;
import com.yb.entity.ChatEntity;
import com.yb.service.ChatService;
import com.yb.entity.vo.ChatVO;
import com.yb.entity.view.ChatView;

@Service("chatService")
public class ChatServiceImpl extends ServiceImpl<ChatDao, ChatEntity> implements ChatService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChatEntity> page = baseMapper.selectPage(new Query<ChatEntity>(params).getPage(),new QueryWrapper<ChatEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ChatEntity> wrapper) {
        Page<ChatEntity> page = baseMapper.selectPage(new Query<ChatEntity>(params).getPage(),wrapper);
        Page<ChatView> pageVo = (Page<ChatView>)page.convert(ChatView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ChatVO> selectListVO(QueryWrapper<ChatEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChatVO selectVO(QueryWrapper<ChatEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChatView> selectListView(QueryWrapper<ChatEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChatView selectView(QueryWrapper<ChatEntity> wrapper) {
        ChatEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ChatView(entity);
        } else {
            return null;
        }
	}


}
