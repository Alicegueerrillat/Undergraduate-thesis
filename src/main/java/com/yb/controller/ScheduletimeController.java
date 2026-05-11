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

import com.yb.entity.ScheduletimeEntity;
import com.yb.entity.view.ScheduletimeView;

import com.yb.service.ScheduletimeService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 课表时间
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
@RestController
@RequestMapping("/scheduletime")
public class ScheduletimeController {
    @Autowired
    private ScheduletimeService scheduletimeService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ScheduletimeEntity scheduletime,
                @RequestParam(required = false) Double sectionnumstart,
                @RequestParam(required = false) Double sectionnumend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ScheduletimeEntity> ew = new QueryWrapper<ScheduletimeEntity>();
        if(sectionnumstart!=null) ew.ge("sectionnum", sectionnumstart);
        if(sectionnumend!=null) ew.le("sectionnum", sectionnumend);


        //查询结果
		PageUtils page = scheduletimeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, scheduletime), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ScheduletimeEntity scheduletime,
                @RequestParam(required = false) Double sectionnumstart,
                @RequestParam(required = false) Double sectionnumend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<ScheduletimeEntity> ew = new QueryWrapper<ScheduletimeEntity>();
        if(sectionnumstart!=null) ew.ge("sectionnum", sectionnumstart);
        if(sectionnumend!=null) ew.le("sectionnum", sectionnumend);

        //查询结果
		PageUtils page = scheduletimeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, scheduletime), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ScheduletimeEntity scheduletime){
       	QueryWrapper<ScheduletimeEntity> ew = new QueryWrapper<ScheduletimeEntity>();
      	ew.allEq(MPUtil.allEQMapPre( scheduletime, "scheduletime"));
        return R.ok().put("data", scheduletimeService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ScheduletimeEntity scheduletime){
        QueryWrapper< ScheduletimeEntity> ew = new QueryWrapper< ScheduletimeEntity>();
 		ew.allEq(MPUtil.allEQMapPre( scheduletime, "scheduletime"));
		ScheduletimeView scheduletimeView =  scheduletimeService.selectView(ew);
		return R.ok("查询课表时间成功").put("data", scheduletimeView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ScheduletimeEntity scheduletime = scheduletimeService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(scheduletime,deSens);
        return R.ok().put("data", scheduletime);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ScheduletimeEntity scheduletime = scheduletimeService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(scheduletime,deSens);
        return R.ok().put("data", scheduletime);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ScheduletimeEntity scheduletime, HttpServletRequest request){
        //ValidatorUtils.validateEntity(scheduletime);
        scheduletimeService.save(scheduletime);
        return R.ok().put("data",scheduletime.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ScheduletimeEntity scheduletime, HttpServletRequest request){
        //ValidatorUtils.validateEntity(scheduletime);
        scheduletimeService.save(scheduletime);
        return R.ok().put("data",scheduletime.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ScheduletimeEntity scheduletime, HttpServletRequest request){
        //ValidatorUtils.validateEntity(scheduletime);
        //全部更新
        scheduletimeService.updateById(scheduletime);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        scheduletimeService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
