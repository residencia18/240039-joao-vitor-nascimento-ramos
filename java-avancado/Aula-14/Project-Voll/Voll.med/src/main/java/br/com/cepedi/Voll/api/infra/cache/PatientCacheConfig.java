package br.com.cepedi.Voll.api.infra.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@CacheConfig
public class PatientCacheConfig {

    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager("patientsActivated");
    }

}
