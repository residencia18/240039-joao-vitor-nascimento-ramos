package br.com.cepedi.Voll.api.infra.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManagerAppointment() {
        return new ConcurrentMapCacheManager("patientsWithAppointmentInDataBetween");
    }

    @Bean
    public CacheManager cacheManagerDoctor() {
        return new ConcurrentMapCacheManager("doctorsActivated");
    }

    @Bean
    @Primary
    public CacheManager cacheManagerPatient() {
        return new ConcurrentMapCacheManager("patientsActivated");
    }

}