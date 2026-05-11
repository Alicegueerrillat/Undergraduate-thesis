package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.XueshengEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.XueshengVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.XueshengView;


/**
 * 学生
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface XueshengService extends IService<XueshengEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XueshengVO> selectListVO(QueryWrapper<XueshengEntity> wrapper);
   	
   	XueshengVO selectVO(@Param("ew") QueryWrapper<XueshengEntity> wrapper);
   	
   	List<XueshengView> selectListView(QueryWrapper<XueshengEntity> wrapper);
   	
   	XueshengView selectView(@Param("ew") QueryWrapper<XueshengEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<XueshengEntity> wrapper);

   	

}

