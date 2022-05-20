package com.lsx.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsx.seckill.mapper.SeckillOrderMapper;
import com.lsx.seckill.pojo.SeckillOrder;
import com.lsx.seckill.service.ISeckillOrderService;
import org.springframework.stereotype.Service;

@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements ISeckillOrderService {
}
