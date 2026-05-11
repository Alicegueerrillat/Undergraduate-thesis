package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.KaoqinshensuEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.KaoqinshensuVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.KaoqinshensuView;


/**
 * 考勤申诉
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface KaoqinshensuService extends IService<KaoqinshensuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KaoqinshensuVO> selectListVO(QueryWrapper<KaoqinshensuEntity> wrapper);
   	
   	KaoqinshensuVO selectVO(@Param("ew") QueryWrapper<KaoqinshensuEntity> wrapper);
   	
   	List<KaoqinshensuView> selectListView(QueryWrapper<KaoqinshensuEntity> wrapper);
   	
   	KaoqinshensuView selectView(@Param("ew") QueryWrapper<KaoqinshensuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<KaoqinshensuEntity> wrapper);

   	

}

