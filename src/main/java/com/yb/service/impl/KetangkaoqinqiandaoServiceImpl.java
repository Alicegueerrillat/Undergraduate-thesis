package com.yb.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


import com.yb.dao.KetangkaoqinqiandaoDao;
import com.yb.entity.KetangkaoqinqiandaoEntity;
import com.yb.service.KetangkaoqinqiandaoService;
import com.yb.entity.vo.KetangkaoqinqiandaoVO;
import com.yb.entity.view.KetangkaoqinqiandaoView;

@Service("ketangkaoqinqiandaoService")
public class KetangkaoqinqiandaoServiceImpl extends ServiceImpl<KetangkaoqinqiandaoDao, KetangkaoqinqiandaoEntity> implements KetangkaoqinqiandaoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KetangkaoqinqiandaoEntity> page = baseMapper.selectPage(new Query<KetangkaoqinqiandaoEntity>(params).getPage(),new QueryWrapper<KetangkaoqinqiandaoEntity>());
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, QueryWrapper<KetangkaoqinqiandaoEntity> wrapper) {
        Page<KetangkaoqinqiandaoEntity> page = baseMapper.selectPage(new Query<KetangkaoqinqiandaoEntity>(params).getPage(),wrapper);
        Page<KetangkaoqinqiandaoView> pageVo = (Page<KetangkaoqinqiandaoView>)page.convert(KetangkaoqinqiandaoView::new);
        return new PageUtils(pageVo);
 	}

    
    @Override
	public List<KetangkaoqinqiandaoVO> selectListVO(QueryWrapper<KetangkaoqinqiandaoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KetangkaoqinqiandaoVO selectVO(QueryWrapper<KetangkaoqinqiandaoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KetangkaoqinqiandaoView> selectListView(QueryWrapper<KetangkaoqinqiandaoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KetangkaoqinqiandaoView selectView(QueryWrapper<KetangkaoqinqiandaoEntity> wrapper) {
        KetangkaoqinqiandaoEntity entity = baseMapper.selectOne(wrapper);
        if (null != entity) {
            return new KetangkaoqinqiandaoView(entity);
        } else {
            return null;
        }
	}


}
