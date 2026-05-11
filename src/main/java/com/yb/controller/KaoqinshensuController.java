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

import com.yb.entity.KaoqinshensuEntity;
import com.yb.entity.view.KaoqinshensuView;

import com.yb.service.KaoqinshensuService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 考勤申诉
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
@RestController
@RequestMapping("/kaoqinshensu")
public class KaoqinshensuController {
    @Autowired
    private KaoqinshensuService kaoqinshensuService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KaoqinshensuEntity kaoqinshensu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			kaoqinshensu.setJiaoshizhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			kaoqinshensu.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<KaoqinshensuEntity> ew = new QueryWrapper<KaoqinshensuEntity>();


        //查询结果
		PageUtils page = kaoqinshensuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoqinshensu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,KaoqinshensuEntity kaoqinshensu,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date shensushijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date shensushijianend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<KaoqinshensuEntity> ew = new QueryWrapper<KaoqinshensuEntity>();
        if(shensushijianstart!=null) ew.ge("shensushijian", shensushijianstart);
        if(shensushijianend!=null) ew.le("shensushijian", shensushijianend);

        //查询结果
		PageUtils page = kaoqinshensuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaoqinshensu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KaoqinshensuEntity kaoqinshensu){
       	QueryWrapper<KaoqinshensuEntity> ew = new QueryWrapper<KaoqinshensuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kaoqinshensu, "kaoqinshensu"));
        return R.ok().put("data", kaoqinshensuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KaoqinshensuEntity kaoqinshensu){
        QueryWrapper< KaoqinshensuEntity> ew = new QueryWrapper< KaoqinshensuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kaoqinshensu, "kaoqinshensu"));
		KaoqinshensuView kaoqinshensuView =  kaoqinshensuService.selectView(ew);
		return R.ok("查询考勤申诉成功").put("data", kaoqinshensuView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KaoqinshensuEntity kaoqinshensu = kaoqinshensuService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kaoqinshensu,deSens);
        return R.ok().put("data", kaoqinshensu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KaoqinshensuEntity kaoqinshensu = kaoqinshensuService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kaoqinshensu,deSens);
        return R.ok().put("data", kaoqinshensu);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KaoqinshensuEntity kaoqinshensu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaoqinshensu);
        kaoqinshensuService.save(kaoqinshensu);
        return R.ok().put("data",kaoqinshensu.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KaoqinshensuEntity kaoqinshensu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaoqinshensu);
        kaoqinshensuService.save(kaoqinshensu);
        return R.ok().put("data",kaoqinshensu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KaoqinshensuEntity kaoqinshensu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaoqinshensu);
        //全部更新
        kaoqinshensuService.updateById(kaoqinshensu);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<KaoqinshensuEntity> list = new ArrayList<KaoqinshensuEntity>();
        for(Long id : ids) {
            KaoqinshensuEntity kaoqinshensu = kaoqinshensuService.getById(id);
            kaoqinshensu.setSfsh(sfsh);
            kaoqinshensu.setShhf(shhf);
            list.add(kaoqinshensu);
        }
        kaoqinshensuService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kaoqinshensuService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
