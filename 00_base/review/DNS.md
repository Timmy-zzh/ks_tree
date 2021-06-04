### DNS是什么

**DNS（Domain Name System），它的作用就是根据域名，查出对应的 IP 地址**，它是 HTTP 协议的前提。只有将域名正确的解析成 IP 地址后，后面的 HTTP 流程才可以继续进行下去。

DNS 服务器的要求，一定是高可用、高并发和分布式的服务器。它被分为多个层次结构。

- 根 DNS 服务器：返回顶级域 DNS 服务器的 IP 地址。
- 顶级域 DNS 服务器：返回权威 DNS 服务器的 IP 地址。
- 权威 DNS 服务器：返回相应主机的 IP 地址。

当开始 DNS 解析的时候，如果 LocalDNS 没有缓存，那就会向 LocalDNS 服务器请求（通常就是运营商），如果还是没有，就会一级一级的，从根域名查对应的顶级域名，再从顶级域名查权威域名服务器，最后通过权威域名服务器，获取具体域名对应的 IP 地址。

### DNS存在的问题

- DNS劫持
- **LocalDNS 调度**、跨网、跨运营商的问题
- DNS 的 TTL修改、跨网结算修改、内容缓存服务器

等等问题。

![图片](https://i.loli.net/2021/04/17/QrISlxFCUhnJMcw.jpg)

###  HTTPDNS

DNS 不仅支持 UDP，它还支持 TCP，但是大部分标准的 DNS 都是基于 UDP 与 DNS 服务器的 53 端口进行交互。利用 HTTP 协议与 DNS 服务器的 80 端口进行交互。不走传统的 DNS 解析，从而绕过运营商的 LocalDNS 服务器，有效的防止了域名劫持，提高域名解析的效率。

![图片](https://i.loli.net/2021/04/17/cDayG2gS439ZVuF.jpg)

### Android端实现

OkHttp默认使用系统DNS服务InetAddress进行域名解析，但同时也暴露了自定义DNS服务的接口，通过该接口我们可以优雅地使用HttpDns。

~~~java

public class OkHttpDns implements Dns {
    private static final Dns SYSTEM = Dns.SYSTEM;
    HttpDnsService httpdns;//httpdns 解析服务
    private static OkHttpDns instance = null;
    private OkHttpDns(Context context) {
        this.httpdns = HttpDns.getService(context, "account id");
    }
    public static OkHttpDns getInstance(Context context) {
        if(instance == null) {
            instance = new OkHttpDns(context);
        }
        return instance;
    }
    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        //通过异步解析接口获取ip
        String ip = httpdns.getIpByHostAsync(hostname);
        if(ip != null) {
            //如果ip不为null，直接使用该ip进行网络请求
            List<InetAddress> inetAddresses = Arrays.asList(InetAddress.getAllByName(ip));
            Log.e("OkHttpDns", "inetAddresses:" + inetAddresses);
            return inetAddresses;
        }
        //如果返回null，走系统DNS服务解析域名
        return Dns.SYSTEM.lookup(hostname);
    }
}
~~~

~~~java
private void okhttpDnsRequest() {
    OkHttpClient client = new OkHttpClient.Builder()
    .dns(OkHttpDns.getInstance(getApplicationContext())) //构建client 传入OkHttpDns
    .build();
    Request request = new Request.Builder()
    .url("http://www.aliyun.com")
    .build();
    Response response = null;
    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                DataInputStream dis = new DataInputStream(response.body().byteStream());
                int len;
                byte[] buff = new byte[4096];
                StringBuilder result = new StringBuilder();
                while ((len = dis.read(buff)) != -1) {
                    result.append(new String(buff, 0, len));
                }
                Log.d("OkHttpDns", "Response: " + result.toString());
            }
        });
}
~~~

### 深挖DNS Protocal

**以太网数据帧从数据进入协议栈的封包过程：**

![image](https://i.loli.net/2021/04/17/6XWmHAnI2OqcTrC.png)

![img](https://i.loli.net/2021/04/17/gKVGix4jWSAmzDh.png)