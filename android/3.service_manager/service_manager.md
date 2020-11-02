##### ServiceManager是什么？

各种服务的大管家，各种服务都在sm中注册

##### sm注册流程

- 打开驱动binder_open
- 设置sm为守护进程，成为binder大管家
- 进入无限循环，等待client端发来请求

<img src="./sm启动流程.png" alt="sm启动流程" style="zoom:75%;" />

##### sm添加服务

<img src="./sm添加服务通信.png" alt="sm添加服务通信" style="zoom:50%;" />

##### AIDL进程间通信自定义

