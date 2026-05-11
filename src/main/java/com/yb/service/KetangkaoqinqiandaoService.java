package com.yb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yb.utils.PageUtils;
import com.yb.entity.KetangkaoqinqiandaoEntity;
import java.util.List;
import java.util.Map;
import com.yb.entity.vo.KetangkaoqinqiandaoVO;
import org.apache.ibatis.annotations.Param;
import com.yb.entity.view.KetangkaoqinqiandaoView;


/**
 * 课堂考勤签到
 *
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface KetangkaoqinqiandaoService extends IService<KetangkaoqinqiandaoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KetangkaoqinqiandaoVO> selectListVO(QueryWrapper<KetangkaoqinqiandaoEntity> wrapper);
   	
   	KetangkaoqinqiandaoVO selectVO(@Param("ew") QueryWrapper<KetangkaoqinqiandaoEntity> wrapper);
   	
   	List<KetangkaoqinqiandaoView> selectListView(QueryWrapper<KetangkaoqinqiandaoEntity> wrapper);
   	
   	KetangkaoqinqiandaoView selectView(@Param("ew") QueryWrapper<KetangkaoqinqiandaoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,QueryWrapper<KetangkaoqinqiandaoEntity> wrapper);

   	

}

