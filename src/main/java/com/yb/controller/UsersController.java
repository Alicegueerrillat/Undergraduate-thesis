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

import com.yb.entity.UsersEntity;
import com.yb.entity.view.UsersView;

import com.yb.service.UsersService;
import com.yb.utils.PageUtils;
import com.yb.utils.R;
import com.yb.utils.EncryptUtil;
import com.yb.utils.MPUtil;
import com.yb.utils.MapUtils;
import com.yb.utils.CommonUtil;
import java.io.IOException;

/**
 * 管理员
 * 后端接口
 * @author 
 * @email 
 * @date 2025-12-26 16:20:26
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        UsersEntity u = usersService.getOne(new QueryWrapper<UsersEntity>().eq("username", username));
        if(u==null || !u.getPassword().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"users",  "管理员" );
        //返回token
		return R.ok().put("token", token);
	}



	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody UsersEntity users){
    	//ValidatorUtils.validateEntity(users);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	UsersEntity u = usersService.getOne(new QueryWrapper<UsersEntity>().eq("username", users.getUsername()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		users.setId(uId);
        users.setPassword(users.getPassword());
        //保存用户
        usersService.save(users);
        return R.ok();
    }


    /**
    * 注销账户
    */
    @RequestMapping("/logoff")
    public R logOff(HttpServletRequest request){
        Long id = (Long)request.getSession().getAttribute("userId");
        UsersEntity u = usersService.getById(id);
        if(u==null) {
            return R.error("账号不存在");
        }
        usersService.updateById(u);
        return R.ok("注销成功");
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
        UsersEntity u = usersService.getById(id);
        return R.ok().put("data", u);
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        UsersEntity u = usersService.getOne(new QueryWrapper<UsersEntity>().eq("username", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setPassword("123456");
        usersService.updateById(u);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 获取账号列表
     */
    @RequestMapping("/accountList")
    public R getAccountList(@RequestParam Map<String, Object> params,UsersEntity users){
        QueryWrapper<UsersEntity> ew = new QueryWrapper<UsersEntity>();
        QueryWrapper<UsersEntity> wrapper =MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, users), params), params);
        List<Map> list = usersService.list(wrapper).stream().map(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", v.getId());
            map.put("account", v.getUsername());
            return map;
        }).collect(Collectors.toList());
        return R.ok().put("data", list);
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,UsersEntity users,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<UsersEntity> ew = new QueryWrapper<UsersEntity>();


        //查询结果
		PageUtils page = usersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, users), params), params));
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
    public R list(@RequestParam Map<String, Object> params,UsersEntity users,
		HttpServletRequest request){
        //设置查询条件
        QueryWrapper<UsersEntity> ew = new QueryWrapper<UsersEntity>();

        //查询结果
		PageUtils page = usersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, users), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( UsersEntity users){
       	QueryWrapper<UsersEntity> ew = new QueryWrapper<UsersEntity>();
      	ew.allEq(MPUtil.allEQMapPre( users, "users"));
        return R.ok().put("data", usersService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(UsersEntity users){
        QueryWrapper< UsersEntity> ew = new QueryWrapper< UsersEntity>();
 		ew.allEq(MPUtil.allEQMapPre( users, "users"));
		UsersView usersView =  usersService.selectView(ew);
		return R.ok("查询管理员成功").put("data", usersView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        UsersEntity users = usersService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(users,deSens);
        return R.ok().put("data", users);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        UsersEntity users = usersService.getById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(users,deSens);
        return R.ok().put("data", users);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UsersEntity users, HttpServletRequest request){
        //ValidatorUtils.validateEntity(users);
        //验证账号唯一性，否则返回错误信息
        UsersEntity u = usersService.getOne(new QueryWrapper<UsersEntity>().eq("username", users.getUsername()));
        if(u!=null) {
            return R.error("用户已存在");
        }
		users.setId(new Date().getTime());
        usersService.save(users);
        return R.ok().put("data",users.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody UsersEntity users, HttpServletRequest request){
        //ValidatorUtils.validateEntity(users);
        //验证账号唯一性，否则返回错误信息
        UsersEntity u = usersService.getOne(new QueryWrapper<UsersEntity>().eq("username", users.getUsername()));
        if(u!=null) {
            return R.error("用户已存在");
        }
		users.setId(new Date().getTime());
        usersService.save(users);
        return R.ok().put("data",users.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        UsersEntity users = usersService.getOne(new QueryWrapper<UsersEntity>().eq("username", username));
        return R.ok().put("data", users);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody UsersEntity users, HttpServletRequest request){
        //ValidatorUtils.validateEntity(users);
        //全部更新
        usersService.updateById(users);
        if(null!=users.getUsername())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(users.getUsername());
            tokenService.update(tokenEntity, new UpdateWrapper<TokenEntity>().eq("userid", users.getId()));
        }
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        usersService.removeBatchByIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,UsersEntity users, HttpServletRequest request,String pre){
        QueryWrapper<UsersEntity> ew = new QueryWrapper<UsersEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = usersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, users), params), params));
        return R.ok().put("data", page);
    }




    // hasAlipay:$hasAlipay




}
