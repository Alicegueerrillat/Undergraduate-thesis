package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.XueyuanEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.XueyuanVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.XueyuanView;


/**
 * 学院
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface XueyuanService extends IService<XueyuanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XueyuanVO> selectListVO(QueryWrapper<XueyuanEntity> wrapper);
   	
   	XueyuanVO selectVO(@Param("ew") QueryWrapper<XueyuanEntity> wrapper);
   	
   	List<XueyuanView> selectListView(QueryWrapper<XueyuanEntity> wrapper);
   	
   	XueyuanView selectView(@Param("ew") QueryWrapper<XueyuanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<XueyuanEntity> wrapper);

   	

}

