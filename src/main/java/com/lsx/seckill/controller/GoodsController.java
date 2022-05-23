package com.lsx.seckill.controller;

import com.lsx.seckill.pojo.User;
import com.lsx.seckill.service.IGoodsService;
import com.lsx.seckill.service.IUserService;
import com.lsx.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    /**
     * Windows优化前QPS：1754
     * Linux优化前QPS：
     * @author LiSongXi
     * @date
     */

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IUserService userService;

    @RequestMapping("/toList")
    public String toList(Model model, User user){

        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsService.findGoodsVo());
        return "goodsList";
    }

    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model,User user, @PathVariable Long goodsId){
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        int secKillStatus = 0;
        int remainSeconds = 0;
        if(nowDate.before(startDate)){
            remainSeconds = (int)(((startDate.getTime() - nowDate.getTime())/1000));
        }else if(nowDate.after(endDate)){
            secKillStatus = 2;
            remainSeconds = -1;
        }else{
            secKillStatus = 1;
            remainSeconds = 0;
        }

        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("secKillStatus",secKillStatus);
        model.addAttribute("user",user);
        model.addAttribute("goods",goodsVo);
        return "goodsDetail";
    }
}
