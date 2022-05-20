package com.lsx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsx.seckill.pojo.Order;
import com.lsx.seckill.pojo.User;
import com.lsx.seckill.vo.GoodsVo;

public interface IOrderService extends IService<Order> {
    //秒杀
    Order seckill(User user, GoodsVo goods);
}
