package com.zkrs.framework.aspectj;

import com.zkrs.common.annotation.Log;
import com.zkrs.common.core.domain.model.LoginUser;
import com.zkrs.common.core.redis.RedisCache;
import com.zkrs.common.enums.BusinessStatus;
import com.zkrs.common.utils.SecurityUtils;
import com.zkrs.common.utils.ServletUtils;
import com.zkrs.common.utils.StringUtils;
import com.zkrs.common.utils.ip.IpUtils;
import com.zkrs.common.utils.reflect.ReflectUtils;
import com.zkrs.framework.config.LoadData;
import com.zkrs.framework.manager.AsyncManager;
import com.zkrs.framework.manager.factory.AsyncFactory;
import com.zkrs.system.domain.ActualCash;
import com.zkrs.system.domain.BusinessDictionary;
import com.zkrs.system.domain.ProjectInfo;
import com.zkrs.system.domain.SysOperLog;
import com.zkrs.system.enumeration.CommonlyCache;
import com.zkrs.system.section.RedisMonitor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

@Aspect
@Component
public class RedisMonitorAspect {
    @Autowired
    RedisCache redisCache;
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);


    /**
     * 处理完请求后执行
     * redisMonitor就是所切注解
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(redisMonitor)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, RedisMonitor redisMonitor, Object jsonResult) {
        try {
            operation(joinPoint, redisMonitor);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void operation(JoinPoint joinPoint, RedisMonitor redisMonitor) {

        Object[] obj2 = joinPoint.getArgs();
        //操作
        String operationV = redisMonitor.operation();
        //参数位置
        int indexV = redisMonitor.index();
        //缓存对象
        CommonlyCache stringTypeV = redisMonitor.stringType();
        //字典缓存
        if (CommonlyCache.DICTIONARIES.getName().equals(stringTypeV.getName())) {
            BusinessDictionary businessDictionary = (BusinessDictionary) obj2[indexV];
            String nameOld = businessDictionary.getOldName()+"";
            String name = businessDictionary.getZdName()+"";
            String id = businessDictionary.getId()+"";
            String rsid= redisCache.getCacheMapValue(CommonlyCache.DICTIONARIES.getName(), nameOld);
               if(rsid!=null){//修改
                   redisCache.delCacheMapValue(CommonlyCache.DICTIONARIES.getName(), nameOld);
                   redisCache.setCacheMapValue(CommonlyCache.DICTIONARIES.getName(), name, id );
               }else{//增加
                   redisCache.setCacheMapValue(CommonlyCache.DICTIONARIES.getName(), name, id );
               }

        }

    }

}
