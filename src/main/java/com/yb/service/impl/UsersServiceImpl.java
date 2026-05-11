package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.UsersDao;
import com.yb.entity.UsersEntity;
import com.yb.service.UsersService;
import com.yb.entity.vo.UsersVO;
import com.yb.entity.view.UsersView;

@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersDao, UsersEntity> implements UsersService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UsersEntity> page = baseMapper.selectPage(new Query<UsersEntity>(params).getPage(),new QueryWrapper<UsersEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<UsersEntity> wrapper) {
        Page<UsersEntity> page = baseMapper.selectPage(new Query<UsersEntity>(params).getPage(),wrapper);
        Page<UsersView> pageVo = (Page<UsersView>)page.convert(UsersView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<UsersVO> selectListVO(QueryWrapper<UsersEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public UsersVO selectVO(QueryWrapper<UsersEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<UsersView> selectListView(QueryWrapper<UsersEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public UsersView selectView(QueryWrapper<UsersEntity> wrapper) {
        UsersEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new UsersView(entity);
        } else {
            return null;
        }
	}


}
