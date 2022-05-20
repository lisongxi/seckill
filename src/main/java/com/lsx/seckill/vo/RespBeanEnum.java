package com.lsx.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    //通用
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常哈哈"),

    //登录
    LOGIN_ERROR(500210,"用户名或者密码不正确"),
    MOBILE_ERROR(500211,"手机号码格式不正确"),
    BIND_ERROR(500212,"参数校验异常"),

    //秒杀模块
    EMPTY_STOCK(500500,"库存不足"),
    REPATE_ERROR(500501,"该商品每人限购一件");
    ;

    private final Integer code;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
