package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.KebiaoxinixEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.KebiaoxinixVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.KebiaoxinixView;


/**
 * 课表信息
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface KebiaoxinixService extends IService<KebiaoxinixEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KebiaoxinixVO> selectListVO(QueryWrapper<KebiaoxinixEntity> wrapper);
   	
   	KebiaoxinixVO selectVO(@Param("ew") QueryWrapper<KebiaoxinixEntity> wrapper);
   	
   	List<KebiaoxinixView> selectListView(QueryWrapper<KebiaoxinixEntity> wrapper);
   	
   	KebiaoxinixView selectView(@Param("ew") QueryWrapper<KebiaoxinixEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<KebiaoxinixEntity> wrapper);

   	

}

