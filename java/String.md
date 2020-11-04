- 是什么
- 使用
- 原理
- jvm优化
- 相关问题



#### String

字符串在java编程中广泛应用，字符串属于对象，用来创建和操作字符串

#### 创建字符串

~~~java
	String str = "123";
~~~

- 在代码中遇到字符串常量时，如"123",编译器会使用该值创建一个String对象
- String类还有很多其他构造方法

~~~java
public final class String implements Serializable, Comparable<String>, CharSequence {
    
    private final char[] value;
    
	public String() {
        this.value = "".value;
    }

    public String(String var1) {
        this.value = var1.value;
        this.hash = var1.hash;
    }

    public String(char[] var1) {
        this.value = Arrays.copyOf(var1, var1.length);
    }
}
~~~

- String类源码中内部使用字符数组(char[])实现，且是final修饰的；
- 所以String类是不可改变的，一旦创建了String对象，它的值就无法改变，如果需要对字符串做很多修改，应该选择使用*StringBuffer或StringBuilder*

~~~java
	String str = "123";
    String str3 = str + "456";
	System.out.println(str==str3);
~~~

- 所以str3与str对象是两个对象，使用加号"+"，编译器会创建一个新的String对象

#### 字符串长度

~~~java
    public int length() {
        return this.value.length;
    }
~~~

- String类有一个length()方法，返回字符串对象包含的字符数,其实返回的是字符数组的长度
- 字符串最大长度是字符数组的长度是int类型长度(4个字节)，为2的31次方-1 = 2147483647
- 一个char类型占用2个字节

#### 字符串相加

~~~java
	String str = "123";
	String str3 = str + "456";
~~~

- 字符串常量相加，jvm会进行优化，常见StringBuild调用append方法相加

- 对应ASM字节码

~~~java
   L1
    LINENUMBER 7 L1
    NEW java/lang/String
    DUP
    LDC "123"
    INVOKESPECIAL java/lang/String.<init> (Ljava/lang/String;)V
    ASTORE 2
   L2
    LINENUMBER 8 L2
    NEW java/lang/StringBuilder
    DUP
    INVOKESPECIAL java/lang/StringBuilder.<init> ()V
    ALOAD 1
    INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
    LDC "456"
    INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
    INVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
    ASTORE 3
~~~

#### 字符串比较

~~~java
	String s1 = "a";
    String s2 = "b";
    String s3 = "ab";
    String s4 = s1 + s2;
    String s5 = "a" + "b";
    String s6 = s1 + "b";
    StringBuffer s7 = new StringBuffer("abc");
    String s8 = s7.substring(3);
    System.out.println(s3 == s4);
    System.out.println(s3 == s5);
    System.out.println(s3 == s6);
    System.out.println(s4 == s6);
    System.out.println(s4 == s7.toString());
    System.out.println(s4 == s8);
        
打印结果：
false
true
false
false
false
false
~~~

- 字符串用==比较，比较的是两个字符串是否指向同一个对象指针
- 程序在运行的时候会创建一个字符串缓冲池，程序会把字符串常量（如"a","b","ab","a"+"b"）放入字符串缓冲池中

- 其中s3与s5的创建

```java
    String s3 = "ab";
    String s5 = "a" + "b";
```
- 这样的表达式创建字符串的时候，程序首先会在String缓冲池中寻找相同值的对象，s3会先放到缓冲池中，在s5被创建的时候，程序会找到具有相同值的s3，将s5引用指向s3引用的对象指针，所以s5==s3由于两个是指向同一个对象针指，所以未true

- 当涉及到变量运算时，所得到的字符串对象存储在各自的内存中，不是缓冲池中的s3对象

~~~java
	String s4 = s1 + s2;
	String s6 = s1 + "b";
~~~











