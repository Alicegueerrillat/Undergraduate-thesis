package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ChengjixinxiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ChengjixinxiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ChengjixinxiView;


/**
 * 成绩信息
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface ChengjixinxiService extends IService<ChengjixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChengjixinxiVO> selectListVO(QueryWrapper<ChengjixinxiEntity> wrapper);
   	
   	ChengjixinxiVO selectVO(@Param("ew") QueryWrapper<ChengjixinxiEntity> wrapper);
   	
   	List<ChengjixinxiView> selectListView(QueryWrapper<ChengjixinxiEntity> wrapper);
   	
   	ChengjixinxiView selectView(@Param("ew") QueryWrapper<ChengjixinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ChengjixinxiEntity> wrapper);

   	

}

