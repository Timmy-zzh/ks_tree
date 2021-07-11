##### 网络

- TCP 传输协议层
  - 三次握手
- Http1.1
  - 长链接 connection - Keep Alive
- Http2
  - 二进制分帧
  - 多路复用
  - 头部压缩
  - 服务器推送
- Https
  - 数字证书
- WebSocket
  - HTML5协议
  - 连接持久化的协议

```html
Upgrade: websocket
Connection: Upgrade
```

##### OkHttp

- 每发起一个请求会对应创建一个Request 对象
- 将请求交给OkHttpClient 进行处理，将请求包装成RealCall 对象，然后丢给Dispatcher 进行调度
- 异步请求会交给内部的线程池进行执行，最后会调用Runnable的run方法
- 在子线程中会通过拦截器获取最后的结果Response对象
- 拦截器包括：
  - 自定义拦截器
  - 重试拦截器
  - Bridge 桥接拦截器，处理器请求头
  - Cache 缓存拦截器
  - Connect 连接拦截器，通过Socket建立连接，内部维护了一个连接池
  - CallServer 拦截器，就是通过已经建立的链接HttpStream 进行数据的读写，HttpCodec

