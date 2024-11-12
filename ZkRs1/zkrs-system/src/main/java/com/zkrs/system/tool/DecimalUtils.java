package com.zkrs.system.tool;


import java.math.BigDecimal;

public class DecimalUtils {

    /**
     * 加法计算（result = x + y）
     *
     * @param x 被加数（可为null）
     * @param y 加数 （可为null）
     * @return 和 （可为null）
     * @author 大脑补丁 on 2020-03-30 14:52
     */
    public static BigDecimal add(BigDecimal x, BigDecimal y) {
        if (x == null) {
            return y;
        }
        if (y == null) {
            return x;
        }
        return x.add(y);
    }

    /**
     * 加法计算（result = a + b + c + d）
     *
     * @param a 被加数（可为null）
     * @param b 加数（可为null）
     * @param c 加数（可为null）
     * @param d 加数（可为null）
     * @return BigDecimal （可为null）
     * @author 大脑补丁 on 2020-03-30 22:17
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b, BigDecimal c, BigDecimal d) {
        BigDecimal ab = add(a, b);
        BigDecimal cd = add(c, d);
        return add(ab, cd);
    }

    /**
     * 累加计算(result=x + result)
     *
     * @param x      被加数（可为null）
     * @param result 和 （可为null,若被加数不为为null，result默认值为0）
     * @return result 和 （可为null）
     * @author 大脑补丁 on 2020-03-30 14:16
     */
    public static BigDecimal accumulate(BigDecimal x, BigDecimal result) {
        if (x == null) {
            return result;
        }
        if (result == null) {
            result = new BigDecimal("0");
        }
        return result.add(x);
    }

    /**
     * 减法计算(result = x - y)
     *
     * @param x 被减数（可为null）
     * @param y 减数（可为null）
     * @return BigDecimal 差 （可为null）
     * @author 大脑补丁 on 2020-03-30 14:47
     */
    public static BigDecimal subtract(BigDecimal x, BigDecimal y) {
        if (x == null || y == null) {
            return null;
        }
        return x.subtract(y);
    }

    /**
     * 乘法计算(result = x × y)
     *
     * @param x 乘数(可为null)
     * @param y 乘数(可为null)
     * @return BigDecimal 积
     * @author 大脑补丁 on 2020-03-30 14:14
     */
    public static BigDecimal multiply(BigDecimal x, BigDecimal y) {
        if (x == null || y == null) {
            return null;
        }
        return x.multiply(y);
    }

    /**
     * 除法计算(result = x ÷ y)
     *
     * @param x 被除数（可为null）
     * @param y 除数（可为null）
     * @return 商 （可为null,四舍五入，默认保留20位小数）
     * @author 大脑补丁 on 2020-03-30 14:15
     */
    public static BigDecimal divide(BigDecimal x, BigDecimal y) {
        if (x == null || y == null || y.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        // 结果为0.000..时，不用科学计数法展示
        return stripTrailingZeros(x.divide(y, 20, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 转为字符串(防止返回可续计数法表达式)
     *
     * @param x 要转字符串的小数
     * @return String
     * @author 大脑补丁 on 2020-03-30 15:08
     */
    public static String toPlainString(BigDecimal x) {
        if (x == null) {
            return null;
        }
        return x.toPlainString();
    }

    /**
     * 保留小数位数
     *
     * @param x     目标小数
     * @param scale 要保留小数位数
     * @return BigDecimal 结果四舍五入
     * @author 大脑补丁 on 2020-03-30 15:17
     */
    public static BigDecimal scale(BigDecimal x, int scale) {
        if (x == null) {
            return null;
        }
        return x.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 整型转为BigDecimal
     *
     * @param x(可为null)
     * @return BigDecimal (可为null)
     * @author 大脑补丁 on 2020-03-30 22:24
     */
    public static BigDecimal toBigDecimal(Integer x) {
        if (x == null) {
            return null;
        }
        return new BigDecimal(x.toString());
    }

    /**
     * 长整型转为BigDecimal
     *
     * @param x(可为null)
     * @return BigDecimal (可为null)
     * @author 大脑补丁 on 2020-03-30 22:24
     */
    public static BigDecimal toBigDecimal(Long x) {
        if (x == null) {
            return null;
        }
        return new BigDecimal(x.toString());
    }

    /**
     * 双精度型转为BigDecimal
     *
     * @param x(可为null)
     * @return BigDecimal (可为null)
     * @author 大脑补丁 on 2020-03-30 22:24
     */
    public static BigDecimal toBigDecimal(Double x) {
        if (x == null) {
            return null;
        }
        return new BigDecimal(x.toString());
    }

    /**
     * 单精度型转为BigDecimal
     *
     * @param x(可为null)
     * @return BigDecimal (可为null)
     * @author 大脑补丁 on 2020-03-30 22:24
     */
    public static BigDecimal toBigDecimal(Float x) {
        if (x == null) {
            return null;
        }
        return new BigDecimal(x.toString());
    }

    /**
     * 字符串型转为BigDecimal
     *
     * @param x(可为null)
     * @return BigDecimal (可为null)
     * @author 大脑补丁 on 2020-03-30 22:24
     */
    public static BigDecimal toBigDecimal(String x) {
        if (x == null) {
            return null;
        }
        return new BigDecimal(x);
    }

    /**
     * 对象类型转为BigDecimal
     *
     * @param x(可为null)
     * @return BigDecimal (可为null)
     * @author 大脑补丁 on 2020-03-30 22:24
     */
    public static BigDecimal toBigDecimal(Object x) {
        if (x == null) {
            return null;
        }
        BigDecimal result = null;
        try {
            result = new BigDecimal(x.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 倍数计算，用于单位换算
     *
     * @param x        目标数(可为null)
     * @param multiple 倍数 (可为null)
     * @return BigDecimal (可为null)
     * @author 大脑补丁 on 2020-03-30 22:43
     */
    public static BigDecimal multiple(BigDecimal x, Integer multiple) {
        if (x == null || multiple == null) {
            return null;
        }
        return DecimalUtils.multiply(x, toBigDecimal(multiple));
    }

    /**
     * 去除小数点后的0（如: 输入1.000返回1）
     *
     * @param x 目标数(可为null)
     * @return
     */
    public static BigDecimal stripTrailingZeros(BigDecimal x) {
        if (x == null) {
            return null;
        }
        return x.stripTrailingZeros();
    }


    public static BigDecimal format(BigDecimal x) {
        if (x == null||"".equals( x)) {
            return new BigDecimal("0");
        }
        return  x;
    }

    public static void main(String[] args) {


        String    hkDate="2023-03-01".substring(7,2);
        System.out.println(hkDate);
        // 定义两个变量，进行运算
        BigDecimal a = new BigDecimal("100");
        BigDecimal b = new BigDecimal("3.14");

        // 加 a+b
        BigDecimal c1 = DecimalUtils.add(a, b);
        // 减 a-b
        BigDecimal c2 = DecimalUtils.subtract(a, b);
        // 乘 a*b
        BigDecimal c3 = DecimalUtils.multiply(a, b);
        // 除 a/b
        BigDecimal c4 = DecimalUtils.subtract(a, b);

        // 累加 sum = a + sum
        BigDecimal sum = null;
        sum = DecimalUtils.accumulate(a, sum);

        // 将变量a结果保留2位小数
        BigDecimal c5 = DecimalUtils.scale(a, 2);

        // 变量a的1000倍运算   a*1000
        BigDecimal c6 = DecimalUtils.multiple(a, 1000);

        //Integer、Long、Float、Double、String、Object转为BigDecimal。
        Object obj = 123;
        BigDecimal c7 = DecimalUtils.toBigDecimal(obj);

        // BigDecimal转为字符串
        String str = DecimalUtils.toPlainString(a);

        // 不同数据类型之间混合运算（如：Double类型除以Long类型）
        BigDecimal mixCalc = DecimalUtils.divide(DecimalUtils.toBigDecimal(1.23D),DecimalUtils.toBigDecimal(1234567L));


        //等于
        BigDecimal b1 = BigDecimal.ZERO;
        BigDecimal b2 = BigDecimal.ZERO;
        System.out.println(b2.compareTo(b1));
        //输出结果 0
        if (b2.compareTo(b1) == 0) {
            System.out.println("b1,b2相等");
        }


        //大于
        BigDecimal b11 = BigDecimal.ZERO;
        BigDecimal b21 = BigDecimal.ONE;
        System.out.println(b21.compareTo(b11));
        //输出 1
        if (b21.compareTo(b11)> 0) {
            System.out.println("b2大于b1");
        }


        //小于
        BigDecimal b111 = BigDecimal.ZERO;
        BigDecimal b211 = BigDecimal.valueOf(-1);
        System.out.println(b211.compareTo(b111));
        //输出 -1
        if (b211.compareTo(b111) < 0) {
            System.out.println("b2小于b1");
        }
    }

}
