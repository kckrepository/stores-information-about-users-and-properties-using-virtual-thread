package org.andi.dani.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@EnableAsync
@Configuration
@ConditionalOnProperty(
        value = "spring.thread-executor",
        havingValue = "virtual"
)
public class ConfigVirtualThread {
    @Bean
    AsyncTaskExecutor applicationTaskExecutor() {
        // enable async servlet support
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        return new TaskExecutorAdapter(executorService::execute);
    }

    @Bean
    TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
        return protocolHandler -> {
            log.info("Configuring " + protocolHandler + " to use VirtualThreadPerTaskExecutor");
            protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        };
    }
}
