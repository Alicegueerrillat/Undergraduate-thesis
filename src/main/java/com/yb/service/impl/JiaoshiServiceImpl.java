package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.JiaoshiDao;
import com.yb.entity.JiaoshiEntity;
import com.yb.service.JiaoshiService;
import com.yb.entity.vo.JiaoshiVO;
import com.yb.entity.view.JiaoshiView;

@Service("jiaoshiService")
public class JiaoshiServiceImpl extends ServiceImpl<JiaoshiDao, JiaoshiEntity> implements JiaoshiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiaoshiEntity> page = baseMapper.selectPage(new Query<JiaoshiEntity>(params).getPage(),new QueryWrapper<JiaoshiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<JiaoshiEntity> wrapper) {
        Page<JiaoshiEntity> page = baseMapper.selectPage(new Query<JiaoshiEntity>(params).getPage(),wrapper);
        Page<JiaoshiView> pageVo = (Page<JiaoshiView>)page.convert(JiaoshiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<JiaoshiVO> selectListVO(QueryWrapper<JiaoshiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JiaoshiVO selectVO(QueryWrapper<JiaoshiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JiaoshiView> selectListView(QueryWrapper<JiaoshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiaoshiView selectView(QueryWrapper<JiaoshiEntity> wrapper) {
        JiaoshiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new JiaoshiView(entity);
        } else {
            return null;
        }
	}


}
