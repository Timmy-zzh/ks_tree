###Linux经典基础知识总结

####用户空间和内核空间

现在操作系统都是采用虚拟存储器，那么对32位操作系统而言，它的寻址空间（虚拟存储空间）为4G（2的32次方）。操作系统的核心是内核，独立于普通的应用程序，可以访问受保护的内存空间，也有访问底层硬件设备的所有权限。为了保证用户进程不能直接操作内核（kernel），保证内核的安全，操心系统将虚拟空间划分为两部分，一部分为内核空间，一部分为用户空间。针对linux操作系统而言，将最高的1G字节（从虚拟地址0xC0000000到0xFFFFFFFF），供内核使用，称为内核空间，而将较低的3G字节（从虚拟地址0x00000000到0xBFFFFFFF），供各个进程使用，称为用户空间。

![image-20200620090802332](https://tva1.sinaimg.cn/large/007S8ZIlly1gfyhdln5jcj30vk0gaju8.jpg)

**Linux内核高端内存**（Hign Memory）

借一段地址空间，建立临时**地址映射**，用完后释放，达到这段地址空间可以循环使用，访问所有物理内存。内核将高端内存划分为3部分：**VMALLOC_START~VMALLOC_END**、**KMAP_BASE~FIXADDR_START**和**FIXADDR_START~4G**。

![image-20200620091124665](https://tva1.sinaimg.cn/large/007S8ZIlly1gfyhh3p3i4j31h20fg42d.jpg)

高端内存映射有三种方式：
**映射到”内核动态映射空间”（noncontiguous memory allocation）**
这种方式很简单，因为通过 vmalloc() ，在”内核动态映射空间”申请内存的时候，就可能从高端内存获得页面（参看 vmalloc 的实现），因此说高端内存有可能映射到”内核动态映射空间”中。

**持久内核映射（permanent kernel mapping）**
如果是通过 alloc_page() 获得了高端内存对应的 page，如何给它找个线性空间？
内核专门为此留出一块线性空间，从 PKMAP_BASE 到 FIXADDR_START ，用于映射高端内存。在 2.6内核上，这个地址范围是 4G-8M 到 4G-4M 之间。这个空间起叫”内核永久映射空间”或者”永久内核映射空间”。这个空间和其它空间使用同样的页目录表，对于内核来说，就是 swapper_pg_dir，对普通进程来说，通过 CR3 寄存器指向。通常情况下，这个空间是 4M 大小，因此仅仅需要一个页表即可，内核通过来 pkmap_page_table 寻找这个页表。通过 kmap()，可以把一个 page 映射到这个空间来。由于这个空间是 4M 大小，最多能同时映射 1024 个 page。因此，对于不使用的的 page，及应该时从这个空间释放掉（也就是解除映射关系），通过 kunmap() ，可以把一个 page 对应的线性地址从这个空间释放出来。

**临时映射（temporary kernel mapping）**
内核在 FIXADDR_START 到 FIXADDR_TOP 之间保留了一些线性空间用于特殊需求。这个空间称为”固定映射空间”在这个空间中，有一部分用于高端内存的临时映射。

关于用户态和内核态的经典总结：

- 用户进程最多只可以访问3G物理内存，而内核进程可以访问所有物理内存。

- 64位Linux内核不存在高端内存，因为64位内核可以支持超过512GB内存。若机器安装的物理内存超过内核地址空间范围，就会存在高端内存。

- 用户进程能访问多少物理内存？内核代码能访问多少物理内存？

  32位系统用户进程最大可以访问3GB，内核代码可以访问所有物理内存。

  64位系统用户进程最大可以访问超过512GB，内核代码可以访问所有物理内存。
  
  

### 内存分区（Zone）

Linux对内存节点再进行划分，分为不同的分区。内核以struct zone来描述内存分区。通常一个节点分为DMA、Normal和High Memory内存区。

- DMA内存区：：即直接内存访问分区，通常为物理内存的起始16M。主要是供一些外设使用，外设和内存直接访问数据访问，而无需系统CPU的参与。
- Normal内存区：从16M到896M内存区。
- HighMemory内存区：896M以后的区域。

### 内存管理

#### 关于地址分类

虚拟地址又叫线性地址。linux没有采用分段机制，所以逻辑地址和虚拟地址（线性地址）（**在用户态，内核态逻辑地址专指下文说的线性偏移前的地址**）是一个概念。物理地址不用说了。内核的虚拟地址和物理地址，大部分只差一个线性偏移量。用户空间的虚拟地址和物理地址则采用了多级页表进行映射，但仍称之为线性地址。

DMA/HIGH_MEM/NROMAL 分区的概念：

![img](https://tva1.sinaimg.cn/large/007S8ZIlly1ggog2299gkj30fg09j3zb.jpg)

**Tips：**由于内核的虚拟和物理地址只差一个偏移量：物理地址 = 逻辑地址 – 0xC0000000。所以如果1G内核空间完全用来线性映射，显然物理内存也只能访问到1G区间，这显然是不合理的。HIGHMEM就是为了解决这个问题，专门开辟的一块非线性映射，可以灵活定制映射，以便访问1G以上物理内存的区域。

在x86结构中，**Linux内核虚拟**地址空间划分0-3G为用户空间，3-4G为内核空间(注意，内核可以使用的线性地址只有1G)。内核虚拟空间（3G~4G）又划分为三种类型的区：
ZONE_DMA：3G之后起始的16MB
ZONE_NORMAL：16MB~896MB
ZONE_HIGHMEM：896MB ~1G（高端内存）

高度内存的分配：

![image-20200712201053795](https://tva1.sinaimg.cn/large/007S8ZIlly1ggog62g7ubj316q0ecaie.jpg)

​		内核直接映射空间 PAGE_OFFSET~VMALLOC_START，kmalloc和__get_free_page()分配的是这里的页面。二者是借助slab分配器，直接分配物理页再转换为逻辑地址（物理地址连续）。适合分配小段内存。此区域 包含了内核镜像、物理页框表mem_map等资源。

内核动态映射空间 VMALLOC_START~VMALLOC_END，被vmalloc用到，可表示的空间大。

内核永久映射空间 PKMAP_BASE ~ FIXADDR_START，kmap；

内核临时映射空间 FIXADDR_START~FIXADDR_TOP，kmap_atomic。

#### 关于内存映射

注意：内存映射分为文件映射和匿名映射。
文件映射是指代表这个映射的vma对应到一个文件中的某个区域。这种映射方式相对较少被用户态程序显式地使用，用户态程序一般习惯于open一个文件、然后read/write去读写文件。而实际上，用户程序也可以使用mmap系统调用将一个文件的某个部分映射到内存上（对应到一个vma），然后以访存的方式去读写文件。尽管用户程序较少这样使用，但是用户进程中却充斥着这样的映射：进程正在执行的可执行代码（包括可执行文件、lib库文件）就是以这样的方式被映射的。

文件映射是将文件的磁盘高速缓存中的页面直接映射到了用户空间（可见，文件映射的页面是磁盘高速缓存页面的子集），用户可以0拷贝地对其进行读写。而使用read/write的话，则会在用户空间的内存和磁盘高速缓存间发生一次拷贝。

匿名映射相对于文件映射，代表这个映射的vma没有对应到文件。对于用户空间普通的内存分配（堆空间、栈空间），都属于匿名映射。每个进程可能通过各自的文件映射来映射到同一个文件上（比如大多数进程都映射了libc库的so文件）；那匿名映射呢？实际上，多个进程也可能通过各自的匿名映射来映射到同一段物理内存上，这种情况是由于fork之后父子进程共享原来的物理内存（copy-on-write）而引起的。

#### 关于内存管理架构

![img](https://tva1.sinaimg.cn/large/007S8ZIlly1ggog9sieodg30wg0u0juo.gif)

**地址映射**：

地址映射必须要有硬件支持，mmu（内存管理单元）就是这个硬件。并且需要有cache来保存页表，这个cache就是TLB（Translation lookaside buffer）。实际上只有用户态的地址映射才需要管理，内核态的地址映射是写死的。

**虚拟地址管理**：

​			每个进程对应一个task结构，它指向一个mm结构，这就是该进程的内存管理器。（对于线程来说，每个线程也都有一个task结构，但是它们都指向同一个mm，所以地址空间是共享的。）用户程序对内存的操作（分配、回收、映射、等等）都是对mm的操作，具体来说是对mm上的vma（虚拟内存空间）的操作。这些vma代表着进程空间的各个区域，比如堆、栈、代码区、数据区、各种映射区、等等。用户程序对内存的操作并不会直接影响到页表，更不会直接影响到物理内存的分配。比如malloc成功，仅仅是改变了某个vma，页表不会变，物理内存的分配也不会变。

​			假设用户分配了内存，然后访问这块内存。由于页表里面并没有记录相关的映射，CPU产生一次缺页异常。内核捕捉异常，检查产生异常的地址是不是存在于一个合法的vma中。如果不是，则给进程一个"段错误"，让其崩溃；如果是，则分配一个物理页，并为之建立映射。

**物理内存管理**：

物理内存管理的第一个层次就是介质的管理。pg_data_t结构就描述了介质。一般而言，我们的内存管理介质只有内存，并且它是均匀的，所以可以简单地认为系统中只有一个pg_data_t对象。每一种介质下面有若干个zone。一般是三个，DMA、NORMAL和HIGH。

**用户空间内存管理**：

malloc是libc的库函数，用户程序一般通过它（或类似函数）来分配内存空间。
libc对内存的分配有两种途径，一是调整堆的大小，二是mmap一个新的虚拟内存区域（堆也是一个vma）。在内核中，堆是一个一端固定、一端可伸缩的vma。可伸缩的一端通过系统调用brk来调整。libc管理着堆的空间，用户调用malloc分配内存时，libc尽量从现有的堆中去分配。如果堆空间不够，则通过brk增大堆空间。当用户将已分配的空间free时，libc可能会通过brk减小堆空间。当用户malloc一块很大的内存时，libc会通过mmap系统调用映射一个新的vma。在进程的vma较少时，内核采用链表来管理vma；vma较多时，改用红黑树来管理。

**用户的栈**：

与堆一样，栈也是一个vma，这个vma是一端固定、一端可伸（注意，不能缩）的。这个vma比较特殊，没有类似brk的系统调用让这个vma伸展，它是自动伸展的。线程的mm是共享其父进程的。虽然栈是mm中的一个vma，但是线程不能与其父进程共用这个vma（两个运行实体显然不用共用一个栈）。于是，在线程创建时，线程库通过mmap新建了一个vma，以此作为线程的栈（大于一般为：2M）。可见，线程的栈在某种意义上并不是真正栈，它是一个固定的区域，并且容量很有限。

#### 内存API总结

常见的包括：kmalloc kzalloc vmalloc malloc 和get_free_page()。

- kmalloc申请的是较小的连续的物理内存，虚拟地址上也是连续的。kmalloc和get_free_page最终调用实现是相同的，只不过在调用最终函数时所传的flag不同而已。除非被阻塞否则他执行的速度非常快，而且不对获得空间清零。
- get_free_page()申请的内存是一整页，一页的大小一般是128K。
- kzalloc 先是用 kmalloc() 申请空间 , 然后用 memset() 清零来初始化 ,所有申请的元素都被初始化为 0.
- vmalloc用于申请较大的内存空间，虚拟内存是连续，但是在物理上它们不要求连续。
- malloc 用于用户空间申请内存。除非被阻塞否则他执行的速度非常快，而且不对获得空间清零。
- https://yangrz.github.io/blog/2017/12/20/ptmalloc/

### 虚拟内存

 Linux进程在虚拟内存中的标准内存段布局如下图所示：

![img](https://tva1.sinaimg.cn/large/e6c9d24ely1go5sdjlsdfj20tl0h240f.jpg)

###MMU总结

内存管理单元MMU（memory management unit）的主要功能是虚拟地址（virtual memory addresses）到物理地址（physical addresses）的转换。除此之外，它还可以实现内存保护（memory protection）、缓存控制（cache control）、总线仲裁（bus arbitration）以及存储体切换（bank switching）。

####工作机制

![MMU功能图](https://tva1.sinaimg.cn/large/007S8ZIlly1ggprszkozyj30ob06gt98.jpg)

CPU将要请求的虚拟地址传给MMU，然后MMU先在高速缓存TLB（Translation Lookaside Buffer）查找转换关系，如果找到了相应的物理地址则直接访问；如果找不到则在地址转换表（Translation Table）里查找计算。

####虚拟地址

现代的内存管理单元是以页的方式来分区虚拟地址空间（the range of addresses used by the processor）的。页的大小是2的n次方，通常为几KB。所以虚拟地址就被分为了两个部分：**virtual page number和offset**。

![Page Lookups](https://tva1.sinaimg.cn/large/007S8ZIlly1ggogmm0j7nj30nn0d9mz1.jpg)

####页表项（page table entry）

上面从虚拟页号在页表里找到的存放物理页表号的条目就是页表项（PTE）。PTE一般占1个字长，里面不仅包含了physical page number，还包含了重写标志位（dirty bit）、访问控制位（accessed bit）、允许读写的进程类型（user/supervisor mode）、是否可以被cached以及映射类型（PTE最后两位）。

####映射

- 映射方式

  ```
  映射方式有两种，段映射和页映射。段映射只用到一级页表，页映射用到一级页表和二级页表。
  ```

- 映射粒度

  ```
  段映射的映射粒度有两种，1M section和16M supersection；页映射的映射粒度有4K small page、64K large page和过时的1K tiny page。
  ```

![映射方式图解](https://tva1.sinaimg.cn/large/007S8ZIlly1ggogmg2xjxj30fi0i2wgx.jpg)

### Linux内核基础概念（内存部分）

Linux内核的主要作用：

- 进程管理
- 时间管理和定时器
- 系统调用接口
- 内存寻址
- 内存管理和页缓存
- VFS
- 抢占式内核
- 块I/O层
- I/O调度程序

![image-20200715202442877](https://tva1.sinaimg.cn/large/007S8ZIlly1ggrxfdc4t8j30oa178mzv.jpg)

#### 内存相关的核心概念

结论：物理内存一定要映射才能被访问，CPU不认物理内存，CPU只认线性地址（虚拟内存），访问物理内存是MMU的事情。理论上可以一次性把所有的物理内存都提前映射好，但是考虑到页表也要占用空间且大部分内存都不会被访问到（即大部分页表项都是白建了），所以随用随建才是比较合理的。《深入理解linux内核》中的核心。

**关于高端内存**:

结论：首先物理内存一定要映射才能被访问，所以32位的OS虚拟内存只有1G，这样肯定是没有办法全部访问到全部的物理内存。假如现在要访问896M映射之外的物理内存，怎么处理呢？先去伙伴系统申请一块内存，然后呢，这时因为不是固定映射，页表中没有映射关系，所以还要再申请一个线性地址（有可能失败），然后建立映射关系（设置页表），最后将申请到的线性地址给CPU使用。内核线性地址空间不足以容纳所有的物理地址空间（1G的内核线性地址空间和最多可达4G的物理地址空间），所以才需要预留一部分（128M）的线性地址空间来动态的映射所有的物理地址空间，于是就产生了所谓的高端内存映射。

![image-20200715214801059](https://tva1.sinaimg.cn/large/007S8ZIlly1ggrzu2jd5lj311d0u00y3.jpg)

![image-20200715204200272](https://tva1.sinaimg.cn/large/007S8ZIlly1ggrxxdp6fcj318q0rgdor.jpg)

**页**：处理器处理数据的基本单位是字。而内核把页作为内存管理的基本单位。

![image-20200715210408071](https://tva1.sinaimg.cn/large/007S8ZIlly1ggryke23mfj30vd0u016b.jpg)

page结构体描述的是物理页而非逻辑页，描述的是内存页的信息而不是页中数据。若一个struct page占用40字节内存，一个页有8KB，内存大小为4G的话，共有524288个页面，需要刚好20MB的大小来存放结构体。

**区**：在Linux的内核中，有些区给专门的硬件使用的，比如DMA设备。区是一种逻辑上的分组的概念，而没有物理上的意义。ZONE_DMA区中的页用来进行DMA时使用。ZONE_HIGHMEM是高端内存，其中的页不能永久的映射到内核地址空间，也就是说，没有虚拟地址。剩余的内存就属于ZONE_NORMAL区。

![image-20200715212249828](https://tva1.sinaimg.cn/large/007S8ZIlly1ggrz3un17bj30xu0u0148.jpg)

内核空间映射：

宏PAGE_OFFSET ＝0xC0000000(32 bit) 这是进程在线性地址空间的偏移量,也是内核线性空间的开始之处。

![image-20200715213005837](https://tva1.sinaimg.cn/large/007S8ZIlly1ggrzbf2o1mj30y40loaje.jpg)

```cpp
（内核0-896M）PA(Physical Address)=VA(virtual address)-PAGE_OFFSET
```

![image-20200715213123089](https://tva1.sinaimg.cn/large/007S8ZIlly1ggrzcqvzk9j30x90u0tgu.jpg)

**gfp_mask**：内存分配掩码  (get free page)

![image-20200715215516308](https://tva1.sinaimg.cn/large/007S8ZIlly1ggs01ly4b2j30ww06et9u.jpg)

![image-20200715215532864](https://tva1.sinaimg.cn/large/007S8ZIlly1ggs01vy55cj317n0u0aj4.jpg)

gfp_mask可以分成三种: 行为修饰符（action modifier）,区修饰符 （zone modifier）和类型（ type）.

- 行为修饰符是用来指定内核该如何分配内存的。比如分配内存时是否可以进行磁盘io，是否可以进行文件系统操作，内核是否可以睡眠（sleep）等等。

- 区修饰符指定内存需要从哪个区来分配。
- 类型是行为修饰符和区修饰符结合之后的产物。在一些特定的内存分配场合下，我们可能需要同时指定多个行为修饰符和区修饰符，而type就是针对这些固定的场合，将所需要的行为修饰符和区修饰符都整合到了一起，这样使用者只要指定一个type就可以了。

#### Linux内核 OOM Killer机制分析

~~~c

//
// Created by Jesson on 2020/7/15.
//


#include <cstdlib>
#include <strings.h>
#include <cstdio>
#include <unistd.h>

#define block (1024L*1024L*MB)
#define MB 512L
unsigned long total = 0L;
int main(){
    for(;;) {
        char* mm = (char*) malloc(block);
        usleep(100000);
        if (NULL == mm)
            continue;
        bzero(mm,block);
        total += MB;
        fprintf(stdout,"alloc %lum mem\n",total);
    }
}


~~~

![image-20200715235149963](https://tva1.sinaimg.cn/large/007S8ZIlly1ggs3evv2u4j316m0f17cq.jpg)

sudo egrep -i -r 'killed process' /var/log

#### 内核内存分配经典2种方式

- malloc

malloc的底层是通过brk来实现的。brk是syscall。

![image-20200717000829642](https://tva1.sinaimg.cn/large/007S8ZIlly1ggt9ijamlbj31920u0anf.jpg)

![image-20200717001534017](https://tva1.sinaimg.cn/large/007S8ZIlly1ggt9pw52wzj30pz0j9trc.jpg)

![image-20200717005157108](https://tva1.sinaimg.cn/large/007S8ZIlly1ggtarr6o5dj30u00va47t.jpg)

~~~c
//
// Created by Jesson on 2020/7/18.
//

 #include <stdio.h>
 #include <unistd.h>
 #include <stdlib.h>
 #include <string.h>

 int main()
 {
         void  *p=sbrk(0);
         brk(p+5);
         strcpy((char*)p,"asdf111");


         printf(" %#x\n",&*(char *)(p+3));//输出f的地址
         printf(" %#x\n",&*(char *)(p+2));//输出d的地址
         void *q=sbrk(2);
         void *m=sbrk(1);
         void *n=sbrk(1);
         printf(" %#x\n",&*(char *)(q));//输出q的地址

         printf(" %#x\n",&*(char *)(m));//输出m的地址
         printf(" %#x\n",&*(char *)(n));//输出n的地址
         printf("%s\n",(char *)(p));


        // brk(p); //release
        // printf(" %#x\n",&*(char *)(p));
        brk(p-2);
      printf(" %#x\n",&*(char *)(p));
         return 0;
 }

~~~

结论：堆中的地址是由低地址往高地址存放的，通过上面的代码我们可以知道所谓的堆区和BSS段是人为区分的，其实它们是连在一起的，开辟空间不复制就是BSS段，复制后就是堆区。

- mmap

![image-20200718132234933](https://tva1.sinaimg.cn/large/007S8ZIlly1ggv2343b30j324o0u048w.jpg)

**优势**

- 读写文件避免了 `read()` 和 `write()` 系统调用，也避免了数据的拷贝。
- 除了潜在的页错误，读写 map 后的文件不引起系统调用或者上下文切换。就像访问内存一样简单。
- 多个进程 map 同一个对象，可以共享数据。
- 可以直接使用指针来跳转到文件某个位置，不必使用 `lseek()` 系统调用。

**劣势**

- 内存浪费。由于必须要使用整数页的内存。
- 导致难以找到连续的内存区域
- 创建和维护映射和相关的数据结构的额外开销。在大文件和频繁访问的文件中，这个开销相比 read write 的 copy 开销小。

![image-20200718132314663](https://tva1.sinaimg.cn/large/007S8ZIlly1ggv23rgyhrj31340tktce.jpg)

```cpp
#include <sys/mman.h>

void * mmap (void *addr, //没有特别需求一般设为0。这个函数会返回一个实际 map 的地址。
             size_t len,
             int prot, //PROT_NONE PROT_READ PROT_WRITE PROT_EXEC
             int flags, //MAP_PRIVATE MAP_SHARED MAP_FIXED
             int fd, //文件描述符
             off_t offset); //文件偏移，从文件起始算起。
```

经典代码解析：

![image-20200718132810641](https://tva1.sinaimg.cn/large/007S8ZIlly1ggv28wwfmfj31dy0tcte8.jpg)

https://github.com/Meituan-Dianping/Logan/blob/master/Logan/Clogan/mmap_util.c

![image-20200718133237824](https://tva1.sinaimg.cn/large/007S8ZIlly1ggv2dj3mdfj31fs0twwje.jpg)

https://github.com/Tencent/MMKV/blob/ed194ddef9611cecad371e8bd32777e699a4c95c/Core/MemoryFile.cpp

![image-20200718134202819](https://tva1.sinaimg.cn/large/007S8ZIlly1ggv2nbjj00j318c0cwmyw.jpg)

注意细节：

- 使用mmap需要注意的一个关键点是，mmap映射区域大小必须是物理页大小(page_size)的整倍数（32位系统中通常是4k字节）。原因是，内存的最小粒度是页，而进程虚拟地址空间和内存的映射也是以页为单位。为了匹配内存的操作，mmap从磁盘到虚拟地址空间的映射也必须是页。
- 内核可以跟踪被内存映射的底层对象（文件）的大小，进程可以合法的访问在当前文件大小以内又在内存映射区以内的那些字节。也就是说，如果文件的大小一直在扩张，只要在映射区域范围内的数据，进程都可以合法得到，这和映射建立时文件的大小无关。
- 映射建立之后，即使文件关闭，映射依然存在。因为映射的是磁盘的地址，不是文件本身，和文件句柄无关。同时可用于进程间通信的有效地址空间不完全受限于被映射文件的大小，因为是按页映射。

 问题1:**一个文件的大小是5000字节，mmap函数从一个文件的起始位置开始，映射5000字节到虚拟内存中。**

因为单位物理页面的大小是4096字节，虽然被映射的文件只有5000字节，但是对应到进程虚拟地址区域的大小需要满足整页大小，因此mmap函数执行后，实际映射到虚拟内存区域8192个 字节，5000~8191的字节部分用零填充。

![image-20200718135103878](https://tva1.sinaimg.cn/large/007S8ZIlly1ggv2ws02i6j30u40gc40w.jpg)

问题2:**一个文件的大小是5000字节，mmap函数从一个文件的起始位置开始，映射15000字节到虚拟内存中，即映射大小超过了原始文件的大小。**

由于文件的大小是5000字节，和情形一一样，其对应的两个物理页。那么这两个物理页都是合法可以读写的，只是超出5000的部分不会体现在原文件中。由于程序要求映射15000字节，而文件只占两个物理页，因此8192字节~15000字节都不能读写，操作时会返回异常。

![image-20200718135233952](https://tva1.sinaimg.cn/large/007S8ZIlly1ggv2y9uimoj31120ck76w.jpg)

**内核相关源码**：

![image-20200718144410028](https://tva1.sinaimg.cn/large/007S8ZIlly1ggv4fyym6aj314v0u0k8d.jpg)

作业：大家写一个demo 测试下mmap。（maps、学会使用这个api）

### 进程通信

需要解决什么问题？为什么需要IPC。

- 管道（Pipe）及有名管道（named pipe）：管道可用于具有亲缘关系进程间的通信，有名管道克服了管道没有名字的限制，因此，除具有管道所具有的功能外，它还允许无亲缘关系进程间的通信；

- 信号（Signal）：信号是比较复杂的通信方式，用于通知接受进程有某种事件发生，除了用于进程间通信外，进程还可以发送信号给进程本身；linux除了支持Unix早期信号语义函数signal外，还支持语义符合Posix.1标准的信号函数sigaction（实际上，该函数是基于BSD的，BSD为了实现可靠信号机制，又能够统一对外接口，用sigaction函数重新实现了signal函数）；

- 报文（Message）队列（消息队列）：消息队列是消息的链接表，包括Posix消息队列system V消息队列。有足够权限的进程可以向队列中添加消息，被赋予读权限的进程则可以读走队列中的消息。消息队列克服了信号承载信息量少，管道只能承载无格式字节流以及缓冲区大小受限等缺点。

- 共享内存：使得多个进程可以访问同一块内存空间，是最快的可用IPC形式。是针对其他通信机制运行效率较低而设计的。往往与其它通信机制，如信号量结合使用，来达到进程间的同步及互斥。

- 信号量（semaphore）：主要作为进程间以及同一进程不同线程之间的同步手段。

- 套接口（Socket）：更为一般的进程间通信机制，可用于不同机器之间的进程间通信。起初是由Unix系统的BSD分支开发出来的，但现在一般可以移植到其它类Unix系统上：Linux和System V的变种都支持套接字。

####管道

在Linux中最为常见的就是Shell的操作，比如ps ｜ grep test ，键盘敲入ps命令，ps执行后的输出作为grep的输入，执行后输出到终端屏幕。管道分为有名管道和无名管道，无名管道主要适用于有亲缘关系的进程间的数据交互，但是借助FIFO可以构建没有亲缘关系的进程之间进行数据传递。

特点：

- 管道是半双工的，数据只能向一个方向流动；需要双方通信时，需要建立起两个管道；

- 匿名管道只能用于父子进程或者兄弟进程之间（具有亲缘关系的进程）；

- 单独构成一种独立的文件系统：管道对于管道两端的进程而言，就是一个文件，但它不是普通的文件，它不属于某种文件系统，而是自立门户，单独构成一种文件系统，并且只存在与内存中。

重点：一个管道实际上就是个只存在于内存中的特殊文件。对于有名管道虽然FIFO文件的inode节点在磁盘上，但是仅是一个节点而已，文件的数据还是存在于内存缓冲页面中，和普通管道相同。

~~~c
//
// Created by Jesson on 2020/7/20.
//
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define MAX_LINE 1024
/**
 * 测试基本的pipe用法
 * @return 
 */
int main(void)
    {
        int n;
        int fd[2];
        pid_t pid;
        char line[MAX_LINE];

        if(pipe(fd)<0){                 /* 先建立管道得到一对文件描述符 */
            exit(0);
        }

        if((pid = fork())<0)            /* 父进程把文件描述符复制给子进程 */
        exit(1);
        else if(pid > 0){                /* 父进程写 */
            close(fd[0]);             /* 关闭读描述符 */
            char data[] = "Hello World!!";
            write(fd[1], data, sizeof(data));
        }
        else{                            /* 子进程读 */
            close(fd[1]);                /* 关闭写端 */
            n = read(fd[0], line, MAX_LINE);
            printf("%s",line);
            fflush(stdout);
        }
        while (1){}
}
~~~

**Linux中关于pipe的核心原理**:

![image-20200720171304714](https://tva1.sinaimg.cn/large/007S8ZIlly1ggxjzjwuzvj310n0u0thi.jpg)

总结：

- 命名管道是创建在文件系统上的。匿名管道也是创建在文件系统上的，只不过是一种特殊的文件系统，创建一个特殊的文件，对应一个特殊的inode，就是内核代码的get_pipe_inode（）。
- 所谓的匿名管道，其实就是内核里面的一串缓存。

~~~c
/**
 *	struct pipe_buffer - a linux kernel pipe buffer
 *	@page: the page containing the data for the pipe buffer
 *	@offset: offset of data inside the @page
 *	@len: length of data inside the @page
 *	@ops: operations associated with this buffer. See @pipe_buf_operations.
 *	@flags: pipe buffer flags. See above.
 *	@private: private data owned by the ops.
 **/
struct pipe_buffer {
	struct page *page;
	unsigned int offset, len;
	const struct pipe_buf_operations *ops;
	unsigned int flags;
	unsigned long private;
};
~~~

![image-20200720173513166](https://tva1.sinaimg.cn/large/007S8ZIlly1ggxkmkavv7j30y30u0qf8.jpg)

####信号量/灯

总结：

- 信号量它不以传送数据为主要目的，它主要是用来保护共享资源（信号量也属于临界资源），使得资源在一个时刻只有一个进程独享。
- 在信号量进行PV操作时都为原子操作（单个指令操作层面），往往我们提到PV的时候就是为了保护共享资源。
- POSIX 信号量与SYSTEM V信号量的比较是重点，对POSIX来说，信号量是个非负整数。常用于线程间同步。而SYSTEM V信号量则是一个或多个信号量的集合，它对应的是一个信号量结构体，这个结构体是为SYSTEM V IPC服务的，信号量只不过是它的一部分。常用于进程间同步。
- POSIX信号量的引用头文件是`<semaphore.h>`，而SYSTEM V信号量的引用头文件是`<sys/sem.h>`。
- 内核中的共享内存，消息队列和信号量的实现机制几乎是相同的，信号量也是开辟一片内存，然后对链表进行操作。

~~~c
//sys/sem.h
/* One sem_array data structure for each set of semaphores in the system. */
struct sem_array {
	struct kern_ipc_perm	____cacheline_aligned_in_smp
				sem_perm;	/* permissions .. see ipc.h */
	time_t			sem_ctime;	/* last change time */
	struct sem		*sem_base;	/* ptr to first semaphore in array */
	struct list_head	pending_alter;	/* pending operations */
						/* that alter the array */
	struct list_head	pending_const;	/* pending complex operations */
						/* that do not alter semvals */
	struct list_head	list_id;	/* undo requests on this array */
	int			sem_nsems;	/* no. of semaphores in array */
	int			complex_count;	/* pending complex operations */
	bool		complex_mode;	/* no parallel simple ops */
};
~~~

![image-20200720175902016](https://tva1.sinaimg.cn/large/007S8ZIlly1ggxlbcbsvaj315g0u0thc.jpg)

注意：newary函数是在底层开内存。

![image-20200720180846027](https://tva1.sinaimg.cn/large/007S8ZIlly1ggxllguivcj31fc0t0dm3.jpg)

~~~c
/* semop system calls takes an array of these. */
struct sembuf {
	unsigned short  sem_num;	/* semaphore index in array */
	short		sem_op;		/* semaphore operation */
	short		sem_flg;	/* operation flags */ //通常为SEM_UNDO,使操作系统跟踪信号量， 
                  //并在进程没有释放该信号量而终止时，操作系统释放信号量 
};
~~~

代码实践：看代码。

在来看JDK的实现：

https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Semaphore.html

![image-20200720184928492](https://tva1.sinaimg.cn/large/007S8ZIlly1ggxmrti6g7j31n10u013q.jpg)

在Android的中的使用：SecurityLogMonitor 参看下源码。等等。

#### 信号

对于 Linux来说，实际信号是软中断，许多重要的程序都需要处理信号。信号，为 Linux 提供了一种处理异步事件的方法。通过kill-l可以查看所有的信号操作。

![image-20200720190024446](https://tva1.sinaimg.cn/large/007S8ZIlly1ggxn37700yj30k708cdhj.jpg)

信号的操作主要是自定义信号，来操作用户自定义的业务。

~~~c
//
// Created by Jesson on 2020/7/20.
//

#include<signal.h>
#include<stdio.h>
#include <unistd.h>

void handler(int signum)
{
    if(signum == SIGIO)
        printf("SIGIO   signal: %d\n", signum);
    else if(signum == SIGUSR1)
        printf("SIGUSR1   signal: %d\n", signum);
    else
        printf("error\n");
}

int main(void)
{
    signal(SIGIO, handler);
    signal(SIGUSR2, handler);
    printf("%d  %d\n", SIGIO, SIGUSR1);
    for(;;)
    {
        sleep(10000);
    }
    return 0;
}
~~~

我们在学习的过程中主要是看一看源码中的用法，在NDK中可以触发捕获系统的信号。在底层中，so的崩溃都是通过Singnal来触发的，所以我们一定要搞清楚这个基础的原理。信号其实是一种软件层面的中断机制，当程序出现错误，比如除零、非法内存访问时，便会产生信号事件。那么进程如何获知并响应该事件呢？Linux的进程是由内核管理的，内核会接收信号，并将其放入到相应的进程信号队列里面。当进程由于系统调用、中断或异常而进入内核态以后，从内核态回到用户态之前会检测信号队列，并查找到相应的信号处理函数。![image-20200720192047713](https://tva1.sinaimg.cn/large/007S8ZIlly1ggxnofbv34j30z20hedks.jpg)

~~~c
const int handledSignals[] = {
    SIGSEGV, SIGABRT, SIGFPE, SIGILL, SIGBUS
};
const int handledSignalsNum = sizeof(handledSignals) / sizeof(handledSignals[0]);
struct sigaction old_handlers[handledSignalsNum];

int nativeCrashHandler_onLoad(JNIEnv *env) {
    struct sigaction handler;
    memset(&handler, 0, sizeof(sigaction));
    handler.sa_sigaction = my_sigaction;
    handler.sa_flags = SA_RESETHAND;

    for (int i = 0; i < handledSignalsNum; ++i) {
        sigaction(handledSignals[i], &handler, &old_handlers[i]);
    }

    return 1;
}

notifyNativeCrash  = (*env)->GetMethodID(env, cls,  "notifyNativeCrash", "()V");
void my_sigaction(int signal, siginfo_t *info, void *reserved) {
    // Here catch the native crash
}
~~~

####共享内存

注意：

- 共享内存并未提供同步机制，我们通常需要用其他的机制来同步对共享内存的访问，例如信号量。
- 不同进程之间共享的内存通常为同一段物理内存。进程可以将同一段物理内存连接到他们自己的地址空间中，所有的进程都可以访问共享内存中的地址。

**MemoryFile**：

http://androidxref.com/9.0.0_r3/xref/frameworks/base/core/jni/android_os_SharedMemory.cpp

![image-20200720194100285](https://tva1.sinaimg.cn/large/007S8ZIlly1ggxo9fgnx3j31880i442t.jpg)

核心：（Ashmem创建三个步骤）

- open /dev/ashmem驱动连通ashmem驱动
- ioctl 发送ASHMEM_SET_NAME命令为该ashmem创建名字
- ioctl通过ASHMEM_SET_SIZE命令设置匿名共享内存的大小

![image-20200720194646072](https://tva1.sinaimg.cn/large/007S8ZIlly1ggxoffulsyj31ki0ps7at.jpg)

核心：

- mmap 做内存映射。
- 业务层对该文件描述符进行读写即可。

~~~c
int fd = open("/dev/ashmem",O_RDWR);
ioctl(fd, ASHMEM_SET_NAME,name);
ioctl(fd,ASHMEM_SET_SIZE, size);
//addr为共享内存地址
addr = (char*)mmap(NULL, size , PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
~~~

看源码吧 骚年。

####消息队列

消息队列是消息的链接表，包括 Posix 消息队列和 System V 消息队列。消息队列将消息看作一个记录，具有特定的格式以及特定的优先级，对消息队列有写权限的进程可以向中按照一定的规则添加新消息；对消息队列有读权限的进程则可以从消息队列中读取消息。

![image-20200725020048452](https://tva1.sinaimg.cn/large/007S8ZIlly1gh2lpukm1pj30zf0u0n65.jpg)

- msgget 函数
  该函数用于获得一个消息队列，内核从指定 IPC_namespace 对应的 IPC_ids 获取相应键值的消息队列，如果没有的话就会新建一个队列。

- msgsnd 函数
  该函数用于向指定消息队列中发送信息，该函数会将消息插入到链表尾部。函数还会将信息发送到队列关联的 sleep 接收者。

- msgrcv 函数
  该函数用于从指定消息队列中接收信息，如果队列中没有消息，就会将进程加入到休眠进程列表中，等待队列中有新的消息加入。

源码中涉及到的文件：

- ./ipc/msg.c
- ./ipc/msgutil.c
- ./ipc/mqueue.c

~~~c++
//
// Created by Jesson on 2020/7/23.
//

#include<sys/types.h>

#include<sys/ipc.h>
#include<sys/msg.h>
#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<string.h>

#define BUFFER_SIZE 512

struct message{
    long msg_type;
    char msg_text[BUFFER_SIZE];
};

int main(){
    int qid;
    key_t key;
    struct message msg;
    //ftok根据不同路径和关键字产生标准的KEY
    if((key = ftok(".", 'a')) == -1){
        perror("ftok");
        exit(1);
    }
    //create
    if((qid = msgget(key, IPC_CREAT|0666)) == -1){
        perror("msgget");
        exit(1);
    }
    printf("open queue %d\n", qid);
    while(1){
        printf("enter some message to the queue:");
        if((fgets(msg.msg_text, BUFFER_SIZE, stdin)) == NULL){
            puts("no message");
            exit(1);
        }
        msg.msg_type = getpid();//消息类型为进程号
        if((msgsnd(qid, &msg, strlen(msg.msg_text), 0)) < 0)//发送消息
            {
            perror("message posted");
            exit(1);
            }
        if(strncmp(msg.msg_text, "quit", 4) == 0)
        {
            break;
        }
    }
    exit(0);
}
~~~

### 内核中进程&线程总结

内核经常需要在后台执行一些操作，这种任务就可以通过内核线程（kernle thread）完成。在内核中线程是独立运行在内核空间的标准进程。内核线程和普通的进程间的区别在于内核线程没有**独立的地址空间**，**mm指针被设置为NULL**；它只在内核空间运行，从来不切换到用户空间去；并且和普通进程一样，可以被调度，也可以被抢占。

/init/main.c

~~~c
static noinline void __init_refok rest_init(void)
{
	int pid;

	rcu_scheduler_starting();
	/*
	 * We need to spawn init first so that it obtains pid 1, however
	 * the init task will end up wanting to create kthreads, which, if
	 * we schedule it before we create kthreadd, will OOPS.
	 */
	kernel_thread(kernel_init, NULL, CLONE_FS | CLONE_SIGHAND);     //创建1号进程
	numa_default_policy();
	pid = kernel_thread(kthreadd, NULL, CLONE_FS | CLONE_FILES);     //创建2号进程
	rcu_read_lock();
	kthreadd_task = find_task_by_pid_ns(pid, &init_pid_ns);
	rcu_read_unlock();
	complete(&kthreadd_done);

	/*
	 * The boot idle thread must execute schedule()
	 * at least once to get things moving:
	 */
	init_idle_bootup_task(current);
	schedule_preempt_disabled();
	/* Call into cpu_idle with preempt disabled */
	cpu_idle();										//空闲进程
}
~~~

![image-20200725024936148](https://tva1.sinaimg.cn/large/007S8ZIlly1gh2n4mn45pj31810u0wme.jpg)

在底层最终是调用了_do_fork来完成进程的创建。

#### PCB

每个进程运行的时候都会拿到4G的虚拟内存。其中3G是交给用户的,然后剩下的1G内存存储内核的东西。PCB其实就存储在1G的内核系统空间里面。
它其实就是一个task_struct结构体，里面存储着进程的所有信息。

task_struct:

~~~c
struct task_struct {
volatile long state;  //说明了该进程是否可以执行,还是可中断等信息
unsigned long flags;  //Flage 是进程号,在调用fork()时给出
intsigpending;   //进程上是否有待处理的信号
mm_segment_taddr_limit; //进程地址空间,区分内核进程与普通进程在内存存放的位置不同
                       //0-0xBFFFFFFF foruser-thead
                       //0-0xFFFFFFFF forkernel-thread
//调度标志,表示该进程是否需要重新调度,若非0,则当从内核态返回到用户态,会发生调度
volatilelong need_resched;
int lock_depth;  //锁深度
longnice;       //进程的基本时间片
//进程的调度策略,有三种,实时进程:SCHED_FIFO,SCHED_RR,分时进程:SCHED_OTHER
unsigned long policy;
struct mm_struct *mm; //进程内存管理信息
int processor;
//若进程不在任何CPU上运行, cpus_runnable 的值是0，否则是1这个值在运行队列被锁时更新
unsigned long cpus_runnable, cpus_allowed;
struct list_head run_list; //指向运行队列的指针
unsigned longsleep_time;  //进程的睡眠时间
//用于将系统中所有的进程连成一个双向循环链表,其根是init_task
struct task_struct *next_task, *prev_task;
struct mm_struct *active_mm;
struct list_headlocal_pages;       //指向本地页面      
unsigned int allocation_order, nr_local_pages;
struct linux_binfmt *binfmt;  //进程所运行的可执行文件的格式
int exit_code, exit_signal;
intpdeath_signal;    //父进程终止是向子进程发送的信号
unsigned longpersonality;
//Linux可以运行由其他UNIX操作系统生成的符合iBCS2标准的程序
intdid_exec:1; 
pid_tpid;    //进程标识符,用来代表一个进程
pid_tpgrp;   //进程组标识,表示进程所属的进程组
pid_t tty_old_pgrp;  //进程控制终端所在的组标识
pid_tsession;  //进程的会话标识
pid_t tgid;
intleader;     //表示进程是否为会话主管
struct task_struct*p_opptr,*p_pptr,*p_cptr,*p_ysptr,*p_osptr;
struct list_head thread_group;  //线程链表
struct task_struct*pidhash_next; //用于将进程链入HASH表
struct task_struct**pidhash_pprev;
wait_queue_head_t wait_chldexit;  //供wait4()使用
struct completion*vfork_done;  //供vfork()使用
unsigned long rt_priority; //实时优先级，用它计算实时进程调度时的weight值
 
//it_real_value，it_real_incr用于REAL定时器，单位为jiffies,系统根据it_real_value
//设置定时器的第一个终止时间.在定时器到期时，向进程发送SIGALRM信号，同时根据
//it_real_incr重置终止时间，it_prof_value，it_prof_incr用于Profile定时器，单位为jiffies。
//当进程运行时，不管在何种状态下，每个tick都使it_prof_value值减一，当减到0时，向进程发送
//信号SIGPROF，并根据it_prof_incr重置时间.
//it_virt_value，it_virt_value用于Virtual定时器，单位为jiffies。当进程运行时，不管在何种
//状态下，每个tick都使it_virt_value值减一当减到0时，向进程发送信号SIGVTALRM，根据
//it_virt_incr重置初值。
unsigned long it_real_value, it_prof_value, it_virt_value;
unsigned long it_real_incr, it_prof_incr, it_virt_value;
struct timer_listreal_timer;   //指向实时定时器的指针
struct tmstimes;     //记录进程消耗的时间
unsigned longstart_time;  //进程创建的时间
//记录进程在每个CPU上所消耗的用户态时间和核心态时间
longper_cpu_utime[NR_CPUS],per_cpu_stime[NR_CPUS]; 
//内存缺页和交换信息:
//min_flt, maj_flt累计进程的次缺页数（Copyon　Write页和匿名页）和主缺页数（从映射文件或交换
//设备读入的页面数）；nswap记录进程累计换出的页面数，即写到交换设备上的页面数。
//cmin_flt, cmaj_flt,cnswap记录本进程为祖先的所有子孙进程的累计次缺页数，主缺页数和换出页面数。
//在父进程回收终止的子进程时，父进程会将子进程的这些信息累计到自己结构的这些域中
unsignedlong min_flt, maj_flt, nswap, cmin_flt, cmaj_flt, cnswap;
int swappable:1; //表示进程的虚拟地址空间是否允许换出
//进程认证信息
//uid,gid为运行该进程的用户的用户标识符和组标识符，通常是进程创建者的uid，gid
//euid，egid为有效uid,gid
//fsuid，fsgid为文件系统uid,gid，这两个ID号通常与有效uid,gid相等，在检查对于文件
//系统的访问权限时使用他们。
//suid，sgid为备份uid,gid
uid_t uid,euid,suid,fsuid;
gid_t gid,egid,sgid,fsgid;
int ngroups; //记录进程在多少个用户组中
gid_t groups[NGROUPS]; //记录进程所在的组
//进程的权能，分别是有效位集合，继承位集合，允许位集合
kernel_cap_tcap_effective, cap_inheritable, cap_permitted;
int keep_capabilities:1;
struct user_struct *user;
struct rlimit rlim[RLIM_NLIMITS];  //与进程相关的资源限制信息
unsigned shortused_math;   //是否使用FPU
charcomm[16];   //进程正在运行的可执行文件名
 //文件系统信息
int link_count, total_link_count;
//NULL if no tty进程所在的控制终端，如果不需要控制终端，则该指针为空
struct tty_struct*tty;
unsigned int locks;
//进程间通信信息
struct sem_undo*semundo;  //进程在信号灯上的所有undo操作
struct sem_queue *semsleeping; //当进程因为信号灯操作而挂起时，他在该队列中记录等待的操作
//进程的CPU状态，切换时，要保存到停止进程的task_struct中
structthread_struct thread;
  //文件系统信息
struct fs_struct *fs;
  //打开文件信息
struct files_struct *files;
  //信号处理函数
spinlock_t sigmask_lock;
struct signal_struct *sig; //信号处理函数
sigset_t blocked;  //进程当前要阻塞的信号，每个信号对应一位
struct sigpendingpending;  //进程上是否有待处理的信号
unsigned long sas_ss_sp;
size_t sas_ss_size;
int (*notifier)(void *priv);
void *notifier_data;
sigset_t *notifier_mask;
u32 parent_exec_id;
u32 self_exec_id;
 
spinlock_t alloc_lock;
void *journal_info;
};
~~~

重点：

- unsigned long policy 表示进程调度策略，其值为下列三种情况之一：
  SCHED_OTHER(值为0)对应普通进程优先级轮转法(round robin)
  SCHED_FIFO(值为1)对应实时进程先来先服务算法；
  SCHED_RR(值为2)对应实时进程优先级轮转法
- volatile long state 标识进程的状态，可为下列六种状态之一：
  可运行状态(TASK-RUNING);
  可中断阻塞状态(TASK-UBERRUPTIBLE)
  不可中断阻塞状态(TASK-UNINTERRUPTIBLE)
  僵死状态(TASK-ZOMBLE)
  暂停态(TASK_STOPPED)
  交换态(TASK_SWAPPING)
- long prority表示进程的优先级
  unsigned long rt_prority 表示实时进程的优先级，对于普通进程无效

#### 关于进程调度

调度器必须在各个进程之间尽可能公平地共享CPU时间, 而同时又要考虑不同的任务优先级。调度器的一个重要目标是有效地分配 CPU 时间片，同时提供很好的用户体验。

调度器的目标主要有以下：

- 进程响应时间尽可能快
- 后台作业的吞吐量尽可能高
- 尽可能避免进程的饥饿现象
- 调和低优先级和高优先级进程

Linux的调度基于分时(time sharing)技术: 多个进程以”时间多路复用”方式运行, 因为CPU的时间被分成”片(slice)”, 给每个可运行进程分配一片CPU时间片, 当然单处理器在任何给定的时刻只能运行一个进程。

**进程的分类：**

| 类别                  | 描述                                               |
| --------------------- | -------------------------------------------------- |
| I/O受限型(I/O密集型)  | 频繁的使用I/O设备, 并花费很多时间等待I/O操作的完成 |
| CPU受限型(计算密集型) | 需要占用大量CPU时间进行数值计算                    |

其他的学术分类：
交互式进程：此类进程经常与用户进行交互,等待用户的唤醒， 因此需要花费很多时间等待用户操作. 当接受了用户的输入后, 进程必须很快被唤醒, 如果唤醒太慢用户会感觉系统反应迟钝。比如移动鼠标和输入文字。
批处理进程：此类进程不必与用户交互，因此经常在后台运行。 因为这样的进程不必很快相应，因此常受到调度程序的慢待。比如编译程序等。
实时进程：此类进程由很强的调度需要, 这样的进程不会被低优先级的进程阻塞。并且它们的响应时间要短，要及时。比如数据采集，机器人控制等。

**调度策略**：

http://pages.cs.wisc.edu/~remzi/OSTEP/cpu-sched.pdf

| 优先级范围 | 描述                                                         |
| :--------- | :----------------------------------------------------------- |
| 0——99      | 实时进程（RT priority）优先级范围                            |
| 100——139   | 非实时进程（非实时调度器SCHED_NORMAL和SCHED_BATCH）优先级范围 |

![image-20200725093757246](https://tva1.sinaimg.cn/large/007S8ZIlly1gh2yximk27j313w09sn05.jpg)

参考下模拟代码搞懂CPU的调度。

####Android 进程优先级的相关概念

![image-20200725100631298](https://tva1.sinaimg.cn/large/007S8ZIlly1gh2zr8kkkqj31jt0u0wt7.jpg)

![image-20200725100651583](https://tva1.sinaimg.cn/large/007S8ZIlly1gh2zrm2isij314a0kawrw.jpg)

### AQS

AQS 全称是 Abstract Queued Synchronizer。主要的作用是用于多线程同步功能的框架。这是顶级牛人Doug Lea 写的，这个人也是Linux内存分配算法的架构人。

![image-20200724234521825](https://tva1.sinaimg.cn/large/007S8ZIlly1gh2hsyk91fj30y70u07a4.jpg)

Sync 在 Semaphore 有两种实现：NonfairSync 和 FairSync，分别对应非公平锁和公平锁。

~~~java
static final class FairSync extends Sync {
        private static final long serialVersionUID = 2014338818796000944L;

        FairSync(int permits) {
            super(permits);
        }

        protected int tryAcquireShared(int acquires) {
            for (;;) {
                // 公平模式下将会先判断是否有线程在等待，在等待先排队
                if (hasQueuedPredecessors())
                    return -1;
                int available = getState();
                int remaining = available - acquires;
                if (remaining < 0 ||
                    compareAndSetState(available, remaining))
                    return remaining;
            }
        }
    }
~~~

我们在来看看Sync抽象类经典的实现。

~~~java
// 经典AQS实现版本
abstract static class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 1192457210091910933L;

        Sync(int permits) { // 资源初始化
            setState(permits);
        }

        final int getPermits() { // 获得当前资源数
            return getState();
        }

        // 非公平方式获取许可，为什么要写在抽象类中，因为所有的tryAcquire方法都会直接以非公平方式
        // 尝试获取许可，无论初始化时使sync是公平还是非公平的。
        final int nonfairTryAcquireShared(int acquires) {
            for (;;) {  // cas减许可
                int available = getState();
                int remaining = available - acquires;
                // 如果剩余的数量小于0(共享模式下判断tryAcquireShared是看返回的整数是否小于0，小于0代表获取失败)，代表获取许可失败，AQS中tryAcquire失败将会进队列等待。
                if (remaining < 0 ||
                    compareAndSetState(available, remaining))
                    return remaining;
            }
        }

        // cas释放许可，公平和非公平模式都一样
        protected final boolean tryReleaseShared(int releases) {
            for (;;) {
                int current = getState();
                int next = current + releases;
                if (next < current) // overflow
                    throw new Error("Maximum permit count exceeded");
                if (compareAndSetState(current, next)) // 没有条件判断，只要许可被加上去就返回true
                    return true;
            }
        }
        // 纯粹的cas减掉一部分许可
        final void reducePermits(int reductions) {
            for (;;) {
                int current = getState();
                int next = current - reductions;
                if (next > current) // underflow
                    throw new Error("Permit count underflow");
                if (compareAndSetState(current, next))
                    return;
            }
        }
        // 许可归零
        final int drainPermits() {
            for (;;) {
                int current = getState();
                if (current == 0 || compareAndSetState(current, 0))
                    return current;
            }
        }
    }
~~~

#### CAS

compareAndSetState：核心和重点方法。

libcore/libart/src/main/java/sun/misc/Unsafe.java：

art/runtime/native/sun_misc_Unsafe.cc:

~~~c++
static jboolean Unsafe_compareAndSwapInt(JNIEnv* env, jobject, jobject javaObj, jlong offset,
                                         jint expectedValue, jint newValue) {
  ScopedFastNativeObjectAccess soa(env);
  mirror::Object* obj = soa.Decode<mirror::Object*>(javaObj);
  // JNI must use non transactional mode.
  bool success = obj->CasFieldStrongSequentiallyConsistent32<false>(MemberOffset(offset),expectedValue, newValue);
  return success ? JNI_TRUE : JNI_FALSE;
}

//object.h：
  template<bool kTransactionActive,
           bool kCheckTransaction = true,
           VerifyObjectFlags kVerifyFlags = kDefaultVerifyFlags>
  ALWAYS_INLINE bool CasField32(MemberOffset field_offset,
                                int32_t old_value,
                                int32_t new_value,
                                CASMode mode,
                                std::memory_order memory_order)
      REQUIRES_SHARED(Locks::mutator_lock_);
~~~

这里我就不在追代码，我建议大家都去看一下，给出结论：

- CompareAndSet是通过C++Atomic类的compare_exchange_strong和compare_exchange_weak来实现的。
- 虚假失败并不经常发生，所以它没有大的表现。相反，容忍这样的故障允许在某些平台上更有效地实现`weak`版本（相比`strong`）：`strong`必须始终检查虚假故障并掩盖它。这很贵。也就是说weak支持在在字段值和期待值一样的时候却返回了false。
- Atomic的操作是支持原子性的，获取这些变量的值时，永远获得修改前的值或修改后的值，不会获得修改过程中的中间数值。那这样就可以实现多线程的无锁编程。
- CAS的实现过程主要有 3 个操作数：内存值 V，旧的预期值 E，要修改的新值 U，当且仅当预期值 E和内存值 V 相同时，才将内存值 V 修改为 U，否则什么都不做。

思考一个问题：在非并发条件下，要实现一个栈的Push操作，我们可能有如下操作：

- 新建一个节点
- 将该节点的next指针指向现有栈顶
- 更新栈顶    

在多线程的环境中我们可能会出现错误，我们利用Atomic来实现：

~~~c++

#include <atomic>
 
template<typename T>
class stack_lock_free
{
public:
    stack_lock_free():_head(nullptr)
    {
    }
 
    void push(const T& data)
    {
        node* new_node = new node(data);
        new_node->next = _head.load();
 				//若head==new_node->next则更新head为new_node,返回true结束循环，插入成功; 若head!=new_node->next表明有其它线程在此期间对head操作了，将new_node->next更新为新的head，返回false，继续进入下一次while循环。
        while(!_head.compare_exchange_weak(new_node->next, new_node));
    }
 
    bool pop(T& data)
    {
        auto result = _head.load();
        while( result != nullptr && !_head.compare_exchange_weak(result,result->next));
        if( !result )
        {
            return false;
        }
        data = result->data;
 
        delete result;
        return true;
    }
 
private:
    struct node
    {
        T data;
        node* next;
 
        node(const T& data) : data(data), next(nullptr) {}
    };
    std::atomic<node*> _head;
};
~~~

注意:_head.compare_exchange_weak(new_node->next, new_node) 等同于下面：

~~~c
if ( head == new_node->next){
     head = new_node;
     return true;
}
else{
    new_node->next = head;
    return false;
}
~~~

####AQS 核心原理

![image-20200725013938083](https://tva1.sinaimg.cn/large/007S8ZIlly1gh2l3tvk5jj30u10u0wo1.jpg)

Node 是一个先进先出的双端队列，并且是等待队列，当多线程争用资源被阻塞时会进入此队列。

~~~java
  static final class Node {
        /** Marker to indicate a node is waiting in shared mode */
        static final Node SHARED = new Node();
        /** Marker to indicate a node is waiting in exclusive mode */
        static final Node EXCLUSIVE = null;

        /** waitStatus value to indicate thread has cancelled */
        static final int CANCELLED =  1;
        /** waitStatus value to indicate successor's thread needs unparking */
        static final int SIGNAL    = -1;
        /** waitStatus value to indicate thread is waiting on condition */
        static final int CONDITION = -2;
        /**
         * waitStatus value to indicate the next acquireShared should
         * unconditionally propagate
         */
        static final int PROPAGATE = -3;

        /**
         * Status field, taking on only the values:
         *   SIGNAL:     The successor of this node is (or will soon be)
         *               blocked (via park), so the current node must
         *               unpark its successor when it releases or
         *               cancels. To avoid races, acquire methods must
         *               first indicate they need a signal,
         *               then retry the atomic acquire, and then,
         *               on failure, block.
         *   CANCELLED:  This node is cancelled due to timeout or interrupt.
         *               Nodes never leave this state. In particular,
         *               a thread with cancelled node never again blocks.
         *   CONDITION:  This node is currently on a condition queue.
         *               It will not be used as a sync queue node
         *               until transferred, at which time the status
         *               will be set to 0. (Use of this value here has
         *               nothing to do with the other uses of the
         *               field, but simplifies mechanics.)
         *   PROPAGATE:  A releaseShared should be propagated to other
         *               nodes. This is set (for head node only) in
         *               doReleaseShared to ensure propagation
         *               continues, even if other operations have
         *               since intervened.
         *   0:          None of the above
         *
         * The values are arranged numerically to simplify use.
         * Non-negative values mean that a node doesn't need to
         * signal. So, most code doesn't need to check for particular
         * values, just for sign.
         *
         * The field is initialized to 0 for normal sync nodes, and
         * CONDITION for condition nodes.  It is modified using CAS
         * (or when possible, unconditional volatile writes).
         */
        volatile int waitStatus;

        /**
         * Link to predecessor node that current node/thread relies on
         * for checking waitStatus. Assigned during enqueuing, and nulled
         * out (for sake of GC) only upon dequeuing.  Also, upon
         * cancellation of a predecessor, we short-circuit while
         * finding a non-cancelled one, which will always exist
         * because the head node is never cancelled: A node becomes
         * head only as a result of successful acquire. A
         * cancelled thread never succeeds in acquiring, and a thread only
         * cancels itself, not any other node.
         */
        volatile Node prev;

        /**
         * Link to the successor node that the current node/thread
         * unparks upon release. Assigned during enqueuing, adjusted
         * when bypassing cancelled predecessors, and nulled out (for
         * sake of GC) when dequeued.  The enq operation does not
         * assign next field of a predecessor until after attachment,
         * so seeing a null next field does not necessarily mean that
         * node is at end of queue. However, if a next field appears
         * to be null, we can scan prev's from the tail to
         * double-check.  The next field of cancelled nodes is set to
         * point to the node itself instead of null, to make life
         * easier for isOnSyncQueue.
         */
        volatile Node next;

        /**
         * The thread that enqueued this node.  Initialized on
         * construction and nulled out after use.
         */
        volatile Thread thread;

        /**
         * Link to next node waiting on condition, or the special
         * value SHARED.  Because condition queues are accessed only
         * when holding in exclusive mode, we just need a simple
         * linked queue to hold nodes while they are waiting on
         * conditions. They are then transferred to the queue to
         * re-acquire. And because conditions can only be exclusive,
         * we save a field by using special value to indicate shared
         * mode.
         */
        Node nextWaiter;

        /**
         * Returns true if node is waiting in shared mode.
         */
         ...
~~~

注意：

- state 表示当前锁状态。当 state = 0 时表示无锁状态；当 state>0 时，表示已经有线程获得了锁，也就是 state=1，如果同一个线程多次获得同步锁的时候，state 会递增，比如重入 5 次，那么 state=5。 而在释放锁的时候，同样需要释放 5 次直到 state=0，其他线程才有资格获得锁。
- 独占模式代表只有一个线程能够持有同步锁。在多线程的操作中，比如我们允许 10 个线程同时进行，超过这个数量的线程就需要阻塞等待。那么status如果<10,那就status+1，否则线程进入等待状态。

大家在学习的过程要学会灵活运用，多看源码，自己动手实践。自己写一个多线程的同步代码，自己实现AQS。

![image-20200725015244813](https://tva1.sinaimg.cn/large/007S8ZIlly1gh2lhgf3s6j315k0cego5.jpg)

通过上面的学习，我们也要了解为什么线程的切换需要成本。

### Android关于进程的总结

#### RPC

- RPC（Remote Procedure Call）即远程过程调用，它是一种通过网络从远程计算机程序上请求服务，在不需要了解底层网络技术的协议下，即可获取计算机进程中的数据。RPC使得开发包括网络分布式多程序在内的应用程序更加容易。
- RPC在OSI网络通信7层模型中，位于传输层与应用层之间，即位于会话层。
- 客户端能向服务端发送若干个进程请求，服务端根据发送的进程参数依次返回对应的计算结果。RPC可以说客户端调用服务端的接口的过程，是面向接口的编程。
- RPC在的Android具体体现，是依赖 bindService()的方式，在onBind方法将服务端的计算结果返回给客户端（Activity等组件）的过程。

![image-20200725101345123](https://tva1.sinaimg.cn/large/007S8ZIlly1gh2zyr19emj30xq0mu121.jpg)

参见代码，掌握Javav中的动态代理实现接口编程。

涉及到的关键点：

- 线程池 优化
- 接口的设计
- 客户端和服务端的交互（数据持久化）

### Android常见的IPC方式总结

#### AIDL

https://developer.android.com/guide/components/aidl

为什么使用？和使用场景 希望大家自己想一下，主要还是关于内存的包括OOM等。

~~~java
ActivityManager.getMemoryClass() //获得正常情况下可用内存的大小
ActivityManager.getLargeMemoryClass() //可以获得开启largeHeap最大的内存大小
~~~

比如耗时资源、webview的加载等、都可以借助AIDL实现双进程加载。那么如何设计一个相对稳定的双进程通信框架？

参见代码实战理解

#### Binder

参考资料：http://gityuan.com/2015/10/31/binder-prepare/

https://developer.android.com/reference/android/os/Binder

https://blog.csdn.net/qq_30993595/article/details/81206781?utm_medium=distribute.pc_relevant.none-task-blog-baidujs-2&spm=1001.2101.3001.4242

**总结：**

为什么Android 需要采用binder机制，而不采用其他的进程机制，例如Socket等等。

- 一些传统进程通信机制使用了两次用户空间和内核空间的数据拷贝，比如管道、消息队列、Socket都需要2次，共享内存方式一次内存拷贝都不需要，但实现方式又比较复杂且没有同步机制；而Binder只用了一次，主要是因为使用了**内存映射**，底层IPC机制多使用一次数据拷贝其实对整体性能影响很大。
- Binder是基于C/S架构的，更符合Android系统架构，另外通过AIDL的机制，通过动态代理就可以实现客户端的远程过程调用，从而更加的安全。在使用Binder时，Android为每个安装好的应用程序分配了自己的UID/PID（用户ID/进程ID），这些身份标示是跟随调用过程而自动传递的。

Binder调用的具体过程是什么样子的？重点是大方向的掌握

假设Client进程想要调用Server进程的object对象的add方法。 

![image-20200730201126506](https://tva1.sinaimg.cn/large/007S8ZIlly1gh99c7di4oj30h80mvare.jpg)

- 关于Binder驱动的理解

Binder驱动也是一个标准的Linux驱动，所以它的注册和使用等操作也跟标准驱动一样。Binder驱动不涉及任何外设，本质上只操作内存，负责将数据从一个进程传递到另外一个进程。

**一个完整的Binder驱动使用步骤如下：**

- 通过init()，将Binder注册成miscdevice，创建/dev/binder 设备节点
- 通过open()，打开Binder驱动，获取其文件描述符
- 通过mmap()，进行内存申请，实现内存映射
- 通过ioctl()，将IPC中的数据传递给Binder驱动

![image-20200730201705388](https://tva1.sinaimg.cn/large/007S8ZIlly1gh99i4cjaxj31al0u0n59.jpg)

~~~c
static const struct file_operations binder_fops = {
	.owner = THIS_MODULE,
	.poll = binder_poll,
	.unlocked_ioctl = binder_ioctl,
	.compat_ioctl = binder_ioctl,
	.mmap = binder_mmap,
	.open = binder_open,
	.flush = binder_flush,
	.release = binder_release,
};
~~~

**Binder通信协议**:

Binder协议可以分为控制协议和驱动协议两类：

**控制协议**是进程通过ioctl(“/dev/binder”) 与Binder设备进行通讯的协议，该协议包含的命令有BINDER_WRITE_READ，BINDER_SET_MAX_THREADS，BINDER_SET_CONTEXT_MGR，BINDER_THREAD_EXIT，BINDER_VERSION等，这在上面**数据处理**那一步中已经描述过

**驱动协议**描述了对于Binder驱动的具体使用过程。驱动协议又可以分为两类：

- 一类是BINDER_COMMAND_PROTOCOL：binder请求码，以”BC_“开头，简称BC码，描述了进程发送给Binder驱动的命令
- 一类是BINDER_RETURN_PROTOCOL ：binder响应码，以”BR_“开头，简称BR码，描述了Binder驱动发送给进程的命令

**Binder请求码有如下：**

- BC_TRANSACTION：Binder事务，即Client对于Server的请求，参数类型是binder_transaction_data
- BC_REPLY：事务的应答，即：Server对于Client的回复，参数类型是binder_transaction_data
- BC_FREE_BUFFER：通知驱动释放内存，参数类型是binder_uintptr_t(指针)
- BC_ACQUIRE：binder_ref强引用计数+1，参数类型是__u32
- BC_RELEASE：binder_ref强引用计数-1，参数类型是__u32
- BC_INCREFS：binder_ref弱引用计数+1，参数类型是__u32
- BC_DECREFS：binder_ref弱引用计数-1，参数类型是__u32
- BC_ACQUIRE_DONEBR：binder_node强引用减1操作，参数类型是binder_ptr_cookie
- BC_INCREFS_DONEBR：binder_node弱引用减1操作，参数类型是binder_ptr_cookie
- BC_ENTER_LOOPER：通知驱动应用线程进入looper，无参数
- BC_REGISTER_LOOPER：通知驱动创建新的looper线程，无参数
- BC_EXIT_LOOPER：通知驱动应用线程退出looper，无参数
- BC_REQUEST_DEATH_NOTIFICATION：注册死亡通知，参数类型是binder_handle_cookie
- BC_CLEAR_DEATH_NOTIFICATION：取消注册的死亡通知，参数类型是binder_handle_cookie
- BC_DEAD_BINDER_DONE：已完成binder的死亡通知，参数类型是binder_uintptr_t

请求处理过程是通过binder_thread_write()方法，该方法用于处理Binder协议中的请求码。当binder_buffer存在数据，binder线程的写操作循环执行。
对于请求码为BC_TRANSACTION或BC_REPLY时，会执行binder_transaction()方法，这是最为频繁的操作

**Binder响应码如下：**

- BR_ERROR：操作发生错误，参数类型__s32
- BR_OK：操作完成，无参数
- BR_NOOP：不做处理
- BR_SPAWN_LOOPER：binder驱动已经检测到进程中没有线程等待即将到来的事务。那么当一个进程接收到这条命令时，该进程必须创建一条新的服务线程并注册该线程，无参数
- BR_TRANSACTION：Binder驱动向Server端发送请求数据，参数类型binder_transaction_data
- BR_REPLY：Binder驱动向Client端发送回复数据
- BR_TRANSACTION_COMPLETE：对请求发送的成功反馈，当Client端向Binder驱动发送BC_TRANSACTION命令后，Client会收到BR_TRANSACTION_COMPLETE命令，告知Client端请求命令发送成功；对于Server向Binder驱动发送BC_REPLY命令后，Server端会收到BR_TRANSACTION_COMPLETE命令，告知Server端请求回应命令发送成功，无参
- BR_DEAD_REPLY：当应用层向Binder驱动发送Binder调用时，若Binder应用层的另一个端已经死亡，则驱动回应BR_DEAD_BINDER命令，往往是线程或节点为空，无参
- BR_FAILED_REPLY：当应用层向Binder驱动发送Binder调用时，若transaction出错，比如调用的函数号不存在，则驱动回应BR_FAILED_REPLY，无参
- BR_INCREFS：binder_ref弱引用加1操作（Server端），参数类型binder_ptr_cookie
- BR_DECREFS：binder_ref弱引用减1操作（Server端），参数类型binder_ptr_cookie
- BR_ACQUIRE：binder_ref强引用加1操作（Server端），参数类型binder_ptr_cookie
- BR_RELEASE：binder_ref强引用减1操作（Server端），参数类型binder_ptr_cookie
- BR_DEAD_BINDER：Binder驱动向client端发送死亡通知，参数类型 binder_uintptr_t(指针)
- BR_CLEAR_DEATH_NOTIFICATION_DONE：BC_CLEAR_DEATH_NOTIFICATION命令对应的响应码，参数类型binder_uintptr_t(指针)

响应处理过程是通过binder_thread_read()方法，该方法根据不同的binder_work->type以及不同状态，生成相应的响应码。

Client对于Server的请求以及Server对于Client回复都需要通过Binder驱动来中转数据。

- 经常听说2次拷贝，1次拷贝，怎么理解？

![image-20200730202352288](https://tva1.sinaimg.cn/large/007S8ZIlly1gh99p5rr94j30lo0gaq62.jpg)

相关源码：/system/core/init/init.cpp

/kernel/drivers/staging/android/binder.c
/kernel/drivers/staging/android/uapi/binder.h

#### Messenger

相对AIDL来说，Messenger的使用是很简单了，省去中间很多繁琐的操作。对AIDL进行了封装，也就是对 Binder 的封装，是进程间通信成本较低的一种方式之一。本质还是Binder。

参见MessengerDemo。重点是理解代码本质。源码

### 多线程相关

在Android的程序开发中，许多耗时操作都要放到子线程中，避免阻塞主线程，导致ANR。但是在使用异步线程的过程中都会遇到与主线程通信的问题。同步和异步的概念，阻塞和非阻塞的概念。

#### 创建分析和思考

思考：为什么阿里不推荐通过Executors直接创建线程池？

我们随便分析一个newFixedThreadPool的源码。

![image-20200730215029786](https://tva1.sinaimg.cn/large/007S8ZIlly1gh9c793jgdj316o0howho.jpg)

LinkedBlockingQueue 是无界的，会导致其无限增大，最终内存撑爆。再看看newCachedThreadPool的源码：

![image-20200730215151978](https://tva1.sinaimg.cn/large/007S8ZIlly1gh9c8ofr97j31660catap.jpg)

SynchronousQueue() 只能存一个队列，可以认为所有 放到 newCachedThreadPool() 中的线程，不会缓存到队列中，而是直接运行的， 由于最大线程数是 Integer.MAX_VALUE ，这个数量级可以认为是无限大了， 随着执行线程数量的增多 和 线程没有及时结束，最终会将内存撑爆。

#### ThreadPoolExecutor分析

~~~java
ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,RejectedExecutionHandler handler)
  
  
//corePoolSize（线程池的基本大小）：当提交一个任务到线程池时，线程池会创建一个线程来执行任务，即使其他空闲的基本线程能够执行新任务也会创建线程，等到需要执行的任务数大于线程池基本大小时就不再创建。如果调用了线程池的prestartAllCoreThreads方法，线程池会提前创建并启动所有基本线程。

//workQueue(任务队列）：用于保存等待执行的任务的阻塞队列。可以选择以下几个阻塞队列。
//ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。
//LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，吞吐量通常要高于ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool()使用了这个队列
//SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
//PriorityBlockingQueue：一个具有优先级的无限阻塞队列。

  //maximumPoolSize（线程池最大大小）：线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。值得注意的是如果使用了无界的任务队列这个参数就没什么效果。

//ThreadFactory：用于设置创建线程的工厂，可以通过线程工厂给每个创建出来的线程做些更有意义的事情，比如设置daemon和优先级等等

  //RejectedExecutionHandler（饱和策略）：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。这个策略默认情况下是AbortPolicy，表示无法处理新任务时抛出异常。以下是JDK1.5提供的四种策略。
//AbortPolicy：直接抛出异常。
//CallerRunsPolicy：只用调用者所在线程来运行任务。
//DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
//DiscardPolicy：不处理，丢弃掉。
//也可以根据应用场景需要来实现RejectedExecutionHandler接口自定义策略。如记录日志或持久化不能处理的任务。

  //keepAliveTime（线程活动保持时间）：线程池的工作线程空闲后，保持存活的时间。所以如果任务很多，并且每个任务执行的时间比较短，可以调大这个时间，提高线程的利用率。

  //TimeUnit（线程活动保持时间的单位）：可选的单位有天（DAYS），小时（HOURS），分钟（MINUTES），毫秒(MILLISECONDS)，微秒(MICROSECONDS, 千分之一毫秒)和毫微秒(NANOSECONDS, 千分之一微秒)。
~~~

线程池执行流程：（背）

- 当前线程池中运行的线程数量还没有达到 corePoolSize 大小时，线程池会创建一个新线程执行提交的任务，无论之前创建的线程是否处于空闲状态。
- 当前线程池中运行的线程数量已经达到 corePoolSize 大小时，线程池会把任务加入到等待队列中，直到某一个线程空闲了，线程池会根据我们设置的等待队列规则，从队列中取出一个新的任务执行。
- 如果线程数大于 corePoolSize 数量但是还没有达到最大线程数 maximumPoolSize，并且等待队列已满，则线程池会创建新的线程来执行任务。
- 最后如果提交的任务，无法被核心线程直接执行，又无法加入等待队列，又无法创建“非核心线程”直接执行，线程池将根据拒绝处理器定义的策略处理这个任务。也可以自定义策略。

![image-20200730220220918](https://tva1.sinaimg.cn/large/007S8ZIlly1gh9cjktkclj30an0igmxt.jpg)



注意：execute() 和 submit() 都是用来执行线程池任务的，它们最主要的区别是，submit() 方法可以接收线程池执行的返回值，而 execute() 不能接收返回值。

代码实践下： 掌握在业务中可以使用了。

### 锁

### 公平锁VS非公平锁

如果一个线程组里，能保证每个线程都能拿到锁，那么这个锁就是公平锁。相反，如果保证不了每个线程都能拿到锁，也就是存在有线程饿死，那么这个锁就是非公平锁。

ReentrantLock:

锁的四大特性：

可重入性：

~~~java

public class ReentrantLockTest extends Thread {

    static ReentrantLock lock = new ReentrantLock();
    static int i = 0;

    @Test
    public void reentrantLockTest() throws InterruptedException {
        ReentrantLockTest test1 = new ReentrantLockTest();
        test1.setName("thread-1");
        ReentrantLockTest test2 = new ReentrantLockTest();
        test2.setName("thread-2");

        test1.start();
        test2.start();
        test1.join();
        test2.join();
        System.out.println(i);//注意最后的数字肯定是 10 如果去掉锁 肯定<10 随机
    }

    public ReentrantLockTest() {
        super();
    }


    @Override
    public void run() {
        for (int j = 0; j < 5; j++) {
            lock.lock();
            lock.lock(); //这里演示的是可重入 也就是说释放一定要2次的
//            lock.lock();
            try {
                System.out.println(this.getName() + " " + i);
                i++;
            } finally {
//                lock.unlock();//一定要解锁
                lock.unlock();//一定要解锁
                System.out.println("======>");
            }
        }
    }
}
~~~

- synchronized （单列模式）
- ReentrantLock （使用 ReentrantLock的时候一定要**手动释放锁**，并且**加锁次数和释放次数要一样**）
- 本质上是通过锁计数器来操作的。https://www.ibm.com/support/knowledgecenter/zh/SSAW57_9.0.5/com.ibm.websphere.nd.multiplatform.doc/ae/rprf_datacounter4.html

**可中断**：可以相应中断自己。

```java
public class LockInterrupt extends Thread {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public LockInterrupt(int lock, String name) {

        super(name);
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {                
                }
                lock1.lockInterruptibly();
            }
        } catch (Exception e) {           
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getId() + ":线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockInterrupt t1 = new LockInterrupt(1, "LockInterrupt1");
        LockInterrupt t2 = new LockInterrupt(2, "LockInterrupt2");
        t1.start();
        t2.start();
        Thread.sleep(1000);
				//启动：发生死锁，线程1得到l1，线程2得到l2，然后彼此又想获得对方的锁 jps jstack命令
        //DeadlockChecker.check();
    }

    static class DeadlockChecker {

        private final static ThreadMXBean mbean = ManagementFactory
                .getThreadMXBean();

        public static void check() {

            Thread tt = new Thread(() -> {
                {
                   
                    while (true) {
                        long[] deadlockedThreadIds = mbean.findDeadlockedThreads();
                        if (deadlockedThreadIds != null) {
                            ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);
                            for (Thread t : Thread.getAllStackTraces().keySet()) {
                                for (int i = 0; i < threadInfos.length; i++) {
                                    if (t.getId() == threadInfos[i].getThreadId()) {
                                        System.out.println(t.getName());
                                        t.interrupt();
                                    }
                                }
                            }
                        }
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                    }

                }
            });
            tt.setDaemon(true);
            tt.start();
        }

    }
}
```

![image-20200730230347925](https://tva1.sinaimg.cn/large/007S8ZIlly1gh9ebka8szj31hc0u0dnk.jpg)

**可超时**：

~~~java
package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo extends Thread{
    public static ReentrantLock lock = new ReentrantLock();

    public TryLockDemo(String name){
        super(name);
    }

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);//必然有线程获取不到 那么就可以退出了
            } else {
                System.out.println(this.getName() + " get lock failed");
            }
        } catch (Exception e) {
        } finally {
            if (lock.isHeldByCurrentThread()) {
                System.out.println("lock.isHeldByCurrentThread: " + this.getName());
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TryLockDemo t1 = new TryLockDemo("TryLockTest1");
        TryLockDemo t2 = new TryLockDemo("TryLockTest2");

        t1.start();
        t2.start();
    }
}
~~~

###synchronized与Lock

线程的状态：

- 新建状态：新建线程对象，并没有调用start()方法之前
- 就绪状态：调用start()方法之后线程就进入就绪状态，但是并不是说只要调用start()方法线程就马上变为当前线程，在变为当前线程之前都是为就绪状态。值得一提的是，线程在睡眠和挂起中恢复的时候也会进入就绪状态哦。
- 运行状态：线程被设置为当前线程，开始执行run()方法。就是线程进入运行状态
- 阻塞状态：线程被暂停，比如说调用sleep()方法后线程就进入阻塞状态
- 死亡状态：线程执行结束

| 类别     | synchronized                                                 | Lock                                                         |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 存在层次 | Java的关键字，在jvm层面上                                    | 是一个类                                                     |
| 锁的释放 | 1、以获取锁的线程执行完同步代码，释放锁 2、线程执行发生异常，jvm会让线程释放锁 | 在finally中必须释放锁，不然容易造成线程死锁                  |
| 锁的获取 | 假设A线程获得锁，B线程等待。如果A线程阻塞，B线程会一直等待   | 分情况而定，Lock有多个锁获取的方式，可以尝试获得锁，线程可以不用一直等待 |
| 锁状态   | 无法判断                                                     | 可以判断                                                     |
| 锁类型   | 可重入 不可中断 非公平                                       | 可重入 可判断 可公平（两者皆可）                             |
| 性能     | 少量同步                                                     | 大量同步                                                     |

### 读写锁

**读写锁允许同一时刻被多个读线程访问，但是在写线程访问时，所有的读线程和其他的写线程都会被阻塞**。

读写锁、分为读锁和写锁，多个读锁不互斥，读锁与写锁互斥，写锁与写锁互斥。比如在设计APP缓存的时候，多个线程读写文件，我们怎么设计？

使用Synchronized同步锁保护线程安全，但是Synchronized存在明显的一个性能问题就是读与读之间互斥，也就是说两个线程的读操作是顺序执行的 。这样的就会导致性能的开销过大。

提供大家一个base类。

~~~java
public abstract class BaseCache {

    private final ReadWriteLock mLock = new ReentrantReadWriteLock();

    /**
     * 读取缓存
     *
     * @param key       缓存key
     * @param existTime 缓存时间
     */
    final <T> T load(Type type, String key, long existTime) {
        //1.先检查key
        Utils.checkNotNull(key, "key == null");

        //2.判断key是否存在,key不存在去读缓存没意义
        if (!containsKey(key)) {
            return null;
        }

        //3.判断是否过期，过期自动清理
        if (isExpiry(key, existTime)) {
            remove(key);
            return null;
        }

        //4.开始真正的读取缓存
        mLock.readLock().lock();
        try {
            // 读取缓存
            return doLoad(type, key);
        } finally {
            mLock.readLock().unlock();
        }
    }

    /**
     * 保存缓存
     *
     * @param key   缓存key
     * @param value 缓存内容
     * @return
     */
    final <T> boolean save(String key, T value) {
        //1.先检查key
        Utils.checkNotNull(key, "key == null");

        //2.如果要保存的值为空,则删除
        if (value == null) {
            return remove(key);
        }

        //3.写入缓存
        boolean status = false;
        mLock.writeLock().lock();
        try {
            status = doSave(key, value);
        } finally {
            mLock.writeLock().unlock();
        }
        return status;
    }

    /**
     * 删除缓存
     */
    final boolean remove(String key) {
        mLock.writeLock().lock();
        try {
            return doRemove(key);
        } finally {
            mLock.writeLock().unlock();
        }
    }


    /**
     * 获取缓存大小
     * @return
     */
    long size() {
        return getSize();
    }

    /**
     * 清空缓存
     */
    final boolean clear() {
        mLock.writeLock().lock();
        try {
            return doClear();
        } finally {
            mLock.writeLock().unlock();
        }
    }

    /**
     * 是否包含 加final 是让子类不能被重写，只能使用doContainsKey
     * 这里加了锁处理，操作安全。<br>
     *
     * @param key 缓存key
     * @return 是否有缓存
     */
    public final boolean containsKey(String key) {
        mLock.readLock().lock();
        try {
            return doContainsKey(key);
        } finally {
            mLock.readLock().unlock();
        }
    }

    /**
     * 是否包含  采用protected修饰符  被子类修改
     */
    protected abstract boolean doContainsKey(String key);

    /**
     * 是否过期
     */
    protected abstract boolean isExpiry(String key, long existTime);

    /**
     * 读取缓存
     */
    protected abstract <T> T doLoad(Type type, String key);

    /**
     * 保存
     */
    protected abstract <T> boolean doSave(String key, T value);

    /**
     * 删除缓存
     */
    protected abstract boolean doRemove(String key);

    /**
     * 清空缓存
     */
    protected abstract boolean doClear();

    /**
     * 获取缓存大小
     *
     * @return
     */
    protected abstract long getSize();
}

~~~

源码分析：

**ReentrantReadWriteLock内部结构**:

~~~java
/*内部读锁*/
private final ReentrantReadWriteLock.ReadLock readerLock;
/*内部写锁*/
private final ReentrantReadWriteLock.WriteLock writerLock;
/*无参构造器 默认是给公平模式*/
public ReentrantReadWriteLock();
/*公平模式构造器*/
public ReentrantReadWriteLock(boolean fair);
/*获取写锁方法*/
public ReentrantReadWriteLock.WriteLock writeLock();
/*获取读锁方法*/
public ReentrantReadWriteLock.ReadLock  readLock();
/*内部类同步器  基于aqs实现功能*/
abstract static class Sync extends AbstractQueuedSynchronizer{}
/*非公平sync*/
static final class NonfairSync extends Sync{}
/*公平sync*/
static final class FairSync extends Sync{}
/*读锁类*/
public static class ReadLock implements Lock, java.io.Serializable{}
/*写锁类*/
public static class WriteLock implements Lock, java.io.Serializable{}
~~~

~~~java
public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
            acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    	}
        protected final boolean tryAcquire(int acquires) {
            Thread current = Thread.currentThread();
          //1. 获取写锁当前的同步状态
            int c = getState();
          // 2. 获取写锁获取的次数
            int w = exclusiveCount(c);
            if (c != 0) {
                // (Note: if c != 0 and w == 0 then shared count != 0)
               // 3.1 当读锁已被读线程获取或者当前线程不是已经获取写锁的线程的话
               // 当前线程获取写锁失败
                if (w == 0 || current != getExclusiveOwnerThread())
                    return false;
               // Reentrant acquire
        	// 3.2 当前线程获取写锁，支持可重复加锁
                if (w + exclusiveCount(acquires) > MAX_COUNT)
                    throw new Error("Maximum lock count exceeded");
                // Reentrant acquire
                setState(c + acquires);
                return true;
            }
           // 3.3 写锁未被任何线程获取，当前线程可获取写锁
            if (writerShouldBlock() ||
                !compareAndSetState(c, c + acquires))
                return false;
            setExclusiveOwnerThread(current);
            return true;
        }
~~~

- 如果等于0表示当前没有线程获取到锁，先查看当前队列是否有优先的等待队列（公平模式才会检查，非公平模式直接返回false），如果有直接返回true，没有则对state值直接进行cas替换操作，将c+acquires赋值给c，如果成功则设置exclusiveOwnerThread为当前线程，失败直接返回。
- 如果不等于0（表示已经有线程获取到了锁），首先判断w是否为0（也就是写锁是否被获取），**如果写锁没有被其他线程获取或者获取写锁的线程不是当前线程则直接返回false** 。其次判断写锁的值是不是超过范围，因为写锁与读锁的值被拆分为16位的数字，则该数字最大值为65535，如果超过范围直接抛出异常。上述条件都通过直接对c进行运算并赋值给c，这里大家可以关注到赋值并未做cas原子操作，是因为该线程已经获取到写锁了，变量操作是线程安全的所以不需要进行cas操作。

**写锁的释放**：

~~~java
protected final boolean tryRelease(int releases) {
    if (!isHeldExclusively())
        throw new IllegalMonitorStateException();
    //1. 同步状态减去写状态
    int nextc = getState() - releases;
    //2. 当前写状态是否为0，为0则释放写锁
    boolean free = exclusiveCount(nextc) == 0;
    if (free)
        setExclusiveOwnerThread(null);
    //3. 不为0则更新同步状态
    setState(nextc);
    return free;
}
~~~

实践。

### Linux进程锁

进程间共享数据的保护，需要进程互斥锁。与线程锁不同，进程锁并没有直接的C库支持，但是在Linux平台，要实现进程之间互斥锁，方法有很多。请大家回忆思考下。

Semaphores：

- Posix信号量

~~~c
sem_t *sem_open(const char *name, int oflag);  
sem_t *sem_open(const char *name, int oflag, mode_t mode, unsigned int value);  
int sem_init(sem_t *sem, int pshared, unsigned int value);  
int sem_wait(sem_t *sem);  
int sem_trywait(sem_t *sem);  
int sem_timedwait(sem_t *sem, const struct timespec *abs_timeout);  
int sem_close(sem_t *sem);  
int sem_destroy(sem_t *sem);  
int sem_unlink(const char *name);  
~~~

- System V信号量

~~~c
int semget(key_t key, int nsems, int semflg);  
int semctl(int semid, int semnum, int cmd, ...);  
int semop(int semid, struct sembuf *sops, unsigned nsops);  
int semtimedop(int semid, struct sembuf *sops, unsigned nsops, struct timespec *timeout);  
~~~

线程锁：

pthread那一套。

~~~c
int pthread_mutex_init (pthread_mutex_t *mutex, const pthread_mutexattr_t *mutexattr);  
int pthread_mutex_destroy (pthread_mutex_t *mutex);  
int pthread_mutex_trylock (pthread_mutex_t *mutex);  
int pthread_mutex_lock (pthread_mutex_t *mutex);  
int pthread_mutex_timedlock (pthread_mutex_t *restrict mutex, const struct timespec *restrict abstime);  
int pthread_mutex_unlock (pthread_mutex_t *mutex); 
~~~

设置互斥锁的进程间共享属性：

~~~c
int pthread_mutexattr_setpshared(pthread_mutexattr_t *mattr, int pshared);   
pthread_mutexattr_t mattr;   
pthread_mutexattr_init(&mattr);   
pthread_mutexattr_setpshared(&mattr, PTHREAD_PROCESS_SHARED);   
~~~

为了达到多进程共享的需要，互斥锁对象需要创建在共享内存中。

缺点也是很明显的。就是兼容性。

- 绝大部分嵌入式Linux系统，glibc或者uclibc，不支持_POSIX_SHARED_MEMORY_OBJECTS；
- 绝大部分嵌入式Linux系统，不支持Posix标准信号量；
- 部分平台，不支持System V标准信号量，比如Android。

终极方案：文件记录锁（一个进程正在读或修改文件的某个部分时，可以阻止其他进程修改同一文件区。）

~~~c
#include <sys/types.h>  
#include <unistd.h>  
#include <fcnt1.h>  
int fcnt1(int filedes, int cmd, .../* struct flock *flockptr */);  
~~~

对于记录锁，cmd是F_GETLK、F_SETLK或F_SETLKW。第三个参数（称其为flockptr）是一个指向flock结构的指针。

~~~c
struct flock {  
    short l_type;    /* Type of lock: F_RDLCK, F_WRLCK, F_UNLCK */  
    short l_whence;  /* How to interpret l_start: SEEK_SET, SEEK_CUR, SEEK_END */  
    off_t l_start;   /* Starting offset for lock */  
    off_t l_len;     /* Number of bytes to lock */  
    pid_t l_pid;     /* PID of process blocking our lock  
};  
~~~

- F_GETLK决定由flockptr所描述的锁是否被另外一把锁所排斥（阻塞）。如果存在一把锁，它阻止创建由flockptr所描述的锁，则这把现存的锁的信息写到flockptr指向的结构中。如果不存在这种情况，则除了将ltype设置为F_UNLCK之外，flockptr所指向结构中的其他信息保持不变。
- F_SETLK设置由flockptr所描述的锁。如果试图建立一把按上述兼容性规则并不允许的锁，则fcntl立即出错返回，此时errno设置为EACCES或EAGAIN。
- F_SETLKW这是F_SETLK的阻塞版本（命令名中的W表示等待（wait））。如果由于存在其他锁，那么按兼容性规则由flockptr所要求的锁不能被创建，则调用进程睡眠。如果捕捉到信号则睡眠中断。

#### 文件记录锁实现方案



