package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.QingjiaxinxiDao;
import com.yb.entity.QingjiaxinxiEntity;
import com.yb.service.QingjiaxinxiService;
import com.yb.entity.vo.QingjiaxinxiVO;
import com.yb.entity.view.QingjiaxinxiView;

@Service("qingjiaxinxiService")
public class QingjiaxinxiServiceImpl extends ServiceImpl<QingjiaxinxiDao, QingjiaxinxiEntity> implements QingjiaxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QingjiaxinxiEntity> page = baseMapper.selectPage(new Query<QingjiaxinxiEntity>(params).getPage(),new QueryWrapper<QingjiaxinxiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<QingjiaxinxiEntity> wrapper) {
        Page<QingjiaxinxiEntity> page = baseMapper.selectPage(new Query<QingjiaxinxiEntity>(params).getPage(),wrapper);
        Page<QingjiaxinxiView> pageVo = (Page<QingjiaxinxiView>)page.convert(QingjiaxinxiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<QingjiaxinxiVO> selectListVO(QueryWrapper<QingjiaxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public QingjiaxinxiVO selectVO(QueryWrapper<QingjiaxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<QingjiaxinxiView> selectListView(QueryWrapper<QingjiaxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QingjiaxinxiView selectView(QueryWrapper<QingjiaxinxiEntity> wrapper) {
        QingjiaxinxiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new QingjiaxinxiView(entity);
        } else {
            return null;
        }
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, QueryWrapper<QingjiaxinxiEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, QueryWrapper<QingjiaxinxiEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, QueryWrapper<QingjiaxinxiEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
