package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.KechengleixingDao;
import com.yb.entity.KechengleixingEntity;
import com.yb.service.KechengleixingService;
import com.yb.entity.vo.KechengleixingVO;
import com.yb.entity.view.KechengleixingView;

@Service("kechengleixingService")
public class KechengleixingServiceImpl extends ServiceImpl<KechengleixingDao, KechengleixingEntity> implements KechengleixingService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KechengleixingEntity> page = baseMapper.selectPage(new Query<KechengleixingEntity>(params).getPage(),new QueryWrapper<KechengleixingEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<KechengleixingEntity> wrapper) {
        Page<KechengleixingEntity> page = baseMapper.selectPage(new Query<KechengleixingEntity>(params).getPage(),wrapper);
        Page<KechengleixingView> pageVo = (Page<KechengleixingView>)page.convert(KechengleixingView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<KechengleixingVO> selectListVO(QueryWrapper<KechengleixingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KechengleixingVO selectVO(QueryWrapper<KechengleixingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KechengleixingView> selectListView(QueryWrapper<KechengleixingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KechengleixingView selectView(QueryWrapper<KechengleixingEntity> wrapper) {
        KechengleixingEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new KechengleixingView(entity);
        } else {
            return null;
        }
	}


}
