package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.KetangkaoqinfaqiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.KetangkaoqinfaqiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.KetangkaoqinfaqiView;


/**
 * 课堂考勤发起
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface KetangkaoqinfaqiService extends IService<KetangkaoqinfaqiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KetangkaoqinfaqiVO> selectListVO(QueryWrapper<KetangkaoqinfaqiEntity> wrapper);
   	
   	KetangkaoqinfaqiVO selectVO(@Param("ew") QueryWrapper<KetangkaoqinfaqiEntity> wrapper);
   	
   	List<KetangkaoqinfaqiView> selectListView(QueryWrapper<KetangkaoqinfaqiEntity> wrapper);
   	
   	KetangkaoqinfaqiView selectView(@Param("ew") QueryWrapper<KetangkaoqinfaqiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<KetangkaoqinfaqiEntity> wrapper);

   	

}

