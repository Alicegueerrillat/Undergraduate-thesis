package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.StoreupDao;
import com.yb.entity.StoreupEntity;
import com.yb.service.StoreupService;
import com.yb.entity.vo.StoreupVO;
import com.yb.entity.view.StoreupView;

@Service("storeupService")
public class StoreupServiceImpl extends ServiceImpl<StoreupDao, StoreupEntity> implements StoreupService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<StoreupEntity> page = baseMapper.selectPage(new Query<StoreupEntity>(params).getPage(),new QueryWrapper<StoreupEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<StoreupEntity> wrapper) {
        Page<StoreupEntity> page = baseMapper.selectPage(new Query<StoreupEntity>(params).getPage(),wrapper);
        Page<StoreupView> pageVo = (Page<StoreupView>)page.convert(StoreupView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<StoreupVO> selectListVO(QueryWrapper<StoreupEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public StoreupVO selectVO(QueryWrapper<StoreupEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<StoreupView> selectListView(QueryWrapper<StoreupEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public StoreupView selectView(QueryWrapper<StoreupEntity> wrapper) {
        StoreupEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new StoreupView(entity);
        } else {
            return null;
        }
	}


}
