package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.KechengxinxiDao;
import com.yb.entity.KechengxinxiEntity;
import com.yb.service.KechengxinxiService;
import com.yb.entity.vo.KechengxinxiVO;
import com.yb.entity.view.KechengxinxiView;

@Service("kechengxinxiService")
public class KechengxinxiServiceImpl extends ServiceImpl<KechengxinxiDao, KechengxinxiEntity> implements KechengxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KechengxinxiEntity> page = baseMapper.selectPage(new Query<KechengxinxiEntity>(params).getPage(),new QueryWrapper<KechengxinxiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<KechengxinxiEntity> wrapper) {
        Page<KechengxinxiEntity> page = baseMapper.selectPage(new Query<KechengxinxiEntity>(params).getPage(),wrapper);
        Page<KechengxinxiView> pageVo = (Page<KechengxinxiView>)page.convert(KechengxinxiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<KechengxinxiVO> selectListVO(QueryWrapper<KechengxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KechengxinxiVO selectVO(QueryWrapper<KechengxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KechengxinxiView> selectListView(QueryWrapper<KechengxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KechengxinxiView selectView(QueryWrapper<KechengxinxiEntity> wrapper) {
        KechengxinxiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new KechengxinxiView(entity);
        } else {
            return null;
        }
	}


}
