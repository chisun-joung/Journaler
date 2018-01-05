package com.example.chisunjoung.journaler.execution

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by chisunjoung on 2018. 1. 5..
 */
class TaskExecutor private constructor(
        corePoolSize: Int,
        maximumPoolSize: Int,
        workQueue: BlockingQueue<Runnable>?
) : ThreadPoolExecutor(
        corePoolSize,
        maximumPoolSize,
        0L,
        TimeUnit.MILLISECONDS,
        workQueue
) {
    companion object {
        fun getInstance(capacity: Int): TaskExecutor {
            return TaskExecutor(
                    capacity,
                    capacity*2,
                    LinkedBlockingDeque<Runnable>()
            )
        }
    }
}