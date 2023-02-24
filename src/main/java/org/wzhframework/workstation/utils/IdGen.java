package org.wzhframework.workstation.utils;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.wzhframework.workstation.cache.RedisGen;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author wzh
 * @since 2023/2/23
 */
@Component
@ConditionalOnBean(RedisGen.class)
public class IdGen {
    private static Map<String, List<Integer>> IDCACHE = new ConcurrentReferenceHashMap<>();

    @Resource
    private RedisGen redisGen;


    @PostConstruct
    public void init() {
        List<Integer> genIds = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            genIds.add(redisGen.nextInt());
        }
        IDCACHE.put("_REQUESTID", genIds);
    }

    public static String getRequestId() {
        StringBuffer buffer = new StringBuffer();
        String appName = SpringUtils.getRequiredProperty("spring.application.name");
        buffer.append(appName);
        buffer.append('-');
        buffer.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        List<Integer> ids = IDCACHE.get("_REQUESTID");
        Iterator<Integer> iterator = ids.iterator();
        if(iterator.hasNext()) {
            buffer.append(iterator.next());
            iterator.remove();
        }
        return buffer.toString();
    }
}
