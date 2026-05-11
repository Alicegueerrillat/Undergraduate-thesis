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

import com.yb.entity.BanjiEntity;
import com.yb.entity.view.BanjiView;

import com.yb.service.BanjiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 班级
 * 后端接口
 * @author 
 * @email 
 * @date
 */
@RestController
@RequestMapping("/banji")
public class BanjiController {
    @Autowired
    private BanjiService banjiService;


    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BanjiEntity banji,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<BanjiEntity> ew = new QueryWrapper<BanjiEntity>();


        //查询结果
		PageUtils page = banjiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, banji), params), params));
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
    public R list(@RequestParam Map<String, Object> params,BanjiEntity banji,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<BanjiEntity> ew = new QueryWrapper<BanjiEntity>();

        //查询结果
		PageUtils page = banjiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, banji), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BanjiEntity banji){
       	QueryWrapper<BanjiEntity> ew = new QueryWrapper<BanjiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( banji, "banji"));
        return R.ok().put("data", banjiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BanjiEntity banji){
        QueryWrapper< BanjiEntity> ew = new QueryWrapper< BanjiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( banji, "banji"));
		BanjiView banjiView =  banjiService.selectView(ew);
		return R.ok("查询班级成功").put("data", banjiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BanjiEntity banji = banjiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(banji,deSens);
        return R.ok().put("data", banji);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BanjiEntity banji = banjiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(banji,deSens);
        return R.ok().put("data", banji);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BanjiEntity banji, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(banjiService.count(new QueryWrapper<BanjiEntity>().eq("banji", banji.getBanji()))>0) {
            return R.error("班级已存在");
        }
        //ValidatorUtils.validateEntity(banji);
        banjiService.save(banji);
        return R.ok().put("data",banji.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BanjiEntity banji, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(banjiService.count(new QueryWrapper<BanjiEntity>().eq("banji", banji.getBanji()))>0) {
            return R.error("班级已存在");
        }
        //ValidatorUtils.validateEntity(banji);
        banjiService.save(banji);
        return R.ok().put("data",banji.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BanjiEntity banji, HttpServletRequest request){
        //ValidatorUtils.validateEntity(banji);
        //验证字段唯一性，否则返回错误信息
        if(banjiService.count(new QueryWrapper<BanjiEntity>().ne("id", banji.getId()).eq("banji", banji.getBanji()))>0) {
            return R.error("班级已存在");
        }
        //全部更新
        banjiService.updateById(banji);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        banjiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
