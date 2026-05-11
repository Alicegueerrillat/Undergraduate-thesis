package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.ZhuanyeDao;
import com.yb.entity.ZhuanyeEntity;
import com.yb.service.ZhuanyeService;
import com.yb.entity.vo.ZhuanyeVO;
import com.yb.entity.view.ZhuanyeView;

@Service("zhuanyeService")
public class ZhuanyeServiceImpl extends ServiceImpl<ZhuanyeDao, ZhuanyeEntity> implements ZhuanyeService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZhuanyeEntity> page = baseMapper.selectPage(new Query<ZhuanyeEntity>(params).getPage(),new QueryWrapper<ZhuanyeEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<ZhuanyeEntity> wrapper) {
        Page<ZhuanyeEntity> page = baseMapper.selectPage(new Query<ZhuanyeEntity>(params).getPage(),wrapper);
        Page<ZhuanyeView> pageVo = (Page<ZhuanyeView>)page.convert(ZhuanyeView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<ZhuanyeVO> selectListVO(QueryWrapper<ZhuanyeEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZhuanyeVO selectVO(QueryWrapper<ZhuanyeEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZhuanyeView> selectListView(QueryWrapper<ZhuanyeEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZhuanyeView selectView(QueryWrapper<ZhuanyeEntity> wrapper) {
        ZhuanyeEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new ZhuanyeView(entity);
        } else {
            return null;
        }
	}


}
