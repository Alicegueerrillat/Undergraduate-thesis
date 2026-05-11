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

import com.yb.entity.KaoqinyujingEntity;
import com.yb.entity.view.KaoqinyujingView;

import com.yb.service.KaoqinyujingService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 考勤预警
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
@RestController
@RequestMapping("/kaoqinyujing")
public class KaoqinyujingController {
    @Autowired
    private KaoqinyujingService kaoqinyujingService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KaoqinyujingEntity kaoqinyujing,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			kaoqinyujing.setJiaoshizhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			kaoqinyujing.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<KaoqinyujingEntity> ew = new QueryWrapper<KaoqinyujingEntity>();


        //查询结果
		PageUtils page = kaoqinyujingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoqinyujing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,KaoqinyujingEntity kaoqinyujing,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date yujingshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date yujingshijianend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<KaoqinyujingEntity> ew = new QueryWrapper<KaoqinyujingEntity>();
        if(yujingshijianstart!=null) ew.ge("yujingshijian", yujingshijianstart);
        if(yujingshijianend!=null) ew.le("yujingshijian", yujingshijianend);

        //查询结果
		PageUtils page = kaoqinyujingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoqinyujing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KaoqinyujingEntity kaoqinyujing){
       	QueryWrapper<KaoqinyujingEntity> ew = new QueryWrapper<KaoqinyujingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kaoqinyujing, "kaoqinyujing"));
        return R.ok().put("data", kaoqinyujingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KaoqinyujingEntity kaoqinyujing){
        QueryWrapper< KaoqinyujingEntity> ew = new QueryWrapper< KaoqinyujingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kaoqinyujing, "kaoqinyujing"));
		KaoqinyujingView kaoqinyujingView =  kaoqinyujingService.selectView(ew);
		return R.ok("查询考勤预警成功").put("data", kaoqinyujingView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KaoqinyujingEntity kaoqinyujing = kaoqinyujingService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kaoqinyujing,deSens);
        return R.ok().put("data", kaoqinyujing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KaoqinyujingEntity kaoqinyujing = kaoqinyujingService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kaoqinyujing,deSens);
        return R.ok().put("data", kaoqinyujing);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KaoqinyujingEntity kaoqinyujing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaoqinyujing);
        kaoqinyujingService.save(kaoqinyujing);
        return R.ok().put("data",kaoqinyujing.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KaoqinyujingEntity kaoqinyujing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaoqinyujing);
        kaoqinyujingService.save(kaoqinyujing);
        return R.ok().put("data",kaoqinyujing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KaoqinyujingEntity kaoqinyujing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaoqinyujing);
        //全部更新
        kaoqinyujingService.updateById(kaoqinyujing);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kaoqinyujingService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
