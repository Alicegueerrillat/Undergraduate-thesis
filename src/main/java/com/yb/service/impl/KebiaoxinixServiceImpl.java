package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.KebiaoxinixDao;
import com.yb.entity.KebiaoxinixEntity;
import com.yb.service.KebiaoxinixService;
import com.yb.entity.vo.KebiaoxinixVO;
import com.yb.entity.view.KebiaoxinixView;

@Service("kebiaoxinixService")
public class KebiaoxinixServiceImpl extends ServiceImpl<KebiaoxinixDao, KebiaoxinixEntity> implements KebiaoxinixService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KebiaoxinixEntity> page = baseMapper.selectPage(new Query<KebiaoxinixEntity>(params).getPage(),new QueryWrapper<KebiaoxinixEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<KebiaoxinixEntity> wrapper) {
        Page<KebiaoxinixEntity> page = baseMapper.selectPage(new Query<KebiaoxinixEntity>(params).getPage(),wrapper);
        Page<KebiaoxinixView> pageVo = (Page<KebiaoxinixView>)page.convert(KebiaoxinixView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<KebiaoxinixVO> selectListVO(QueryWrapper<KebiaoxinixEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KebiaoxinixVO selectVO(QueryWrapper<KebiaoxinixEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KebiaoxinixView> selectListView(QueryWrapper<KebiaoxinixEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KebiaoxinixView selectView(QueryWrapper<KebiaoxinixEntity> wrapper) {
        KebiaoxinixEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new KebiaoxinixView(entity);
        } else {
            return null;
        }
	}


}
