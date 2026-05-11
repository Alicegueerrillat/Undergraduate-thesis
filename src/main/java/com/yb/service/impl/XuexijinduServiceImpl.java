package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.XuexijinduDao;
import com.yb.entity.XuexijinduEntity;
import com.yb.service.XuexijinduService;
import com.yb.entity.vo.XuexijinduVO;
import com.yb.entity.view.XuexijinduView;

@Service("xuexijinduService")
public class XuexijinduServiceImpl extends ServiceImpl<XuexijinduDao, XuexijinduEntity> implements XuexijinduService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XuexijinduEntity> page = baseMapper.selectPage(new Query<XuexijinduEntity>(params).getPage(),new QueryWrapper<XuexijinduEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<XuexijinduEntity> wrapper) {
        Page<XuexijinduEntity> page = baseMapper.selectPage(new Query<XuexijinduEntity>(params).getPage(),wrapper);
        Page<XuexijinduView> pageVo = (Page<XuexijinduView>)page.convert(XuexijinduView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<XuexijinduVO> selectListVO(QueryWrapper<XuexijinduEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XuexijinduVO selectVO(QueryWrapper<XuexijinduEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XuexijinduView> selectListView(QueryWrapper<XuexijinduEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XuexijinduView selectView(QueryWrapper<XuexijinduEntity> wrapper) {
        XuexijinduEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new XuexijinduView(entity);
        } else {
            return null;
        }
	}


}
