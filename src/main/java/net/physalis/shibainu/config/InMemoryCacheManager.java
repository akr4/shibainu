package net.physalis.shibainu.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableCaching
@PropertySource("classpath:/config/app-${app.env}.properties")
@Profile("cache.inmemory")
public class InMemoryCacheManager {

    @Bean
    public ConcurrentMapCacheManager cacheManager() {
        return new ConcurrentMapCacheManager(
                "Book",
                "Book.findAll"
        );
    }
}
