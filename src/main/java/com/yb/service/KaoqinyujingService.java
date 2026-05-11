package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.KaoqinyujingEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.KaoqinyujingVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.KaoqinyujingView;


/**
 * 考勤预警
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface KaoqinyujingService extends IService<KaoqinyujingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KaoqinyujingVO> selectListVO(QueryWrapper<KaoqinyujingEntity> wrapper);
   	
   	KaoqinyujingVO selectVO(@Param("ew") QueryWrapper<KaoqinyujingEntity> wrapper);
   	
   	List<KaoqinyujingView> selectListView(QueryWrapper<KaoqinyujingEntity> wrapper);
   	
   	KaoqinyujingView selectView(@Param("ew") QueryWrapper<KaoqinyujingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<KaoqinyujingEntity> wrapper);

   	

}

