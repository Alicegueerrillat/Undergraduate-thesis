package com.yb.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import com.yb.utils.*;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.yb.annotation.IgnoreAuth;

import com.yb.entity.KetangkaoqinfaqiEntity;
import com.yb.entity.view.KetangkaoqinfaqiView;

import com.yb.service.KetangkaoqinfaqiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 课堂考勤发起
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
@RestController
@RequestMapping("/ketangkaoqinfaqi")
public class KetangkaoqinfaqiController {
    @Autowired
    private KetangkaoqinfaqiService ketangkaoqinfaqiService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KetangkaoqinfaqiEntity ketangkaoqinfaqi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			ketangkaoqinfaqi.setJiaoshizhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			ketangkaoqinfaqi.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<KetangkaoqinfaqiEntity> ew = new QueryWrapper<KetangkaoqinfaqiEntity>();


        //查询结果
		PageUtils page = ketangkaoqinfaqiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ketangkaoqinfaqi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KetangkaoqinfaqiEntity ketangkaoqinfaqi,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fabushijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fabushijianend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date youxiaoshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date youxiaoshijianend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<KetangkaoqinfaqiEntity> ew = new QueryWrapper<KetangkaoqinfaqiEntity>();
        if(fabushijianstart!=null) ew.ge("fabushijian", fabushijianstart);
        if(fabushijianend!=null) ew.le("fabushijian", fabushijianend);
        if(youxiaoshijianstart!=null) ew.ge("youxiaoshijian", youxiaoshijianstart);
        if(youxiaoshijianend!=null) ew.le("youxiaoshijian", youxiaoshijianend);

        //查询结果
		PageUtils page = ketangkaoqinfaqiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ketangkaoqinfaqi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KetangkaoqinfaqiEntity ketangkaoqinfaqi){
       	QueryWrapper<KetangkaoqinfaqiEntity> ew = new QueryWrapper<KetangkaoqinfaqiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( ketangkaoqinfaqi, "ketangkaoqinfaqi"));
        return R.ok().put("data", ketangkaoqinfaqiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KetangkaoqinfaqiEntity ketangkaoqinfaqi){
        QueryWrapper< KetangkaoqinfaqiEntity> ew = new QueryWrapper< KetangkaoqinfaqiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( ketangkaoqinfaqi, "ketangkaoqinfaqi"));
		KetangkaoqinfaqiView ketangkaoqinfaqiView =  ketangkaoqinfaqiService.selectView(ew);
		return R.ok("查询课堂考勤发起成功").put("data", ketangkaoqinfaqiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KetangkaoqinfaqiEntity ketangkaoqinfaqi = ketangkaoqinfaqiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ketangkaoqinfaqi,deSens);
        return R.ok().put("data", ketangkaoqinfaqi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KetangkaoqinfaqiEntity ketangkaoqinfaqi = ketangkaoqinfaqiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ketangkaoqinfaqi,deSens);
        return R.ok().put("data", ketangkaoqinfaqi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KetangkaoqinfaqiEntity ketangkaoqinfaqi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ketangkaoqinfaqi);
        ketangkaoqinfaqiService.save(ketangkaoqinfaqi);
        return R.ok().put("data",ketangkaoqinfaqi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KetangkaoqinfaqiEntity ketangkaoqinfaqi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ketangkaoqinfaqi);
        ketangkaoqinfaqiService.save(ketangkaoqinfaqi);
        return R.ok().put("data",ketangkaoqinfaqi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KetangkaoqinfaqiEntity ketangkaoqinfaqi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ketangkaoqinfaqi);
        //全部更新
        ketangkaoqinfaqiService.updateById(ketangkaoqinfaqi);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        ketangkaoqinfaqiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
