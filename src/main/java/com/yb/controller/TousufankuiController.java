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

import com.yb.entity.TousufankuiEntity;
import com.yb.entity.view.TousufankuiView;

import com.yb.service.TousufankuiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 投诉反馈
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
@RestController
@RequestMapping("/tousufankui")
public class TousufankuiController {
    @Autowired
    private TousufankuiService tousufankuiService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,TousufankuiEntity tousufankui,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			tousufankui.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<TousufankuiEntity> ew = new QueryWrapper<TousufankuiEntity>();


        //查询结果
		PageUtils page = tousufankuiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tousufankui), params), params));
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
    public R list(@RequestParam Map<String, Object> params,TousufankuiEntity tousufankui,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dengjishijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date dengjishijianend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<TousufankuiEntity> ew = new QueryWrapper<TousufankuiEntity>();
        if(dengjishijianstart!=null) ew.ge("dengjishijian", dengjishijianstart);
        if(dengjishijianend!=null) ew.le("dengjishijian", dengjishijianend);

        //查询结果
		PageUtils page = tousufankuiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tousufankui), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( TousufankuiEntity tousufankui){
       	QueryWrapper<TousufankuiEntity> ew = new QueryWrapper<TousufankuiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( tousufankui, "tousufankui"));
        return R.ok().put("data", tousufankuiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(TousufankuiEntity tousufankui){
        QueryWrapper< TousufankuiEntity> ew = new QueryWrapper< TousufankuiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( tousufankui, "tousufankui"));
		TousufankuiView tousufankuiView =  tousufankuiService.selectView(ew);
		return R.ok("查询投诉反馈成功").put("data", tousufankuiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        TousufankuiEntity tousufankui = tousufankuiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(tousufankui,deSens);
        return R.ok().put("data", tousufankui);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        TousufankuiEntity tousufankui = tousufankuiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(tousufankui,deSens);
        return R.ok().put("data", tousufankui);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TousufankuiEntity tousufankui, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tousufankui);
        tousufankuiService.save(tousufankui);
        return R.ok().put("data",tousufankui.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody TousufankuiEntity tousufankui, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tousufankui);
        tousufankuiService.save(tousufankui);
        return R.ok().put("data",tousufankui.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody TousufankuiEntity tousufankui, HttpServletRequest request){
        //ValidatorUtils.validateEntity(tousufankui);
        //全部更新
        tousufankuiService.updateById(tousufankui);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<TousufankuiEntity> list = new ArrayList<TousufankuiEntity>();
        for(Long id : ids) {
            TousufankuiEntity tousufankui = tousufankuiService.getById(id);
            tousufankui.setSfsh(sfsh);
            tousufankui.setShhf(shhf);
            list.add(tousufankui);
        }
        tousufankuiService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        tousufankuiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
