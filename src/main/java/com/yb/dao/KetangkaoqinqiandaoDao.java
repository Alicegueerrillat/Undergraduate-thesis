package com.yb.dao;

import com.yb.entity.KetangkaoqinqiandaoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.ibatis.annotations.Param;
import com.yb.entity.vo.KetangkaoqinqiandaoVO;
import com.yb.entity.view.KetangkaoqinqiandaoView;


/**
 * 课堂考勤签到
 * 
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
public interface KetangkaoqinqiandaoDao extends BaseMapper<KetangkaoqinqiandaoEntity> {
	
	List<KetangkaoqinqiandaoVO> selectListVO(@Param("ew") QueryWrapper<KetangkaoqinqiandaoEntity> wrapper);
	
	KetangkaoqinqiandaoVO selectVO(@Param("ew") QueryWrapper<KetangkaoqinqiandaoEntity> wrapper);
	
	List<KetangkaoqinqiandaoView> selectListView(@Param("ew") QueryWrapper<KetangkaoqinqiandaoEntity> wrapper);

	List<KetangkaoqinqiandaoView> selectListView(Page page,@Param("ew") QueryWrapper<KetangkaoqinqiandaoEntity> wrapper);

	
	KetangkaoqinqiandaoView selectView(@Param("ew") QueryWrapper<KetangkaoqinqiandaoEntity> wrapper);
	

}
