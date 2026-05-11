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

import com.yb.entity.KetangkaoqinqiandaoEntity;
import com.yb.entity.view.KetangkaoqinqiandaoView;

import com.yb.service.KetangkaoqinqiandaoService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 课堂考勤签到
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
@RestController
@RequestMapping("/ketangkaoqinqiandao")
public class KetangkaoqinqiandaoController {
    @Autowired
    private KetangkaoqinqiandaoService ketangkaoqinqiandaoService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KetangkaoqinqiandaoEntity ketangkaoqinqiandao,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			ketangkaoqinqiandao.setJiaoshizhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			ketangkaoqinqiandao.setXueshengzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        QueryWrapper<KetangkaoqinqiandaoEntity> ew = new QueryWrapper<KetangkaoqinqiandaoEntity>();


        //查询结果
		PageUtils page = ketangkaoqinqiandaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ketangkaoqinqiandao), params), params));
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
    public R list(@RequestParam Map<String, Object> params,KetangkaoqinqiandaoEntity ketangkaoqinqiandao,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date qiandaoshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date qiandaoshijianend,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<KetangkaoqinqiandaoEntity> ew = new QueryWrapper<KetangkaoqinqiandaoEntity>();
        if(qiandaoshijianstart!=null) ew.ge("qiandaoshijian", qiandaoshijianstart);
        if(qiandaoshijianend!=null) ew.le("qiandaoshijian", qiandaoshijianend);

        //查询结果
		PageUtils page = ketangkaoqinqiandaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ketangkaoqinqiandao), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KetangkaoqinqiandaoEntity ketangkaoqinqiandao){
       	QueryWrapper<KetangkaoqinqiandaoEntity> ew = new QueryWrapper<KetangkaoqinqiandaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( ketangkaoqinqiandao, "ketangkaoqinqiandao"));
        return R.ok().put("data", ketangkaoqinqiandaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KetangkaoqinqiandaoEntity ketangkaoqinqiandao){
        QueryWrapper< KetangkaoqinqiandaoEntity> ew = new QueryWrapper< KetangkaoqinqiandaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( ketangkaoqinqiandao, "ketangkaoqinqiandao"));
		KetangkaoqinqiandaoView ketangkaoqinqiandaoView =  ketangkaoqinqiandaoService.selectView(ew);
		return R.ok("查询课堂考勤签到成功").put("data", ketangkaoqinqiandaoView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KetangkaoqinqiandaoEntity ketangkaoqinqiandao = ketangkaoqinqiandaoService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ketangkaoqinqiandao,deSens);
        return R.ok().put("data", ketangkaoqinqiandao);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KetangkaoqinqiandaoEntity ketangkaoqinqiandao = ketangkaoqinqiandaoService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ketangkaoqinqiandao,deSens);
        return R.ok().put("data", ketangkaoqinqiandao);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KetangkaoqinqiandaoEntity ketangkaoqinqiandao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ketangkaoqinqiandao);
        ketangkaoqinqiandaoService.save(ketangkaoqinqiandao);
        return R.ok().put("data",ketangkaoqinqiandao.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KetangkaoqinqiandaoEntity ketangkaoqinqiandao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ketangkaoqinqiandao);
        ketangkaoqinqiandaoService.save(ketangkaoqinqiandao);
        return R.ok().put("data",ketangkaoqinqiandao.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KetangkaoqinqiandaoEntity ketangkaoqinqiandao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ketangkaoqinqiandao);
        //全部更新
        ketangkaoqinqiandaoService.updateById(ketangkaoqinqiandao);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        ketangkaoqinqiandaoService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
