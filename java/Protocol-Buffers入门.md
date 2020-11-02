#### Protocol Buffers

##### 1.Protocol Buffers是什么？

- Protocol Buffers(简称protobuf)是谷歌的一项技术，用于将结构化的数据序列化，反序列化，常用于网络传输
- 用于结构化数据和字节码之间相互转换
  - 序列化：结构体转换为字节流（编码，向网络发送消息）
  - 反序列化：从字节流转换为结构体（解码，从网络请求中接收消息）
  - 一般用于网络传输，支持多种编程语言
- 类是xml，json等生成和解析，但protobuf效率高于xml，因为protobuf生成的是字节码，可读性比xml差

##### 2.protobuf的使用

1. 定义数据结构，写一个.proto文件,保存为StudentMsg.proto文件

~~~java
message Student {  
    // ID  
    required int32 id = 1;    
    // 姓名  
    required string name = 2;  
    // email  
    optional string email = 3;  
    // 朋友  
    repeated string friends = 4;  
}
~~~

2. 使用protoc.exe生成java代码

~~~java
protoc.exe --java_out=E:\Java PersonMsg.proto
~~~

3. 序列化与反序列化

~~~java
public class TcpClient {  
  
    public static void main(String[] args) throws IOException {  
        Socket socket = null;  
        DataOutputStream out = null;  
        DataInputStream in = null;  
        
        try {  
            socket = new Socket("localhost", 8080);  
            out = new DataOutputStream(socket.getOutputStream());  
            in = new DataInputStream(socket.getInputStream());  
              
            // 创建一个Student传给服务器  
            StudentMsg.Student.Builder builder = StudentMsg.Student.newBuilder();  
            builder.setId(1);  
            builder.setName("客户端");  
            builder.setEmail("xxg@163.com");  
            builder.addFriends("A");  
            builder.addFriends("B");  
            StudentMsg.Student student = builder.build();  
            byte[] outputBytes = student.toByteArray(); // Student转成字节码  
            out.writeInt(outputBytes.length); // write header  
            out.write(outputBytes); // write body  
            out.flush();  
              
            // 获取服务器传过来的Student  
            int bodyLength = in.readInt();  // read header  
            byte[] bodyBytes = new byte[bodyLength];  
            in.readFully(bodyBytes);  // read body  
            StudentMsg.Student student2 = StudentMsg.Student.parseFrom(bodyBytes); // body字节码解析成Student  
            System.out.println("Header:" + bodyLength);  
            System.out.println("Body:");  
            System.out.println("ID:" + student2.getId());  
            System.out.println("Name:" + student2.getName());  
            System.out.println("Email:" + student2.getEmail());  
            System.out.println("Friends:");  
            List<String> friends = student2.getFriendsList();  
            for(String friend : friends) {  
                System.out.println(friend);  
            }  
  
        } finally {  
            // 关闭连接  
            in.close();  
            out.close();  
            socket.close();  
        }  
    }  
}
~~~

##### 日志打印

~~~java
用客户端分别测试上面三个TCP服务器：
服务器输出：
ID:1
Name:客户端
Email:xxg@163.com
Friends:
A
B
客户端输出：
Header:32
Body:
ID:9
Name:服务器
Email:123@abc.com
Friends:
X
Y
~~~

#### 3.Protocol Buffers优缺点

- 优点
  - 性能好/效率高
  - 代码生成机制
  - 支持“向后兼容”和“向前兼容”
  - 支持多种语言
- 缺点
  - 应用不够广（相比xml和json）
  - 二进制格式导致可读性差
  - 缺乏自描述

#### 4.Protocol Buffer 与 XML，JSON的比较

- Protocol Buffer 和 XML，JSON一样都是结构数据格式化的工具，但他们的数据格式差异比较大
- protobuf序列化之后得到的数据是二进制流，不是字符串
- xml和json格式数据信息都包含在了序列化之后的数据中，不需要其他信息就能还原序列化以后的数据
- 但使用protobuf需要事先定义数据的格式（.proto协议文件）,还原一个序列化之后的数据需要使用到这个定义好的数据格式
- 最后，在传输数据量较大的需求场景下，protobuf比xml，json更小，更快，使用和维护更简单
- 而且protobuf可以跨平台，跨语言使用
