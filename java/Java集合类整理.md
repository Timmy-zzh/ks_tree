#### Java集合类



##### List

1. ArrayList：
   - 底层数据结构为数组，元素可以随机存取
   - 插入，获取效率高；删除，添加元素效率低
   - 数据扩容，需要复制数组内部数据
2. LinkedList：底层数据结构为链表
   - 底层数据结构为链表
   - 元素添加，删除效率高，获取效率低
   - 缺点：每个节点除了数据域，还需要保存下一个节点指向的指针
3. Vector
   - 底层数据结构为数组
   - 数据操作线程安全（在方法中添加了synchronized关键字）

#### Set

1. HashSet：
   - 元素唯一，不能重复，使用hashcode作为判断
   - 内部通过封装map实现，存入的元素作为map结构的key值
2. TreeSet：
   - 有序

#### Map

1. HashMap:
   - 哈希表结构实现（数组+链表），当链表长度超过8时，链表转换为红黑树
   - [key,value]键值对方式存储，key值唯一，key和value都可以为null
   - 存储对象，将[key,value]键值传给put方法
     - 调用hash(k)方法计算k的hash值，然后结合数组长度，计算得到数组下标(i = (n - 1) & hash)
     - 当容器中的元素个数大于阈值（容量*0.75），则进行扩容调用resize方法容量变为2n
     - 如果k的hash值在HashMap中不存在，则执行插入，若存在则发生碰撞
     - 如果k的hash值在HashMap中存在，且他们的equals返回true，则更新键值对；
     - 如果hash值存在，且equals方法返回false，则插入链表尾部（尾插发），或者红黑树中
     - Jdk1.7之前使用头插法，jdk1.8使用尾插法，当链表大于8时，就把链表转换成红黑树
   - 获取对象，将k传给get方法
     - 调用hash(k)方法计算k的hash值，从而获取到该键所在链表的数组下标
     - 顺序便利链表，equals方法查找相同链表中k值对应的value值
     - hashcode是定位的，确定存储位置；equals是定性的，确定两者是否相等
2. LinkedHashMap：
   - LruCache：最近最少使用
3. HashTable:
   - key和value都不能为null
   - 直接使用对象的hashCode
   - 线程安全，使用synchronize关键字加锁（对象锁）
4. TreeMap：
   - 有序，实现了SortMap接口
5. LinkedHashMap：
   - 保存了记录的插入顺序，在用Iterator遍历时，先取到的记录肯定是先插入的，
   - 遍历比HashMap慢
6. ConcurrentHashMap
   - 分段锁，ReentrantLock + Segment + HashEntry（jdk1.7以前）
     - 把HashMap分成多个段，每段分配一把锁，这样支持多线程访问
     - 锁粒度：基于Segment，包含多个HashEntry
   - CAS+synchronized（jdk1.8）
     - 锁粒度：Node首结点

#####Android优化数据结构

1. SparseArray
   - key值为int类型，value为Object类型
   - 内部通过两个数组实现mKeys，mValues
   - 使用二分查找法获取key值在数组中的位置，耗时长，节省内存
2. ArrayMap
   - [key，value]键值对结构，都为Object类型
   - 内部使用两个数组实现，mHashs（key的hash值数据）,mValues（奇数保存key值，偶数保存vlaue值）
   - 根据key的获取hash值，然后计算出key在mValues数组中的index位置



#####常见mst

1.hashcode与equals方法

- hashCode：通过和当前线程有关的一个随机数和三个确定值，运用xorshift随机算法得到的一个随机数
- equals：自己重写，可用于判断两个对象是否相同
- equals相等的两个对象的hashcode肯定相等， hashcode相等的两个对象equals方法不一定相等，也就说hashcode不是绝对可靠的。
- == 判断内存是否相等

2.map中key的hash算法

~~~java
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
~~~

- 先获取key的hashcode，然后与低16位进行异或操作，这样设计主要是从速度，功效和质量来考虑的，减少系统开销，也不会因为高位没有参与下表计算，从而引起的碰撞
- 使用异或操作的原因：保证了对象的hashCode的32位值只要有一位发生改变，整个hash()返回值就会改变，目的是尽可能减少碰撞

3.加载因子0.75？

- 加载因子越大，填满的元素越多，控件利用率越高，但发生冲突的机会变大了
- 加载因为越小，填满的元素越少，冲突发生的机会减少，但空间浪费了更多，而且还会提高扩容rehash操作次数
- 解决hash冲突方法：线性探测法/拉链法
- 根据泊松分布原则，选择0.75作为加载因子是事件和空间成本上决定的

4.链表转换成红黑树？

- 使用红黑树是为了解决二叉树的缺陷，二叉查找树在特殊情况下会变成一条线性结构（和链表一样），遍历查找操作会非常慢
- 红黑树属于平衡二叉树，当有新数据插入时，通过左旋，右旋，变色这些操作来保持平衡
- 引入红黑树就是为了查找数据时，链表查询深度问题















