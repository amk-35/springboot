package com.example.demo;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setAllowNullValues(false);
        cacheManager.setCacheNames(Arrays.asList("productCache","allProductCache"));
        System.out.println("here are the caches"+ cacheManager.getCacheNames());
        return cacheManager;
    }

    @CacheEvict(value="allProductCache", allEntries = true)
    @Scheduled(fixedDelay=100000, initialDelay = 0)
    public void evictCache(){
        System.out.println("Evicted the caches");
    }

}