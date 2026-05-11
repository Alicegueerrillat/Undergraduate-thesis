package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.KechengkaoqinDao;
import com.yb.entity.KechengkaoqinEntity;
import com.yb.service.KechengkaoqinService;
import com.yb.entity.vo.KechengkaoqinVO;
import com.yb.entity.view.KechengkaoqinView;

@Service("kechengkaoqinService")
public class KechengkaoqinServiceImpl extends ServiceImpl<KechengkaoqinDao, KechengkaoqinEntity> implements KechengkaoqinService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KechengkaoqinEntity> page = baseMapper.selectPage(new Query<KechengkaoqinEntity>(params).getPage(),new QueryWrapper<KechengkaoqinEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<KechengkaoqinEntity> wrapper) {
        Page<KechengkaoqinEntity> page = baseMapper.selectPage(new Query<KechengkaoqinEntity>(params).getPage(),wrapper);
        Page<KechengkaoqinView> pageVo = (Page<KechengkaoqinView>)page.convert(KechengkaoqinView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<KechengkaoqinVO> selectListVO(QueryWrapper<KechengkaoqinEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KechengkaoqinVO selectVO(QueryWrapper<KechengkaoqinEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KechengkaoqinView> selectListView(QueryWrapper<KechengkaoqinEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KechengkaoqinView selectView(QueryWrapper<KechengkaoqinEntity> wrapper) {
        KechengkaoqinEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new KechengkaoqinView(entity);
        } else {
            return null;
        }
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, QueryWrapper<KechengkaoqinEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, QueryWrapper<KechengkaoqinEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, QueryWrapper<KechengkaoqinEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
