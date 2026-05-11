package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.BanjiDao;
import com.yb.entity.BanjiEntity;
import com.yb.service.BanjiService;
import com.yb.entity.vo.BanjiVO;
import com.yb.entity.view.BanjiView;

@Service("banjiService")
public class BanjiServiceImpl extends ServiceImpl<BanjiDao, BanjiEntity> implements BanjiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BanjiEntity> page = baseMapper.selectPage(new Query<BanjiEntity>(params).getPage(),new QueryWrapper<BanjiEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<BanjiEntity> wrapper) {
        Page<BanjiEntity> page = baseMapper.selectPage(new Query<BanjiEntity>(params).getPage(),wrapper);
        Page<BanjiView> pageVo = (Page<BanjiView>)page.convert(BanjiView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<BanjiVO> selectListVO(QueryWrapper<BanjiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public BanjiVO selectVO(QueryWrapper<BanjiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BanjiView> selectListView(QueryWrapper<BanjiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BanjiView selectView(QueryWrapper<BanjiEntity> wrapper) {
        BanjiEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new BanjiView(entity);
        } else {
            return null;
        }
	}


}
