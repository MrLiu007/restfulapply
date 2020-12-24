package com.magic.liuzm.controller.asyn.deferredresult;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zemin.liu
 * @date 2020/12/17 16:32
 * @description 下单服务
 */
@Slf4j
@Service
public class OrderService {

    private static final String CLASSNAME = "OrderService";

    /**
     * @author zemin.liu
     * @description 下单服务
     * @date   16:36
     * @param goodsId
     * @return java.lang.String
     */
    public String order(String goodsId){

        final String flag = CLASSNAME + "order";
        log.info("flag:{},下单开始...",flag);
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String orderNo = "T_"+ System.currentTimeMillis();
        log.info("flag:{},orderNo:{},下单完成...",flag,orderNo);
        return orderNo;
    }
}
