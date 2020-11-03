- 是什么？
- 使用
- 原理？
- 在jvm中的使用？
- 常见面试题



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

- String类源码中内部使用字符数组(char[])实现，且时final修饰的；
- 所以String类是不可改变的，一旦创建了String对象，它的值就无法改变，如果需要对字符串做很多修改，应该选择使用*StringBuffer或StringBuilder*

~~~java
		String str = "123";
        String str3 = str + "456";

        System.out.println(str==str3);
~~~

- 所以str3与str对象是两个对象，使用加号"+"，编译器会创建一个新的String对象

#### 字符串长度

- String类有一个length()方法，返回字符串对象包含的字符数,其实返回的是字符数组的长度，所以字符串最大长度是字符数组的长度是int类型长度(4个字节).

~~~java
    public int length() {
        return this.value.length;
    }
~~~



















