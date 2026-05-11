package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.StoreupEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.StoreupVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.StoreupView;


/**
 * 收藏表
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface StoreupService extends IService<StoreupEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<StoreupVO> selectListVO(QueryWrapper<StoreupEntity> wrapper);
   	
   	StoreupVO selectVO(@Param("ew") QueryWrapper<StoreupEntity> wrapper);
   	
   	List<StoreupView> selectListView(QueryWrapper<StoreupEntity> wrapper);
   	
   	StoreupView selectView(@Param("ew") QueryWrapper<StoreupEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<StoreupEntity> wrapper);

   	

}

