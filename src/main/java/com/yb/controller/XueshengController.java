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
import com.yb.service.TokenService;
import com.yb.entity.TokenEntity;
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

import com.yb.entity.XueshengEntity;
import com.yb.entity.view.XueshengView;

import com.yb.service.XueshengService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 学生
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
@RestController
@RequestMapping("/xuesheng")
public class XueshengController {
    @Autowired
    private XueshengService xueshengService;







	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xueshengzhanghao", username));
        // 当用户不存在或md5方式验证密码不通过时
        if(u==null || !u.getMima().equals(EncryptUtil.md5(password))) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"xuesheng",  "学生" );
        //返回token
		return R.ok().put("token", token);
	}

    /**
     * 人脸登录
     */
    @IgnoreAuth
    @RequestMapping(value = "/faceLogin")
    public R faceLogin(String face, HttpServletRequest request) {
        //获取所有用户信息
        List<XueshengEntity> uList = xueshengService.list(new QueryWrapper<XueshengEntity>());
        R result = null;
        XueshengEntity user = null;
        for(XueshengEntity u : uList) {
            if (null != CommonUtil.getPropertyByFieldNames(u, "touxiang", "headportrait")) {
                // 循环将集合中的用户头像跟前端提供的头像照片做比较
                result = CommonUtil.matchFace(face, CommonUtil.getPropertyByFieldNames(u, "touxiang", "headportrait").toString().substring(7));
                // 当照片相似度大于等于80时，则认为人脸匹配成功
                if (result.get("code").toString().equals("0")&&new BigDecimal(result.get("score").toString()).compareTo(new BigDecimal(80)) == 1) {
                    user = u;
                    break;
                }
            }
        }
        //当照片未匹配到相似度高于80的用户时，认为人脸识别失败，并返回提示
        if(user==null) {
            return R.error("人脸识别失败");
        }
        // 获取登录token
		String token = tokenService.generateToken(user.getId(), user.getXueshengzhanghao(),"xuesheng", "学生");
                //返回token及登录账号
        return R.ok().put("token", token).put("username", user.getXueshengzhanghao());
    }


	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody XueshengEntity xuesheng){
    	//ValidatorUtils.validateEntity(xuesheng);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xueshengzhanghao", xuesheng.getXueshengzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同学生账号，否则返回错误信息
        if(xueshengService.count(new QueryWrapper<XueshengEntity>().eq("xueshengzhanghao", xuesheng.getXueshengzhanghao()))>0) {
            return R.error("学生账号已存在");
        }
		Long uId = new Date().getTime();
		xuesheng.setId(uId);
        //设置登录密码md5方式加密
        xuesheng.setMima(EncryptUtil.md5(xuesheng.getMima()));
        //保存用户
        xueshengService.save(xuesheng);
        return R.ok();
    }



	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}

	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        XueshengEntity u = xueshengService.getById(id);
        return R.ok().put("data", u);
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xueshengzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456，并使用md5方式加密
        u.setMima(EncryptUtil.md5("123456"));
        xueshengService.updateById(u);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 获取账号列表
     */
    @RequestMapping("/accountList")
    public R getAccountList(@RequestParam Map<String, Object> params,XueshengEntity xuesheng){
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
        QueryWrapper<XueshengEntity> wrapper =MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params);
        List<Map> list = xueshengService.list(wrapper).stream().map(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", v.getId());
            map.put("account", v.getXueshengzhanghao());
            return map;
        }).collect(Collectors.toList());
        return R.ok().put("data", list);
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XueshengEntity xuesheng,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();


        //查询结果
		PageUtils page = xueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params));
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
    public R list(@RequestParam Map<String, Object> params,XueshengEntity xuesheng,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();

        //查询结果
		PageUtils page = xueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XueshengEntity xuesheng){
       	QueryWrapper<XueshengEntity> ew = new QueryWrapper<XueshengEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xuesheng, "xuesheng"));
        return R.ok().put("data", xueshengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XueshengEntity xuesheng){
        QueryWrapper< XueshengEntity> ew = new QueryWrapper< XueshengEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xuesheng, "xuesheng"));
		XueshengView xueshengView =  xueshengService.selectView(ew);
		return R.ok("查询学生成功").put("data", xueshengView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XueshengEntity xuesheng = xueshengService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuesheng,deSens);
        return R.ok().put("data", xuesheng);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XueshengEntity xuesheng = xueshengService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuesheng,deSens);
        return R.ok().put("data", xuesheng);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XueshengEntity xuesheng, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(xueshengService.count(new QueryWrapper<XueshengEntity>().eq("xueshengzhanghao", xuesheng.getXueshengzhanghao()))>0) {
            return R.error("学生账号已存在");
        }
        //ValidatorUtils.validateEntity(xuesheng);
        //验证账号唯一性，否则返回错误信息
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xueshengzhanghao", xuesheng.getXueshengzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	xuesheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		xuesheng.setId(new Date().getTime());
        //密码使用md5方式加密
        xuesheng.setMima(EncryptUtil.md5(xuesheng.getMima()));
        xueshengService.save(xuesheng);
        return R.ok().put("data",xuesheng.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XueshengEntity xuesheng, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(xueshengService.count(new QueryWrapper<XueshengEntity>().eq("xueshengzhanghao", xuesheng.getXueshengzhanghao()))>0) {
            return R.error("学生账号已存在");
        }
        //ValidatorUtils.validateEntity(xuesheng);
        //验证账号唯一性，否则返回错误信息
        XueshengEntity u = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xueshengzhanghao", xuesheng.getXueshengzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	xuesheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		xuesheng.setId(new Date().getTime());
        //密码使用md5方式加密
        xuesheng.setMima(EncryptUtil.md5(xuesheng.getMima()));
        xueshengService.save(xuesheng);
        return R.ok().put("data",xuesheng.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        XueshengEntity xuesheng = xueshengService.getOne(new QueryWrapper<XueshengEntity>().eq("xueshengzhanghao", username));
        return R.ok().put("data", xuesheng);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody XueshengEntity xuesheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuesheng);
        //验证字段唯一性，否则返回错误信息
        if(xueshengService.count(new QueryWrapper<XueshengEntity>().ne("id", xuesheng.getId()).eq("xueshengzhanghao", xuesheng.getXueshengzhanghao()))>0) {
            return R.error("学生账号已存在");
        }
	    XueshengEntity xueshengEntity = xueshengService.getById(xuesheng.getId());
        //如果密码不为空，则判断是否和输入密码一致，不一致则重新设置
        if(StringUtils.isNotBlank(xuesheng.getMima()) && !xuesheng.getMima().equals(xueshengEntity.getMima())) {
            //密码使用md5方式加密
            xuesheng.setMima(EncryptUtil.md5(xuesheng.getMima()));
        }
        //全部更新
        xueshengService.updateById(xuesheng);
        if(null!=xuesheng.getXueshengzhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(xuesheng.getXueshengzhanghao());
            tokenService.update(tokenEntity, new UpdateWrapper<TokenEntity>().eq("userid", xuesheng.getId()));
        }
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xueshengService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
