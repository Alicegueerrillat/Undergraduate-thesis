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

import com.yb.entity.ChengjixinxiEntity;
import com.yb.entity.view.ChengjixinxiView;

import com.yb.service.ChengjixinxiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 成绩信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
@RestController
@RequestMapping("/chengjixinxi")
public class ChengjixinxiController {
    @Autowired
    private ChengjixinxiService chengjixinxiService;

    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChengjixinxiEntity chengjixinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			chengjixinxi.setJiaoshizhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			chengjixinxi.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<ChengjixinxiEntity> ew = new QueryWrapper<ChengjixinxiEntity>();


        //查询结果
		PageUtils page = chengjixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chengjixinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ChengjixinxiEntity chengjixinxi,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dengjiriqistart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dengjiriqiend,
                @RequestParam(required = false) Double chengjistart,
                @RequestParam(required = false) Double chengjiend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ChengjixinxiEntity> ew = new QueryWrapper<ChengjixinxiEntity>();
        if(dengjiriqistart!=null) ew.ge("dengjiriqi", dengjiriqistart);
        if(dengjiriqiend!=null) ew.le("dengjiriqi", dengjiriqiend);
        if(chengjistart!=null) ew.ge("chengji", chengjistart);
        if(chengjiend!=null) ew.le("chengji", chengjiend);

        //查询结果
		PageUtils page = chengjixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chengjixinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChengjixinxiEntity chengjixinxi){
       	QueryWrapper<ChengjixinxiEntity> ew = new QueryWrapper<ChengjixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chengjixinxi, "chengjixinxi"));
        return R.ok().put("data", chengjixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChengjixinxiEntity chengjixinxi){
        QueryWrapper< ChengjixinxiEntity> ew = new QueryWrapper< ChengjixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chengjixinxi, "chengjixinxi"));
		ChengjixinxiView chengjixinxiView =  chengjixinxiService.selectView(ew);
		return R.ok("查询成绩信息成功").put("data", chengjixinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChengjixinxiEntity chengjixinxi = chengjixinxiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chengjixinxi,deSens);
        return R.ok().put("data", chengjixinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChengjixinxiEntity chengjixinxi = chengjixinxiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chengjixinxi,deSens);
        return R.ok().put("data", chengjixinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChengjixinxiEntity chengjixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chengjixinxi);
        chengjixinxiService.save(chengjixinxi);
        return R.ok().put("data",chengjixinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChengjixinxiEntity chengjixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chengjixinxi);
        chengjixinxiService.save(chengjixinxi);
        return R.ok().put("data",chengjixinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChengjixinxiEntity chengjixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chengjixinxi);
        //全部更新
        chengjixinxiService.updateById(chengjixinxi);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chengjixinxiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
