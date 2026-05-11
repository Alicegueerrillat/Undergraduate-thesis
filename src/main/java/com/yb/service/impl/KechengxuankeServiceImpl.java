package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.KechengxuankeDao;
import com.yb.entity.KechengxuankeEntity;
import com.yb.service.KechengxuankeService;
import com.yb.entity.vo.KechengxuankeVO;
import com.yb.entity.view.KechengxuankeView;

@Service("kechengxuankeService")
public class KechengxuankeServiceImpl extends ServiceImpl<KechengxuankeDao, KechengxuankeEntity> implements KechengxuankeService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KechengxuankeEntity> page = baseMapper.selectPage(new Query<KechengxuankeEntity>(params).getPage(),new QueryWrapper<KechengxuankeEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<KechengxuankeEntity> wrapper) {
        Page<KechengxuankeEntity> page = baseMapper.selectPage(new Query<KechengxuankeEntity>(params).getPage(),wrapper);
        Page<KechengxuankeView> pageVo = (Page<KechengxuankeView>)page.convert(KechengxuankeView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<KechengxuankeVO> selectListVO(QueryWrapper<KechengxuankeEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KechengxuankeVO selectVO(QueryWrapper<KechengxuankeEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KechengxuankeView> selectListView(QueryWrapper<KechengxuankeEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KechengxuankeView selectView(QueryWrapper<KechengxuankeEntity> wrapper) {
        KechengxuankeEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new KechengxuankeView(entity);
        } else {
            return null;
        }
	}


}
