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

import com.yb.entity.JiaoxuetongzhiEntity;
import com.yb.entity.view.JiaoxuetongzhiView;

import com.yb.service.JiaoxuetongzhiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 教学通知
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
@RestController
@RequestMapping("/jiaoxuetongzhi")
public class JiaoxuetongzhiController {
    @Autowired
    private JiaoxuetongzhiService jiaoxuetongzhiService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiaoxuetongzhiEntity jiaoxuetongzhi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			jiaoxuetongzhi.setJiaoshizhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<JiaoxuetongzhiEntity> ew = new QueryWrapper<JiaoxuetongzhiEntity>();


        //查询结果
		PageUtils page = jiaoxuetongzhiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoxuetongzhi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JiaoxuetongzhiEntity jiaoxuetongzhi,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date tongzhishijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date tongzhishijianend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<JiaoxuetongzhiEntity> ew = new QueryWrapper<JiaoxuetongzhiEntity>();
        if(tongzhishijianstart!=null) ew.ge("tongzhishijian", tongzhishijianstart);
        if(tongzhishijianend!=null) ew.le("tongzhishijian", tongzhishijianend);

        //查询结果
		PageUtils page = jiaoxuetongzhiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoxuetongzhi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiaoxuetongzhiEntity jiaoxuetongzhi){
       	QueryWrapper<JiaoxuetongzhiEntity> ew = new QueryWrapper<JiaoxuetongzhiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiaoxuetongzhi, "jiaoxuetongzhi"));
        return R.ok().put("data", jiaoxuetongzhiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiaoxuetongzhiEntity jiaoxuetongzhi){
        QueryWrapper< JiaoxuetongzhiEntity> ew = new QueryWrapper< JiaoxuetongzhiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiaoxuetongzhi, "jiaoxuetongzhi"));
		JiaoxuetongzhiView jiaoxuetongzhiView =  jiaoxuetongzhiService.selectView(ew);
		return R.ok("查询教学通知成功").put("data", jiaoxuetongzhiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiaoxuetongzhiEntity jiaoxuetongzhi = jiaoxuetongzhiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jiaoxuetongzhi,deSens);
        return R.ok().put("data", jiaoxuetongzhi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiaoxuetongzhiEntity jiaoxuetongzhi = jiaoxuetongzhiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jiaoxuetongzhi,deSens);
        return R.ok().put("data", jiaoxuetongzhi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiaoxuetongzhiEntity jiaoxuetongzhi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiaoxuetongzhi);
        jiaoxuetongzhiService.save(jiaoxuetongzhi);
        return R.ok().put("data",jiaoxuetongzhi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiaoxuetongzhiEntity jiaoxuetongzhi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiaoxuetongzhi);
        jiaoxuetongzhiService.save(jiaoxuetongzhi);
        return R.ok().put("data",jiaoxuetongzhi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiaoxuetongzhiEntity jiaoxuetongzhi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiaoxuetongzhi);
        //全部更新
        jiaoxuetongzhiService.updateById(jiaoxuetongzhi);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiaoxuetongzhiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
