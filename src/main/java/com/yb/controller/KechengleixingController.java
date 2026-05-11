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

import com.yb.entity.KechengleixingEntity;
import com.yb.entity.view.KechengleixingView;

import com.yb.service.KechengleixingService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 课程类型
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
@RestController
@RequestMapping("/kechengleixing")
public class KechengleixingController {
    @Autowired
    private KechengleixingService kechengleixingService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KechengleixingEntity kechengleixing,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<KechengleixingEntity> ew = new QueryWrapper<KechengleixingEntity>();


        //查询结果
		PageUtils page = kechengleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengleixing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,KechengleixingEntity kechengleixing,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<KechengleixingEntity> ew = new QueryWrapper<KechengleixingEntity>();

        //查询结果
		PageUtils page = kechengleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengleixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KechengleixingEntity kechengleixing){
       	QueryWrapper<KechengleixingEntity> ew = new QueryWrapper<KechengleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kechengleixing, "kechengleixing"));
        return R.ok().put("data", kechengleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KechengleixingEntity kechengleixing){
        QueryWrapper< KechengleixingEntity> ew = new QueryWrapper< KechengleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kechengleixing, "kechengleixing"));
		KechengleixingView kechengleixingView =  kechengleixingService.selectView(ew);
		return R.ok("查询课程类型成功").put("data", kechengleixingView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KechengleixingEntity kechengleixing = kechengleixingService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kechengleixing,deSens);
        return R.ok().put("data", kechengleixing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KechengleixingEntity kechengleixing = kechengleixingService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kechengleixing,deSens);
        return R.ok().put("data", kechengleixing);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KechengleixingEntity kechengleixing, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(kechengleixingService.count(new QueryWrapper<KechengleixingEntity>().eq("kechengleixing", kechengleixing.getKechengleixing()))>0) {
            return R.error("课程类型已存在");
        }
        //ValidatorUtils.validateEntity(kechengleixing);
        kechengleixingService.save(kechengleixing);
        return R.ok().put("data",kechengleixing.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KechengleixingEntity kechengleixing, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(kechengleixingService.count(new QueryWrapper<KechengleixingEntity>().eq("kechengleixing", kechengleixing.getKechengleixing()))>0) {
            return R.error("课程类型已存在");
        }
        //ValidatorUtils.validateEntity(kechengleixing);
        kechengleixingService.save(kechengleixing);
        return R.ok().put("data",kechengleixing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KechengleixingEntity kechengleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengleixing);
        //验证字段唯一性，否则返回错误信息
        if(kechengleixingService.count(new QueryWrapper<KechengleixingEntity>().ne("id", kechengleixing.getId()).eq("kechengleixing", kechengleixing.getKechengleixing()))>0) {
            return R.error("课程类型已存在");
        }
        //全部更新
        kechengleixingService.updateById(kechengleixing);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kechengleixingService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
