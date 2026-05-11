package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.ZhuanyeEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.ZhuanyeVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.ZhuanyeView;


/**
 * 专业
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface ZhuanyeService extends IService<ZhuanyeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhuanyeVO> selectListVO(QueryWrapper<ZhuanyeEntity> wrapper);
   	
   	ZhuanyeVO selectVO(@Param("ew") QueryWrapper<ZhuanyeEntity> wrapper);
   	
   	List<ZhuanyeView> selectListView(QueryWrapper<ZhuanyeEntity> wrapper);
   	
   	ZhuanyeView selectView(@Param("ew") QueryWrapper<ZhuanyeEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<ZhuanyeEntity> wrapper);

   	

}

