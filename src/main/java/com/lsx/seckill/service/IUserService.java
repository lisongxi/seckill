package com.lsx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsx.seckill.pojo.User;
import com.lsx.seckill.vo.LoginVo;
import com.lsx.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    //根据cookie获取用户
    User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response);
}
