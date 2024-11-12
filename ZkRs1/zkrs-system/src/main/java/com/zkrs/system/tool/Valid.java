package com.zkrs.system.tool;



import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.zkrs.common.utils.reflect.ReflectUtils;
import com.zkrs.system.domain.api.ApiRs;
import com.zkrs.system.section.ApiResultsVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;

import java.math.BigDecimal;

import java.text.DecimalFormat;

import java.text.ParseException;

import java.text.ParsePosition;

import java.text.SimpleDateFormat;

import java.util.*;





public class Valid {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");

    public static boolean isValidDate(String sDate) {
        sDate = getDateStr(sDate);
        if(sDate == null){
            return false;
        }
        String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
        String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
                + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
                + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
                + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        if ((sDate != null)) {
            Pattern pattern = Pattern.compile(datePattern1);
            Matcher match = pattern.matcher(sDate);
            if (match.matches()) {
                pattern = Pattern.compile(datePattern2);
                match = pattern.matcher(sDate);
                return match.matches();
            } else {
                return false;
            }
        }
        return false;
    }




    //时间判断
    public static Boolean  convertResultOne1(String t,Object o)  {
        Field[] fields=o.getClass().getDeclaredFields();
       boolean isRs=false;
        //获取注解字段
        for(int i=0;i<fields.length;i++) {
            ApiResultsVo apiResultsVo = fields[i].getAnnotation(ApiResultsVo.class);
            if (apiResultsVo!=null&&"date".equals( apiResultsVo.attributeName())) {
             //格式数据
                String attributeValue = ReflectUtils.getFieldValue(o, fields[i].getName());
               Boolean rs= isValidDate(attributeValue);
               if(rs){//通过
                   ReflectUtils.setFieldValue(o, fields[i].getName(), getDateStr(t));
                   isRs=true;
               }
            }
        }
        return  isRs;
    }

    public static String getDateStr(String dateStr) {
        try {
            if(dateStr.contains("-")){
                return dateStr;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            return sdf1.format(sdf.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }
    public static boolean isValidMonthDate(String sDate) {
        boolean dateflag = true;
        try {
            format.parse(sDate);
        } catch (ParseException e) {
            dateflag = false;
        }
        return dateflag;
    }
    /**
     * 价格保留小数位
     */

    public static final int PRICE_SCALE = 2;

    /**
     * 数量和金额、税额保留小数位
     */

    public static final int QTY_AMOUNT_RATE_SCALE = 4;

    private static final Pattern PATTERN_NUMBER = Pattern.compile("\\d+$"); // 数字

    private static final Pattern PATTERN_LETTER = Pattern.compile("[a-zA-Z]+$"); // 英文字母

    private static final Pattern PATTERN_FLOAT = Pattern.compile("\\d+.\\d*$"); // 数字包括小数

    private static final Pattern PATTERN_CHINESE = Pattern.compile("[\\u4e00-\\u9fa5]+"); //汉子

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    private static final String NULL_STR = "NULL";

    private static final String NULL_STR2 = "null";

    private static final String SPACE_STR = " ";

    /**
     * 是否有值 : hasValue
     *
     * @param rs
     * @return
     */

    public static boolean hv(String rs) {

        return rs != null && rs.length() > 0;

    }

    /**
     * 是否有值 : hasValue
     *
     * @param rs
     * @return
     */

    public static boolean hv(Integer rs) {

        return rs != null && rs != 0;

    }

    /**
     * 是否有值 : hasValue 0返回true
     *
     * @param rs
     * @return
     */

    public static boolean hv2(Integer rs) {

        return rs != null;

    }

    /**
     * 是否有值 : hasValue
     *
     * @param rs
     * @return
     * @add xiaofeng 2013-03-08
     */

    public static boolean hv(Double rs) {

        return rs != null && rs != 0d;

    }

    /**
     * 是否有值 : hasValue
     *
     * @param rs
     * @return
     * @add xiaofeng 2010-12-15
     */

    public static boolean hv(Date rs) {

        return rs != null;

    }

    /**
     * 是否有值 : hasValue
     *
     * @param rs
     * @return
     */

    public static boolean hv(Long rs) {

        return rs != null && rs != 0L;

    }

    /**
     * 是否有值 : hasValue
     *
     * @param str
     * @return
     */

    public static boolean hv(String[] str) {

        return str != null && str.length > 0;

    }

    /**
     * 是否有值 : hasValue
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * 注意：如果list的第一个元素是null那么返回false
     *
     * @param list
     * @return
     */

    public static boolean hv(List list) {

        if (list != null && list.size() > 0) {

            if (hv(list.get(0))) {

                return true;

            }

        }

        return false;

    }

    /**
     * 是否有值 : hasValue
     *
     * @param obj
     * @return
     */

    public static boolean hv(Object obj) {

        return obj != null;

    }

    /***
     * Collcetion了类型是否有值
     * @param coll

     * @return

     */

    public static boolean hv(Collection coll) {

        return coll != null && !coll.isEmpty();

    }

    /**
     * map是否有值 : hasValue
     *
     * @param obj
     * @return
     */

    public static boolean hv(Map obj) {

        return null != obj && !obj.isEmpty();

    }

    /**
     * 是否有值 : hasValue
     *
     * @param obj
     * @return
     */

    public static boolean hv(Object[] obj) {

        return obj != null && obj.length > 0;

    }

    /**
     * 是否有值 : hasValue
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * 注意：该方法主要用于判断多个参数同时不为null时才用
     * 用法:Scm.hv(obj1,obj2,obj3,...,args)
     *
     * @param obj  参数1
     * @param args 参数列表
     * @return
     * @add xiaofeng 2010-12-03
     */

    public static boolean hv(Object obj, Object... args) {

        if (!hv(obj)) {

            return false;

        }

        for (Object arg : args) {

            if (!hv(arg)) {

                return false;

            }

        }

        return true;

    }

    /**
     * 其中一个参数是否有值 : oneHasValue
     *
     * @param args 参数列表
     * @return
     * @add xiaofeng 2010-12-15
     */

    public static boolean oneHv(Object... args) {

        for (Object arg : args) {

            if (arg instanceof String) { // 如果类型是字符串特殊处理

                if (hv(Valid.ts(arg))) {

                    return true;

                }

            } else {

                if (hv(arg)) {

                    return true;

                }

            }

        }

        return false;

    }

    /**
     * 是否相等 : equals
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * 注意：src,dest其中一个值不为null
     * 用法:Scm.eq(null,1); Scm.eq(1,2);
     * Scm.eq(2,null);等
     *
     * @param src  源字符串
     * @param dest 目标字符串
     * @return
     * @add xiaofeng 2010-12-03
     */

    public static boolean eq(Object src, Object dest) {

        if (src == null && dest == null) {

            return true;

        }

        return hv(src) ? src.equals(dest) : dest.equals(src);

    }


    /**
     * 转换成String : toString
     *
     * @param obj
     * @return
     */

    public static String ts(Object obj) {

        return hv(obj) ? String.valueOf(obj) : null;

    }

    /**
     * 转换成String : toString
     *
     * @param rs
     * @return
     * @add xiaofeng 2011-02-16
     */

    public static String ts(String rs) {

        return rs == null ? "" : rs;

    }

    /**
     * SQL拼接中单引号处理 : singleQuoteMark
     *
     * @param rs
     * @return
     * @add xiaofeng 2011-02-16
     */

    public static String sqm(String rs) {

        return replace(rs, "'", "''");

    }

    /**
     * 字符串替换
     * <p>
     * <p>
     * <p>
     * 注意：不需要判断rs == null
     *
     * @param rs          原字符串
     * @param target      需要替换的内容
     * @param replacement 替换成的内容
     * @return
     * @add xiaofeng 2011-02-16
     */

    public static String replace(String rs, CharSequence target, CharSequence replacement) {

        return rs == null ? "" : rs.replace(target, replacement);

    }

    /**
     * 转换成Integer : toInteger
     *
     * @param rs
     * @return
     */

    public static Integer ti(String rs) {

        return hv(rs) ? Integer.parseInt(rs) : null;

    }

    /**
     * 转换成有效的Integer类型
     *
     * @param rs
     * @return
     * @add xiaofeng 2010-12-9
     */

    public static Integer ti(Integer rs) {

        return hv(rs) ? rs : 0;

    }

    /**
     * 转换成Integer : toInteger
     *
     * @param rs
     * @return
     * @add xiaofeng 2011-1-19
     */

    public static Integer ti(Long rs) {

        return hv(rs) ? rs.intValue() : null;

    }

    /**
     * 转换成Double : toDouble
     *
     * @param rs
     * @return
     * @add xiaofeng 2010-12-03
     */

    public static Double td(String rs) {

        return hv(rs) ? Double.parseDouble(rs) : null;

    }

    /**
     * 转换成有效的Double类型 : toAmount
     *
     * @param rs
     * @return
     * @add xiaofeng 2010-12-9
     */

    public static Double ta(Double rs) {

        return hv(rs) ? rs : 0.00;

    }

    /**
     * 转换成有效的BigDecimal类型 : toAmount
     *
     * @param rs
     * @return
     * @add xiaofeng 2010-12-9
     */

    public static BigDecimal ta(BigDecimal rs) {

        return hv(rs) ? rs : BigDecimal.ZERO;

    }

    /**
     * 转换成有效的Double类型 : toAmount
     *
     * @param rs
     * @return
     * @add xiaofeng 2010-12-9
     */

    public static Double ta(Long rs) {

        return hv(rs) ? Double.parseDouble(Valid.ts(rs)) : 0.00;

    }

    /**
     * 转换成Long : toLong
     *
     * @param str
     * @return
     */

    public static Long tl(String str) {

        return hv(str) ? Long.parseLong(str) : null;

    }

    /**
     * 转换成Long : toLong
     *
     * @param i
     * @return
     */

    public static Long tl(Integer i, Long dec) {

        return hv(i) ? Long.valueOf(i) : dec;

    }

    /**
     * 转换成Long : toLong
     *
     * @param i
     * @return
     */

    public static Long tl(Integer i) {

        return hv(i) ? Long.valueOf(i) : null;

    }

    /**
     * 转换成有效的Long类型 : toAmount
     *
     * @param rs
     * @return
     * @add xiaofeng 2010-12-9
     */

    public static Long tl(Long rs) {

        return hv(rs) ? rs : 0;

    }

    /**
     * Exception输出 ：printStackTrace
     *
     * @param e
     * @return
     */

    public static void pe(Exception e) {

//        logger.error("errMsg = {}, stack info = {}", e.getMessage(), e);
    }

    /**
     * 日期格式 : formatDate
     *
     * @param date
     * @return
     */

    public static String fd(Date date) {

        return fd(date, "yyyy-MM-dd");

    }

    /**
     * 日期相加
     *
     * @param date
     * @param day
     * @return
     */

    public static Date addDate(Date date, int day) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.add(Calendar.DATE, day);

        return calendar.getTime();

    }

    /**
     * 日期格式 : formatDate
     *
     * @param date
     * @param args
     * @return
     */

    public static String fd(Date date, String args) {

        return hv(date) ? new SimpleDateFormat(args).format(date) : null;

    }

    /**
     * 日期格式 : formatDate
     *
     * @param date
     * @param args
     * @return
     */

    public static String fdA(Date date, String args) {

// date.setHours(23);

// date.setMinutes(59);

// date.setSeconds(59);

        Calendar c = Calendar.getInstance();

        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY, 23);

        c.set(Calendar.MINUTE, 59);

        c.set(Calendar.SECOND, 59);

// date.setTime(999999);

        return hv(c.getTime()) ? new SimpleDateFormat(args).format(c.getTime()) : null;

    }

    /**
     * 得到一天中的最大时间
     *
     * @param date
     * @return
     */

    public static Date fdA(Date date) {

        Calendar c = Calendar.getInstance();

        c.setTime(date);

        c.set(Calendar.HOUR_OF_DAY, 23);

        c.set(Calendar.MINUTE, 59);

        c.set(Calendar.SECOND, 59);

// date.setTime(999999);

        return c.getTime();

    }

    /**
     * 得到一天中的最大时间
     *
     * @param dateString
     * @return
     */

    public static Date maxDayTime(String dateString) {

        Calendar c = Calendar.getInstance();

        c.setTime(str2Date(dateString));

        c.set(Calendar.HOUR_OF_DAY, 23);

        c.set(Calendar.MINUTE, 59);

        c.set(Calendar.SECOND, 59);

        return c.getTime();

    }

    /**
     * 得到一天中的最小时间
     *
     * @param dateString
     * @return
     */

    public static Date minDayTime(String dateString) {

        Calendar c = Calendar.getInstance();

        c.setTime(str2Date(dateString));

        c.set(Calendar.HOUR_OF_DAY, 0);

        c.set(Calendar.MINUTE, 0);

        c.set(Calendar.SECOND, 0);

        return c.getTime();

    }

    /**
     * 返回list.size() : listSize
     *
     * @param list
     * @return
     * @add xiaofeng 2010-12-03
     */

    public static Integer ls(List list) {

        return hv(list) ? list.size() : 0;

    }


    /**
     * 转绝对值
     *
     * @param v1
     * @return
     */

    public static Double abs(Double v1) {

        return new BigDecimal(v1).abs().doubleValue();

    }

    public static BigDecimal abs(BigDecimal v1) {

        return v1.abs();

    }

    /**
     * 凑整
     *
     * @param v1
     * @return
     */

    public static Double ceil(Double v1) {

        return Math.ceil(v1);

    }

// Double精度 end

    /**
     * 某天星期几
     * <p>
     * <p>
     * <p>
     * 注意：1:星期日 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六
     *
     * @param date
     * @return
     * @add xiaofeng 2010-12-25
     */

    public static Integer getDayOfWeek(Date date) {

        Calendar c = Calendar.getInstance();

        c.setTime(date);

        return c.get(Calendar.DAY_OF_WEEK);

    }

    /**
     * 判断字符串中是否包含中文
     * <p>
     * <p>
     * <p>
     * 注意 : 如果str为null返回false
     *
     * @param str
     * @return
     * @add xiaofeng 2011-1-4
     */

    public static boolean isChinese(String str) {

        return hv(str) && PATTERN_CHINESE.matcher(str).find();

    }

    /**
     * 字符串首字母是否为数字
     * <p>
     * <p>
     * <p>
     * 注意 : 如果str为null返回false
     *
     * @param str
     * @return
     * @add xiaofeng 2011-1-4
     */

    public static boolean isNaN(String str) {

        return hv(str) && Character.isDigit(str.charAt(0));

    }

    /**
     * 字符串首字母是否为字母
     * <p>
     * <p>
     * <p>
     * 注意 : 如果str为null返回false
     *
     * @param str
     * @return
     * @add xiaofeng 2011-1-4
     */

    public static boolean isLetter(String str) {

        return hv(str) && Character.isLetter(str.charAt(0));

    }

    /**
     * 字符串转成日期
     *
     * @param str     日期字符串：例如：2011-11-11
     * @param pattern 日期格式 例如 ： yyyy-MM-dd
     * @return
     * @add xiaofeng 2011-2-18
     */

    public static Date str2Date(String str, String pattern) {

        if (!Valid.hv(str)) {

            return null;

        }

        SimpleDateFormat simpledateformat = new SimpleDateFormat(pattern);

        ParsePosition parseposition = new ParsePosition(0);

        Date date = simpledateformat.parse(str, parseposition);

        return date;

    }

    /**
     * 字符串转成日期
     * <p>
     * <p>
     * <p>
     * 注意：日期格式"yyyy-MM-dd"
     *
     * @param str 日期字符串：例如：2011-11-11
     * @return
     * @add xiaofeng 2011-2-18
     */

    public static Date str2Date(String str) {

        return str2Date(str, "yyyy-MM-dd");

    }

    /**
     * 截取
     *
     * @param str
     * @return
     * @author changhl
     * @date Mar 7, 2011 9:47:19 AM
     * @description:
     */

    public static String tn(String str) {

        if (Valid.hv(str)) {

            if (str.contains("|")) {

                return str.split("\\|")[1];

            } else {

                return str;

            }

        }

        return str;

    }

    /**
     * bool为true，返回exp1；为false，返回exp2
     *
     * @param bool 原字符串
     * @param exp1
     * @param exp2
     * @return
     * @add xiaofeng 2011-3-9
     */

    public static String nvl(boolean bool, String exp1, String exp2) {

        return bool ? exp1 : exp2;

    }

    /**
     * 得到月末最后一天
     *
     * @param date
     * @return
     * @author changhl
     * @date Mar 28, 2011 3:51:08 PM last day of month
     * @description:
     */

    public static Date ldom(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();

    }

    /**
     * 得到月初第一天
     *
     * @param date
     * @return
     * @author changhl
     * @date Mar 28, 2011 3:51:08 PM first day of month
     * @description:
     */

    public static Date fdom(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();

    }

    /**
     * 获取当前日期
     *
     * @return
     */

    public static String getCurrentDate() {

        return Valid.fd(new Date());

    }

    /**
     * 获取日期
     *
     * @param date
     * @return
     * @author changhl
     * @date Mar 28, 2011 3:51:08 PM first day of month
     * @description:
     */

    public static int getDate(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        return calendar.get(Calendar.DATE);

    }

    /**
     * 是否数字
     *
     * @param code
     * @return
     */

    public static boolean isDigital(String code) {

        Matcher dMatcher = PATTERN_NUMBER.matcher(code);

        return dMatcher.matches();

    }

    public static boolean isDigital2(String code) {

        Matcher dMatcher = PATTERN_FLOAT.matcher(code);

        Matcher dMatcher1 = PATTERN_NUMBER.matcher(code);

        return dMatcher.matches() || dMatcher1.matches();

    }

    /**
     * 是否英文字母
     *
     * @param code
     * @return
     */

    public static boolean isCharacter(String code) {

        Matcher wMatcher = PATTERN_LETTER.matcher(code);

        return wMatcher.matches();

    }

    /**
     * 格式化金额
     *
     * @param val
     * @return
     */

    public static String formatMoney(Object val) {

        DecimalFormat dFormat = new DecimalFormat("#0.00");

        return dFormat.format(val);

    }

    public static String decodeURI(String code, String encoding) {

        try {

            return java.net.URLDecoder.decode(code, encoding);

        } catch (UnsupportedEncodingException e) {

            Valid.pe(e);

        }

        return "";

    }

    /**
     * 两个日期之间相差的天数
     *
     * @param start 较小的时间
     * @param end   较大的时间
     * @return 相差天数
     * @throws ParseException
     */

    public static int daysBetween(Date start, Date end) {

        start = Valid.str2Date(Valid.fd(start));

        end = Valid.str2Date(Valid.fd(end));

        Calendar cal = Calendar.getInstance();

        cal.setTime(start);

        long time1 = cal.getTimeInMillis();

        cal.setTime(end);

        long time2 = cal.getTimeInMillis();

        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays));

    }

    //校验8位字符串是否为正确的日期格式
    private static boolean isValidDateTo(String str) {
        boolean result = true;
        //判断字符串长度是否为8位
        if (str.length() == 8) {
            // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
            //SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            try {
                // 设置lenient为false.
                // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
                format.setLenient(false);
                format.parse(str);
            } catch (ParseException e) {
                // e.printStackTrace();
                // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
                result = false;
            }
        } else {
            result = false;
        }

        return result;
    }




    public static Boolean  isNoNull(String rs){
         if(rs!=null&&!"".equals(rs)){
             //不等于空
             return  true;
         }
        return  false;
    }


    /**

     * 判断字符串是否为数字，小数

     *

     * @param str

     * @return

     */

    public static Boolean isDouble(String str, Integer scale) {

        try {

// 判断是否为数字

            Double.valueOf(str);

// 判断小数点后位数

            String[] s = str.split("\\.");

            if (s.length > 1 && s[1].length() > scale) {

                return false;

            }

        } catch (Exception e) {

            return false;

        }

        return true;

    }

    /**

     * 判断一个数是否为整数 (如：8.00 是整数，8.01不是整数)

     *

     * @param bigDecimal

     * @return

     * @author zhutong 2108/7/16

     */

    public static int checkIsInteger(BigDecimal bigDecimal) {

//将传入参数小数点后位数全部舍去，然后再与自身比较，如果相等则说明传入参数为整数

        BigDecimal floor = bigDecimal.setScale(0, BigDecimal.ROUND_DOWN);

        int compileResult = bigDecimal.compareTo(floor);

        if (compileResult == 0) {

            return 1;

        }

        return 0;

    }





    /**

     * 判断两个字符串，如果src为null，dst为空串，返回true，或dst为null,src为空串返回true

     */

    public static boolean equalsIgnoreNull(String src, String dst) {

        if (StringUtils.isBlank(src)) {

            if (StringUtils.isBlank(dst)) {

                return true;

            } else {

                return false;

            }

        } else {

            if (StringUtils.isNotBlank(dst)) {

                return eq(src, dst);

            } else {

                return false;

            }

        }

    }

    /***

     * List 深度克隆

     * @param srcList

     * @param

     * @return

     * @throws Exception

     */

    public static List listDeepCopy(List srcList) throws Exception{

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

        ObjectOutputStream out = new ObjectOutputStream(byteOut);

        out.writeObject(srcList);

        out.flush();

        byte[] objBytes=byteOut.toByteArray();

        out.close();

        ByteArrayInputStream byteIn = new ByteArrayInputStream(objBytes);

        ObjectInputStream inStream = new ObjectInputStream(byteIn);

        List destList = (List) inStream.readObject();

        inStream.close();

        return destList;

    }

    /**

     * 判断是否是Null 包括"null" "NULL" 字符串

     * @param object

     * @return

     */

    public static boolean isNull(String object) {

        if (StringUtils.isNotBlank(object)) {

            if (NULL_STR2.equals(object) || NULL_STR.equals(object)) { return true;}

            return false;

        }

        return true;

    }

    /**

     * 去除前后空格 保留中间空格

     * @param str

     */

    public static String saveInnerSpace(String str) {

        if (!Valid.hv(str)) {

            return str;

        }

        str = str.trim();

        while (str.startsWith(SPACE_STR)) {

            str = str.substring(1, str.length()).trim();

        }

        while (str.endsWith(SPACE_STR)) {

            str = str.substring(0, str.length() - 1).trim();

        }

        return str;

    }

    /**

     * 忽略前后空格 判断是否有值

     * @param str

     * @return

     */

    public static boolean hvIgnoreSpace(String str) {

        return hv(str) && saveInnerSpace(str).length() > 0;

    }

    /**

     * 根据单价位数判断是否符合要求

     * @param price

     * @param precision

     * @return

     */

    public static boolean checkPrice(String price, int precision){

        int priceLength = price.indexOf(".")>0 ? price.substring(price.indexOf(".")+1).length():0;

        if(priceLength>precision){

            return false;

        }

        return true;

    }

    /**

     * 获取小数精度

     * @param moneyStr

     * @return

     */

    public static int getNumberDecimalDigits(String moneyStr) {

        String[] num = moneyStr.split("\\.");

        if (num.length == 2) {

            for (;;){

                if (num[1].endsWith("0")) {

                    num[1] = num[1].substring(0, num[1].length() - 1);

                }else {

                    break;

                }

            }

            return num[1].length();

        }else {

            return 0;

        }

    }



    /***

     * 切割字符串

     * @author dxf

     * @param list

     * @param len

     * @param

     * @return

     */

    public static List splitList(List list, int len) {

        if (list == null || list.isEmpty() || len < 1) {

            return Collections.emptyList();

        }

        List result = new ArrayList<>();

        int size = list.size();

        int count = (size + len - 1) / len;

        for (int i = 0; i < count; i++) {

            List subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));

            result.add(subList);

        }

        return result;

    }

    public static void main(String[] args)
    {


        System.out.println(":::::"+    Valid.isDouble("88.01",2));
    }






}
