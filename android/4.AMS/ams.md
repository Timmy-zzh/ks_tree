##### AMS启动流程

SystemServer.class

-startBootstrapServices()

--初始化ActivityManagerService

--ActivityManagerService.Lifecycle.class

--startService()

-setSystemProcess()

--ServiceManager.addService()  注册AMS到sm中

##### AMS相关类

ProcessRecord进程相关类

##### 与Activity管理相关类

ActivityRecord:一个代表Activity

TaskRecord （ArrayList<ActivityRecord> Activity栈）

ActivityStack（ArrayList<TaskRecord>）

ActivityStackSupervisor （管理ActivityStack）

