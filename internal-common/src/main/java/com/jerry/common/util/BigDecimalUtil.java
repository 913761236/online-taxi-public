package com.jerry.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
public abstract class BigDecimalUtil {

    private BigDecimalUtil() {}

    /**
     * 加法
     */
    public static double add(double d1, double d2) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);

        return b1.add(b2).doubleValue();
    }

    /**
     * 减法
     */
    public static double subtract(double d1, double d2) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 乘法
     *
     */
    public static double multiply(double d1, double d2) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 除法
     */
    public static double divide(int d1, int d2) {
        if (d2 <= 0) {
            throw new IllegalArgumentException("除数不能为0");
        }

        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.divide(b2, 2, RoundingMode.HALF_UP).doubleValue();
    }
}
