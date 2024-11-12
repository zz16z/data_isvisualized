package com.zkrs.framework.config;

import com.zkrs.common.core.redis.RedisCache;
import com.zkrs.system.domain.BusinessDictionary;
import com.zkrs.system.domain.ProjectInfo;
import com.zkrs.system.enumeration.CommonlyCache;
import com.zkrs.system.service.IBusinessDictionaryService;
import com.zkrs.system.service.IProjectInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Order(1)
public class LoadData implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LoadData.class);
    @Autowired
    RedisCache  redisCache;
    @Autowired
    private IProjectInfoService projectInfoService;
    @Autowired
    private IBusinessDictionaryService businessDictionaryService;
    @Override
    public void run(String... args) throws Exception {
        /*
        FastJson2JsonRedisSerializer serializerJson = new FastJson2JsonRedisSerializer(Object.class);
        StringRedisSerializer serializer = new StringRedisSerializer();
         */
        //删除项目名缓存
        //redisCache.deleteObject(CommonlyCache.PROJECT_INFO.getName());
        //缓存项目名称
       // List<ProjectInfo>  listProjectInfo= projectInfoService.selectProjectInfoCache(null);
           /*
        redisCache.deleteObject(CommonlyCache.DICTIONARIES.getName());
        List<BusinessDictionary>  listBusiness=businessDictionaryService.selectBusinessDictionaryList(null);
          */
        /*
        redisCache.redisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                //缓存项目名称暂时不做
//                if(listProjectInfo!=null&&listProjectInfo.size()>0){
//                    for(ProjectInfo item:listProjectInfo ){
//                        connection.hSet(serializer.serialize(CommonlyCache.PROJECT_INFO.getName()),
//                                serializer.serialize(item.getXmqkXmmc()),
//                                serializerJson.serialize(item.getXmqkProjectId())) ;
//                    }
//                }
                if(listBusiness!=null&&listBusiness.size()>0){
                    for(BusinessDictionary item:listBusiness ){
                        connection.hSet(serializer.serialize(CommonlyCache.DICTIONARIES.getName()),
                                serializer.serialize( item.getZdName()),
                                serializerJson.serialize(item.getId()+"" )) ;
                    }
                }


                return null;
            }
        }, serializer);

        //加载项目名称数据
        log.info("缓存加载完毕！");
         */
    }
}
