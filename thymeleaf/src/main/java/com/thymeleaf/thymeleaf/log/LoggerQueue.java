package com.thymeleaf.thymeleaf.log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列
 */
public class LoggerQueue {
    public static final int QUEUE_MAX_SIZE = 10000;
    private static LoggerQueue alarmMessageQueue = new LoggerQueue();
    //阻塞队列
    private BlockingQueue<LoggerMessage> blockingQueue = new LinkedBlockingQueue<LoggerMessage>(QUEUE_MAX_SIZE);

    public LoggerQueue() {
    }
    public static LoggerQueue getInstance(){
        return alarmMessageQueue;
    }

    /**
     * 消息入队
     * @param log
     * @return
     */
    public boolean push(LoggerMessage log){
        return this.blockingQueue.add(log);//队列满了就抛出异常,不阻塞
    }

    public LoggerMessage poll(){
        LoggerMessage result = null;
        try{
            result = this.blockingQueue.take();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return result;
    }
}
