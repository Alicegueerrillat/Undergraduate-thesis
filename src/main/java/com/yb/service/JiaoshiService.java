package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.JiaoshiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.JiaoshiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.JiaoshiView;


/**
 * 教师
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface JiaoshiService extends IService<JiaoshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiaoshiVO> selectListVO(QueryWrapper<JiaoshiEntity> wrapper);
   	
   	JiaoshiVO selectVO(@Param("ew") QueryWrapper<JiaoshiEntity> wrapper);
   	
   	List<JiaoshiView> selectListView(QueryWrapper<JiaoshiEntity> wrapper);
   	
   	JiaoshiView selectView(@Param("ew") QueryWrapper<JiaoshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<JiaoshiEntity> wrapper);

   	

}

