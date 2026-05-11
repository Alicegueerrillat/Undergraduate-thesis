package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.XuexijinduEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.XuexijinduVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.XuexijinduView;


/**
 * 学习进度
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface XuexijinduService extends IService<XuexijinduEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XuexijinduVO> selectListVO(QueryWrapper<XuexijinduEntity> wrapper);
   	
   	XuexijinduVO selectVO(@Param("ew") QueryWrapper<XuexijinduEntity> wrapper);
   	
   	List<XuexijinduView> selectListView(QueryWrapper<XuexijinduEntity> wrapper);
   	
   	XuexijinduView selectView(@Param("ew") QueryWrapper<XuexijinduEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<XuexijinduEntity> wrapper);

   	

}

