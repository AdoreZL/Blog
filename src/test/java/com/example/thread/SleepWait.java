package com.example.thread;

/**
 * @author zl
 * @version 1.0
 * @date 2020/4/6 14:35
 */
/*
* wait 和 sleep 区别？

1，wait可以指定时间也可以不指定。
   sleep必须指定时间。

2，在同步中时，对cpu的执行权和锁的处理不同。
	wait：释放执行权，释放锁。
	sleep:释放执行权，不释放锁。
* */
public class SleepWait {
    void show(){}
}
