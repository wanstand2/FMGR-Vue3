package com.ruoyi.fmgr.service.utils;

import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.common.core.redis.RedisCache;

@Service
public class CodeGenerater {

    @Autowired
    private RedisCache redisCache;
    
    private static final long DAY_TIME = 1000L * 60L * 60L * 24L;

    public String generateCode(String table, int length, Date d) {
        if(d == null) {
            d = new Date();
        }
        String datetime = new SimpleDateFormat("yyMMdd").format(d);
        System.out.println(datetime);
        String key = "fmgre_" + table + "_" + datetime;
        Long autoId = redisCache.increment(key, 1L);
        if(autoId.equals(1L)) {
            redisCache.expire(key, 
            ((d.getTime() % DAY_TIME) + 1) * DAY_TIME - d.getTime() - 100L, 
            TimeUnit.MICROSECONDS);
        }
        return datetime + String.format("%0" + length + "d", autoId);
    }
}
