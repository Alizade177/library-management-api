package com.farid.libraryapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Value("${async.notification.core-pool-size}")
    private int corePoolSize;

    @Value("${async.notification.max-pool-size}")
    private int maxPoolSize;

    @Value("${async.notification.queue-capacity}")
    private int queueCapacity;

    @Bean(name = "notificationExecutor")
    public Executor notificationExecutor() {

        ThreadPoolTaskExecutor executor =
                new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("notification-");

        executor.initialize();

        return executor;
    }
}