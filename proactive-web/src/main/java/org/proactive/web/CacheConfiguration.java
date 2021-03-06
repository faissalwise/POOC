package org.proactive.web;

import java.util.concurrent.TimeUnit;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;
import org.proactive.configuration.DatabaseConfiguration;
import org.proactive.configuration.MetricsConfiguration;
import org.proactive.configuration.ProActiveProperties;
import org.proactive.configuration.WebConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(ProActiveProperties proActiveProperties) {
        ProActiveProperties.Cache.Ehcache ehcache =
                proActiveProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(org.proactive.web.domain.ConfigParam.class.getName(), jcacheConfiguration);
//            cm.createCache(org.proactive.web.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
//            cm.createCache(org.proactive.web.domain.User.class.getName(), jcacheConfiguration);
//            cm.createCache(org.proactive.web.domain.Authority.class.getName(), jcacheConfiguration);
//            cm.createCache(org.proactive.web.domain.User.class.getName() + ".authorities", jcacheConfiguration);
//            cm.createCache(org.proactive.web.domain.SocialUserConnection.class.getName(), jcacheConfiguration);
        };
    }
}
