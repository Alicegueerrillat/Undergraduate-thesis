package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.KaoqinshensuDao;
import com.yb.entity.KaoqinshensuEntity;
import com.yb.service.KaoqinshensuService;
import com.yb.entity.vo.KaoqinshensuVO;
import com.yb.entity.view.KaoqinshensuView;

@Service("kaoqinshensuService")
public class KaoqinshensuServiceImpl extends ServiceImpl<KaoqinshensuDao, KaoqinshensuEntity> implements KaoqinshensuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KaoqinshensuEntity> page = baseMapper.selectPage(new Query<KaoqinshensuEntity>(params).getPage(),new QueryWrapper<KaoqinshensuEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<KaoqinshensuEntity> wrapper) {
        Page<KaoqinshensuEntity> page = baseMapper.selectPage(new Query<KaoqinshensuEntity>(params).getPage(),wrapper);
        Page<KaoqinshensuView> pageVo = (Page<KaoqinshensuView>)page.convert(KaoqinshensuView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<KaoqinshensuVO> selectListVO(QueryWrapper<KaoqinshensuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KaoqinshensuVO selectVO(QueryWrapper<KaoqinshensuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KaoqinshensuView> selectListView(QueryWrapper<KaoqinshensuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KaoqinshensuView selectView(QueryWrapper<KaoqinshensuEntity> wrapper) {
        KaoqinshensuEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new KaoqinshensuView(entity);
        } else {
            return null;
        }
	}


}
