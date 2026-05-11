package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.JiaoxuetongzhiDao;
import com.yb.entity.JiaoxuetongzhiEntity;
import com.yb.service.JiaoxuetongzhiService;
import com.yb.entity.vo.JiaoxuetongzhiVO;
import com.yb.entity.view.JiaoxuetongzhiView;

@Service("jiaoxuetongzhiService")
public class JiaoxuetongzhiServiceImpl extends ServiceImpl<JiaoxuetongzhiDao, JiaoxuetongzhiEntity> implements JiaoxuetongzhiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiaoxuetongzhiEntity> page = baseMapper.selectPage(new Query<JiaoxuetongzhiEntity>(params).getPage(),new QueryWrapper<JiaoxuetongzhiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<JiaoxuetongzhiEntity> wrapper) {
        Page<JiaoxuetongzhiEntity> page = baseMapper.selectPage(new Query<JiaoxuetongzhiEntity>(params).getPage(),wrapper);
        Page<JiaoxuetongzhiView> pageVo = (Page<JiaoxuetongzhiView>)page.convert(JiaoxuetongzhiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<JiaoxuetongzhiVO> selectListVO(QueryWrapper<JiaoxuetongzhiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JiaoxuetongzhiVO selectVO(QueryWrapper<JiaoxuetongzhiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JiaoxuetongzhiView> selectListView(QueryWrapper<JiaoxuetongzhiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiaoxuetongzhiView selectView(QueryWrapper<JiaoxuetongzhiEntity> wrapper) {
        JiaoxuetongzhiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new JiaoxuetongzhiView(entity);
        } else {
            return null;
        }
	}


}
