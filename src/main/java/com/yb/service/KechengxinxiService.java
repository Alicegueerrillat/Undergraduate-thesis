package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.KechengxinxiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.KechengxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.KechengxinxiView;


/**
 * 课程信息
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface KechengxinxiService extends IService<KechengxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KechengxinxiVO> selectListVO(QueryWrapper<KechengxinxiEntity> wrapper);
   	
   	KechengxinxiVO selectVO(@Param("ew") QueryWrapper<KechengxinxiEntity> wrapper);
   	
   	List<KechengxinxiView> selectListView(QueryWrapper<KechengxinxiEntity> wrapper);
   	
   	KechengxinxiView selectView(@Param("ew") QueryWrapper<KechengxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<KechengxinxiEntity> wrapper);

   	

}

