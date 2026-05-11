package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ChengjixinxiDao;
import com.yb.entity.ChengjixinxiEntity;
import com.yb.service.ChengjixinxiService;
import com.yb.entity.vo.ChengjixinxiVO;
import com.yb.entity.view.ChengjixinxiView;

@Service("chengjixinxiService")
public class ChengjixinxiServiceImpl extends ServiceImpl<ChengjixinxiDao, ChengjixinxiEntity> implements ChengjixinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChengjixinxiEntity> page = baseMapper.selectPage(new Query<ChengjixinxiEntity>(params).getPage(),new QueryWrapper<ChengjixinxiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ChengjixinxiEntity> wrapper) {
        Page<ChengjixinxiEntity> page = baseMapper.selectPage(new Query<ChengjixinxiEntity>(params).getPage(),wrapper);
        Page<ChengjixinxiView> pageVo = (Page<ChengjixinxiView>)page.convert(ChengjixinxiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ChengjixinxiVO> selectListVO(QueryWrapper<ChengjixinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChengjixinxiVO selectVO(QueryWrapper<ChengjixinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChengjixinxiView> selectListView(QueryWrapper<ChengjixinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChengjixinxiView selectView(QueryWrapper<ChengjixinxiEntity> wrapper) {
        ChengjixinxiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ChengjixinxiView(entity);
        } else {
            return null;
        }
	}


}
