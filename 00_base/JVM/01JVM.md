1. AppRuntime  -- ART
   - AOT : ahead of time
2. LLVM： 编译器
3. ART ： Android Run Time ：安卓运行时环境
   	- 使用Clang 进行编译
      	- gcc ： Linux编译器   -s ， 替代llvm
4. 垃圾回收：
   - GC触发的机制
5. 垃圾回收算法
6. Dalvik vs  ART
   - 状态：
     - monitor ：监听
     - native 
     - VM write
     - 僵尸态
   - AOT ： 编译时间变长
7. 虚拟机的作用
   - 字节码：多个字节码打包成dex文件
   - 处理数据与指令
8. 基于虚拟机栈，  寄存器
   - 基于寄存器的话，指令条数更少  add R1 R2 R3
   - 与寄存器栈比较，没有入栈出栈操作
9. ART
   - libart.so
   - JNI (javaVM , JNIEnv)
10. ClassLoader
    - Dex 
11. 虚拟机启动，创建
12. JNI ONload 调用函数时机
13. JNI ： 优化，线程，特性
14. OAT
    - .art : dex文件的集合 -- 镜像文件 boot.art
    -  .oat :  工具dex2oat   :存储代码
    -  .odex： 优化后的dex文件
15. 虚拟机如何启动？-- 重要
    - init.c
    - app_main.cpp -- main()
    - runtime.start() -- 启动Zygote，
    - JniInvocation.init()
    - startVM() -- 启动虚拟机
    - startReg -- 注册native函数
    - JNI_CreateJavaVM:  进程，不同线程之间的数据









