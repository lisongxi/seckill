package com.lsx.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsx.seckill.exception.GlobalException;
import com.lsx.seckill.mapper.UserMapper;
import com.lsx.seckill.pojo.User;
import com.lsx.seckill.service.IUserService;
import com.lsx.seckill.utils.CookieUtil;
import com.lsx.seckill.utils.MD5Util;
import com.lsx.seckill.utils.UUIDUtil;
import com.lsx.seckill.vo.LoginVo;
import com.lsx.seckill.vo.RespBean;
import com.lsx.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired(required=false)
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean doLogin(LoginVo loginVo , HttpServletRequest request, HttpServletResponse response){
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //获取用户
        User user =  userMapper.selectById(mobile);
        if(user == null){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }

        if(!MD5Util.formPassToDBPass(password,user.getSlat()).equals(user.getPassword())){
            throw  new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }


        //生成cookie
        String ticket = UUIDUtil.uuid();
        //将用户信息存入redis
        redisTemplate.opsForValue().set("user:"+ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success();
    }

    @Override
    public User getUserByCookie(String userTicket,HttpServletRequest request ,HttpServletResponse response){
        if(StringUtils.isEmpty(userTicket)){
            return null;
        }
        User user = (User)redisTemplate.opsForValue().get("user:"+userTicket);
        if(user!=null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }
}
