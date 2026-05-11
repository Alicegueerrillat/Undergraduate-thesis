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

import com.yb.entity.KechengxuankeEntity;
import com.yb.entity.view.KechengxuankeView;

import com.yb.service.KechengxuankeService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 课程选课
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
@RestController
@RequestMapping("/kechengxuanke")
public class KechengxuankeController {
    @Autowired
    private KechengxuankeService kechengxuankeService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KechengxuankeEntity kechengxuanke,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			kechengxuanke.setJiaoshizhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			kechengxuanke.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<KechengxuankeEntity> ew = new QueryWrapper<KechengxuankeEntity>();


        //查询结果
		PageUtils page = kechengxuankeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengxuanke), params), params));
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
    public R list(@RequestParam Map<String, Object> params,KechengxuankeEntity kechengxuanke,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date xuankeshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date xuankeshijianend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<KechengxuankeEntity> ew = new QueryWrapper<KechengxuankeEntity>();
        if(xuankeshijianstart!=null) ew.ge("xuankeshijian", xuankeshijianstart);
        if(xuankeshijianend!=null) ew.le("xuankeshijian", xuankeshijianend);

        //查询结果
		PageUtils page = kechengxuankeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengxuanke), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KechengxuankeEntity kechengxuanke){
       	QueryWrapper<KechengxuankeEntity> ew = new QueryWrapper<KechengxuankeEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kechengxuanke, "kechengxuanke"));
        return R.ok().put("data", kechengxuankeService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KechengxuankeEntity kechengxuanke){
        QueryWrapper< KechengxuankeEntity> ew = new QueryWrapper< KechengxuankeEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kechengxuanke, "kechengxuanke"));
		KechengxuankeView kechengxuankeView =  kechengxuankeService.selectView(ew);
		return R.ok("查询课程选课成功").put("data", kechengxuankeView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KechengxuankeEntity kechengxuanke = kechengxuankeService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kechengxuanke,deSens);
        return R.ok().put("data", kechengxuanke);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KechengxuankeEntity kechengxuanke = kechengxuankeService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kechengxuanke,deSens);
        return R.ok().put("data", kechengxuanke);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KechengxuankeEntity kechengxuanke, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengxuanke);
        kechengxuankeService.save(kechengxuanke);
        return R.ok().put("data",kechengxuanke.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KechengxuankeEntity kechengxuanke, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengxuanke);
        kechengxuankeService.save(kechengxuanke);
        return R.ok().put("data",kechengxuanke.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KechengxuankeEntity kechengxuanke, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengxuanke);
        //全部更新
        kechengxuankeService.updateById(kechengxuanke);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kechengxuankeService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
