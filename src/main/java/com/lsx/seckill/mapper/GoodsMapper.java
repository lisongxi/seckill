package com.lsx.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsx.seckill.pojo.Goods;
import com.lsx.seckill.service.IGoodsService;
import com.lsx.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {

    //获取商品列表
    List<GoodsVo> findGoodsVo();

    //获取商品详情
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
