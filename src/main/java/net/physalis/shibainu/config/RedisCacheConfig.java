package net.physalis.shibainu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

@Configuration
@EnableCaching
@PropertySource("classpath:/config/app-${app.env}.properties")
@Profile("cache.redis")
public class RedisCacheConfig {

    @Autowired
    private Environment env;

    @Bean
    public RedisCacheManager cacheManager() {
        RedisTemplate<Integer, Object> tmpl = new RedisTemplate<>();
        tmpl.setConnectionFactory(redisConnectionFactory());
        tmpl.setKeySerializer(IntSerializer.INSTANCE);
        tmpl.setValueSerializer(new JdkSerializationRedisSerializer());
        tmpl.afterPropertiesSet();
        RedisCacheManager cacheManager = new RedisCacheManager(tmpl);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory cf = new JedisConnectionFactory();
        cf.setHostName(env.getProperty("cache.redis.hostName", "localhost"));
        cf.setPort(env.getProperty("cache.redis.port", Integer.class, 6379));
        cf.afterPropertiesSet();
        return cf;
    }

    public static enum IntSerializer implements RedisSerializer<Integer> {
        INSTANCE;

        @Override
        public byte[] serialize(Integer i) throws SerializationException {
            if (null != i) {
                return i.toString().getBytes();
            } else {
                return new byte[0];
            }
        }

        @Override
        public Integer deserialize(byte[] bytes) throws SerializationException {
            if (bytes.length > 0) {
                return Integer.parseInt(new String(bytes));
            } else {
                return null;
            }
        }
    }

}
