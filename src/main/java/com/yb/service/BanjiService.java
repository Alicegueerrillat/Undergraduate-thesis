package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.BanjiEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.BanjiVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.BanjiView;


/**
 * 班级
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
public interface BanjiService extends IService<BanjiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BanjiVO> selectListVO(QueryWrapper<BanjiEntity> wrapper);
   	
   	BanjiVO selectVO(@Param("ew") QueryWrapper<BanjiEntity> wrapper);
   	
   	List<BanjiView> selectListView(QueryWrapper<BanjiEntity> wrapper);
   	
   	BanjiView selectView(@Param("ew") QueryWrapper<BanjiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<BanjiEntity> wrapper);

   	

}

