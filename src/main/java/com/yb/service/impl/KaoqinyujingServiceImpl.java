package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.KaoqinyujingDao;
import com.yb.entity.KaoqinyujingEntity;
import com.yb.service.KaoqinyujingService;
import com.yb.entity.vo.KaoqinyujingVO;
import com.yb.entity.view.KaoqinyujingView;

@Service("kaoqinyujingService")
public class KaoqinyujingServiceImpl extends ServiceImpl<KaoqinyujingDao, KaoqinyujingEntity> implements KaoqinyujingService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KaoqinyujingEntity> page = baseMapper.selectPage(new Query<KaoqinyujingEntity>(params).getPage(),new QueryWrapper<KaoqinyujingEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<KaoqinyujingEntity> wrapper) {
        Page<KaoqinyujingEntity> page = baseMapper.selectPage(new Query<KaoqinyujingEntity>(params).getPage(),wrapper);
        Page<KaoqinyujingView> pageVo = (Page<KaoqinyujingView>)page.convert(KaoqinyujingView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<KaoqinyujingVO> selectListVO(QueryWrapper<KaoqinyujingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KaoqinyujingVO selectVO(QueryWrapper<KaoqinyujingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KaoqinyujingView> selectListView(QueryWrapper<KaoqinyujingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KaoqinyujingView selectView(QueryWrapper<KaoqinyujingEntity> wrapper) {
        KaoqinyujingEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new KaoqinyujingView(entity);
        } else {
            return null;
        }
	}


}
