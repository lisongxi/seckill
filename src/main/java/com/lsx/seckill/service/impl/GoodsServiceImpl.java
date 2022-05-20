package com.lsx.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsx.seckill.mapper.GoodsMapper;
import com.lsx.seckill.pojo.Goods;
import com.lsx.seckill.service.IGoodsService;
import com.lsx.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    //获取商品列表
    @Override
    public List<GoodsVo> findGoodsVo(){
        return goodsMapper.findGoodsVo();
    }
}
