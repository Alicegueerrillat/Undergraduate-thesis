package com.yb.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yb.dao.TokenDao;
import com.yb.entity.TokenEntity;
import com.yb.service.TokenService;
import com.yb.utils.CommonUtil;
import com.yb.utils.PageUtils;
import com.yb.utils.Query;


/**
 * token
 */
@Service("tokenService")
public class TokenServiceImpl extends ServiceImpl<TokenDao, TokenEntity> implements TokenService {


	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<TokenEntity> page = baseMapper.selectPage(
                new Query<TokenEntity>(params).getPage(),
                new QueryWrapper<TokenEntity>()
        );
        return new PageUtils(page);
	}

	@Override
	public List<TokenEntity> selectListView(QueryWrapper<TokenEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params,
			QueryWrapper<TokenEntity> wrapper) {
		 	Page<TokenEntity> page =new Query<TokenEntity>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
	}

	@Override
	public String generateToken(Long userid,String username, String tableName, String role) {
		TokenEntity tokenEntity = this.getOne(new QueryWrapper<TokenEntity>().eq("userid", userid).eq("role", role).eq("username", username));
		String token = CommonUtil.getRandomString(32);
		Calendar cal = Calendar.getInstance();   
    	cal.setTime(new Date());   
    	cal.add(Calendar.HOUR_OF_DAY, 1);
		if(tokenEntity!=null) {
			tokenEntity.setToken(token);
			tokenEntity.setExpiratedtime(cal.getTime());
			this.updateById(tokenEntity);
		} else {
			this.save(new TokenEntity(userid,username, tableName, role, token, cal.getTime()));
		}
		return token;
	}

	@Override
	public TokenEntity getTokenEntity(String token) {
		TokenEntity tokenEntity = this.getOne(new QueryWrapper<TokenEntity>().eq("token", token));
		if(tokenEntity == null || tokenEntity.getExpiratedtime().getTime()<new Date().getTime()) {
			return null;
		}
		return tokenEntity;
	}
}
