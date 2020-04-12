package com.springcloudtest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class MyThreadPoolExecutorUtil extends ThreadPoolTaskExecutor {

    private static final long serialVersionUID = 5431011474708268199L;

    private static final  Logger logger = LoggerFactory.getLogger(MyThreadPoolExecutorUtil.class);


    /**
     * 输出线程池信息
     * @param
     */
    private void showThreadPoolTaskExecutor(String threadflag){
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

        if (threadPoolExecutor==null) {
            return;
        }
        logger.info("线程名称:[{}],线程标识:[{}],任务总数:[{}],已完成的任务总数:[{}],活跃数:[{}],队列数:[{}]",
                this.getThreadNamePrefix(),threadflag,threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getCompletedTaskCount(),threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getQueue().size());
    }

    @Override
    public void execute(Runnable task) {
        showThreadPoolTaskExecutor("do RunningTask");
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        showThreadPoolTaskExecutor("do RunningTask,long startTimeout");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        showThreadPoolTaskExecutor("do submit_RunningTask");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showThreadPoolTaskExecutor("do submit_CallableTask");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        showThreadPoolTaskExecutor("do submitListenable_Runnable");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showThreadPoolTaskExecutor("do submitListenable_Callable");
        return super.submitListenable(task);
    }
}
