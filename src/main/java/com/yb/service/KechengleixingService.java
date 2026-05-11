package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.KechengleixingEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.KechengleixingVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.KechengleixingView;


/**
 * 课程类型
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface KechengleixingService extends IService<KechengleixingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KechengleixingVO> selectListVO(QueryWrapper<KechengleixingEntity> wrapper);
   	
   	KechengleixingVO selectVO(@Param("ew") QueryWrapper<KechengleixingEntity> wrapper);
   	
   	List<KechengleixingView> selectListView(QueryWrapper<KechengleixingEntity> wrapper);
   	
   	KechengleixingView selectView(@Param("ew") QueryWrapper<KechengleixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<KechengleixingEntity> wrapper);

   	

}

