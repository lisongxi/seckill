package com.lsx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsx.seckill.pojo.Goods;
import com.lsx.seckill.vo.GoodsVo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IGoodsService extends IService<Goods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
