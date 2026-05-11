package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.TousufankuiDao;
import com.yb.entity.TousufankuiEntity;
import com.yb.service.TousufankuiService;
import com.yb.entity.vo.TousufankuiVO;
import com.yb.entity.view.TousufankuiView;

@Service("tousufankuiService")
public class TousufankuiServiceImpl extends ServiceImpl<TousufankuiDao, TousufankuiEntity> implements TousufankuiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TousufankuiEntity> page = baseMapper.selectPage(new Query<TousufankuiEntity>(params).getPage(),new QueryWrapper<TousufankuiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<TousufankuiEntity> wrapper) {
        Page<TousufankuiEntity> page = baseMapper.selectPage(new Query<TousufankuiEntity>(params).getPage(),wrapper);
        Page<TousufankuiView> pageVo = (Page<TousufankuiView>)page.convert(TousufankuiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<TousufankuiVO> selectListVO(QueryWrapper<TousufankuiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public TousufankuiVO selectVO(QueryWrapper<TousufankuiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<TousufankuiView> selectListView(QueryWrapper<TousufankuiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TousufankuiView selectView(QueryWrapper<TousufankuiEntity> wrapper) {
        TousufankuiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new TousufankuiView(entity);
        } else {
            return null;
        }
	}


}
