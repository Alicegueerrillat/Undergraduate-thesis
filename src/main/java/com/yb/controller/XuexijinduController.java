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

import com.yb.entity.XuexijinduEntity;
import com.yb.entity.view.XuexijinduView;

import com.yb.service.XuexijinduService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 学习进度
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
@RestController
@RequestMapping("/xuexijindu")
public class XuexijinduController {
    @Autowired
    private XuexijinduService xuexijinduService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XuexijinduEntity xuexijindu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			xuexijindu.setJiaoshizhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			xuexijindu.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<XuexijinduEntity> ew = new QueryWrapper<XuexijinduEntity>();


        //查询结果
		PageUtils page = xuexijinduService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexijindu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,XuexijinduEntity xuexijindu,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dengjishijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dengjishijianend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<XuexijinduEntity> ew = new QueryWrapper<XuexijinduEntity>();
        if(dengjishijianstart!=null) ew.ge("dengjishijian", dengjishijianstart);
        if(dengjishijianend!=null) ew.le("dengjishijian", dengjishijianend);

        //查询结果
		PageUtils page = xuexijinduService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuexijindu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XuexijinduEntity xuexijindu){
       	QueryWrapper<XuexijinduEntity> ew = new QueryWrapper<XuexijinduEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xuexijindu, "xuexijindu"));
        return R.ok().put("data", xuexijinduService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XuexijinduEntity xuexijindu){
        QueryWrapper< XuexijinduEntity> ew = new QueryWrapper< XuexijinduEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xuexijindu, "xuexijindu"));
		XuexijinduView xuexijinduView =  xuexijinduService.selectView(ew);
		return R.ok("查询学习进度成功").put("data", xuexijinduView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XuexijinduEntity xuexijindu = xuexijinduService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuexijindu,deSens);
        return R.ok().put("data", xuexijindu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XuexijinduEntity xuexijindu = xuexijinduService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuexijindu,deSens);
        return R.ok().put("data", xuexijindu);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XuexijinduEntity xuexijindu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuexijindu);
        xuexijinduService.save(xuexijindu);
        return R.ok().put("data",xuexijindu.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XuexijinduEntity xuexijindu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuexijindu);
        xuexijinduService.save(xuexijindu);
        return R.ok().put("data",xuexijindu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XuexijinduEntity xuexijindu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuexijindu);
        //全部更新
        xuexijinduService.updateById(xuexijindu);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xuexijinduService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
