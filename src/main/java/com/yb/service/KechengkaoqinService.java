package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.KechengkaoqinEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.KechengkaoqinVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.KechengkaoqinView;


/**
 * 课程考勤
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface KechengkaoqinService extends IService<KechengkaoqinEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KechengkaoqinVO> selectListVO(QueryWrapper<KechengkaoqinEntity> wrapper);
   	
   	KechengkaoqinVO selectVO(@Param("ew") QueryWrapper<KechengkaoqinEntity> wrapper);
   	
   	List<KechengkaoqinView> selectListView(QueryWrapper<KechengkaoqinEntity> wrapper);
   	
   	KechengkaoqinView selectView(@Param("ew") QueryWrapper<KechengkaoqinEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<KechengkaoqinEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,QueryWrapper<KechengkaoqinEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,QueryWrapper<KechengkaoqinEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,QueryWrapper<KechengkaoqinEntity> wrapper);



}

