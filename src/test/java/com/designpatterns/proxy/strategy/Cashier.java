package com.designpatterns.proxy.strategy;

import java.math.BigDecimal;

/**
 * @author: ZL
 * @Date: 2020/7/27 12:50
 * @Description:
 */
public class Cashier {
        /*
        * 会员，策略对象
        * */
    private Buyer buyer;

    public Cashier(Buyer buyer) {
        this.buyer = buyer;
    }

    public BigDecimal quote(BigDecimal orderPrice) {
        return this.buyer.calPrice(orderPrice);
    }
}
