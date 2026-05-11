package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.XueshengDao;
import com.yb.entity.XueshengEntity;
import com.yb.service.XueshengService;
import com.yb.entity.vo.XueshengVO;
import com.yb.entity.view.XueshengView;

@Service("xueshengService")
public class XueshengServiceImpl extends ServiceImpl<XueshengDao, XueshengEntity> implements XueshengService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XueshengEntity> page = baseMapper.selectPage(new Query<XueshengEntity>(params).getPage(),new QueryWrapper<XueshengEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<XueshengEntity> wrapper) {
        Page<XueshengEntity> page = baseMapper.selectPage(new Query<XueshengEntity>(params).getPage(),wrapper);
        Page<XueshengView> pageVo = (Page<XueshengView>)page.convert(XueshengView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<XueshengVO> selectListVO(QueryWrapper<XueshengEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XueshengVO selectVO(QueryWrapper<XueshengEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XueshengView> selectListView(QueryWrapper<XueshengEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XueshengView selectView(QueryWrapper<XueshengEntity> wrapper) {
        XueshengEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new XueshengView(entity);
        } else {
            return null;
        }
	}


}
