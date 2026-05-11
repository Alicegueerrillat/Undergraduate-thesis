package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.UsersEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.UsersVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.UsersView;


/**
 * 管理员
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface UsersService extends IService<UsersEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<UsersVO> selectListVO(QueryWrapper<UsersEntity> wrapper);
   	
   	UsersVO selectVO(@Param("ew") QueryWrapper<UsersEntity> wrapper);
   	
   	List<UsersView> selectListView(QueryWrapper<UsersEntity> wrapper);
   	
   	UsersView selectView(@Param("ew") QueryWrapper<UsersEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<UsersEntity> wrapper);

   	

}

