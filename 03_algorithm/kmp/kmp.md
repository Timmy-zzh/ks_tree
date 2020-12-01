#### KMP

#####kmp是什么？ 

- kmp算法是由三位学者发明的:Kunth,Morris和Pratt，所以取了三位学者名字的首字母

#####kmp的作用

- 主要用于在字符串匹配上
- kmp的主要思想是：当出现字符串不匹配时，可以记录一部分之前已经匹配的文本内容，利用这些信息避免从头再去做匹配

##### 最长公共前后缀

- 前缀：指不包含最后一个字符的所有以第一个字符开头的连续子串
- 后缀：指不包含第一个字符的所有以最后一个字符结尾的连续子串

##### 前缀表

- 前缀表是下标i之前(包括i)的字符串中，有多大长度的相同前缀后缀
- next数组就是一个前缀表
- 作用：前缀表是用来回溯的，它记录了模式串与主串(文本串)不匹配的时候，模式串应该从哪里开始重新匹配

##### 前缀表的计算

- 模式串为aabaaf
  - 长度为前1个字符的子串a,最长相同前后缀的长度为0.（一个长度字符不能前缀不能算第一个）
  - 长度为前2个字符的子串aa，最长相同前后缀长度为1
  - 长度为前3个字符的子串aab，最长相同前后缀长度为0
  - 长度为前4个字符的子串aaba，最长相同前后缀长度为1
  - 长度为前5个字符的子串aabaa，最长相同前后缀长度为2
  - 长度为前6个字符的子串aabaaf，最长相同前后缀长度为0



<img src="/Users/timmy1/project/LeetCode/app/src/main/assets/kmp/kmp-前缀表.png" alt="kmp-前缀表" style="zoom:30%;" />

- 前缀表里的数值代表着就是：当前位置之前的子串有多大长度相同的前缀后缀
- 避免前缀表进入死循环，把前缀表里的数值统统减一，开始位置设置为-1

<img src="/Users/timmy1/project/LeetCode/app/src/main/assets/kmp/前缀表数值统统减一.png" alt="前缀表数值统统减一" style="zoom:30%;" />

##### 求前缀表next数组

构造next数组就是计算模式串s，前缀表的过程，主要有如下三步

- 初始化

- 处理前后缀不相同的情况

- 处理前后缀相同的情况

1. 初始化

- 定义两个指针i和j，j指向前缀终止位置，i指向后缀终止位置
- next[i]表示i(包括i)之前最长想等的前后缀长度(其实就是j)

~~~java
对next数组初始化赋值如下：
int j = -1;
next[0] = -1
~~~

2. 处理前后缀不相同的情况

- 因为j初始化为-1，那么i就从1开始，进行s[i]与s[j+1]的比较

~~~
遍历模式串s的循环下表i从1开始
for(int i = 1; i < s.size(); i++) {
~~~

- 如果s[i]与s[j+1]不相同，也就是遇到前后缀末尾不相同的情况，就要向前回溯
  - next[j]记录着j(包括j)之前的子串相同前后缀的长度
  - 当s[i]与s[j+1]不相同，就要找j+1前一个元素在next数组里的值(就是next[j])

~~~java
处理前后缀不相同的情况代码如下：
while (j >= 0 && s[i] != s[j + 1]) { // 前后缀不相同了
    j = next[j]; // 向前回溯
}
~~~

3. 处理前后缀相同的情况

- 如果s[i]与s[j+1]相同，那就同时向后移动i和j说明找到了相同的前后缀，同时还要将j(前缀的长度)赋值给next[i],因为next[i]要记录相同前后缀的长度

~~~java
if (s[i] == s[j + 1]) { // 找到相同的前后缀
    j++;
}
next[i] = j;
~~~

##### 使用next数组来做匹配

在文本串s里找是否出现过模式串t

- 定义两个下标j 指向模式串起始位置，i指向文本串起始位置

~~~java
j初始值为-1， i从0开始遍历
for (int i = 0; i < s.size(); i++) 
~~~

- 对s[i]与t[j+1]进行比较，如果s[i]与t[j+1]不相同，j就要从next数组里寻找下一个匹配的位置

~~~java
while(j >= 0 && s[i] != t[j + 1]) {
    j = next[j];
}
~~~

- 如果s[i]与t[j+1]相同，那么i和j同时向后移动

~~~java
if (s[i] == t[j + 1]) {
    j++; // i的增加在for循环里
}
~~~

- 如果j指向了模式串t的末尾，就说明模式串t完全匹配文本串s里的某个子串了

~~~java
if (j == (t.size() - 1) ) {
    return (i - t.size() + 1);
}
~~~

##### 整体代码如下

~~~java

    /**
     * 题目：28. 实现 strStr()
     * 实现 strStr() 函数。
     * <p>
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出
     * needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * <p>
     * 示例 1:
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * <p>
     * 示例 2:
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     * <p>
     * 说明:
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     * <p>
     * ---?在文本串：aabaabaafa中查找是否出现过一个模式串：aabaaf
     * 解题思路：
     * 1。构建模式串的next数组，
     * 2。然后主串与模式串进行，比较，当字符不匹配的时候，通过next前缀表找到重新匹配的位置
     */
    public int strStr(String haystack, String needle) {
        int[] next = new int[needle.length()];
        getNext(needle, next);

        int j = -1;
        char[] s = haystack.toCharArray();  //文本串
        char[] t = needle.toCharArray();    //模式串
        for (int i = 0; i < s.length; i++) {
            while (j >= 0 && s[i] != t[j + 1]) {    //不匹配
                j = next[j];
            }
            if (s[i] == t[j + 1]) {     //文本串与模式串匹配
                j++;
            }
            if (j == t.length - 1) {  //模式串到末尾
                return (i - t.length + 1);
            }
        }
        return -1;
    }

    /**
     * 获取模式串的前缀表next数组
     * aabaaf
     * - 长度为前1个字符的子串a,最长相同前后缀的长度为0       -1
     * - 长度为前2个字符的子串aa，最长相同前后缀长度为1       0
     * - 长度为前3个字符的子串aab，最长相同前后缀长度为0      -1
     * - 长度为前4个字符的子串aaba，最长相同前后缀长度为1     0
     * - 长度为前5个字符的子串aabaa，最长相同前后缀长度为2    1
     * - 长度为前6个字符的子串aabaaf，最长相同前后缀长度为0   -1
     */
    private void getNext(String needle, int[] next) {
        char[] chars = needle.toCharArray();
        int j = -1;     //j指向前缀终止位置,i指向后缀终止位置
        next[0] = -1;   //长度为1的子串，默认相同前后缀长度为-1
        for (int i = 1; i < chars.length; i++) {
            while (j >= 0 && chars[i] != chars[j + 1]) {  
              	//字符不相等，需要回溯，找到相同前缀的后面字符位置
                j = next[j];
            }
            if (chars[i] == chars[j + 1]) {    //字符相等，j长度++
                j++;
            }
            next[i] = j;
        }
    }
~~~





















