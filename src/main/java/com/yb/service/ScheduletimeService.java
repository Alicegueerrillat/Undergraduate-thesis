package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ScheduletimeEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ScheduletimeVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ScheduletimeView;


/**
 * 课表时间
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface ScheduletimeService extends IService<ScheduletimeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ScheduletimeVO> selectListVO(QueryWrapper<ScheduletimeEntity> wrapper);
   	
   	ScheduletimeVO selectVO(@Param("ew") QueryWrapper<ScheduletimeEntity> wrapper);
   	
   	List<ScheduletimeView> selectListView(QueryWrapper<ScheduletimeEntity> wrapper);
   	
   	ScheduletimeView selectView(@Param("ew") QueryWrapper<ScheduletimeEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ScheduletimeEntity> wrapper);

   	

}

