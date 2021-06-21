#### HashMap

#### 1.特性

- 数组 + 链表 的底层 结构
- 默认容量：16
- 加载因子：0.75

#### 2.put数据

- 散列算法
  - 对key进行hash值计算
  - 然后与数组长度取模操作，得到该节点插入的数组下标位置
- 插入链表
  - 链表数量超过8个，会将链表转换成红黑树
- 调用方法 put(K key,V value);

##### 扩容

- 将容量修改为之前的2倍

#### 3.源码分析

##### 3.1.构造函数

~~~java
		//重要属性：
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // HashMap默认容量大小16
    static final int MAXIMUM_CAPACITY = 1 << 30;				//容量最大值
    static final float DEFAULT_LOAD_FACTOR = 0.75f;			//加载因子0.75
    int threshold;							//阈值，数组扩容阈值，大小为当前数组容量 * 加载因子

		/**
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     * 默认大小16，加载因子为0.75
     */
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }
~~~

##### Node节点

~~~java
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;		//后继节点

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
~~~

##### 3.2.添加元素 put方法

~~~java
    public V put(K key, V value) {
      	// 1.先调用hash方法获取key的hash值，然后调用putVal方法
        return putVal(hash(key), key, value, false, true);
    }

		//返回Object key值的hash值，为null返回0，
		//不为null先获取key的hashCode的值h，然后二进制操作（h >>> 16） 往右移动16位
		//-接着再与h 进行异或^操作
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
      // Node节点p对象为当前key值中，数组下标的节点
        Node<K,V>[] tab; Node<K,V> p; int n, i;
      //table为null，调用resize()方法获取初始化创建的tab，并获取tab数组的大小，
      //resize方法默认创建一个大小16的Node数组，
        if ((tab = table) == null || (n = tab.length) == 0){
            n = (tab = resize()).length;							
        }
      // 根据key的hash值定位到数组下标位置 i = (n - 1) & hash
      // 如果当前数组下标为null，则新建一个Node节点放到该数组下标i位置
        if ((p = tab[i = (n - 1) & hash]) == null){			
          	// 如果数组下标的元素为null，则新建一个Node节点
            tab[i] = newNode(hash, key, value, null);
        }else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k)))){
                e = p;		//如果hash值相同，且key值相等，替换
            }else if (p instanceof TreeNode){	//红黑树
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            }else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {	
                      // 沿着数组下标的第一个节点p，不断往后遍历查找到当前链路的最后一个节点p，
                      // -新建新节点连接在尾节点后面
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                          //当数组节点链表的节点数超过8个时，链表转换成红黑树结果
                            treeifyBin(tab, hash);
                        break;
                    }
                  // 找到相同的hash值，e节点为相同key值的节点对象
                    if (e.hash == hash && 
                        ((k = e.key) == key || (key != null && key.equals(k)))){
                        break;
                    }
                    p = e;
                }
            }
          // e不为null，说明存在相同key值的节点，将数据域进行替换即可
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)		//添加元素后超过数组大小阈值，需要进行数组扩容
            resize();
        afterNodeInsertion(evict);
        return null;
    }


    final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
      	//oldCap表示之前数组的大小，默认为0
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY){
              // newCap = oldCap << 1 数组新容量在原数组容量基础上的两倍
              // newThr 为扩展阈值，也扩大为之前的两倍
                newThr = oldThr << 1; // double threshold
            }
        }else if (oldThr > 0){ // initial capacity was placed in threshold
          	//oldThr 为 threshold，用户可以在构造函数中设置threshold的大小
            newCap = oldThr;
        }else {               // zero initial threshold signifies using defaults
          	//oldCap == 0,数组还每创建呢，
          	//--第一次Node[]数组table 为null，oldCap为0，命中该条件，newCap设置为16
            newCap = DEFAULT_INITIAL_CAPACITY;
          	// newThr等于数组容量的大小 * 扩展因子，而后会赋值给threshold的
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        //创建Node数组，并给table赋值
      	@SuppressWarnings({"rawtypes","unchecked"})
      			Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
      
      // 新创建更大容量的数组，需要将原先的数组内容移动到新数组链表节点上去
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
~~~

##### 3.3.获取数据get() 函数

~~~java
    public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
      // (n - 1) & hash -->根据hash值获取当前key值在数组中的下标位置
      //first = tab[(n - 1) & hash]  --》first为数组下标中第一个头节点
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && 				// 判断头节点是否命中
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                if (first instanceof TreeNode){		//红黑树获取节点
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                }
              // 从头节点不断获取后继节点，判断是否命中，命中的话直接返回
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }


	//红黑树中根据key值获取节点对象
    static final class TreeNode<K,V> extends LinkedHashMap.LinkedHashMapEntry<K,V> {
      
        final TreeNode<K,V> getTreeNode(int h, Object k) {
            return ((parent != null) ? root() : this).find(h, k, null);
        }
    }
~~~







#### 

