package com.designpatterns.proxy.strategy;

import java.math.BigDecimal;

/**
 * @author: ZL
 * @Date: 2020/7/27 11:44
 * @Description:
 */
public interface Buyer {
    /*
    * 计算应付价格
    * */
    BigDecimal calPrice(BigDecimal orderPrice);
}
