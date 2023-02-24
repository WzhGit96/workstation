package org.wzhframework.workstation.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Component;
import org.wzhframework.workstation.config.RedisConfig;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author wzh
 * @since 2023/2/23
 */
@Component
@ConditionalOnBean(RedisConfig.class)
public class RedisGen {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private RedisAtomicInteger counterIntTx;

    @PostConstruct
    public void init() {
        counterIntTx = new RedisAtomicInteger("_REQUESTID", redisTemplate.getConnectionFactory());
    }

    public int nextInt() {
        int i = counterIntTx.incrementAndGet();
        if (i < 0) {
            synchronized (this) {
                i = counterIntTx.incrementAndGet();
                if (i < 0) {
                    counterIntTx.set(0);
                    i = 0;
                }
            }
        }
        return i;
    }

    @PreDestroy
    public void destory() {
        counterIntTx = null;
    }
}
