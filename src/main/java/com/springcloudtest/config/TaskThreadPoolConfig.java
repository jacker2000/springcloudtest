package com.springcloudtest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
//异步线程
@EnableAsync

public class TaskThreadPoolConfig {

    /**
     * /**
     * 如何来配置参数：
     * 	tasks: 每秒的任务数，假设为500~1000
     *	taskcost: 每个任务花费时间，假设0.1s (300ms = 1000 * 0.3)
     *	responsetime: 系统允许容忍的最大响应时间，假设1s
     *
     * 	corePoolSize = tasks/(1/taskcost) = tasks * taskcost = (500~1000) * 0.1 = 50~100 个线程。
     * 		corePoolSize 设置应该大于50，根据8020原则，如果80%的每秒任务数小于800，那么corePoolSize 设置为80即可.
     *
     * 	queueCapacity = (corePoolSize / taskcost) * responsetime
     * 		计算可得 queueCapacity = 80 / 0.1 * 1 = 80 。意思是队列里的线程可以等待1s,超过了需要新开线程来执行。
     * 		切记不能设置为Integer.MAX_VALUE, 这样队列会很大，线程数只会保持在corePoolSize大小，当任务徒增时，不能新开线程来执行，响应时间会随之徒增。
     *
     * 	maxPoolSize = (max(tasks)-queueCapacity) / (1/taskcost)
     * 		计算可得 maxPoolSize = (1000 -80) / 10 = 92
     * 		(最大任务数-队列容量) / 每个线程每秒处理能力 = 最大线程数
     *
     * 	rejectedExecutionHandler: 根据具体情况来决定，任务不重要可丢弃，任务重要则需要利用一些缓冲机制来处理。
     *
     * @return
     */
    @Bean(name = "springTaskExecutor")
    public TaskExecutor springTaskExecutor(){

        ThreadPoolTaskExecutor threadPoolExecutor = new MyThreadPoolExecutorUtil();
        //设置核心线程数：平时保留的线程数
        threadPoolExecutor.setCorePoolSize(300);
        //设置最大线程数：当workQueue 都放不下时，启动新线程，最大线程数
        threadPoolExecutor.setMaxPoolSize(1000);
        //设置队列容量：LinkedBlockingQueue<Runnable>(queueCapacity),当默认65536（Integer.MAX_VALUE）大量请求任务时，容易造成内存耗尽。
        threadPoolExecutor.setQueueCapacity(500);
        //设置线程活跃时间（秒）：超出corePoolSize 数量的线程的保留时间
        threadPoolExecutor.setKeepAliveSeconds(60);
        //设置默认线程名称
        threadPoolExecutor.setThreadNamePrefix("policy-");
        //设置拒绝策略：饱和策略：【AbortPolicy(默认：直接抛弃)、CallerRunsPolicy：用调用者的线程执行任务、DiscardOldesPolicy：抛弃队列中最久的任务、DiscardPolicy：抛弃当前任务】
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //等待所有任务结束后再关闭线程池
        threadPoolExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return threadPoolExecutor;
    }

}
