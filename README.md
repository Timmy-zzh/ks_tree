#### 1.JVM
<div id="top">顶部</div>
#### 1.1.程序运行时内存分配

- 程序计数器
  - 当前线程执行的位置
- 虚拟机栈
  - 栈帧...
    - 局部变量表
    - 操作数栈
    - 动态链接
    - 返回地址
- 本地方法栈
- 方法区
  - 已被jvm加载的类信息，常量，静态变量等
- 堆区
  - 存放对象实例
  - 分代

#### 1.2.GC算法与分代回收策略

- 可达性分析
- GCRoots
  - Java虚拟机栈（局部变量表）中引用的对象
  - 方法区中静态引用指向的对象
  - 处于存活状态的线程对象
  - Native方法中JNI引用对象
- GC算法
  - 标记清除（缺点：内存碎片）
  - 复制算法（只能使用一半空间）
  - 标记整理
- 分代回收策略
  - 年轻代
    - Eden
    - S0
    - S1
  - 老年代



#### 2.class字节码文件

#### 2.1.class字节码文件结构

- 无符号数和表
- class文件组成
  - 魔数 — 0XCAFEBABE
  - 版本号
  - 常量池
  - 类的访问标志
  - 类索引，父类，接口索引
  - 字段表
  - 方法标志
  - 属性表

#### 2.2.使用ASM框架动态生成代码

- ClassVisitor 访问者模式

#### 2.3.ClassLoader 类加载机制

- 类被加载器加载时机
  - 调用类的构造器
  - 调用类中静态变量或静态方法
- JVM中自带的类加载器
  - BootstrapClassLoader
  - ExtClassLoader
  - AppClassLoader
- 双亲委派模式
  - loadClass(String name)方法,参数为全类明
  - 父类加载器parent不为空，调用parent的loadClass方法加载
  - parent为空，调用BootstrapClassLoader加载该类
  - 都没有加载成功，调用当前加载器的findClass方法加载

#### 2.4.Class文件加载过程

- class字节码文件加载到内存过程： 装载-> 链接（验证-准备-解析）->初始化
- 装载
  - jvm查找.class文件并生成字节流，根据字节流创建java.lang.Class对象
  - 存储在方法区
- 链接
  - 验证
    - 文件格式验证
    - 元数据验证等
  - 准备
    - 对类中的**静态变量**分配内存，并设置为0值
  - 解析
    - 将常量池中的符号引用转换为直接引用（具体的内存地址）
- 初始化
  - 执行类构造器 clinit 方法过程

- 对象初始化顺序
  - 父类静态变量和静态代码块
  - 子类静态变量和静态代码块
  - 父类普通成员变量和普通代码块
  - 父类构造函数
  - 子类普通成员变量和普通代码块
  - 子类构造函数



#### 3.并发编程

#### 3.1.Java内存模型（JMM）

- CPU与内存，硬件发展不对等
  - 工作内存
  - 主内存
- 高速缓存
  - 缓存一致性问题
  - 指令重排
- happens-before原则
  - volatile：修饰变量，保证数据原子性
  - synchronized修饰：同步

#### 3.2.Thread

- 进程与线程
  - CPU时间片轮转机制
- 线程的创建
  - 自定义类Thread
  - 实现接口Rnnable
- 线程终止
  - interrupt()方法
- 线程生命周期
  - 新建状态：new
  - 就绪状态：start
  - 运行状态：
  - 无限等待：wait
    - _WaitSet
  - 限期等待：sleep
  - 阻塞等待：
    - _EntryList
  - 死亡状态

#### 3.3.synchronized

- 修饰方法，代码块
- 类锁与对象锁
- 实现原理
  - 内置锁：monitorenter与monitorexit指令
  - instanceOopDesc对象，对象头，标记字段（Mark Word）
  - ObjectMonitor：监视器

```java
关键属性：
_owner:		//指向持有ObjectMonitor对象的线程
_WaitSet:	//存放处于wait状态的线程队列
_EntryList：//存放处于等待锁block状态的线程队列
_recursions://锁的重入次数
_count;		//记录该线程获取锁的次数
```
- 线程优化
  - 锁自旋
  - 轻量级锁
    - 不存在锁竞争情况
  - 偏向锁

#### 3.4.ReentrantLock

- 手动加锁和释放锁
- 公平锁与非公平锁
- 实现原理
  - AQS + CAS
  - 模版设计模式
  - state锁状态
  - Node双端队列
- 主要操作逻辑
  - 竞争锁失败：保存到队列末端
  - 释放锁操作：现场唤醒

#### 2.5.线程池

- 线程池体系
  - 不同线程池实现
- 线程池执行流程
  - 核心线程
  - 阻塞队列
  - 最大线程
  - 拒绝策略

[回到顶部](#top)
