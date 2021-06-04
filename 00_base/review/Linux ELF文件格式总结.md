###**数据类型**
首先在解析之前， 必须对数据类型格式声明一下名称大小说明

| 名称          | 大小 | 说明           |
| ------------- | ---- | -------------- |
| Elf32_Addr    | 4    | 无符号程序地址 |
| Elf32_Half    | 2    | 无符号中等整数 |
| Elf32_Off     | 4    | 无符号文件偏移 |
| Elf32_SWord   | 4    | 有符号大整数   |
| Elf32_Word    | 4    | 无符号大整数   |
| unsigned char | 1    | 无符号整数     |
|               |      |                |

###**概述**

- **ELF头部(ELF_Header)**: 每个ELF文件都必须存在一个ELF_Header,这里存放了很多重要的信息用来描述整个文件的组织,如: 版本信息,入口信息,偏移信息等。程序执行也必须依靠其提供的信息。
- **程序头部表(Program_Header_Table)**: 可选的一个表，用于告诉系统如何在内存中创建映像,在图中也可以看出来,有程序头部表才有段,有段就必须有程序头部表。其中存放各个段的基本信息(包括地址指针)。
- **节区头部表(Section_Header_Table)**: 类似与Program_Header_Table,但与其相对应的是节区(Section)。
- **节区(Section)**: 将文件分成一个个节区，每个节区都有其对应的功能，如符号表，哈希表等。
- **段(Segment)**: 嗯…就是将文件分成一段一段映射到内存中。段中通常包括一个或多个节区

**注：每个节区都应该是前后相连的，且不可有重叠。即在一个地址上的字节只能属于一个节区**。

###**ELF_Header**

~~~c
struct Elf32_Ehdr {
  unsigned char e_ident[EI_NIDENT]; // ELF Identification bytes
  Elf32_Half    e_type;      // Type of file (see ET_* below)
  Elf32_Half    e_machine;   // Required architecture for this file (see EM_*)
  Elf32_Word    e_version;   // Must be equal to 1
  Elf32_Addr    e_entry;     // Address to jump to in order to start program
  Elf32_Off     e_phoff;     // Program header table's file offset, in bytes
  Elf32_Off     e_shoff;     // Section header table's file offset, in bytes
  Elf32_Word    e_flags;     // Processor-specific flags
  Elf32_Half    e_ehsize;    // Size of ELF header, in bytes
  Elf32_Half    e_phentsize; // Size of an entry in the program header table
  Elf32_Half    e_phnum;     // Number of entries in the program header table
  Elf32_Half    e_shentsize; // Size of an entry in the section header table
  Elf32_Half    e_shnum;     // Number of entries in the section header table
  Elf32_Half    e_shstrndx;  // Sect hdr table index of sect name string table
  bool checkMagic() const {
    return (memcmp(e_ident, ElfMagic, strlen(ElfMagic))) == 0;
  }
  unsigned char getFileClass() const { return e_ident[EI_CLASS]; }
  unsigned char getDataEncoding() const { return e_ident[EI_DATA]; }
};
~~~

魔术字，入口地址，程序头位置、长度和数量，文件头大小（52字节），段表位置、长度和 个数。

​    1、e_ident数组：前4个字节为`.ELF`，是elf标志头，第5个字节为该文件标志符，为1代表这是一个32位的elf文件，后面几个字节代表版本等信息。
    2、e_type字段：表示是可执行文件还是链接文件等，安卓上的so文件就是分享文件，一般该字段为3。
    3、e_machine字段：该字段标志该文件运行在什么机器架构上，例如ARM。
    4、e_version字段：该字段表示当前so文件的版本信息，一般为1。
    5、e_entry字段：该字段是一个偏移地址，为程序启动的地址。
    6、e_phoff字段：该字段也是一个偏移地址，指向程序头(Pargram Header)的起始地址。
    7、e_shoff字段：该字段是一个偏移地址，指向节区头(Section Header)的起始地址。
    8、e_flags字段：该字段表示该文件的权限，常见的值有1、2、4，分别代表read、write、exec。
    9、e_ehsize字段：该字段表示elf文件头部大小，一般固定为52。
    10、e_phentsize字段：该字段表示程序头(Program Header)大小，一般固定为32。
    11、e_phnum字段：该字段表示文件中有几个程序头。
    12、e_shentsize:该字段表示节区头(Section Header)大小，一般固定为40。
    13、e_shnum字段：该字段表示文件中有几个节区头。
    14、e_shstrndx字段：该字段是一个数字，这个表明了`.shstrtab节区(这个节区存储着所有节区的名字，例如.text)`的节区头是第几个。  

~~~cpp
struct DataOffest parseSoHeader(FILE *fp,struct DataOffest off)
{
    Elf32_Ehdr header;
    int i = 0;

    fseek(fp,0,SEEK_SET);
    fread(&header,1,sizeof(header),fp);
    printf("ELF Header:\n");
    printf("    Header Magic: ");
    for (i = 0; i < 16; i++)
    {
        printf("%02x ",header.e_ident[i]);
    }
    printf("\n");
    printf("    So File Type: 0x%02x",header.e_type);
    switch (header.e_type)
    {
    case 0x00:
        printf("(No file type)\n");
        break;
    case 0x01:
        printf("(Relocatable file)\n");
        break;
    case 0x02:
        printf("(Executable file)\n");
        break;
    case 0x03:
        printf("(Shared object file)\n");
        break;
    case 0x04:
        printf("(Core file)\n");
        break;
    case 0xff00:
        printf("(Beginning of processor-specific codes)\n");
        break;
    case 0xffff:
        printf("(Processor-specific)\n");
        break;
    default:
        printf("\n");
        break;
    }
    printf("    Required Architecture: 0x%04x",header.e_machine);
    if (header.e_machine == 0x28)
    {
        printf("(ARM)\n");
    }
    else
    {
        printf("\n");
    }
    printf("    Version: 0x%02x\n",header.e_version);
    printf("    Start Program Address: 0x%08x\n",header.e_entry);
    printf("    Program Header Offest: 0x%08x\n",header.e_phoff);
    off.programheadoffset = header.e_phoff;
    printf("    Section Header Offest: 0x%08x\n",header.e_shoff);
    off.sectionheadoffest = header.e_shoff;
    printf("    Processor-specific Flags: 0x%08x\n",header.e_flags);
    printf("    ELF Header Size: 0x%04x\n",header.e_ehsize);
    printf("    Size of an entry in the program header table: 0x%04x\n",header.e_phentsize);
    printf("    Program Header Size: 0x%04x\n",header.e_phnum);
    off.programsize = header.e_phnum;
    printf("    Size of an entry in the section header table: 0x%04x\n",header.e_shentsize);
    printf("    Section Header Size: 0x%04x\n",header.e_shnum);
    off.sectionsize = header.e_shnum;
    printf("    String Section Index: 0x%04x\n",header.e_shstrndx);
    off.shstrtabindex = header.e_shstrndx;
    return off;
}
~~~



###Program header

目标文件或者共享文件的program header table描述了系统执行一个程序所需要的段或者其它信息。目标文件的一个段（segment）包含一个或者多个section。Program header只对可执行文件和共享目标文件有意义，对于程序的链接没有任何意义。

~~~c
/* Program Header */
typedef struct {
    Elf32_Word    p_type;        /* segment type */
    Elf32_Off    p_offset;    /* segment offset */
    Elf32_Addr    p_vaddr;    /* virtual address of segment */
    Elf32_Addr    p_paddr;    /* physical address - ignored? */
    Elf32_Word    p_filesz;    /* number of bytes in file for seg. */
    Elf32_Word    p_memsz;    /* number of bytes in mem. for seg. */
    Elf32_Word    p_flags;    /* flags */
    Elf32_Word    p_align;    /* memory alignment */
} Elf32_Phdr;
~~~

    1、p_type字段：该字段表明了段(Segment)类型，例如`PT_LOAD`类型,具体请在源码中查看。
    2、p_offest字段：该字段表明了这个段在该so文件的起始地址。
    3、p_vaddr字段：该字段指明了加载进内存后的虚拟地址，我们静态解析时用不到该字段。
    4、p_paddr字段：该字段指明加载进内存后的实际物理地址，跟上面的那个字段一样，解析时用不到。
    5、p_filesz字段：该字段表明了这个段的大小，单位为字节。
    6、p_memsz字段：该字段表明了这个段加载到内存后使用的字节数。
    7、p_flags字段：该字段跟elf头部的e_flags一样，指明了该段的属性，是可读还是可写。
    8、p_align字段：该字段用来指明在内存中对齐字节数的。  

~~~cpp
struct DataOffest parseSoPargramHeader(FILE *fp,struct DataOffest off)
{
    Elf32_Half init;
    Elf32_Half addr;
    int i;
    Elf32_Phdr programHeader;

    init = off.programheadoffset;
    for (i = 0; i < off.programsize; i++)
    {
        addr = init + (i * 0x20);
        fseek(fp,addr,SEEK_SET);
        fread(&programHeader,1,32,fp);
        switch (programHeader.p_type)
        {
        case 2:
            off.dynameicoff = programHeader.p_offset;
            off.dynameicsize = programHeader.p_filesz;
            break;
        default:
            break;
        }
        printf("\n\nSegment Header %d:\n",(i + 1));
        printf("    Type of segment: 0x%08x\n",programHeader.p_type);
        printf("    Segment Offset: 0x%08x\n",programHeader.p_offset);
        printf("    Virtual address of beginning of segment: 0x%08x\n",programHeader.p_vaddr);
        printf("    Physical address of beginning of segment: 0x%08x\n",programHeader.p_paddr);
        printf("    Num. of bytes in file image of segment: 0x%08x\n",programHeader.p_filesz);
        printf("    Num. of bytes in mem image of segment (may be zero): 0x%08x\n",programHeader.p_memsz);
        printf("    Segment flags: 0x%08x\n",programHeader.p_flags);
        printf("    Segment alignment constraint: 0x%08x\n",programHeader.p_align);
    }
    return off;
}
~~~

###Section Header

~~~cpp
// Section header.
struct Elf32_Shdr {
  Elf32_Word sh_name;      // Section name (index into string table)
  Elf32_Word sh_type;      // Section type (SHT_*)
  Elf32_Word sh_flags;     // Section flags (SHF_*)
  Elf32_Addr sh_addr;      // Address where section is to be loaded
  Elf32_Off  sh_offset;    // File offset of section data, in bytes
  Elf32_Word sh_size;      // Size of section, in bytes
  Elf32_Word sh_link;      // Section type-specific header table index link
  Elf32_Word sh_info;      // Section type-specific extra information
  Elf32_Word sh_addralign; // Section address alignment
  Elf32_Word sh_entsize;   // Size of records contained within the section
};

// Section header for ELF64 - same fields as ELF32, different types.
struct Elf64_Shdr {
  Elf64_Word  sh_name;
  Elf64_Word  sh_type;
  Elf64_Xword sh_flags;
  Elf64_Addr  sh_addr;
  Elf64_Off   sh_offset;
  Elf64_Xword sh_size;
  Elf64_Word  sh_link;
  Elf64_Word  sh_info;
  Elf64_Xword sh_addralign;
  Elf64_Xword sh_entsize;
};
~~~

​    1、sh_name字段：该字段是一个索引值，是`.shstrtab`表(节区名字字符串表)的索引，指明了该节区的名字。
    2、sh_type字段：该字段表明该节区的类型，例如值为`SHT_PROGBITS`,则该节区可能是`.text`或者`.rodata`，至于具体怎么区分，当然看sh_name字段。
    3、sh_flags字段：跟上面的一样，就不再细说了。
    4、sh_addr字段：该字段是一个地址，是该节区加载进内存后的地址。
    5、sh_offset字段：该字段也是一个地址，是该节区在该so文件中的偏移地址。
    6、sh_size字段：该字段表明了该节区的大小，单位是字节。
    7、sh_link和sh_info字段：这两个字段只适用于少数节区，我们这里解析用不到，感兴趣的可以去看官方文档。
    8、sh_addralign字段：该字段指明在内存中的对齐字节。
    9、sh_entsize字段：该字段指明了该节区中每个项占用的字节数。  

~~~cpp
struct DataOffest parseSoSectionHeader(FILE *fp,struct DataOffest off,struct ShstrtabTable StrList[100])
{
    Elf32_Half init;
    Elf32_Half addr;
    Elf32_Shdr sectionHeader;
    int i,id,n;
    char ch;
    int k = 0;

    init = off.sectionheadoffest;
    for (i = 0; i < off.sectionsize; i++)
    {
        addr = init + (i * 0x28);
        fseek(fp,addr,SEEK_SET);
        fread(§ionHeader,1,40,fp); 
        switch (sectionHeader.sh_type)
        {
        case 2:
            off.symtaboff = sectionHeader.sh_offset;
            off.symtabsize = sectionHeader.sh_size;
            break;
        case 3:
            if(k == 0)
            {
                off.stroffset = sectionHeader.sh_offset;
                off.strsize = sectionHeader.sh_size;
                k++;
            }
            else if (k == 1)
            {
                off.str1offset = sectionHeader.sh_offset;
                off.str1size = sectionHeader.sh_size;
                k++;
            }
            else
            {
                off.str2offset = sectionHeader.sh_offset;
                off.str2size = sectionHeader.sh_size;
                k++;
            }
            break;
        default:
            break;
        }
        id = sectionHeader.sh_name;
        printf("\n\nSection Header %d\n",(i + 1));
        printf("    Section Name: ");
        for (n = 0; n < 50; n++)
        {
            ch = StrList[id].str[n];
            if (ch == 0)
            {
                printf("\n");
                break;
            }
            else
            {
                printf("%c",ch);
            }
        }
        printf("    Section Type: 0x%08x\n",sectionHeader.sh_type);
        printf("    Section Flag: 0x%08x\n",sectionHeader.sh_flags);
        printf("    Address where section is to be loaded: 0x%08x\n",sectionHeader.sh_addr);
        printf("    Offset: 0x%x\n",sectionHeader.sh_offset);
        printf("    Size of section, in bytes: 0x%08x\n",sectionHeader.sh_size);
        printf("    Section type-specific header table index link: 0x%08x\n",sectionHeader.sh_link);
        printf("    Section type-specific extra information: 0x%08x\n",sectionHeader.sh_info);
        printf("    Section address alignment: 0x%08x\n",sectionHeader.sh_addralign);
        printf("    Size of records contained within the section: 0x%08x\n",sectionHeader.sh_entsize);
    }
    return off;
}
~~~

###字符串节区解析

**因so加固等只涉及到几个节区，所以重点关注.shstrtab、.strtab、.dynstr、.text、.symtab、.dynamic节区**  

**在elf头部中有个e_shstrndx字段，该字段指明了.shstrtab节区头部是文件中第几个节区头部，我们可以根据这找到.shstrtab节区的偏移地址，然后读取出来，就可以为每个节区名字赋值了，然后就可以顺着锁定剩下的两个字符串节区。** **在elf文件中，字符串表示方式如下：字符串的头部和尾部用标示字节00标志，同时上一个字符串尾部标识符00作为下一个字符串头部标识符。例如我有两个紧邻的字符串分别是a和b，那么他们在elf文件中16进制为00 97 00 98 00。**  

###.dynamic解析

~~~c
struct Elf32_Dyn
{
  Elf32_Sword d_tag;            // Type of dynamic table entry.
  union
  {
      Elf32_Word d_val;         // Integer value of entry.
      Elf32_Addr d_ptr;         // Pointer value of entry.
  } d_un;
};

// Dynamic table entry for ELF64.
struct Elf64_Dyn
{
  Elf64_Sxword d_tag;           // Type of dynamic table entry.
  union
  {
      Elf64_Xword d_val;        // Integer value of entry.
      Elf64_Addr  d_ptr;        // Pointer value of entry.
  } d_un;
};
~~~

~~~cpp
void parseSoDynamicSection(FILE *fp,struct DataOffest off)
{
    int dynamicnum;
    Elf32_Off init;
    Elf32_Off addr;
    Elf32_Dyn dynamicData;
    int i;

    init = off.dynameicoff;
    dynamicnum = (off.dynameicsize / 8);

    printf("Dynamic:\n");
    printf("\t\tTag\t\t\tType\t\t\tName/Value\n");

    for (i = 0; i < dynamicnum; i++)
    {
        addr = init + (i * 8);
        fseek(fp,addr,SEEK_SET);
        fread(&dynamicData,1,8,fp);
        printf("\t\t0x%08x\t\tNOPRINTF\t\t0x%x\n",dynamicData.d_tag,dynamicData.d_un);
    }

}
~~~

### .symtab解析

**该节区是该so文件的符号表，它在elf.h文件中的数据结构是Elf32_Sym

~~~cpp
// Symbol table entries for ELF32.
struct Elf32_Sym {
  Elf32_Word    st_name;  // Symbol name (index into string table)
  Elf32_Addr    st_value; // Value or address associated with the symbol
  Elf32_Word    st_size;  // Size of the symbol
  unsigned char st_info;  // Symbol's type and binding attributes
  unsigned char st_other; // Must be zero; reserved
  Elf32_Half    st_shndx; // Which section (header table index) it's defined in

  // These accessors and mutators correspond to the ELF32_ST_BIND,
  // ELF32_ST_TYPE, and ELF32_ST_INFO macros defined in the ELF specification:
  unsigned char getBinding() const { return st_info >> 4; }
  unsigned char getType() const { return st_info & 0x0f; }
  void setBinding(unsigned char b) { setBindingAndType(b, getType()); }
  void setType(unsigned char t) { setBindingAndType(getBinding(), t); }
  void setBindingAndType(unsigned char b, unsigned char t) {
    st_info = (b << 4) + (t & 0x0f);
  }
};

// BEGIN android-added for <elf.h> compat
static inline unsigned char ELF32_ST_BIND(unsigned char st_info) { return st_info >> 4; }
static inline unsigned char ELF32_ST_TYPE(unsigned char st_info) { return st_info & 0x0f; }
static inline unsigned char ELF64_ST_BIND(unsigned char st_info) { return st_info >> 4; }
static inline unsigned char ELF64_ST_TYPE(unsigned char st_info) { return st_info & 0x0f; }
// END android-added for <elf.h> compat

// Symbol table entries for ELF64.
struct Elf64_Sym {
  Elf64_Word      st_name;  // Symbol name (index into string table)
  unsigned char   st_info;  // Symbol's type and binding attributes
  unsigned char   st_other; // Must be zero; reserved
  Elf64_Half      st_shndx; // Which section (header tbl index) it's defined in
  Elf64_Addr      st_value; // Value or address associated with the symbol
  Elf64_Xword     st_size;  // Size of the symbol

  // These accessors and mutators are identical to those defined for ELF32
  // symbol table entries.
  unsigned char getBinding() const { return st_info >> 4; }
  unsigned char getType() const { return st_info & 0x0f; }
  void setBinding(unsigned char b) { setBindingAndType(b, getType()); }
  void setType(unsigned char t) { setBindingAndType(getBinding(), t); }
  void setBindingAndType(unsigned char b, unsigned char t) {
    st_info = (b << 4) + (t & 0x0f);
  }
};
~~~

​    1、st_name字段：该字段是一个索引值，指明了该项的名字。
    2、st_value字段：该字段表明了相关联符号的取值。
    3、stz-size字段：该字段指明了每个项所占用的字节数。
    4、st_info和st_other字段：这两个字段指明了符号的类型。
    5、st_shndx字段：相关索引。  

### .text解析

本质上是ARM指令的解析，因为工作量很大，.text节区存储着可执行指令，我们可以通过节区头部的名字锁定.text的偏移地址和大小，找到该节区后，我们会发现这个节区存储的就是arm机器码，直接照着指令集翻译即可，没有其他的结构参考。

![image-20210223201136466](https://tva1.sinaimg.cn/large/008eGmZEly1gnxq8ivp6pj31om0u0npd.jpg)

### Xhook学到了什么？

~~~c
root@debian:~$ arm-linux-androideabi-readelf -S ./libtest.so
 
There are 25 section headers, starting at offset 0x31c8:

Section Headers:
  [Nr] Name              Type            Addr     Off    Size   ES Flg Lk Inf Al
  [ 0]                   NULL            00000000 000000 000000 00      0   0  0
  [ 1] .note.android.ide NOTE            00000134 000134 000098 00   A  0   0  4
  [ 2] .note.gnu.build-i NOTE            000001cc 0001cc 000024 00   A  0   0  4
  [ 3] .dynsym           DYNSYM          000001f0 0001f0 0003a0 10   A  4   1  4
  [ 4] .dynstr           STRTAB          00000590 000590 0004b1 00   A  0   0  1
  [ 5] .hash             HASH            00000a44 000a44 000184 04   A  3   0  4
  [ 6] .gnu.version      VERSYM          00000bc8 000bc8 000074 02   A  3   0  2
  [ 7] .gnu.version_d    VERDEF          00000c3c 000c3c 00001c 00   A  4   1  4
  [ 8] .gnu.version_r    VERNEED         00000c58 000c58 000020 00   A  4   1  4
  [ 9] .rel.dyn          REL             00000c78 000c78 000040 08   A  3   0  4
  [10] .rel.plt          REL             00000cb8 000cb8 0000f0 08  AI  3  18  4
  [11] .plt              PROGBITS        00000da8 000da8 00017c 00  AX  0   0  4
  [12] .text             PROGBITS        00000f24 000f24 0015a4 00  AX  0   0  4
  [13] .ARM.extab        PROGBITS        000024c8 0024c8 00003c 00   A  0   0  4
  [14] .ARM.exidx        ARM_EXIDX       00002504 002504 000100 08  AL 12   0  4
  [15] .fini_array       FINI_ARRAY      00003e3c 002e3c 000008 04  WA  0   0  4
  [16] .init_array       INIT_ARRAY      00003e44 002e44 000004 04  WA  0   0  1
  [17] .dynamic          DYNAMIC         00003e48 002e48 000118 08  WA  4   0  4
  [18] .got              PROGBITS        00003f60 002f60 0000a0 00  WA  0   0  4
  [19] .data             PROGBITS        00004000 003000 000004 00  WA  0   0  4
  [20] .bss              NOBITS          00004004 003004 000000 00  WA  0   0  1
  [21] .comment          PROGBITS        00000000 003004 000065 01  MS  0   0  1
  [22] .note.gnu.gold-ve NOTE            00000000 00306c 00001c 00      0   0  4
  [23] .ARM.attributes   ARM_ATTRIBUTES  00000000 003088 00003b 00      0   0  1
  [24] .shstrtab         STRTAB          00000000 0030c3 000102 00      0   0  1
Key to Flags:
  W (write), A (alloc), X (execute), M (merge), S (strings), I (info),
  L (link order), O (extra OS processing required), G (group), T (TLS),
  C (compressed), x (unknown), o (OS specific), E (exclude),
  y (noread), p (processor specific)
~~~

- `.dynstr`：保存了所有的字符串常量信息。
- `.dynsym`：保存了符号（symbol）的信息（符号的类型、起始地址、大小、符号名称在 `.dynstr` 中的索引编号等）。函数也是一种符号。
- `.text`：程序代码经过编译后生成的机器指令。
- `.dynamic`：供动态链接器使用的各项信息，记录了当前 ELF 的外部依赖，以及其他各个重要 section 的起始位置等信息。
- `.got`：Global Offset Table。用于记录外部调用的入口地址。动态链接器（linker）执行重定位（relocate）操作时，这里会被填入真实的外部调用的绝对地址。
- `.plt`：Procedure Linkage Table。外部调用的跳板，主要用于支持 lazy binding 方式的外部调用重定位。（Android 目前只有 MIPS 架构支持 lazy binding）
- `.rel.plt`：对外部函数直接调用的重定位信息。
- `.rel.dyn`：除 `.rel.plt` 以外的重定位信息。（比如通过全局函数指针来调用外部函数）

<img src="/Users/jesson/Library/Application Support/typora-user-images/image-20210227154525055.png" alt="image-20210227154525055" style="zoom:90%;" />

####ELF经典自制图

https://android.googlesource.com/platform/bionic/+/master/linker/

![img](https://tva1.sinaimg.cn/large/008eGmZEly1go2552hs79j30u018jadc.jpg)

#### 基地址的问题

https://man7.org/linux/man-pages/man3/dl_iterate_phdr.3.html

~~~c
Built-in Function: void __builtin___clear_cache (void *begin, void *end)
This function is used to flush the processor’s instruction cache for the region of memory between begin inclusive and end exclusive. Some targets require that the instruction cache be flushed, after modifying memory containing code, in order to obtain deterministic behavior.

If the target does not require instruction cache flushes, __builtin___clear_cache has no effect. Otherwise either instructions are emitted in-line to clear the instruction cache or a call to the __clear_cache function in libgcc is made.
~~~

PROGBITS

#### 流程处理

**xhook 中执行 PLT hook 的流程：**

1. 读 maps，获取 ELF 的内存首地址（start address）。
2. 验证 ELF 头信息。
3. 从 PHT 中找到类型为 `PT_LOAD` 且 offset 为 `0` 的 segment。计算 ELF 基地址。
4. 从 PHT 中找到类型为 `PT_DYNAMIC` 的 segment，从中获取到 `.dynamic` section，从 `.dynamic` section中获取其他各项 section 对应的内存地址。
5. 在 `.dynstr` section 中找到需要 hook 的 symbol 对应的 index 值。
6. 遍历所有的 `.relxxx` section（重定位 section），查找 symbol index 和 symbol type 都匹配的项，对于这项重定位项，执行 hook 操作。hook 流程如下：

- 读 maps，确认当前 hook 地址的内存访问权限。
- 如果权限不是可读也可写，则用 `mprotect` 修改访问权限为可读也可写。
- 如果调用方需要，就保留 hook 地址当前的值，用于返回。
- 将 hook 地址的值替换为新的值。（执行 hook）
- 如果之前用 `mprotect` 修改过内存访问权限，现在还原到之前的权限。
- 清除 hook 地址所在内存页的处理器指令缓存。

 