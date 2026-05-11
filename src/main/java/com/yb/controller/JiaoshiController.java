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

import com.yb.entity.JiaoshiEntity;
import com.yb.entity.view.JiaoshiView;

import com.yb.service.JiaoshiService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 教师
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:25
 */
@RestController
@RequestMapping("/jiaoshi")
public class JiaoshiController {
    @Autowired
    private JiaoshiService jiaoshiService;







	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        JiaoshiEntity u = jiaoshiService.getOne(new QueryWrapper<JiaoshiEntity>().eq("jiaoshizhanghao", username));
        // 当用户不存在或md5方式验证密码不通过时
        if(u==null || !u.getMima().equals(EncryptUtil.md5(password))) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"jiaoshi",  "教师" );
        //返回token
		return R.ok().put("token", token);
	}



	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody JiaoshiEntity jiaoshi){
    	//ValidatorUtils.validateEntity(jiaoshi);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	JiaoshiEntity u = jiaoshiService.getOne(new QueryWrapper<JiaoshiEntity>().eq("jiaoshizhanghao", jiaoshi.getJiaoshizhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同教师账号，否则返回错误信息
        if(jiaoshiService.count(new QueryWrapper<JiaoshiEntity>().eq("jiaoshizhanghao", jiaoshi.getJiaoshizhanghao()))>0) {
            return R.error("教师账号已存在");
        }
		Long uId = new Date().getTime();
		jiaoshi.setId(uId);
        //设置登录密码md5方式加密
        jiaoshi.setMima(EncryptUtil.md5(jiaoshi.getMima()));
        //保存用户
        jiaoshiService.save(jiaoshi);
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
        JiaoshiEntity u = jiaoshiService.getById(id);
        return R.ok().put("data", u);
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        JiaoshiEntity u = jiaoshiService.getOne(new QueryWrapper<JiaoshiEntity>().eq("jiaoshizhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456，并使用md5方式加密
        u.setMima(EncryptUtil.md5("123456"));
        jiaoshiService.updateById(u);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 获取账号列表
     */
    @RequestMapping("/accountList")
    public R getAccountList(@RequestParam Map<String, Object> params,JiaoshiEntity jiaoshi){
        QueryWrapper<JiaoshiEntity> ew = new QueryWrapper<JiaoshiEntity>();
        QueryWrapper<JiaoshiEntity> wrapper =MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoshi), params), params);
        List<Map> list = jiaoshiService.list(wrapper).stream().map(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", v.getId());
            map.put("account", v.getJiaoshizhanghao());
            return map;
        }).collect(Collectors.toList());
        return R.ok().put("data", list);
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiaoshiEntity jiaoshi,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<JiaoshiEntity> ew = new QueryWrapper<JiaoshiEntity>();


        //查询结果
		PageUtils page = jiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoshi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JiaoshiEntity jiaoshi,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<JiaoshiEntity> ew = new QueryWrapper<JiaoshiEntity>();

        //查询结果
		PageUtils page = jiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoshi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiaoshiEntity jiaoshi){
       	QueryWrapper<JiaoshiEntity> ew = new QueryWrapper<JiaoshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiaoshi, "jiaoshi"));
        return R.ok().put("data", jiaoshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiaoshiEntity jiaoshi){
        QueryWrapper< JiaoshiEntity> ew = new QueryWrapper< JiaoshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiaoshi, "jiaoshi"));
		JiaoshiView jiaoshiView =  jiaoshiService.selectView(ew);
		return R.ok("查询教师成功").put("data", jiaoshiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiaoshiEntity jiaoshi = jiaoshiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jiaoshi,deSens);
        return R.ok().put("data", jiaoshi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiaoshiEntity jiaoshi = jiaoshiService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jiaoshi,deSens);
        return R.ok().put("data", jiaoshi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiaoshiEntity jiaoshi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(jiaoshiService.count(new QueryWrapper<JiaoshiEntity>().eq("jiaoshizhanghao", jiaoshi.getJiaoshizhanghao()))>0) {
            return R.error("教师账号已存在");
        }
        //ValidatorUtils.validateEntity(jiaoshi);
        //验证账号唯一性，否则返回错误信息
        JiaoshiEntity u = jiaoshiService.getOne(new QueryWrapper<JiaoshiEntity>().eq("jiaoshizhanghao", jiaoshi.getJiaoshizhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	jiaoshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		jiaoshi.setId(new Date().getTime());
        //密码使用md5方式加密
        jiaoshi.setMima(EncryptUtil.md5(jiaoshi.getMima()));
        jiaoshiService.save(jiaoshi);
        return R.ok().put("data",jiaoshi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiaoshiEntity jiaoshi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(jiaoshiService.count(new QueryWrapper<JiaoshiEntity>().eq("jiaoshizhanghao", jiaoshi.getJiaoshizhanghao()))>0) {
            return R.error("教师账号已存在");
        }
        //ValidatorUtils.validateEntity(jiaoshi);
        //验证账号唯一性，否则返回错误信息
        JiaoshiEntity u = jiaoshiService.getOne(new QueryWrapper<JiaoshiEntity>().eq("jiaoshizhanghao", jiaoshi.getJiaoshizhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	jiaoshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		jiaoshi.setId(new Date().getTime());
        //密码使用md5方式加密
        jiaoshi.setMima(EncryptUtil.md5(jiaoshi.getMima()));
        jiaoshiService.save(jiaoshi);
        return R.ok().put("data",jiaoshi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiaoshiEntity jiaoshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiaoshi);
        //验证字段唯一性，否则返回错误信息
        if(jiaoshiService.count(new QueryWrapper<JiaoshiEntity>().ne("id", jiaoshi.getId()).eq("jiaoshizhanghao", jiaoshi.getJiaoshizhanghao()))>0) {
            return R.error("教师账号已存在");
        }
	    JiaoshiEntity jiaoshiEntity = jiaoshiService.getById(jiaoshi.getId());
        //如果密码不为空，则判断是否和输入密码一致，不一致则重新设置
        if(StringUtils.isNotBlank(jiaoshi.getMima()) && !jiaoshi.getMima().equals(jiaoshiEntity.getMima())) {
            //密码使用md5方式加密
            jiaoshi.setMima(EncryptUtil.md5(jiaoshi.getMima()));
        }
        //全部更新
        jiaoshiService.updateById(jiaoshi);
        if(null!=jiaoshi.getJiaoshizhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(jiaoshi.getJiaoshizhanghao());
            tokenService.update(tokenEntity, new UpdateWrapper<TokenEntity>().eq("userid", jiaoshi.getId()));
        }
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiaoshiService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }





    // hasAlipay:否




}
