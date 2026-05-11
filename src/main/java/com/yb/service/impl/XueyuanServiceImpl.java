package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.XueyuanDao;
import com.yb.entity.XueyuanEntity;
import com.yb.service.XueyuanService;
import com.yb.entity.vo.XueyuanVO;
import com.yb.entity.view.XueyuanView;

@Service("xueyuanService")
public class XueyuanServiceImpl extends ServiceImpl<XueyuanDao, XueyuanEntity> implements XueyuanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XueyuanEntity> page = baseMapper.selectPage(new Query<XueyuanEntity>(params).getPage(),new QueryWrapper<XueyuanEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<XueyuanEntity> wrapper) {
        Page<XueyuanEntity> page = baseMapper.selectPage(new Query<XueyuanEntity>(params).getPage(),wrapper);
        Page<XueyuanView> pageVo = (Page<XueyuanView>)page.convert(XueyuanView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<XueyuanVO> selectListVO(QueryWrapper<XueyuanEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XueyuanVO selectVO(QueryWrapper<XueyuanEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XueyuanView> selectListView(QueryWrapper<XueyuanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XueyuanView selectView(QueryWrapper<XueyuanEntity> wrapper) {
        XueyuanEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new XueyuanView(entity);
        } else {
            return null;
        }
	}


}
