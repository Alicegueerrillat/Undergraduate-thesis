package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.KechengxuankeEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.KechengxuankeVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.KechengxuankeView;


/**
 * 课程选课
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface KechengxuankeService extends IService<KechengxuankeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KechengxuankeVO> selectListVO(QueryWrapper<KechengxuankeEntity> wrapper);
   	
   	KechengxuankeVO selectVO(@Param("ew") QueryWrapper<KechengxuankeEntity> wrapper);
   	
   	List<KechengxuankeView> selectListView(QueryWrapper<KechengxuankeEntity> wrapper);
   	
   	KechengxuankeView selectView(@Param("ew") QueryWrapper<KechengxuankeEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<KechengxuankeEntity> wrapper);

   	

}

