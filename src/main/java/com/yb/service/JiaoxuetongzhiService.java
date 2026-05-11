package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.JiaoxuetongzhiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.JiaoxuetongzhiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.JiaoxuetongzhiView;


/**
 * 教学通知
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface JiaoxuetongzhiService extends IService<JiaoxuetongzhiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiaoxuetongzhiVO> selectListVO(QueryWrapper<JiaoxuetongzhiEntity> wrapper);
   	
   	JiaoxuetongzhiVO selectVO(@Param("ew") QueryWrapper<JiaoxuetongzhiEntity> wrapper);
   	
   	List<JiaoxuetongzhiView> selectListView(QueryWrapper<JiaoxuetongzhiEntity> wrapper);
   	
   	JiaoxuetongzhiView selectView(@Param("ew") QueryWrapper<JiaoxuetongzhiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<JiaoxuetongzhiEntity> wrapper);

   	

}

