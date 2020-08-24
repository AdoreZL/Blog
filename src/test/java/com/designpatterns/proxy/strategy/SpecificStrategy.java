package com.designpatterns.proxy.strategy;

import java.math.BigDecimal;

/**
 * @author: ZL
 * @Date: 2020/7/27 11:53
 * @Description:  具体策略类
 */
public class SpecificStrategy {
    public static class ParticularlyVipBuyer implements Buyer{
        @Override
        public BigDecimal calPrice(BigDecimal orderPrice) {
            if (orderPrice.compareTo(new BigDecimal(30)) > 0) {
                return orderPrice.multiply(new BigDecimal(0.7));
            }
            return null;
        }
    }

    public static class SuperVipBuyer implements Buyer{
        @Override
        public BigDecimal calPrice(BigDecimal orderPrice) {
            return orderPrice.multiply(new BigDecimal(0.8));
        }
    }

    public static class VipBuyer implements Buyer{
        @Override
        public BigDecimal calPrice(BigDecimal orderPrice) {
            int  superVipExpiredDays  = getSuperVipExpiredDays();
            int superVipLeadDiscountTimes  = getSuperVipLeadDiscountTimes();
            if(superVipExpiredDays < 7 && superVipLeadDiscountTimes ==0){

                return orderPrice.multiply(new BigDecimal(0.8));
            }
            return orderPrice.multiply(new BigDecimal(0.9));
        }

        private int getSuperVipLeadDiscountTimes() {
            return 0;
        }

        private int getSuperVipExpiredDays() {
            return 8;
        }
    }
}
