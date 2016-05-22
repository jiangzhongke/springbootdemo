package com.bootdemo.task.executor;

/**
 * @ClassName:     DemoAsyncTask
 * @Description:   异步任务测试
 * @author:    	   tommy
 * @date:          2016/05/18 12:00
 *
 * ${tags}
 */


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class DemoAsyncTask {
	
    @Async
    public void executeAsyncTask(Long i){
        System.out.println("执行异步任务:"+i);
    }

    @Async
    public void executeAsyncTaskPlus(Long i){
        System.out.println("执行异步任务+1:"+(i+1));
    }
}