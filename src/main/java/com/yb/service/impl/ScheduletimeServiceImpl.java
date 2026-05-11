package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ScheduletimeDao;
import com.yb.entity.ScheduletimeEntity;
import com.yb.service.ScheduletimeService;
import com.yb.entity.vo.ScheduletimeVO;
import com.yb.entity.view.ScheduletimeView;

@Service("scheduletimeService")
public class ScheduletimeServiceImpl extends ServiceImpl<ScheduletimeDao, ScheduletimeEntity> implements ScheduletimeService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ScheduletimeEntity> page = baseMapper.selectPage(new Query<ScheduletimeEntity>(params).getPage(),new QueryWrapper<ScheduletimeEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ScheduletimeEntity> wrapper) {
        Page<ScheduletimeEntity> page = baseMapper.selectPage(new Query<ScheduletimeEntity>(params).getPage(),wrapper);
        Page<ScheduletimeView> pageVo = (Page<ScheduletimeView>)page.convert(ScheduletimeView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ScheduletimeVO> selectListVO(QueryWrapper<ScheduletimeEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ScheduletimeVO selectVO(QueryWrapper<ScheduletimeEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ScheduletimeView> selectListView(QueryWrapper<ScheduletimeEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ScheduletimeView selectView(QueryWrapper<ScheduletimeEntity> wrapper) {
        ScheduletimeEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ScheduletimeView(entity);
        } else {
            return null;
        }
	}


}
