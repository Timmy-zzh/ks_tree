《深入理解Android内核设计思想》

LLVM ： 源码===》机器码  前端（源代码解析 error 处理 ） 优化器  后端 （代码生成 （源码 --》指令集合)）分层的好处

NDK toolchain

LLVM ART？  AOT（预编译）

算法 垃圾回收

1、GC触发机制是什么？

- Malloc 堆内存满 分配新的内存块
- Concurrent
- 三方框架 APM DUMP 哈哈库 创建Hprof
- gc函数

2、垃圾回收算法？

ART VS Dalvik

3、虚拟机作用？

class

指令+数据 add sub

调用栈  寄存器？数 ADD R1，R2，R3 （1条指令）2次？ 1次 

ART？

JNI（javaVM、JNIENV）



虚拟机相关：

1、CLassLoader（热修复 BootClassLoader、Dex、System）

2、Zygote进程、虚拟机创建和启动、JNI ONload调用函数时机

3、JNI（优化、特性、线程）



Dex字节码：

请大家参考相关书籍掌握



OAT：

- .art: image （dex文件的集合）Zygote启动 boot.art

- .oat: dex2oat  boot.oat (代码）

- .odex:优化后的dex文件

虚拟机如何启动？







### Dalvik优化点

dex优化，Java是将java源代码编译成.class文件，而Android在Java虚拟机的基础上，将多个.class文件打包成一个或者多个.dex。

相关命令:

~~~shell
jar cvf AllDex.jar Dex1.class Dex2.class

dx --dex --output AllDex.dex AllDex.jars

dexdump -d -l plain AllDex.dex
~~~

还记得分包后的65536问题吗？

![image-20201129200216094](https://tva1.sinaimg.cn/large/0081Kckwly1gl6ao9pt6fj318c0u0ajq.jpg)

### Android Dalvik虚拟机和ART虚拟机对比

#### Dalvik vs ART

- Dalvik
  Android4.4及以前使用的都是Dalvik虚拟机，我们知道Apk在打包的过程中会先将java等源码通过javac编译成.class文件，但是我们的Dalvik虚拟机只会执行.dex文件，这个时候dx会将.class文件转换成Dalvik虚拟机执行的.dex文件。Dalvik虚拟机在启动的时候会先将.dex文件转换成快速运行的机器码，又因为65535这个问题，导致我们在应用冷启动的时候有一个合包的过程，最后导致的一个结果就是我们的app启动慢，这就是Dalvik虚拟机的JIT特性（Just In Time）。
- ART
  ART虚拟机是在Android5.0才开始使用的Android虚拟机，ART虚拟机必须要兼容Dalvik虚拟机的特性，但是ART有一个很好的特性AOT（ahead of time），这个特性就是我们在安装APK的时候就将dex直接处理成可直接供ART虚拟机使用的机器码，ART虚拟机将.dex文件转换成可直接运行的.oat文件，ART虚拟机天生支持多dex，所以也不会有一个合包的过程，所以ART虚拟机会很大的提升APP冷启动速度。

- ART优点：
  1. 加快APP冷启动速度
  2. 提升GC速度
  3. 提供功能全面的Debug特性
- ART缺点：
  1. APP安装速度慢，因为在APK安装的时候要生成可运行.oat文件
  2. APK占用空间大，因为在APK安装的时候要生成可运行.oat文件

### ART虚拟机的重点

apk以进程的形式运行，进程的创建是由zygote来完成的。所以第一步要搞清楚Android应用进程启动过程。VM是在什么地方被创建的？是通过什么形式创建的？要思考。

####**Android的应用进程启动主要过程：**

Tips：流程的速记。

~~~c
- zygote启动脚本 （在 linux 系统中，service 通常是一种被称为守护进程 (daemon) 的程序。它通常在系统启动时启动，并一直运行于后台，直到系统关闭时终止。） 
service zygote /system/bin/app_process64 -Xzygote /system/bin --zygote --start-system-server
    class main
    priority -20
    user root
    group root readproc reserved_disk
    socket zygote stream 660 root system
    socket usap_pool_primary stream 660 root system
    onrestart write /sys/android_power/request_state wake
    onrestart write /sys/power/state on
    onrestart restart audioserver
    onrestart restart cameraserver
    onrestart restart media
    onrestart restart netd
    onrestart restart wificond
    writepid /dev/cpuset/foreground/tasks
    
- app_main.cpp
AppRuntime runtime;
runtime.start("com.android.internal.os.ZygoteInit", args, zygote);
在start方法中完成vm的启动、JNI的注册等核心业务。

~~~

- Android的应用进程启动是apk在manifest里申明的Activity，Service，BroadcastReceiver等组件被调起时而触发的。我们以Activity为例，当点击桌面上的应用图标时，桌面会调用startActivity，启动manifest里申明的相应Launcher的Activity，而Activity的startActivity会通过Binder调用来到ActivityManagerService(AMS)里。AMS是system_server的一个服务，负责管理Android系统的所有Activity的栈，逻辑比较复杂，在这里就不详细分析。AMS里startActivity的时候，如果发现这个应用进程没有启动，那么就会通过Zygote创建出这个进程。 

- Zygote创建进程是socket跨进程的调用。通过LocalSocket通信，来完成进程的创建，所以这里的Process.start只是一个Client端的调用，实际是由Server端的接收到消息后处理的。Server端是app_process这个进程里，这是个常驻的系统服务。

![image-20201129202429174](https://tva1.sinaimg.cn/large/0081Kckwly1gl6bbdbt5hj31980u0qep.jpg)

最终会调用startVm()函数。

![image-20201129202913902](https://tva1.sinaimg.cn/large/0081Kckwly1gl6bgb82a3j31890u0dmi.jpg)

JNI_CreateJavaVM是个接口，不同的虚拟机有不同的实现。

**经典问题：**

问题1:虚拟机启动的过程主要包含哪些步骤？

问题2:JavaVM和JNIEnv是在哪里被创建的？

问题3:Android中获取native层Runtime的方法？1.动态的API dlopen

问题4:JavaVMExt和JNIEnvExt区别和联系，各是什么含义？

学会在源码中答案，加强理解和记忆。



start()主要的功能点：

- jni_invocation.Init()方法初始化jni接口
- startVm()方法创建虚拟机
- startReg()方法注册Android方法
- CallStaticVoidMethod()方法打开ZygoteInit类的main方法。完成从c/c++到java代码。

startVm()主要的功能点：

- startVM的前半部分是在处理虚拟机的启动参数，处理完配置参数后，会调用libart.so提供的一个接口：JNI_CreateJavaVM函数。

~~~c++
extern "C" jint JNI_CreateJavaVM(JavaVM** p_vm, JNIEnv** p_env, void* vm_args) {
  ScopedTrace trace(__FUNCTION__);
  const JavaVMInitArgs* args = static_cast<JavaVMInitArgs*>(vm_args);
  if (JavaVMExt::IsBadJniVersion(args->version)) {
    LOG(ERROR) << "Bad JNI version passed to CreateJavaVM: " << args->version;
    return JNI_EVERSION;
  }
  RuntimeOptions options;
//解析虚拟机选项
  for (int i = 0; i < args->nOptions; ++i) {
    JavaVMOption* option = &args->options[i];
    options.push_back(std::make_pair(std::string(option->optionString), option->extraInfo));
  }
  bool ignore_unrecognized = args->ignoreUnrecognized;
  //创建一个Runtime运行时对象
  if (!Runtime::Create(options, ignore_unrecognized)) {
    return JNI_ERR;
  }

  // Initialize native loader. This step makes sure we have
  // everything set up before we start using JNI.
  android::InitializeNativeLoader();

  Runtime* runtime = Runtime::Current();
  bool started = runtime->Start();
  if (!started) {
    delete Thread::Current()->GetJniEnv();
    delete runtime->GetJavaVM();
    LOG(WARNING) << "CreateJavaVM failed";
    return JNI_ERR;
  }
//获取已经启动完成的虚拟机实例，并通过p_vm这个指向指针的指针变量，把虚拟机实例传给AndroidRuntime中的变量JavaVM* AndroidRuntime::mJavaVM。
  *p_env = Thread::Current()->GetJniEnv();
  *p_vm = runtime->GetJavaVM();
  return JNI_OK;
}

//runtime负责提供art虚拟机的运行时环境，然后调用其init方法来初始化虚拟机。注意runtime的核心知识点
step1,new gc::heap()，创建Heap对象，这是虚拟机管理对内存的起点。
step2,new JavaVmExt(),创建Java虚拟机实例。
step3,Thread::attach()，attach主线程。
step4,new ClassLinker()，成功attach到runtime环境后，创建ClassLinker实例负责管理java class。

到这里，虚拟机的创建和初始化就完成了。
~~~

#### JavaVM和JNIEnv

~~~c
#if defined(__cplusplus)
typedef _JNIEnv JNIEnv;
typedef _JavaVM JavaVM;
#else
typedef const struct JNINativeInterface* JNIEnv;
typedef const struct JNIInvokeInterface* JavaVM;
#endif
 
struct JNIInvokeInterface {
    void*       reserved0;
    void*       reserved1;
    void*       reserved2;
 
    jint        (*DestroyJavaVM)(JavaVM*);
    jint        (*AttachCurrentThread)(JavaVM*, JNIEnv**, void*);
    jint        (*DetachCurrentThread)(JavaVM*);
    jint        (*GetEnv)(JavaVM*, void**, jint);
    jint        (*AttachCurrentThreadAsDaemon)(JavaVM*, JNIEnv**, void*);
};
 
struct _JavaVM {
    const struct JNIInvokeInterface* functions;
 
#if defined(__cplusplus)
    jint DestroyJavaVM()
    { return functions->DestroyJavaVM(this); }
    jint AttachCurrentThread(JNIEnv** p_env, void* thr_args)
    { return functions->AttachCurrentThread(this, p_env, thr_args); }
    jint DetachCurrentThread()
    { return functions->DetachCurrentThread(this); }
    jint GetEnv(void** env, jint version)
    { return functions->GetEnv(this, env, version); }
    jint AttachCurrentThreadAsDaemon(JNIEnv** p_env, void* thr_args)
    { return functions->AttachCurrentThreadAsDaemon(this, p_env, thr_args); }
#endif /*__cplusplus*/
};
~~~

，JNIEnv定义了所有的JNI调用的接口：

~~~c
struct JNINativeInterface {
    void*       reserved0;
    void*       reserved1;
    void*       reserved2;
    void*       reserved3;
 
    jint        (*GetVersion)(JNIEnv *);
 
    jclass      (*DefineClass)(JNIEnv*, const char*, jobject, const jbyte*,
                        jsize);
    jclass      (*FindClass)(JNIEnv*, const char*);
 
    jmethodID   (*FromReflectedMethod)(JNIEnv*, jobject);
    jfieldID    (*FromReflectedField)(JNIEnv*, jobject);
    /* spec doesn't show jboolean parameter */
    jobject     (*ToReflectedMethod)(JNIEnv*, jclass, jmethodID, jboolean);
 
    jclass      (*GetSuperclass)(JNIEnv*, jclass);
    jboolean    (*IsAssignableFrom)(JNIEnv*, jclass, jclass);
 
    /* spec doesn't show jboolean parameter */
    jobject     (*ToReflectedField)(JNIEnv*, jclass, jfieldID, jboolean);
 
    jint        (*Throw)(JNIEnv*, jthrowable);
    jint        (*ThrowNew)(JNIEnv *, jclass, const char *);
    jthrowable  (*ExceptionOccurred)(JNIEnv*);
    void        (*ExceptionDescribe)(JNIEnv*);
    void        (*ExceptionClear)(JNIEnv*);
    void        (*FatalError)(JNIEnv*, const char*);
 
    jint        (*PushLocalFrame)(JNIEnv*, jint);
    jobject     (*PopLocalFrame)(JNIEnv*, jobject);
 
    jobject     (*NewGlobalRef)(JNIEnv*, jobject);
    void        (*DeleteGlobalRef)(JNIEnv*, jobject);
    void        (*DeleteLocalRef)(JNIEnv*, jobject);
    jboolean    (*IsSameObject)(JNIEnv*, jobject, jobject);
 
    jobject     (*NewLocalRef)(JNIEnv*, jobject);
    jint        (*EnsureLocalCapacity)(JNIEnv*, jint);
 
    jobject     (*AllocObject)(JNIEnv*, jclass);
    jobject     (*NewObject)(JNIEnv*, jclass, jmethodID, ...);
    jobject     (*NewObjectV)(JNIEnv*, jclass, jmethodID, va_list);
    jobject     (*NewObjectA)(JNIEnv*, jclass, jmethodID, jvalue*);
 
    jclass      (*GetObjectClass)(JNIEnv*, jobject);
    jboolean    (*IsInstanceOf)(JNIEnv*, jobject, jclass);
    jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
 
    jobject     (*CallObjectMethod)(JNIEnv*, jobject, jmethodID, ...);
    jobject     (*CallObjectMethodV)(JNIEnv*, jobject, jmethodID, va_list);
    jobject     (*CallObjectMethodA)(JNIEnv*, jobject, jmethodID, jvalue*);
    jboolean    (*CallBooleanMethod)(JNIEnv*, jobject, jmethodID, ...);
    jboolean    (*CallBooleanMethodV)(JNIEnv*, jobject, jmethodID, va_list);
    jboolean    (*CallBooleanMethodA)(JNIEnv*, jobject, jmethodID, jvalue*);
    jbyte       (*CallByteMethod)(JNIEnv*, jobject, jmethodID, ...);
    jbyte       (*CallByteMethodV)(JNIEnv*, jobject, jmethodID, va_list);
    jbyte       (*CallByteMethodA)(JNIEnv*, jobject, jmethodID, jvalue*);
    jchar       (*CallCharMethod)(JNIEnv*, jobject, jmethodID, ...);
    jchar       (*CallCharMethodV)(JNIEnv*, jobject, jmethodID, va_list);
    jchar       (*CallCharMethodA)(JNIEnv*, jobject, jmethodID, jvalue*);
    jshort      (*CallShortMethod)(JNIEnv*, jobject, jmethodID, ...);
    jshort      (*CallShortMethodV)(JNIEnv*, jobject, jmethodID, va_list);
    jshort      (*CallShortMethodA)(JNIEnv*, jobject, jmethodID, jvalue*);
    jint        (*CallIntMethod)(JNIEnv*, jobject, jmethodID, ...);
    jint        (*CallIntMethodV)(JNIEnv*, jobject, jmethodID, va_list);
    jint        (*CallIntMethodA)(JNIEnv*, jobject, jmethodID, jvalue*);
    jlong       (*CallLongMethod)(JNIEnv*, jobject, jmethodID, ...);
    jlong       (*CallLongMethodV)(JNIEnv*, jobject, jmethodID, va_list);
    jlong       (*CallLongMethodA)(JNIEnv*, jobject, jmethodID, jvalue*);
    jfloat      (*CallFloatMethod)(JNIEnv*, jobject, jmethodID, ...);
    jfloat      (*CallFloatMethodV)(JNIEnv*, jobject, jmethodID, va_list);
    jfloat      (*CallFloatMethodA)(JNIEnv*, jobject, jmethodID, jvalue*);
    jdouble     (*CallDoubleMethod)(JNIEnv*, jobject, jmethodID, ...);
    jdouble     (*CallDoubleMethodV)(JNIEnv*, jobject, jmethodID, va_list);
    jdouble     (*CallDoubleMethodA)(JNIEnv*, jobject, jmethodID, jvalue*);
    void        (*CallVoidMethod)(JNIEnv*, jobject, jmethodID, ...);
    void        (*CallVoidMethodV)(JNIEnv*, jobject, jmethodID, va_list);
    void        (*CallVoidMethodA)(JNIEnv*, jobject, jmethodID, jvalue*);
 
    jobject     (*CallNonvirtualObjectMethod)(JNIEnv*, jobject, jclass,
                        jmethodID, ...);
    jobject     (*CallNonvirtualObjectMethodV)(JNIEnv*, jobject, jclass,
                        jmethodID, va_list);
    jobject     (*CallNonvirtualObjectMethodA)(JNIEnv*, jobject, jclass,
                        jmethodID, jvalue*);
    jboolean    (*CallNonvirtualBooleanMethod)(JNIEnv*, jobject, jclass,
                        jmethodID, ...);
    jboolean    (*CallNonvirtualBooleanMethodV)(JNIEnv*, jobject, jclass,
                         jmethodID, va_list);
    jboolean    (*CallNonvirtualBooleanMethodA)(JNIEnv*, jobject, jclass,
                         jmethodID, jvalue*);
    jbyte       (*CallNonvirtualByteMethod)(JNIEnv*, jobject, jclass,
                        jmethodID, ...);
    jbyte       (*CallNonvirtualByteMethodV)(JNIEnv*, jobject, jclass,
                        jmethodID, va_list);
    jbyte       (*CallNonvirtualByteMethodA)(JNIEnv*, jobject, jclass,
                        jmethodID, jvalue*);
    jchar       (*CallNonvirtualCharMethod)(JNIEnv*, jobject, jclass,
                        jmethodID, ...);
    jchar       (*CallNonvirtualCharMethodV)(JNIEnv*, jobject, jclass,
                        jmethodID, va_list);
    jchar       (*CallNonvirtualCharMethodA)(JNIEnv*, jobject, jclass,
                        jmethodID, jvalue*);
    jshort      (*CallNonvirtualShortMethod)(JNIEnv*, jobject, jclass,
                        jmethodID, ...);
    jshort      (*CallNonvirtualShortMethodV)(JNIEnv*, jobject, jclass,
                        jmethodID, va_list);
    jshort      (*CallNonvirtualShortMethodA)(JNIEnv*, jobject, jclass,
                        jmethodID, jvalue*);
    jint        (*CallNonvirtualIntMethod)(JNIEnv*, jobject, jclass,
                        jmethodID, ...);
    jint        (*CallNonvirtualIntMethodV)(JNIEnv*, jobject, jclass,
                        jmethodID, va_list);
    jint        (*CallNonvirtualIntMethodA)(JNIEnv*, jobject, jclass,
                        jmethodID, jvalue*);
    jlong       (*CallNonvirtualLongMethod)(JNIEnv*, jobject, jclass,
                        jmethodID, ...);
    jlong       (*CallNonvirtualLongMethodV)(JNIEnv*, jobject, jclass,
                        jmethodID, va_list);
    jlong       (*CallNonvirtualLongMethodA)(JNIEnv*, jobject, jclass,
                        jmethodID, jvalue*);
    jfloat      (*CallNonvirtualFloatMethod)(JNIEnv*, jobject, jclass,
                        jmethodID, ...);
    jfloat      (*CallNonvirtualFloatMethodV)(JNIEnv*, jobject, jclass,
                        jmethodID, va_list);
    jfloat      (*CallNonvirtualFloatMethodA)(JNIEnv*, jobject, jclass,
                        jmethodID, jvalue*);
    jdouble     (*CallNonvirtualDoubleMethod)(JNIEnv*, jobject, jclass,
                        jmethodID, ...);
    jdouble     (*CallNonvirtualDoubleMethodV)(JNIEnv*, jobject, jclass,
                        jmethodID, va_list);
    jdouble     (*CallNonvirtualDoubleMethodA)(JNIEnv*, jobject, jclass,
                        jmethodID, jvalue*);
    void        (*CallNonvirtualVoidMethod)(JNIEnv*, jobject, jclass,
                        jmethodID, ...);
    void        (*CallNonvirtualVoidMethodV)(JNIEnv*, jobject, jclass,
                        jmethodID, va_list);
    void        (*CallNonvirtualVoidMethodA)(JNIEnv*, jobject, jclass,
                        jmethodID, jvalue*);
 
    jfieldID    (*GetFieldID)(JNIEnv*, jclass, const char*, const char*);
 
    jobject     (*GetObjectField)(JNIEnv*, jobject, jfieldID);
    jboolean    (*GetBooleanField)(JNIEnv*, jobject, jfieldID);
    jbyte       (*GetByteField)(JNIEnv*, jobject, jfieldID);
    jchar       (*GetCharField)(JNIEnv*, jobject, jfieldID);
    jshort      (*GetShortField)(JNIEnv*, jobject, jfieldID);
    jint        (*GetIntField)(JNIEnv*, jobject, jfieldID);
    jlong       (*GetLongField)(JNIEnv*, jobject, jfieldID);
    jfloat      (*GetFloatField)(JNIEnv*, jobject, jfieldID);
    jdouble     (*GetDoubleField)(JNIEnv*, jobject, jfieldID);
 
    void        (*SetObjectField)(JNIEnv*, jobject, jfieldID, jobject);
    void        (*SetBooleanField)(JNIEnv*, jobject, jfieldID, jboolean);
    void        (*SetByteField)(JNIEnv*, jobject, jfieldID, jbyte);
    void        (*SetCharField)(JNIEnv*, jobject, jfieldID, jchar);
    void        (*SetShortField)(JNIEnv*, jobject, jfieldID, jshort);
    void        (*SetIntField)(JNIEnv*, jobject, jfieldID, jint);
    void        (*SetLongField)(JNIEnv*, jobject, jfieldID, jlong);
    void        (*SetFloatField)(JNIEnv*, jobject, jfieldID, jfloat);
    void        (*SetDoubleField)(JNIEnv*, jobject, jfieldID, jdouble);
 
    jmethodID   (*GetStaticMethodID)(JNIEnv*, jclass, const char*, const char*);
 
    jobject     (*CallStaticObjectMethod)(JNIEnv*, jclass, jmethodID, ...);
    jobject     (*CallStaticObjectMethodV)(JNIEnv*, jclass, jmethodID, va_list);
    jobject     (*CallStaticObjectMethodA)(JNIEnv*, jclass, jmethodID, jvalue*);
    jboolean    (*CallStaticBooleanMethod)(JNIEnv*, jclass, jmethodID, ...);
    jboolean    (*CallStaticBooleanMethodV)(JNIEnv*, jclass, jmethodID,
                        va_list);
    jboolean    (*CallStaticBooleanMethodA)(JNIEnv*, jclass, jmethodID,
                        jvalue*);
    jbyte       (*CallStaticByteMethod)(JNIEnv*, jclass, jmethodID, ...);
    jbyte       (*CallStaticByteMethodV)(JNIEnv*, jclass, jmethodID, va_list);
    jbyte       (*CallStaticByteMethodA)(JNIEnv*, jclass, jmethodID, jvalue*);
    jchar       (*CallStaticCharMethod)(JNIEnv*, jclass, jmethodID, ...);
    jchar       (*CallStaticCharMethodV)(JNIEnv*, jclass, jmethodID, va_list);
    jchar       (*CallStaticCharMethodA)(JNIEnv*, jclass, jmethodID, jvalue*);
    jshort      (*CallStaticShortMethod)(JNIEnv*, jclass, jmethodID, ...);
    jshort      (*CallStaticShortMethodV)(JNIEnv*, jclass, jmethodID, va_list);
    jshort      (*CallStaticShortMethodA)(JNIEnv*, jclass, jmethodID, jvalue*);
    jint        (*CallStaticIntMethod)(JNIEnv*, jclass, jmethodID, ...);
    jint        (*CallStaticIntMethodV)(JNIEnv*, jclass, jmethodID, va_list);
    jint        (*CallStaticIntMethodA)(JNIEnv*, jclass, jmethodID, jvalue*);
    jlong       (*CallStaticLongMethod)(JNIEnv*, jclass, jmethodID, ...);
    jlong       (*CallStaticLongMethodV)(JNIEnv*, jclass, jmethodID, va_list);
    jlong       (*CallStaticLongMethodA)(JNIEnv*, jclass, jmethodID, jvalue*);
    jfloat      (*CallStaticFloatMethod)(JNIEnv*, jclass, jmethodID, ...);
    jfloat      (*CallStaticFloatMethodV)(JNIEnv*, jclass, jmethodID, va_list);
    jfloat      (*CallStaticFloatMethodA)(JNIEnv*, jclass, jmethodID, jvalue*);
    jdouble     (*CallStaticDoubleMethod)(JNIEnv*, jclass, jmethodID, ...);
    jdouble     (*CallStaticDoubleMethodV)(JNIEnv*, jclass, jmethodID, va_list);
    jdouble     (*CallStaticDoubleMethodA)(JNIEnv*, jclass, jmethodID, jvalue*);
    void        (*CallStaticVoidMethod)(JNIEnv*, jclass, jmethodID, ...);
    void        (*CallStaticVoidMethodV)(JNIEnv*, jclass, jmethodID, va_list);
    void        (*CallStaticVoidMethodA)(JNIEnv*, jclass, jmethodID, jvalue*);
 
    jfieldID    (*GetStaticFieldID)(JNIEnv*, jclass, const char*,
                        const char*);
 
    jobject     (*GetStaticObjectField)(JNIEnv*, jclass, jfieldID);
    jboolean    (*GetStaticBooleanField)(JNIEnv*, jclass, jfieldID);
    jbyte       (*GetStaticByteField)(JNIEnv*, jclass, jfieldID);
    jchar       (*GetStaticCharField)(JNIEnv*, jclass, jfieldID);
    jshort      (*GetStaticShortField)(JNIEnv*, jclass, jfieldID);
    jint        (*GetStaticIntField)(JNIEnv*, jclass, jfieldID);
    jlong       (*GetStaticLongField)(JNIEnv*, jclass, jfieldID);
    jfloat      (*GetStaticFloatField)(JNIEnv*, jclass, jfieldID);
    jdouble     (*GetStaticDoubleField)(JNIEnv*, jclass, jfieldID);
 
    void        (*SetStaticObjectField)(JNIEnv*, jclass, jfieldID, jobject);
    void        (*SetStaticBooleanField)(JNIEnv*, jclass, jfieldID, jboolean);
    void        (*SetStaticByteField)(JNIEnv*, jclass, jfieldID, jbyte);
    void        (*SetStaticCharField)(JNIEnv*, jclass, jfieldID, jchar);
    void        (*SetStaticShortField)(JNIEnv*, jclass, jfieldID, jshort);
    void        (*SetStaticIntField)(JNIEnv*, jclass, jfieldID, jint);
    void        (*SetStaticLongField)(JNIEnv*, jclass, jfieldID, jlong);
    void        (*SetStaticFloatField)(JNIEnv*, jclass, jfieldID, jfloat);
    void        (*SetStaticDoubleField)(JNIEnv*, jclass, jfieldID, jdouble);
 
    jstring     (*NewString)(JNIEnv*, const jchar*, jsize);
    jsize       (*GetStringLength)(JNIEnv*, jstring);
    const jchar* (*GetStringChars)(JNIEnv*, jstring, jboolean*);
    void        (*ReleaseStringChars)(JNIEnv*, jstring, const jchar*);
    jstring     (*NewStringUTF)(JNIEnv*, const char*);
    jsize       (*GetStringUTFLength)(JNIEnv*, jstring);
    /* JNI spec says this returns const jbyte*, but that's inconsistent */
    const char* (*GetStringUTFChars)(JNIEnv*, jstring, jboolean*);
    void        (*ReleaseStringUTFChars)(JNIEnv*, jstring, const char*);
    jsize       (*GetArrayLength)(JNIEnv*, jarray);
    jobjectArray (*NewObjectArray)(JNIEnv*, jsize, jclass, jobject);
    jobject     (*GetObjectArrayElement)(JNIEnv*, jobjectArray, jsize);
    void        (*SetObjectArrayElement)(JNIEnv*, jobjectArray, jsize, jobject);
 
    jbooleanArray (*NewBooleanArray)(JNIEnv*, jsize);
    jbyteArray    (*NewByteArray)(JNIEnv*, jsize);
    jcharArray    (*NewCharArray)(JNIEnv*, jsize);
    jshortArray   (*NewShortArray)(JNIEnv*, jsize);
    jintArray     (*NewIntArray)(JNIEnv*, jsize);
    jlongArray    (*NewLongArray)(JNIEnv*, jsize);
    jfloatArray   (*NewFloatArray)(JNIEnv*, jsize);
    jdoubleArray  (*NewDoubleArray)(JNIEnv*, jsize);
 
    jboolean*   (*GetBooleanArrayElements)(JNIEnv*, jbooleanArray, jboolean*);
    jbyte*      (*GetByteArrayElements)(JNIEnv*, jbyteArray, jboolean*);
    jchar*      (*GetCharArrayElements)(JNIEnv*, jcharArray, jboolean*);
    jshort*     (*GetShortArrayElements)(JNIEnv*, jshortArray, jboolean*);
    jint*       (*GetIntArrayElements)(JNIEnv*, jintArray, jboolean*);
    jlong*      (*GetLongArrayElements)(JNIEnv*, jlongArray, jboolean*);
    jfloat*     (*GetFloatArrayElements)(JNIEnv*, jfloatArray, jboolean*);
    jdouble*    (*GetDoubleArrayElements)(JNIEnv*, jdoubleArray, jboolean*);
 
    void        (*ReleaseBooleanArrayElements)(JNIEnv*, jbooleanArray,
                        jboolean*, jint);
    void        (*ReleaseByteArrayElements)(JNIEnv*, jbyteArray,
                        jbyte*, jint);
    void        (*ReleaseCharArrayElements)(JNIEnv*, jcharArray,
                        jchar*, jint);
    void        (*ReleaseShortArrayElements)(JNIEnv*, jshortArray,
                        jshort*, jint);
    void        (*ReleaseIntArrayElements)(JNIEnv*, jintArray,
                        jint*, jint);
    void        (*ReleaseLongArrayElements)(JNIEnv*, jlongArray,
                        jlong*, jint);
    void        (*ReleaseFloatArrayElements)(JNIEnv*, jfloatArray,
                        jfloat*, jint);
    void        (*ReleaseDoubleArrayElements)(JNIEnv*, jdoubleArray,
                        jdouble*, jint);
 
    void        (*GetBooleanArrayRegion)(JNIEnv*, jbooleanArray,
                        jsize, jsize, jboolean*);
    void        (*GetByteArrayRegion)(JNIEnv*, jbyteArray,
                        jsize, jsize, jbyte*);
    void        (*GetCharArrayRegion)(JNIEnv*, jcharArray,
                        jsize, jsize, jchar*);
    void        (*GetShortArrayRegion)(JNIEnv*, jshortArray,
                        jsize, jsize, jshort*);
    void        (*GetIntArrayRegion)(JNIEnv*, jintArray,
                        jsize, jsize, jint*);
    void        (*GetLongArrayRegion)(JNIEnv*, jlongArray,
                        jsize, jsize, jlong*);
    void        (*GetFloatArrayRegion)(JNIEnv*, jfloatArray,
                        jsize, jsize, jfloat*);
    void        (*GetDoubleArrayRegion)(JNIEnv*, jdoubleArray,
                        jsize, jsize, jdouble*);
 
    /* spec shows these without const; some jni.h do, some don't */
    void        (*SetBooleanArrayRegion)(JNIEnv*, jbooleanArray,
                        jsize, jsize, const jboolean*);
    void        (*SetByteArrayRegion)(JNIEnv*, jbyteArray,
                        jsize, jsize, const jbyte*);
    void        (*SetCharArrayRegion)(JNIEnv*, jcharArray,
                        jsize, jsize, const jchar*);
    void        (*SetShortArrayRegion)(JNIEnv*, jshortArray,
                        jsize, jsize, const jshort*);
    void        (*SetIntArrayRegion)(JNIEnv*, jintArray,
                        jsize, jsize, const jint*);
    void        (*SetLongArrayRegion)(JNIEnv*, jlongArray,
                        jsize, jsize, const jlong*);
    void        (*SetFloatArrayRegion)(JNIEnv*, jfloatArray,
                        jsize, jsize, const jfloat*);
    void        (*SetDoubleArrayRegion)(JNIEnv*, jdoubleArray,
                        jsize, jsize, const jdouble*);
 
    jint        (*RegisterNatives)(JNIEnv*, jclass, const JNINativeMethod*,
                        jint);
    jint        (*UnregisterNatives)(JNIEnv*, jclass);
    jint        (*MonitorEnter)(JNIEnv*, jobject);
    jint        (*MonitorExit)(JNIEnv*, jobject);
    jint        (*GetJavaVM)(JNIEnv*, JavaVM**);
 
    void        (*GetStringRegion)(JNIEnv*, jstring, jsize, jsize, jchar*);
    void        (*GetStringUTFRegion)(JNIEnv*, jstring, jsize, jsize, char*);
 
    void*       (*GetPrimitiveArrayCritical)(JNIEnv*, jarray, jboolean*);
    void        (*ReleasePrimitiveArrayCritical)(JNIEnv*, jarray, void*, jint);
 
    const jchar* (*GetStringCritical)(JNIEnv*, jstring, jboolean*);
    void        (*ReleaseStringCritical)(JNIEnv*, jstring, const jchar*);
 
    jweak       (*NewWeakGlobalRef)(JNIEnv*, jobject);
    void        (*DeleteWeakGlobalRef)(JNIEnv*, jweak);
 
    jboolean    (*ExceptionCheck)(JNIEnv*);
 
    jobject     (*NewDirectByteBuffer)(JNIEnv*, void*, jlong);
    void*       (*GetDirectBufferAddress)(JNIEnv*, jobject);
    jlong       (*GetDirectBufferCapacity)(JNIEnv*, jobject);
 
    /* added in JNI 1.6 */
    jobjectRefType (*GetObjectRefType)(JNIEnv*, jobject);
};
~~~

启动虚拟机的核心函数是startVM.

~~~c
extern "C" jint JNI_CreateJavaVM(JavaVM** p_vm, JNIEnv** p_env, void* vm_args) {
  ScopedTrace trace(__FUNCTION__);
  const JavaVMInitArgs* args = static_cast<JavaVMInitArgs*>(vm_args);
  if (JavaVMExt::IsBadJniVersion(args->version)) {
    LOG(ERROR) << "Bad JNI version passed to CreateJavaVM: " << args->version;
    return JNI_EVERSION;
  }
  //解析虚拟机选项
  RuntimeOptions options;
  for (int i = 0; i < args->nOptions; ++i) {
    JavaVMOption* option = &args->options[i];
    options.push_back(std::make_pair(std::string(option->optionString), option->extraInfo));
  }
  //创建一个Runtime运行时对象
  bool ignore_unrecognized = args->ignoreUnrecognized;
  if (!Runtime::Create(options, ignore_unrecognized)) {
    return JNI_ERR;
  }

  // Initialize native loader. This step makes sure we have
  // everything set up before we start using JNI.
  android::InitializeNativeLoader();

  
//通过Runtime启动其管理的虚拟机
  Runtime* runtime = Runtime::Current();
  bool started = runtime->Start();
  if (!started) {
    delete Thread::Current()->GetJniEnv();
    delete runtime->GetJavaVM();
    LOG(WARNING) << "CreateJavaVM failed";
    return JNI_ERR;
  }

  *p_env = Thread::Current()->GetJniEnv();
//获取已经启动完成的虚拟机实例，并通过p_vm这个指向指针的指针变量，把虚拟机实例传给AndroidRuntime中的变量JavaVM* AndroidRuntime::mJavaVM。
  *p_vm = runtime->GetJavaVM();
  return JNI_OK;
}

extern "C" jint JNI_GetCreatedJavaVMs(JavaVM** vms_buf, jsize buf_len, jsize* vm_count) {
  Runtime* runtime = Runtime::Current();
  if (runtime == nullptr || buf_len == 0) {
    *vm_count = 0;
  } else {
    *vm_count = 1;
    vms_buf[0] = runtime->GetJavaVM();
  }
  return JNI_OK;
}

// Historically unsupported.
extern "C" jint JNI_GetDefaultJavaVMInitArgs(void* /*vm_args*/) {
  return JNI_ERR;
}

}  // namespace art

~~~

bool Runtime::Init(RuntimeArgumentMap&& runtime_options_in) 函数解读：

- step1：new gc::heap()，创建Heap对象，这是虚拟机管理对内存的起点。

- step2：new JavaVmExt(),创建Java虚拟机实例。

- step3：Thread::attach()，attach主线程。
- step4：new ClassLinker()，成功attach到runtime环境后，创建ClassLinker实例负责管理java class。

###Java内存区域（运行时数据区域）和内存模型（JMM）

Java 内存区域和内存模型是不一样的东西，内存区域是指 Jvm 运行时将数据分区域存储，强调对内存空间的划分。而内存模型（Java Memory Model，简称 JMM ）是定义了线程和主内存之间的抽象关系，即 JMM 定义了 JVM 在计算机内存(RAM)中的工作方式，如果我们要想深入了解Java并发编程，就要先理解好Java内存模型。

####Java运行时数据区域

Java 虚拟机有自动内存管理机制，如果出现内存泄漏和溢出方面的问题，排查错误就必须要了解虚拟机是怎样使用内存的。

![image-20201130193550211](https://tva1.sinaimg.cn/large/0081Kckwly1gl7fj5owlnj31gi0nwqv5.jpg)**程序计数器**

程序计数器（Program Counter Register）是一块较小的内存空间，它可以看作是当前线程所执行的字节码的行号指示器。

由于 Java 虚拟机的多线程是通过线程轮流切换并分配处理器执行时间的方式来实现的，在任何一个确定的时刻，一个处理器内核都只会执行一条线程中的指令。因此，为了线程切换后能恢复到正确的执行位置，每条线程都需要有一个独立的程序计数器，各条线程之间计数器互不影响，独立存储，我们称这类内存区域为“线程私有”的内存。如果线程正在执行的是一个 Java 方法，这个计数器记录的是正在执行的虚拟机字节码指令的地址；如果正在执行的是 Native 方法，这个计数器值则为空（Undefined）。此内存区域是唯一一个在 Java 虚拟机规范中没有规定任何 OutOfMemoryError 情况的区域。

**Java虚拟机栈**

与程序计数器一样，Java 虚拟机栈（Java Virtual Machine Stacks）也是线程私有的，它的生命周期与线程相同。

虚拟机栈描述的是 Java 方法执行的内存模型：每个方法在执行的同时都会创建一个栈帧（Stack Frame，是方法运行时的基础数据结构）用于存储局部变量表、操作数栈、动态链接、方法出口等信息。每一个方法从调用直至执行完成的过程，就对应着一个栈帧在虚拟机栈中入栈到出栈的过程。

在活动线程中，只有位千栈顶的帧才是有效的，称为当前栈帧。正在执行的方法称为当前方法，栈帧是方法运行的基本结构。在执行引擎运行时，所有指令都只能针对当前栈帧进行操作。

<img src="/Users/jesson/Library/Application Support/typora-user-images/image-20201130193746933.png" alt="image-20201130193746933" style="zoom:50%;" />

**本地方法栈**

线程开始调用本地方法时，会进入 个不再受 JVM 约束的世界。本地方法可以通过 JNI(Java Native Interface)来访问虚拟机运行时的数据区，甚至可以调用寄存器，具有和 JVM 相同的能力和权限。 当大量本地方法出现时，势必会削弱 JVM 对系统的控制力，因为它的出错信息都比较黑盒。对内存不足的情况，本地方法栈还是会抛出 nativeheapOutOfMemory。

**Java堆**

Java 堆（Java Heap）是 Java 虚拟机所管理的内存中最大的一块。Java 堆是被所有线程共享的一块内存区域，在虚拟机启动时创建。此内存区域的唯一目的就是存放对象实例，几乎所有的对象实例都在这里分配内存。

Java 堆可以处于物理上不连续的内存空间中，只要逻辑上是连续的即可，当前主流的虚拟机都是按照可扩展来实现的（通过 -Xmx 和 -Xms 控制）。如果在堆中没有内存完成实例分配，并且堆也无法再扩展时，将会抛出 OutOfMemoryError 异常。

从内存回收的角度来看，由于现在收集器基本都采用分代收集算法，所以 Java 堆中还可以细分为：新生代和老年代；再细致一点的有 Eden 空间、From Survivor 空间、To Survivor 空间等。从内存分配的角度来看，线程共享的 Java 堆中可能划分出多个线程私有的分配缓冲区（Thread Local Allocation Buffer,TLAB）。

![image-20201130195622471](https://tva1.sinaimg.cn/large/0081Kckwly1gl7g4fowdgj31jc0qi4n7.jpg)

####Java内存模型

注意：

- **JMM是个抽象的内存模型，所以所谓的本地内存，主内存都是抽象概念，并不一定就真实的对应cpu缓存和物理内存**。

**模型**

![image-20201130201528830](https://tva1.sinaimg.cn/large/0081Kckwly1gl7goayj47j310c0u0dlr.jpg)

**通信**

在Java中JMM内存模型定义了八种操作来实现同步的细节。

- **read** 读取，作用于主内存把变量从主内存中读取到本本地内存。
- **load** 加载，主要作用本地内存，把从主内存中读取的变量加载到本地内存的变量副本中
- **use** 使用，主要作用本地内存，把工作内存中的一个变量值传递给执行引擎，每当虚拟机遇到一个需要使用变量的值的字节码指令时将会执行这个操作。、
- **assign** 赋值 作用于工作内存的变量，它把一个从执行引擎接收到的值赋值给工作内存的变量，每当虚拟机遇到一个给变量赋值的字节码指令时执行这个操作。
- **store** 存储 作用于工作内存的变量，把工作内存中的一个变量的值传送到主内存中，以便随后的write的操作。
- **write** 写入 作用于主内存的变量，它把store操作从工作内存中一个变量的值传送到主内存的变量中。
- **lock** 锁定 ：作用于主内存的变量，把一个变量标识为一条线程独占状态。
- **unlock** 解锁：作用于主内存变量，把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线程锁定。

规则：

- 不允许read和load、store和write的操作单独出现。
- 不允许一个线程丢弃它的最近assign的操作，即变量在工作内存中改变了之后必须同步到主内存中。
- 不允许一个线程无原因地（没有发生过任何assign操作）把数据从工作内存同步回主内存中。
- 一个新的变量只能在主内存中诞生，不允许在工作内存中直接使用一个未被初始化（load或assign）的变量。即就是对一个变量实施use和store操作之前，必须先执行过了assign和load操作。
- 一个变量在同一时刻只允许一条线程对其进行lock操作，lock和unlock必须成对出现
- 如果对一个变量执行lock操作，将会清空工作内存中此变量的值，在执行引擎使用这个变量前需要重新执行load或assign操作初始化变量的值
- 如果一个变量事先没有被lock操作锁定，则不允许对它执行unlock操作；也不允许去unlock一个被其他线程锁定的变量。
- 对一个变量执行unlock操作之前，必须先把此变量同步到主内存中（执行store和write操作）。

其实想一想，也真的很好理解。A和B线程如果要通信？怎么操作

- A线程先把本地内存的值写入主内存
- B线程从主内存中去读取出A线程写的值

结合上面的通信大家就能想明白的。

####Memory Barrier

**内存屏障（memory barrier）是一个CPU指令。**主要的作用如下：

- 确保一些特定操作执行的顺序；

- 影响一些数据的可见性(可能是某些指令执行后的结果)；

编译器和CPU可以在保证输出结果一样的情况下对指令重排序，使性能得到优化。插入一个内存屏障，相当于告诉CPU和编译器先于这个命令的必须先执行，后于这个命令的必须后执行。内存屏障另一个作用是强制更新一次不同CPU的缓存。尤其是在多核的环境下，为了充分利用CPU，会通过流水线将指令并行进行。为了能并行执行，又需要将指令进行重排序以便进行并行执行。

####Handler同步屏障(SyncBarrier)

通常我们使用Handler发消息的时候，都是用的默认的构造方法生成Handler，然后用send方法来发送消息，其实这时候我们发送的都是**同步消息**，发出去之后就会在**消息队列**里面排队处理。如果主线程的消息过多，在16ms之内没有执行完，必然会造成卡顿或者掉帧。那怎么才能不排队，没有延时的处理呢？异步

~~~java
public int postSyncBarrier() {
        return postSyncBarrier(SystemClock.uptimeMillis());
    }

    private int postSyncBarrier(long when) {
        // Enqueue a new sync barrier token.
        // We don't need to wake the queue because the purpose of a barrier is to stall it.
        synchronized (this) {
            final int token = mNextBarrierToken++;
            final Message msg = Message.obtain();
            msg.markInUse();
            msg.when = when;
            msg.arg1 = token;

            Message prev = null;
            Message p = mMessages;
            if (when != 0) {
                while (p != null && p.when <= when) {
                    prev = p;
                    p = p.next;
                }
            }
            if (prev != null) { // invariant: p == prev.next
                msg.next = p;
                prev.next = msg;
            } else {
                msg.next = p;
                mMessages = msg;
            }
            return token;
        }
    }
~~~

处理异步消息：

~~~java
 Message next() {
        省略代码
        for (;;) {
            if (nextPollTimeoutMillis != 0) {
                Binder.flushPendingCommands();
            }

            nativePollOnce(ptr, nextPollTimeoutMillis);

            synchronized (this) {
                // Try to retrieve the next message.  Return if found.
                final long now = SystemClock.uptimeMillis();
                Message prevMsg = null;
                Message msg = mMessages;
                if (msg != null && msg.target == null) {//判断是否为屏障消息
                    // Stalled by a barrier.  Find the next asynchronous message in the queue.
                    do {
                        prevMsg = msg;
                        msg = msg.next;
                    } while (msg != null && !msg.isAsynchronous());
                }
            省略代码
        }
    }
~~~

###Android VM

目前 Android 虚拟机有两种类型：Dalvik 和 ART，他们都是用来运行 Android App 的。在移动端上，所有的资源都是受限制的例如，电池电量、 CPU 运算能力、内存资源。所以必须优化程序让他能在低功率的设备上运行。

![image-20201203193138978](https://tva1.sinaimg.cn/large/0081Kckwly1glaw9p3726j317k0betet.jpg)

JVM 的指令是基于栈的，而 Android VM 的指令是基于寄存器的。最直观的效果就是 Android VM 执行程序的效率更高，这是因为寄存器离 CPU 更近、数据传输更快。

| Android VM                                                   | JVM                                         |
| ------------------------------------------------------------ | ------------------------------------------- |
| 基于寄存器模式，运行时速度更快更节省内存                     | 基于栈模式                                  |
| 使用自己的特有的字节码且输入源为 .dex 文件从 Android 2.2 开始 Dalvik 虚拟机引入了 JIT 及时编译功能 | 执行字节码输入源为 .class 文件拥有 JIT 功能 |
| 每一个 Application 都有一个单独的 VM                         | 一个 JVM 实例可以共享个多个 Application     |
| 是 Android 平台特有的，不支持其他平台操作系统                | JVM 支持多平台操作系统                      |
| 每个 Application 都有常量池                                  | 每个 .class 都有常量池                      |
| 可执行文件是 apk                                             | 可执行文件是 jar                            |

###JVM / Dalvik VM垃圾回收机制

![image-20201203193533709](https://tva1.sinaimg.cn/large/0081Kckwly1glawdpq08nj310o0qoq9w.jpg)

问题1:可回收对象的判定？

引用计数算法 VS 可达性分析算法

引用计数算法：给对象添加一个引用计数器，每当有一个地方引用它的时候，计数器的值就加1；当引用失效的时候，计数器的值就减1；任何时刻计数器为0的对象是不可能再被引用的。（**相互引用的问题无法解决**）

可达性分析算法：**通过一系列称为“GC Roots”的对象作为起始点，从这些结点开始向下搜索，搜索所走过的路径成为“引用链”，当一个对象到GC Roots没有一个对象相连时，则证明此对象是不可用的(不可达)。**

![image-20201203193815220](https://tva1.sinaimg.cn/large/0081Kckwly1glawgi1mh4j30qe0i4gqg.jpg)

在Java语言中，可作为GC Roots的对象包括下面几种：

- **上面说的JVM栈(栈帧数据中的本地变量表)中引用的对象。**
- **方法区中类静态属性引用的对象。**
- **方法区中常量引用的对象。**
- **Native 方法栈中JNI引用的对象。**

面试考点：**两次标记的过程**。**finalize**（）函数的作用？？

问题2:什么是引用？

**如果reference类型的数据中储存的数值代表的是另外一块内存的起始地址，就称这个数据代表着一个引用。**在JDK1.2之后，Java对引用的概念进行了扩充，将引用分为**强引用、软引用、弱引用、虚引用**。

**强引用：**就是指在程序代码之中普遍存在的，类似于“Object obj = new Object();”这样的引用，只要强引用还存在，垃圾回收器永远不会回收掉被引用的对象。

**软引用：**用来描述一些**还有用但并非必须的对象**。对于软引用关联的对象，在系统**将要发生内存溢出异常之前，将会把这些对象列进回收的范围**，进行**第二次回收**——如果这次回收还没有腾出足够的内存，才会内存溢出抛出异常。在JDK1.2之后，提供了SoftReference来实现软引用。

**弱引用：**也是用来描述非必须对象的，但是他的强度比软引用更弱一些。被弱引用引用的对象，只能生存到下一次GC之前，**当GC发生时，无论当前内存是否足够，都会回收掉被弱引用关联的对象**。JDK1.2之后，提供了WeakRefernce类来实现弱引用。

**虚引用：**是最弱的一种引用，一个对象有虚引用的存在，完全不会对其生存时间构成影响，也无法通过虚引用来获得一个对象的实例。**为一个对象设置一个虚引用关联的唯一目的就是能够在这个对象呗收集器回收的的时候收到一个系统的通知**。

###关于GC

~~~c
D/dalvikvm(30615): GC FOR ALLOC freed 4442K, 25% free 20183K/26856K, paused 24ms , total 24ms

I/art(198): Explicit concurrent mark sweep GC freed 700(30KB) AllocSpaceobjects, 0(0B) LOS objects, 792% free, 18MB/21MB, paused 186us total 12.763ms

D/dalvikvm: <GC_Reason> <Amount_freed>, <Heap_stats>,  <Pause_time>  
~~~

一般情况下一共有以下几种触发GC操作的原因：

- GC_CONCURRENT: 当我们应用程序的堆内存快要满的时候，系统会自动触发GC操作来释放内存。
- GC_FOR_MALLOC: 当我们的应用程序需要分配更多内存，可是现有内存已经不足的时候，系统会进行GC操作来释放内存。
- GC_HPROF_DUMP_HEAP: 当生成HPROF文件的时候，系统会进行GC操作。
- GC_EXPLICIT: 主动通知系统去进行GC操作，比如调用System.gc()方法来通知系统。或者在DDMS中，通过工具按钮也是可以显式地告诉系统进行GC操作的。

Amount_freed:表示系统通过这次GC操作释放了多少内存。
Heap_stats:会显示当前内存的空闲比例以及使用情况（活动对象所占内存 / 当前程序总内存）。Pause_time:表示这次GC操作导致应用程序暂停的时间。

**导致GC频繁执行有两个原因**:

- **Memory Churn（内存抖动）**，内存抖动是因为大量的对象被创建又在短时间内马上被释放。
- 瞬间产生大量的对象会严重占用Young Generation的内存区域，当达到阀值，剩余空间不够的时候，也会触发GC。即使每次分配的对象占用了很少的内存，但是他们叠加在一起会增加 Heap的压力，从而触发更多其他类型的GC。这个操作有可能会影响到帧率，并使得用户感知到性能问题。

###快手OOM

https://www.infoq.cn/video/EDV9u2HZlwmycLiFJm5s

#####HAHA & Shark：

**HAHA 为啥慢**：

- 没有懒加载， hprof 内容会全部 load 到内存中。
- Domainitor tree 和 retained size 全量计算，而我们只关心关键对象
- 频发触发 GC : 这是因为 Java 的集合类没有针对计算密集型任务做优化，含有大量用于的拆箱、装箱、扩容拷贝等操作，大量的对象创建频发的 GC ，GC 反过来进一步降低对象分配速度，陷入恶性循环。

**Shark 为啥快：**

Shark 的核心思想就是懒加载和数据结构优化：

1. 索引。Sark低内存开销的本质就是通过索引做到了懒加载，遍历 hprof 时存储对象在 hprof 中的文职，并建立做索引方便按需解析
2. 数据结构上做了深度优化，主要使用了更高效的 map，一是对于 key 和 value 都是基本类型的 hppc，二是对于 value 不是基本类型的，使用 SotredBytesMap 存储内容。
3. Hppc 是 High Performance Primitive Collection 的缩写，Shark 使用了 Kotlin 将其进行了了重写，hppc 只支持基本类型，我们知道 kotlin 只有基本类型没有装箱类型，所以没有了拆箱和装箱的性能损耗，相关集合操作也做了大量优化。

#####可达性分析

- 泄漏对象最典型的 Activity 和 Fragment，有着明显的生命周期特征且持有大量资源，除此外我们还对自定义核心组件做了泄漏判定，比如 Presenter。

- 常见的大对象有 Bitmap、Array、TextTure 等，Bitamp/TextTure 的数量、宽高，Array 长度等等可以结合业务数据比如屏幕大小、View Size 等进行判定。

- 有些对象既是泄漏也是大对象，关键对象的选取策略需要各 App 结合实际情况来灵活定制。

#####阈值监控

LeakCanary 监控泄漏利用了弱引用的特征，为 Activity 对象创建弱引用，当 Activity 变为弱可达（没有强引用）的时候，弱引用将会加入弱引用队列中，但是 GC 的触发时机不可知，所以不能仅仅依靠弱引用队列是否不为空就确定存在内存泄漏。

频繁的 GC 会造成用户感知的明显卡顿，快手采用了一定的策略来分析。

- Java 堆、线程数量、文件描述符数量突破阈值触发采集

- Java 堆上涨速度突破阈值触发采集。

- 发生 OOM 是如果策略 1、2 未命中触发采集。

LeakCanary 的弱引用策略仅能解决内存泄漏问题，通过阈值监控的方式可以同时解决内存泄漏和峰值的问题，也不需要手动埋点，甚至还能解决频繁对象的引发的内存抖动问题。

##### 快手创新点

异步dump 优化：

~~~c
// If "direct_to_ddms" is true, the other arguments are ignored, and data is
// sent directly to DDMS.
// If "fd" is >= 0, the output will be written to that file descriptor.
// Otherwise, "filename" is used to create an output file.
void DumpHeap(const char* filename, int fd, bool direct_to_ddms) {
  CHECK(filename != nullptr);
  Thread* self = Thread::Current();
  // Need to take a heap dump while GC isn't running. See the comment in Heap::VisitObjects().
  // Also we need the critical section to avoid visiting the same object twice. See b/34967844
  gc::ScopedGCCriticalSection gcs(self,
                                  gc::kGcCauseHprof,
                                  gc::kCollectorTypeHprof);
  ScopedSuspendAll ssa(__FUNCTION__, true /* long suspend */);
  Hprof hprof(filename, fd, direct_to_ddms);
  hprof.Dump();
}

}  // namespace hprof
}  // namespace art

~~~

- dump 操作会通过 Native 层的 hprof.Dump() 将内存数据按照 Hprof 协议的格式按照二进制的形式保存到磁盘中
- 为了保证 dump 过程中内存数据的不变性在执行 hprof.Dump() 之前会通过 ScopedSuspendAll （构造函数内调用了 SupendAll）暂停了所有 Java 线程，在 dump 结束后通过 ScopedSusendAll 析构函数中（通过 ResumeAll ）恢复线程

Android 是在 Linux 内核基础上构建的操作系统，所以 Android 创建进程的流程大体如下：

- 子进程都由父进程 fork 而来（Android 为 App 创建进程时 fork 的是 zygote 进程），且符合 COW 流程（copy-on-write 写时复制）

- COW指的子进程在创建时拷贝的是父进程的虚拟内存而不是物理内存。子进程拥有了父进程的虚拟内存就可以通过页表共享父进程的物理内存（这是关键）

- 当子进程或者父进程对同享的物理内存修改时，内核会拦截此过程，将共享的内存以页为单位进行拷贝，父进程将保留原始物理空间，而子进程将会使用拷贝后的新物理内存。

这意味着我们可以在 dump 之前先 fork App 进程，这样子进程就获得了父进程所有的内存镜像。

![image-20201203202318487](https://tva1.sinaimg.cn/large/0081Kckwly1glaxrdbjduj317x0u0wuw.jpg)

![image-20201203202355951](https://tva1.sinaimg.cn/large/0081Kckwly1glaxs24vwcj315u0goaqm.jpg)

关于Hprof裁剪的问题：

![image-20201203204624765](https://tva1.sinaimg.cn/large/0081Kckwly1glayff9biuj316h0u0wp5.jpg)

关于Hook点的分析：

看源码。