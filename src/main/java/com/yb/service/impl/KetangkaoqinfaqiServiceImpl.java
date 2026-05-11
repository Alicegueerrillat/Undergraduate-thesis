package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.KetangkaoqinfaqiDao;
import com.yb.entity.KetangkaoqinfaqiEntity;
import com.yb.service.KetangkaoqinfaqiService;
import com.yb.entity.vo.KetangkaoqinfaqiVO;
import com.yb.entity.view.KetangkaoqinfaqiView;

@Service("ketangkaoqinfaqiService")
public class KetangkaoqinfaqiServiceImpl extends ServiceImpl<KetangkaoqinfaqiDao, KetangkaoqinfaqiEntity> implements KetangkaoqinfaqiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KetangkaoqinfaqiEntity> page = baseMapper.selectPage(new Query<KetangkaoqinfaqiEntity>(params).getPage(),new QueryWrapper<KetangkaoqinfaqiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<KetangkaoqinfaqiEntity> wrapper) {
        Page<KetangkaoqinfaqiEntity> page = baseMapper.selectPage(new Query<KetangkaoqinfaqiEntity>(params).getPage(),wrapper);
        Page<KetangkaoqinfaqiView> pageVo = (Page<KetangkaoqinfaqiView>)page.convert(KetangkaoqinfaqiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<KetangkaoqinfaqiVO> selectListVO(QueryWrapper<KetangkaoqinfaqiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KetangkaoqinfaqiVO selectVO(QueryWrapper<KetangkaoqinfaqiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KetangkaoqinfaqiView> selectListView(QueryWrapper<KetangkaoqinfaqiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KetangkaoqinfaqiView selectView(QueryWrapper<KetangkaoqinfaqiEntity> wrapper) {
        KetangkaoqinfaqiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new KetangkaoqinfaqiView(entity);
        } else {
            return null;
        }
	}


}
