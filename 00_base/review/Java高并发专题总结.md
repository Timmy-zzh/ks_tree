#### JVM内存结构

Java代码是要运行在虚拟机上的，而虚拟机在执行Java程序的过程中会把所管理的内存划分为若干个不同的数据区域，这些区域都有各自的用途。

https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html#jvms-2.5.4

![image-20210319193720164](https://i.loli.net/2021/03/19/2L1PZQDkfA6cuvR.png)

注意：

- 方法区，只是一种概念上的区域，并说明了其应该具有什么功能。但是并没有规定这个区域到底应该处于何处。所以，对于不同的虚拟机实现来说，是由一定的自由度的。

- 运行时常量池用于存放编译期生成的各种字面量和符号应用。但是，Java语言并不要求常量只有在编译期才能产生。比如在运行期，String.intern也会把新的常量放入池中。
- 除了以上介绍的JVM运行时内存外，还有一块内存区域可供使用，那就是**直接内存**。Java虚拟机规范并没有定义这块内存区域，所以他并不由JVM管理，是利用本地方法库直接在堆外申请的内存区域。

总结：JVM内存结构，由Java虚拟机规范定义。描述的是Java程序执行过程中，由JVM管理的不同数据区域。各个区域有其特定的功能。

#### Java内存模型

JMM并不像JVM内存结构一样是真实存在的。他只是一个抽象的概念。

http://www.cs.umd.edu/~pugh/java/memoryModel/jsr133.pdf

JMM是和多线程相关的，他描述了一组规则或规范，这个规范定义了一个线程对共享变量的写入时对另一个线程是可见的。Java的多线程之间是通过共享内存进行通信的，而由于采用共享内存进行通信，在通信过程中会存在一系列如可见性、原子性、顺序性等问题，而JMM就是围绕着多线程通信以及与其相关的一系列特性而建立的模型。

![image-20210319195509635](https://i.loli.net/2021/03/19/HaATnFNOmsKp6lW.png)

#### Java对象模型

Java是一种面向对象的语言，而Java对象在JVM中的存储也是有一定的结构的。而这个关于Java对象自身的存储模型称之为Java对象模型。HotSpot虚拟机中，设计了一个OOP-Klass Model。OOP（Ordinary Object Pointer）指的是普通对象指针，而Klass用来描述对象实例的具体类型。

#### 计算机内存模型问题

计算机在执行程序的时候，每条指令都是在CPU中执行的，而执行的时候，又免不了要和数据打交道。而计算机上面的数据，是存放在主存当中的，也就是计算机的物理内存。

CPU的执行速度越来越快。而由于内存的技术并没有太大的变化，所以从内存中读取和写入数据的过程和CPU的执行速度比起来差距就会越来越大,这就导致CPU每次操作内存都要耗费很多等待时间。

**解决方案**：

**当程序在运行过程中，会将运算需要的数据从主存复制一份到CPU的高速缓存当中，那么CPU进行计算时就可以直接从它的高速缓存读取数据和向其中写入数据，当运算结束之后，再将高速缓存中的数据刷新到主存当中。**

**处理器优化和指令重排**

在CPU和主存之间增加缓存，在多线程场景下会存在**缓存一致性问题**。Java虚拟机的即时编译器（JIT）也会做**指令重排**。现在很多流行的处理器会对代码进行优化乱序处理，很多编程语言的编译器也会有类似的优化。

总结一下： 三性问题。

- **原子性**是指在一个操作中就是cpu不可以在中途暂停然后再调度，既不被中断操作，要不执行完成，要不就不执行。（**处理器优化**）

- **可见性**是指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。（**缓存一致性问题**）

- **有序性**即程序执行的顺序按照代码的先后顺序执行。（**指令重排**）

#### 线程安全

**atomic**：

原子性，提供了互斥访问，同一时刻只能有一个线程来对它进行操作。那么在java里，保证同一时刻只有一个线程对它进行操作。

代码详见

#### 锁

##### synchronized

依赖于JVM去实现锁，因此在这个关键字作用对象的作用范围内，都是同一时刻只能有一个线程对其进行操作的。synchronized是java中的一个关键字，是一种同步锁。它可以修饰的对象主要有四种：

- 修饰代码块：大括号括起来的代码，作用于调用的对象。
- 修饰方法：整个方法，作用于调用的对象。
- 修饰静态方法：整个静态方法，作用于所有对象。
- 修饰类：括号括起来的部分，作用于所有对象。

代码理解：注意类锁和对象锁的基本区别。

##### lock

Lock锁，可以得到和 synchronized一样的效果，即实现原子性、有序性和可见性。相较于synchronized，Lock锁可手动获取锁和释放锁、可中断的获取锁、超时获取锁。Lock 是一个接口，两个直接实现类：ReentrantLock（重入锁）, ReentrantReadWriteLock（读写锁）。

##### ReentrantLock vs synchronized

（1）synchronized是独占锁，加锁和解锁的过程自动进行，易于操作，但不够灵活。ReentrantLock也是独占锁，加锁和解锁的过程需要手动进行，不易操作，但非常灵活。

（2）synchronized可重入，因为加锁和解锁自动进行，不必担心最后是否释放锁；ReentrantLock也可重入，但加锁和解锁需要手动进行，且次数需一样，否则其他线程无法获得锁。

（3）synchronized不可响应中断，一个线程获取不到锁就一直等着；ReentrantLock可以相应中断。

~~~java
public class ReentrantLock implements Lock, java.io.Serializable {
    private final Sync sync;
    abstract static class Sync extends AbstractQueuedSynchronizer {

        /**
         * Performs {@link Lock#lock}. The main reason for subclassing
         * is to allow fast path for nonfair version.
         */
        abstract void lock();

        /**
         * Performs non-fair tryLock.  tryAcquire is implemented in
         * subclasses, but both need nonfair try for trylock method.
         */
        final boolean nonfairTryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                if (compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            return false;
        }

        protected final boolean tryRelease(int releases) {
            int c = getState() - releases;
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }

    }
    //默认非公平锁
    public ReentrantLock() {
        sync = new NonfairSync();
    }
    //fair为false时，采用公平锁策略
    public ReentrantLock(boolean fair) {    
        sync = fair ? new FairSync() : new NonfairSync();
    }
    public void lock() {
        sync.lock();
    }
    public void unlock() {    sync.release(1);}
    public Condition newCondition() {    
        return sync.newCondition();
    }
    ...
}
~~~

使用方式：

~~~java
Lock lock = new ReentrantLock();
Condition condition = lock.newCondition();
lock.lock();
try {
  while(条件判断表达式) {
      condition.wait();
  }
 // 处理逻辑
} finally {
    lock.unlock();
}
~~~

#### AQS（AbstractQueuedSynchronizer）

![img](https://tva1.sinaimg.cn/large/008eGmZEly1goq693fxn7j30u013mkjn.jpg)

```java
public abstract class AbstractQueuedSynchronizer extends
    AbstractOwnableSynchronizer implements java.io.Serializable { 
    //等待队列的头节点
    private transient volatile Node head;
    //等待队列的尾节点
    private transient volatile Node tail;
    //同步状态
    private volatile int state;
    protected final int getState() { return state;}
    protected final void setState(int newState) { state = newState;}
    ...
}
```

队列同步器AQS是用来构建锁或其他同步组件的基础框架，内部使用一个int成员变量表示同步状态，通过内置的FIFO队列来完成资源获取线程的排队工作，其中内部状态state，等待队列的头节点head和尾节点head，都是通过volatile修饰，保证了多线程之间的可见。

```java
static final class Node {
        static final Node SHARED = new Node();
        static final Node EXCLUSIVE = null;
        static final int CANCELLED =  1;
        static final int SIGNAL    = -1;
        static final int CONDITION = -2;
        static final int PROPAGATE = -3;
        volatile int waitStatus;
        volatile Node prev;
        volatile Node next;
        volatile Thread thread;
        Node nextWaiter;
        ...
    }
```

![image-20210319213954020](https://i.loli.net/2021/03/19/SKHhvIQpgiMDmGB.png)

黄色节点是默认head节点，其实是一个空节点，我觉得可以理解成代表当前持有锁的线程，每当有线程竞争失败，都是插入到队列的尾节点，tail节点始终指向队列中的最后一个元素。

每个节点中， 除了存储了当前线程，前后节点的引用以外，还有一个waitStatus变量，用于描述节点当前的状态。多线程并发执行时，队列中会有多个节点存在，这个waitStatus其实代表对应线程的状态：有的线程可能获取锁因为某些原因放弃竞争；有的线程在等待满足条件，满足之后才能执行等等。一共有4中状态：

- CANCELLED 取消状态
- SIGNAL 等待触发状态
- CONDITION 等待条件状态
- PROPAGATE 状态需要向后传播

**线程获取锁过程**：

- 线程A执行CAS执行成功，state值被修改并返回true，线程A继续执行。
- 线程A执行CAS指令失败，说明线程B也在执行CAS指令且成功，这种情况下线程A会执行下面操作。
- 线程A生成新Node节点node，并通过CAS指令插入到等待队列的队尾（同一时刻可能会有多个Node节点插入到等待队列中），如果tail节点为空，则将head节点指向一个空节点（代表线程B），具体实现如下：



```java
private Node addWaiter(Node mode) {
    Node node = new Node(Thread.currentThread(), mode);
    // Try the fast path of enq; backup to full enq on failure
    Node pred = tail;
    if (pred != null) {
        node.prev = pred;
        if (compareAndSetTail(pred, node)) {
            pred.next = node;
            return node;
        }
    }
    enq(node);
    return node;
}
private Node enq(final Node node) {
    for (;;) {
        Node t = tail;
        if (t == null) { // Must initialize
            if (compareAndSetHead(new Node()))
                tail = head;
        } else {
            node.prev = t;
            if (compareAndSetTail(t, node)) {
                t.next = node;
                return t;
            }
        }
    }
}
```

- node插入到队尾后，该线程不会立马挂起，会进行自旋操作。因为在node的插入过程，线程B（即之前没有阻塞的线程）可能已经执行完成，所以要判断该node的前一个节点pred是否为head节点（代表线程B），如果pred == head，表明当前节点是队列中第一个“有效的”节点，因此再次尝试tryAcquire获取锁，
   1、如果成功获取到锁，表明线程B已经执行完成，线程A不需要挂起。
   2、如果获取失败，表示线程B还未完成，至少还未修改state值。进行下面操作。



```java
final boolean acquireQueued(final Node node, int arg) {
    boolean failed = true;
    try {
        boolean interrupted = false;
        for (;;) {
            final Node p = node.predecessor();
            if (p == head && tryAcquire(arg)) {
                setHead(node);
                p.next = null; // help GC
                failed = false;
                return interrupted;
            }
            if (shouldParkAfterFailedAcquire(p, node) &&
                parkAndCheckInterrupt())
                interrupted = true;
        }
    } finally {
        if (failed)
            cancelAcquire(node);
    }
}
```

- 前面我们已经说过只有前一个节点pred的线程状态为SIGNAL时，当前节点的线程才能被挂起。
   1、如果pred的waitStatus == 0，则通过CAS指令修改waitStatus为Node.SIGNAL。
   2、如果pred的waitStatus > 0，表明pred的线程状态CANCELLED，需从队列中删除。
   3、如果pred的waitStatus为Node.SIGNAL，则通过LockSupport.park()方法把线程A挂起，并等待被唤醒，被唤醒后进入下一步。
   具体实现如下：

```java
private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
    int ws = pred.waitStatus;
    if (ws == Node.SIGNAL)
        /*
         * This node has already set status asking a release
         * to signal it, so it can safely park.
         */
        return true;
    if (ws > 0) {
        /*
         * Predecessor was cancelled. Skip over predecessors and
         * indicate retry.
         */
        do {
            node.prev = pred = pred.prev;
        } while (pred.waitStatus > 0);
        pred.next = node;
    } else {
        /*
         * waitStatus must be 0 or PROPAGATE.  Indicate that we
         * need a signal, but don't park yet.  Caller will need to
         * retry to make sure it cannot acquire before parking.
         */
        compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
    }
    return false;
}
```

- 线程每次被唤醒时，都要进行中断检测，如果发现当前线程被中断，那么抛出InterruptedException并退出循环。从无限循环的代码可以看出，并不是被唤醒的线程一定能获得锁，必须调用tryAccquire重新竞争，因为锁是非公平的，有可能被新加入的线程获得，从而导致刚被唤醒的线程再次被阻塞，这个细节充分体现了“非公平”的精髓。

#### 可重入锁/自旋锁/可中断锁

在Java同步框架AbstractQueuedSynchronizer提到了两个概念，一个是独占锁，一个是共享锁，所谓独占锁就是只能有一个线程获取到锁，其他线程必须在这个锁释放了锁之后才能竞争而获得锁。而共享锁则可以允许多个线程获取到锁。

**可重入锁**：

可重入性表现在同一个线程可以多次获得锁，而不同线程依然不可多次获得锁。

**可中断锁**：

Java并没有提供任何直接中断某线程的方法，只提供了**中断机制**。线程A向线程B发出“请你停止运行”的请求（线程B也可以自己给自己发送此请求），但线程B并不会立刻停止运行，而是自行选择合适的时机以自己的方式响应中断，也可以直接忽略此中断。也就是说，Java的**中断不能直接终止线程**，而是需要被中断的线程自己决定怎么处理。如果线程A持有锁，线程B等待获取该锁。由于线程A持有锁的时间过长，线程B不想继续等待了，我们可以让线程B中断自己或者在别的线程里中断它，这种就是**可中断锁**。synchronized就是**不可中断锁**，而Lock的实现类都是**可中断锁**。

```java
/* Lock接口 */
public interface Lock {

    void lock(); // 拿不到锁就一直等，拿到马上返回。

    void lockInterruptibly() throws InterruptedException; // 拿不到锁就一直等，如果等待时收到中断请求，则需要处理InterruptedException。

    boolean tryLock(); // 无论拿不拿得到锁，都马上返回。拿到返回true，拿不到返回false。

    boolean tryLock(long time, TimeUnit unit) throws InterruptedException; // 同上，可以自定义等待的时间。

    void unlock();

    Condition newCondition();
}
```

**自旋锁（spinlock）**：是指当一个线程在获取锁的时候，如果锁已经被其它线程获取，那么该线程将循环等待，然后不断的判断锁是否能够被成功获取，直到获取到锁才会退出循环。

~~~java
public class SpinLock {

private AtomicReference cas = new AtomicReference();

public void lock() {

Thread current = Thread.currentThread();

// 利用CAS

while (!cas.compareAndSet(null, current)) {

// DO nothing

}

}

public void unlock() {

Thread current = Thread.currentThread();

cas.compareAndSet(current, null);

}

}
//上面Java实现的自旋锁不是公平的，即无法满足等待时间最长的线程优先获取锁。不公平的锁就会存在“线程饥饿”问题。
~~~

#### 读写锁

设计APP缓存的时候，多个线程读文件 设计？缓存或者是数据库读写的时候都需要考虑到。

~~~java
/**
         * 设计一个缓存系统
         * 读写锁的应用。
         * JDK1.5自带的读写锁特性，读与读不互斥，读与写互斥，写与写互斥。
         * 为什么要使用读写锁？一句话概括那就是提高系统性能，如何提高呢？
         * 试想，对于所有对读的操作是不需要线程互斥的，而如果方法内
         * 使用了synchronized关键字同步以达到线程安全，对于所有的线程不管是读还是写的操作都要同步。
         * 这时如果有大量的读操作时就会又性能瓶颈。
         *
         * 所以，当一个方法内有多个线程访问，并且方法内有读和写读操作时，
         * 提升性能最好的线程安全办法时采用读写锁的机制对读写互斥、写写互斥。这样对于读读就没有性能问题了
         *
         */
  //缓存的map
	private Map<String, Object> map = new HashMap<String, Object>();
	// 读写锁对象
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void readWriteMathod(String key){
 
        readWriteLock.readLock().lock();//读锁，只对写的线程互斥
        Object value = null;
        try {
            // 尝试从缓存中获取数据
            value = map.get(key);
            if (value == null) {
                readWriteLock.readLock().unlock();//发现目标值为null,释放掉读锁
                readWriteLock.writeLock().lock();//发现目标值为null,需要取值操作,上写锁
                try {
                    value = map.get(key);// 很严谨这一步。再次取目标值
                    if (value == null) {//很严谨这一步。再次判断目标值,防止写锁释放后，后面获得写锁的线程再次进行取值操作
                        // 模拟DB操作
                        value = new Random().nextInt(10000) + "test";
                        map.put(key, value);
 
                        System.out.println("db completed!");
                    }
                    readWriteLock.readLock().lock();//再次对读进行锁住，以防止写的操作，造成数据错乱
                } finally {
                    /*
                     * 先加读锁再释放写锁读作用：
                     * 防止多个线程获得写锁进行写的操作，所以在写锁还没有释放前要上读锁
                     */
                    readWriteLock.writeLock().unlock();
                }
            }
 
        } finally {
            readWriteLock.readLock().unlock();
        }
        
    /**
	 *  test main
	 * @param args
	 */
	public static void main(String[] args) {
		final CacheTest cache = new CacheTest();
		final String key = "user";
		for (int i = 0; i < 1000; i++) {
			new Thread(){
				public void run() {
					System.out.println(cache.getData(key));
				};
			}.start();
		}
	}
}
~~~

#### 文件锁

- 建议锁

​      只在合作进程（在读写文件之前尝试加锁）间有效。

​      其他进程非要读写是拦不住的。

- 强制锁

​     需要 `mount -o mand`  和 `chmod g+s,g-x lockfile` 同时满足才行

​      linux 内核会阻塞其他进程的 IO 请求

​      可以通过删除锁文件绕过

|              |                   Posix locks: fcntl/lockf                   |                    BSD locks: flock                     |
| ------------ | :----------------------------------------------------------: | :-----------------------------------------------------: |
| 范围         |                          字节范围锁                          |                   只能对整个文件加锁                    |
| 类型         |      建议锁（默认）/ 强制锁（非 POSIX 标准，默认关闭）       |                         建议锁                          |
| 关联关系     | 与进程关联( 标准 POSIX )/ 与文件描述符关联(非 POSIX 标准， 需特定参数， linux 3.15 支持) |                    与文件描述符关联                     |
| 网络文件系统 |                   支持 NFS   不支持 ocfs2                    | 支持 NFS（实现仿 fcntl， linux 2.6.12 支持） 支持 ocfs2 |

~~~c
int fcntl(int fd, int cmd, ... /* arg */ )
功能描述：对文件描述符提供控制，针对cmd的值,fcntl能够接受第三个参数
返回值根据不同参数不同，失败均返回-1
    
    
    
fcntl函数有5种功能：

复制一个现有的描述符（cmd=F_DUPFD）.
获得／设置文件描述符标记(cmd=F_GETFD或F_SETFD).
获得／设置文件状态标记(cmd=F_GETFL或F_SETFL).
获得／设置异步I/O所有权(cmd=F_GETOWN或F_SETOWN).
获得／设置记录锁(cmd=F_GETLK,F_SETLK或F_SETLKW).
~~~

主要的作用：**记录锁（record locking）**的功能是：一个进程正在读或修改文件的某个部分时，可以阻止其他进程修改同一文件区。

`struct flock`结构体

~~~c
在操作记录锁时，传入的arg参数为struct flock结构体，定义如下

struct flock{
    short l_type;  // F_RDLCK读锁，F_WRLCK写锁，F_UNLCK解锁
    off_t l_start; // 锁偏移的字节
    short l_whence;// 锁开始的位置，SEEK_SET,SEEK_CURorSEEK_END
    off_t l_len;   // 锁的范围，0表示从起始到文件末
    pid_t l_pid;   // 占有锁的进程ID(仅由F_GETLK返回)
};
~~~

基本规则：

多个进程在一个给定的字节 上可以有一把共享的读锁，但是在一个给定字节上的写锁则只能由一个进程独用。更进一步而言，如果在一个给定字节上已经有一把或多把读锁，则不能在该字节上再加写锁；如果在一个字节上已经有一把独占性的写锁，则不能再对它加任何读锁。

![image-20210327223202912.png](https://i.loli.net/2021/03/27/wMFRoYXbAkLJjQ9.png)

#### **线程安全与调度**

##### ThreadLocal

ThreadLocal是为每一个线程创建一个单独的变量副本，每个线程都可以独立地改变自己所拥有的变量副本，而不会影响其他线程所对应的副本。

一般来说，当某些数据是以线程为作用域并且不同线程具有不同的数据副本的时候，就可以考虑采用ThreadLocal。比如对于Handler来说，它需要获取当前线程的Looper，很显然Looper的作用域就是线程并且不同线程具有不同的Looper，这个时候通过ThreadLocal就可以轻松实现Looper在线程中的存取，如果不采用ThreadLocal，那么系统就必须提供一个全局的哈希表供Handler查找指定线程的Looper，这样一来就必须提供一个类似于LooperManager的类了，但是系统并没有这么做而是选择了ThreadLocal，这就是ThreadLocal的好处。



```java
public final class Looper {
				...省略
static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
				...省略
public static void prepare() {
    prepare(true);
}
private static void prepare(boolean quitAllowed) {
    if (sThreadLocal.get() != null) {
        throw new RuntimeException("Only one Looper may be created per thread");
    }
    sThreadLocal.set(new Looper(quitAllowed));
}
    public static Looper myLooper() {
    return sThreadLocal.get();
}
				...省略
```

使用ThreadLocal定义的变量，将指向当前线程本地的一个LocalMap空间。ThreadLocal变量作为key，其内容作为value，保存在本地。多线程对ThreadLocal对象进行操作，实际上是对各自的本地变量进行操作，不存在线程安全问题。

##### CountDownLatch

它可以实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。

##### CyclicBarrier

##### FutureTask

**ForkJoinPool**

上述内容参见直播课。



