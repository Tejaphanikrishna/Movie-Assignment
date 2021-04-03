package com.mindtree.MovieAssignment;

import org.springframework.cache.ehcache.EhCacheCacheManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.config.CacheConfiguration;

@SpringBootApplication
@EnableCaching
@Configuration
public class MovieAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieAssignmentApplication.class, args);
	}

	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager() {
		CacheConfiguration cache_ten = new CacheConfiguration();
		cache_ten.setName("cache");
		cache_ten.setMemoryStoreEvictionPolicy("LRU");
		cache_ten.setMaxEntriesLocalHeap(1000);
		cache_ten.setTimeToLiveSeconds(10);
		net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
		configuration.addCache(cache_ten);

		return net.sf.ehcache.CacheManager.newInstance(configuration);
	}

	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}
}
