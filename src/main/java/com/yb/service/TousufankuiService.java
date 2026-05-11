package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.TousufankuiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.TousufankuiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.TousufankuiView;


/**
 * 投诉反馈
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface TousufankuiService extends IService<TousufankuiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<TousufankuiVO> selectListVO(QueryWrapper<TousufankuiEntity> wrapper);
   	
   	TousufankuiVO selectVO(@Param("ew") QueryWrapper<TousufankuiEntity> wrapper);
   	
   	List<TousufankuiView> selectListView(QueryWrapper<TousufankuiEntity> wrapper);
   	
   	TousufankuiView selectView(@Param("ew") QueryWrapper<TousufankuiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<TousufankuiEntity> wrapper);

   	

}

