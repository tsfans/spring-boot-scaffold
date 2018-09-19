package cn.swift.configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadExecutorConfiguration implements AsyncConfigurer {

  private static final Logger logger = LoggerFactory.getLogger(AsyncUncaughtExceptionHandler.class);

  @Value("${threadpool.corePoolSize:10}")
  private int corePoolSize;
  @Value("${threadpool.maxPoolSize:20}")
  private int maxPoolSize;
  @Value("${threadpool.queueCapacity:10}")
  private int queueCapacity;
  @Value("${threadpool.keepAliveSeconds:5}")
  private int keepAliveSeconds;
  @Value("${threadpool.awaitTerminationSeconds:30}")
  private int awaitTerminationSeconds;

  @Bean
  public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setQueueCapacity(queueCapacity);
    executor.setKeepAliveSeconds(keepAliveSeconds);
    executor.setAwaitTerminationSeconds(awaitTerminationSeconds);
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.setThreadNamePrefix("spring-thread-" + executor);
    executor.initialize();
    return executor;
  }

  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setQueueCapacity(queueCapacity);
    executor.setKeepAliveSeconds(keepAliveSeconds);
    executor.setAwaitTerminationSeconds(awaitTerminationSeconds);
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.setThreadNamePrefix("aync-task-thread-" + executor);
    executor.initialize();
    return executor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    AsyncUncaughtExceptionHandler handler = (throwable, method, params) -> {
      logger.error("Exception message - " + throwable.getMessage());
      logger.error("Method name - " + method.getName());
      for (Object param : params) {
        logger.error("Parameter value - " + param);
      }
    };
    return handler;
  }

}
