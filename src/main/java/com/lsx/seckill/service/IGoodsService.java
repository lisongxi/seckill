package com.lsx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsx.seckill.pojo.Goods;
import com.lsx.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper
public interface IGoodsService extends IService<Goods> {

     List<GoodsVo> findGoodsVo();
}
