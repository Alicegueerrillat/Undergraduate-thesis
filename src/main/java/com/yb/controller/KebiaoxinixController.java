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

import com.yb.entity.KebiaoxinixEntity;
import com.yb.entity.view.KebiaoxinixView;

import com.yb.service.KebiaoxinixService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 课表信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
@RestController
@RequestMapping("/kebiaoxinix")
public class KebiaoxinixController {
    @Autowired
    private KebiaoxinixService kebiaoxinixService;

    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KebiaoxinixEntity kebiaoxinix,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			kebiaoxinix.setJiaoshizhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<KebiaoxinixEntity> ew = new QueryWrapper<KebiaoxinixEntity>();


        //查询结果
		PageUtils page = kebiaoxinixService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kebiaoxinix), params), params));
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
    public R list(@RequestParam Map<String, Object> params,KebiaoxinixEntity kebiaoxinix,
                @RequestParam(required = false) Double weekstart,
                @RequestParam(required = false) Double weekend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<KebiaoxinixEntity> ew = new QueryWrapper<KebiaoxinixEntity>();
        if(weekstart!=null) ew.ge("week", weekstart);
        if(weekend!=null) ew.le("week", weekend);

        //查询结果
		PageUtils page = kebiaoxinixService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kebiaoxinix), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KebiaoxinixEntity kebiaoxinix){
       	QueryWrapper<KebiaoxinixEntity> ew = new QueryWrapper<KebiaoxinixEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kebiaoxinix, "kebiaoxinix"));
        return R.ok().put("data", kebiaoxinixService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KebiaoxinixEntity kebiaoxinix){
        QueryWrapper< KebiaoxinixEntity> ew = new QueryWrapper< KebiaoxinixEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kebiaoxinix, "kebiaoxinix"));
		KebiaoxinixView kebiaoxinixView =  kebiaoxinixService.selectView(ew);
		return R.ok("查询课表信息成功").put("data", kebiaoxinixView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KebiaoxinixEntity kebiaoxinix = kebiaoxinixService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kebiaoxinix,deSens);
        return R.ok().put("data", kebiaoxinix);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KebiaoxinixEntity kebiaoxinix = kebiaoxinixService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kebiaoxinix,deSens);
        return R.ok().put("data", kebiaoxinix);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KebiaoxinixEntity kebiaoxinix, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kebiaoxinix);
        kebiaoxinixService.save(kebiaoxinix);
        return R.ok().put("data",kebiaoxinix.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KebiaoxinixEntity kebiaoxinix, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kebiaoxinix);
        kebiaoxinixService.save(kebiaoxinix);
        return R.ok().put("data",kebiaoxinix.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KebiaoxinixEntity kebiaoxinix, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kebiaoxinix);
        //全部更新
        kebiaoxinixService.updateById(kebiaoxinix);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kebiaoxinixService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
