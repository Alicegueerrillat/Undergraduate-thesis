package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.QingjiaxinxiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.QingjiaxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.QingjiaxinxiView;


/**
 * 请假信息
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface QingjiaxinxiService extends IService<QingjiaxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QingjiaxinxiVO> selectListVO(QueryWrapper<QingjiaxinxiEntity> wrapper);
   	
   	QingjiaxinxiVO selectVO(@Param("ew") QueryWrapper<QingjiaxinxiEntity> wrapper);
   	
   	List<QingjiaxinxiView> selectListView(QueryWrapper<QingjiaxinxiEntity> wrapper);
   	
   	QingjiaxinxiView selectView(@Param("ew") QueryWrapper<QingjiaxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<QingjiaxinxiEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,QueryWrapper<QingjiaxinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,QueryWrapper<QingjiaxinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,QueryWrapper<QingjiaxinxiEntity> wrapper);



}

