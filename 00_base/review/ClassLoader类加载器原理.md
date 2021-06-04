### ClassLoader类加载器原理

#### 类加载的机制的层次结构

每个编写的".java"拓展名类文件都存储着需要执行的程序逻辑，这些".java"文件经过Java编译器编译成拓展名为".class"的文件，".class"文件中保存着Java代码经转换后的虚拟机指令，当需要使用某个类时，虚拟机将会加载它的".class"文件，并创建对应的class对象，将class文件加载到虚拟机的内存，这个过程称为类加载，这里我们需要了解一下类加载的过程，如下：

![image-20210530182136442](https://i.loli.net/2021/05/30/T2mjARrMX68YVwp.png)

**参考《深入Java虚拟机》**

- 加载：类加载过程的一个阶段：通过一个类的完全限定查找此类字节码文件，并利用字节码文件创建一个Class对象。

- 验证：目的在于确保Class文件的字节流中包含信息符合当前虚拟机要求，不会危害虚拟机自身安全。主要包括四种验证，文件格式验证，元数据验证，字节码验证，符号引用验证。

- 准备：为类变量(即static修饰的字段变量)分配内存并且设置该类变量的初始值即0(如static int i=5;这里只将i初始化为0，至于5的值将在初始化时赋值)，这里不包含用final修饰的static，因为final在编译的时候就会分配了，注意这里不会为实例变量分配初始化，类变量会分配在方法区中，而实例变量是会随着对象一起分配到Java堆中。

- 解析：主要将常量池中的符号引用替换为直接引用的过程。符号引用就是一组符号来描述目标，可以是任何字面量，而直接引用就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄。有类或接口的解析，字段解析，类方法解析，接口方法解析(这里涉及到字节码变量的引用）。

- 初始化：类加载最后阶段，若该类具有超类，则对其进行初始化，执行静态初始化器和静态初始化成员变量(如前面只初始化了默认值的static变量将会在这个阶段赋值，成员变量也将被初始化。
  

类加载器的任务是根据一个类的全限定名来读取此类的二进制字节流到JVM中，然后转换为一个与目标类对应的java.lang.Class对象实例，在虚拟机提供了3种类加载器，引导（Bootstrap）类加载器、扩展（Extension）类加载器、系统（System）类加载器（也称应用类加载器）。
**启动（Bootstrap）类加载器**

启动类加载器主要加载的是JVM自身需要的类，这个类加载使用C++语言实现的，是虚拟机自身的一部分，它负责将`<JAVA_HOME>/lib`路径下的核心类库或-Xbootclasspath参数指定的路径下的jar包加载到内存中，注意必由于虚拟机是按照文件名识别加载jar包的，如rt.jar，如果文件名不被虚拟机识别，即使把jar包丢到lib目录下也是没有作用的(出于安全考虑，Bootstrap启动类加载器只加载包名为java、javax、sun等开头的类)。

**扩展（Extension）类加载器**

负责加载`<JAVA_HOME>/lib/ext`目录下或者由系统变量-Djava.ext.dir指定位路径中的类库，开发者可以直接使用标准扩展类加载器。

**系统（System）类加载器**

应用程序加载器是指 Sun公司实现的`sun.misc.Launcher$AppClassLoader`。它负责加载系统类路径`java -classpath`或`-D java.class.path `指定路径下的类库，也就是我们经常用到的classpath路径，开发者可以直接使用系统类加载器，一般情况下该类加载是程序中默认的类加载器，通过`ClassLoader#getSystemClassLoader()`方法可以获取到该类加载器。

#### 双亲委派模式

双亲委派模式要求除了顶层的启动类加载器外，其余的类加载器都应当有自己的父类加载器，请注意双亲委派模式中的父子关系并非通常所说的类继承关系，而是采用组合关系来复用父类加载器的相关代码，类加载器间的关系如下：

![image-20210530182916765](https://i.loli.net/2021/05/30/4QRyjt5e13CFdvs.png)

一个类加载器收到了类加载请求，它并不会自己先去加载，而是把这个请求委托给父类的加载器去执行，如果父类加载器还存在其父类加载器，则进一步向上委托，依次递归，请求最终将到达顶层的启动类加载器，如果父类加载器可以完成类加载任务，就成功返回，倘若父类加载器无法完成此加载任务，子加载器才会尝试自己去加载。
![image-20210530183107054](https://i.loli.net/2021/05/30/3RPhTu6fdDXxJHt.png)

#### ClassLoader核心API

loadClass(String)

~~~java
/***
双亲委派模式的实现
***/
protected Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException
    {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                try {
                    if (parent != null) {
                        c = parent.loadClass(name, false);
                    } else {
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    c = findClass(name);
                }
            }
            return c;
    }
~~~

findClass(String)

findClass()方法是在loadClass()方法中被调用的，当loadClass()方法中父加载器加载失败后，则会调用自己的findClass()方法来完成类加载，这样就可以保证自定义的类加载器也符合双亲委托模式。需要注意的是ClassLoader类中并没有实现findClass()方法的具体代码逻辑，取而代之的是抛出ClassNotFoundException异常，同时应该知道的是findClass方法通常是和defineClass方法一起使用，ClassLoader类中findClass()方法源码如下：

~~~java
 protected Class<?> findClass(String name) throws ClassNotFoundException {
        throw new ClassNotFoundException(name);
    }
~~~

defineClass(byte[] b, int off, int len)

defineClass()方法是用来将byte字节流解析成JVM能够识别的Class对象(ClassLoader中已实现该方法逻辑)，通过这个方法不仅能够通过class文件实例化class对象，也可以通过其他方式实例化class对象，如通过网络接收一个类的字节码，然后转换为byte字节流创建对应的Class对象。

#### 类加载器间的关系

- 启动类加载器，由C++实现，没有父类。

- 拓展类加载器(ExtClassLoader)，由Java语言实现，父类加载器为null

- 系统类加载器(AppClassLoader)，由Java语言实现，父类加载器为ExtClassLoader

- 自定义类加载器，父类加载器肯定为AppClassLoader。
  

~~~JAVA
import java.io.FileInputStream;
import java.lang.reflect.Method;

class FileClassLoader extends  ClassLoader{

    private final String classPath;
    public FileClassLoader(String classPath) {
        this.classPath = classPath;
    }
    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.", "/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name
                + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }


    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }


    public static void main(String[] args) throws Exception{
        FileClassLoader classLoader = new FileClassLoader("C:\\Users\\Admin\\Desktop\\JVM\\src");
        //Test.class目录在D:/test/com/xdwang/demo下
        Class clazz = classLoader.loadClass("com.jesson.demo.JavaBean");
        Object obj = clazz.newInstance();
        Method helloMethod = clazz.getDeclaredMethod("hello", null);
        helloMethod.invoke(obj, null);
    }
}

~~~

#### Android中的Classloader

本质上，Android 和传统的 JVM 是一样的，也需要通过 ClassLoader 将目标类加载到内存，类加载器之间也符合双亲委派模型。但是在 Android 中， ClassLoader 的加载细节有略微的差别。

在 Android 虚拟机里是无法直接运行 .class 文件的，Android 会将所有的 .class 文件转换成一个 .dex 文件，并且 Android 将加载 .dex 文件的实现封装在 BaseDexClassLoader 中，而我们一般只使用它的两个子类：PathClassLoader 和 DexClassLoader。


**DexClassLoader**

A class loader that loads classes from .jar and .apk filescontaining a classes.dex entry. 

This can be used to execute code notinstalled as part of an application.

DexClassLoader 可以从 SD 卡上加载包含 class.dex 的 .jar 和 .apk 文件，这也是插件化和热修复的基础，在不需要安装应用的情况下，完成需要使用的 dex 的加载。

**一个App正常运行最少需要哪些ClassLoade？**

最少需要BootClassLoader和PathClassLoader。首先BootClassLoader是无可或缺的，因为它需要加载framework层的一些class文件，而PathClassLoader用来加载已安装到系统上的文件。

**热修复原理**

~~~
类加载方案的原理是在app重新启动后让Classloader加载新的类。因为当app运行到一半时，所需发生变更的类已经被加载过，而在Android上无法对一个类进行卸载操作，若不重启，原来的类还存储于虚拟机中，新类无法被加载。因此只有在下次重启时，在业务逻辑运行之前抢先加载补丁中的新类，这样后续访问此类时，才会Resolve为新类，从而达到热修复的目的。
~~~



#### 源码解读

