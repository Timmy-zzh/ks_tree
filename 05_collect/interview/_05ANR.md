##### ANR



- ANR现象
  - 应用程序在UI线程被阻塞太长时间，就会出现ANR，系统会弹出一个提示框

- ANR出现的原因
  - 1.KeyDispathTimeout: intpu事件在5S内没有处理完成发生了ANR
  - 2.BroadcastTimeout：
    - 前台广播：onReceiver在10s内没有处理完成发生ANR
    - 后台广播：onReceiver在60s内没有处理完成发生ANR
  - 3.ServiceTimeout
    - 前台服务：生命周期调用20s内没有处理完成
    - 后台服务：生命周期200s内没有处理完成
  - 4.ContentProviderTimeout
    - 内容提供者在10s内没有处理完成发生ANR
- ANR出现的原因：
  - 主线程频繁进行耗时的IO操作，如数据库读写
  - 多线程操作的死锁，主线程被block阻塞了
  - 主线程被Binder对端block
  - SystemServer中WatchDog出现ANR
  - service binder的连接达到上限
- ANR的问题查找
  - 1.查看events_log
    - 通过搜索am_anr关键字，查找出现anr的时间点，进程pid，和anr类型
    - 并查看anr出现的时间节点5s前的操作
  - 2.traces.txt 日志分析： /data/anr/traces.txt 
    - 通过traces日志获取发生anr时的堆栈信息
- ANR的解决办法
  - BlockCanary