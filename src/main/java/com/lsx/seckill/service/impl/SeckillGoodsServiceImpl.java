package com.lsx.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsx.seckill.mapper.SeckillGoodsMapper;
import com.lsx.seckill.pojo.SeckillGoods;
import com.lsx.seckill.service.ISeckillGoodsService;
import org.springframework.stereotype.Service;

@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements ISeckillGoodsService {
}
