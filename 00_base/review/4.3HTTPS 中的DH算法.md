### HTTPS 中的DH算法

**DH是什么？作用干啥？**

~~~java
对称加密算法解决了数据加密的问题。对方要解密，就必须需要这个密钥。如何传递密钥？

如何如何在不安全的信道上安全地传输密钥？

DH算法解决了密钥在双方不直接传递密钥的情况下完成密钥交换，记住一点：DH算法的本质就是双方各自生成自己的私钥和公钥，私钥仅对自己可见，然后交换公钥，并根据自己的私钥和对方的公钥，生成最终的密钥secretKey，DH算法通过数学定律保证了双方各自计算出的secretKey是相同的。

public class Main {
    public static void main(String[] args) {
        // Bob和Alice:
        Person bob = new Person("Bob");
        Person alice = new Person("Alice");

        // 各自生成KeyPair:
        bob.generateKeyPair();
        alice.generateKeyPair();

        // 双方交换各自的PublicKey:
        // Bob根据Alice的PublicKey生成自己的本地密钥:
        bob.generateSecretKey(alice.publicKey.getEncoded());
        // Alice根据Bob的PublicKey生成自己的本地密钥:
        alice.generateSecretKey(bob.publicKey.getEncoded());

        // 检查双方的本地密钥是否相同:
        bob.printKeys();
        alice.printKeys();
        // 双方的SecretKey相同，后续通信将使用SecretKey作为密钥进行AES加解密...
    }
}

class Person {
    public final String name;

    public PublicKey publicKey;
    private PrivateKey privateKey;
    private byte[] secretKey;

    public Person(String name) {
        this.name = name;
    }

    // 生成本地KeyPair:
    public void generateKeyPair() {
        try {
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance("DH");
            kpGen.initialize(512);
            KeyPair kp = kpGen.generateKeyPair();
            this.privateKey = kp.getPrivate();
            this.publicKey = kp.getPublic();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public void generateSecretKey(byte[] receivedPubKeyBytes) {
        try {
            // 从byte[]恢复PublicKey:
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(receivedPubKeyBytes);
            KeyFactory kf = KeyFactory.getInstance("DH");
            PublicKey receivedPublicKey = kf.generatePublic(keySpec);
            // 生成本地密钥:
            KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
            keyAgreement.init(this.privateKey); // 自己的PrivateKey
            keyAgreement.doPhase(receivedPublicKey, true); // 对方的PublicKey
            // 生成SecretKey密钥:
            this.secretKey = keyAgreement.generateSecret();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public void printKeys() {
        System.out.printf("Name: %s\n", this.name);
        System.out.printf("Private key: %x\n", new BigInteger(1, this.privateKey.getEncoded()));
        System.out.printf("Public key: %x\n", new BigInteger(1, this.publicKey.getEncoded()));
        System.out.printf("Secret key: %x\n", new BigInteger(1, this.secretKey));
    }
}
~~~

![img](https://i.loli.net/2021/05/23/91UmOxNDTbpzfH6.png)

![image-20210523225642641](https://i.loli.net/2021/05/23/AFLCteGDsRMN7Wm.png)

![image-20210523225850094](https://i.loli.net/2021/05/23/5Jgy3PAL4rsUHXT.png)

在DH密钥交换过程中主要需要的参数有6个，其中两个(dh_p和dh_g)成为域参数，由服务器选取。协商过程中，客户端和服务器各自生成另外两个参数，相互发送其中一个参数（dh_Ys和dh_Yc)到对端，经过计算，获得预主共享密钥(PreMasterSecret)，我们可以先看一下DH算法的数学基础。

~~~
+--------------------------------------------------------------------+
|                    Global Pulic Elements                           |
|                                                                    |
|       dh_p                         prime number                    |
|       dh_g                         prime number, dh_g < dh_p       |
+--------------------------------------------------------------------+
+--------------------------------------------------------------------+
|                    User A Key Generation                           |
|                                                                    |
|       Select private dh_Ys_a       sh_Ys_a < dh_p                  |
|       Calculate public dh_Yc_a     dh_Yc_a = dh_g^dh_Ys_a mod dh_p |
+--------------------------------------------------------------------+
+--------------------------------------------------------------------+
|                    User B Key Generation                           |
|                                                                    |
|       Select private dh_Ys_b       sh_Ys_b < dh_p                  |
|       Calculate public dh_Yc_b     dh_Yc_b = dh_g^dh_Ys_b mod dh_p |
+--------------------------------------------------------------------+
+--------------------------------------------------------------------+
|               Calculation of Secret Key by User A                  |
|                                                                    |
|       Secret Key premaster       premaster = dh_Yc_b^dh_Yc_a mod p |
+--------------------------------------------------------------------+
+--------------------------------------------------------------------+
|               Calculation of Secret Key by User B                  |
|                                                                    |
|       Secret Key premaster       premaster = dh_Yc_a^dh_Yc_b mod p |
+--------------------------------------------------------------------+

DH算法的主要功能是用于在不安全的通道中交换对称加密的密钥,而不适用于加密数据进行
传输(效率慢)
DH算法原理:
    1.通信双方A和B约定两个大整数 n 和 g (^为次方,mod为取余)(n和g不是随便的,
      需要满足DH算法,不然很容易就暴力破解)
    2.A生成一个随机数a(密钥),a保密,并且把g的a次方对n取余(结果Ka)发送个B
      (Ka = g^a mod n)
    3.B生成一个随机数b,b保密,并且吧G的b次方对n取余(结果Kb)发送给A
      (kb = g^b mod n)
    4.A现在拥有Kb和a,B现在拥有Ka和b
    5.A计算K = Kb ^ a mod n
    6.b计算k = Ka ^ b mon n
    7.Kb ^ a mod n = (g^b mod n) ^ a mon n 
                   = (g ^ a mod n) ^b mod n 
                   (emm,离散不好表示没想明白,数学家证明了上面是成立的)
    8.所以k就是密钥.
    拦截者只能获取到Ka和Kb,n和g,对于大数n和g,利用离散对数来计算k是非常困难的事.
    
大致流程如下:
    1. server 构建密钥对:公钥public key1 和私钥 private key1
    2. server --> client:server向client公开自己的公钥public key1
    3. client (根据public key1,就是上面所述的n和g) 构建自己的本地密钥对:
        public key2 和private key2
    4. client --> server:client 向server 公开自己的公钥public key2
    5. server 使用密钥对:private key1,public key2计算出密钥Y1
    6. client 使用密钥对:private key2,public key1计算出密钥Y2
    7. 根据上面描述的算法,密钥Y1 = Y2
~~~

公开的参数：`dh_p, dh_g`, `dh_Yc_a`, `dh_Yc_a`
非公开参数：`dh_Ys_a`, `dh_Ys_b`, `premaster`

premaster一般算不出来，所以保证了安全。



**https流程总结：**

客户端向服务器发送Client Hello,告诉服务器，我支持的协议版本，加密套件等信息。

a. 服务端收到响应，选择双方都支持的协议，套件，向客户端发送Server Hello。同时服务器也将自己的证书发送到客户端(Certificate)。

b. 服务器利用私钥将客户端随机数，服务器随机数，服务器DH参数签名，生成服务器签名。

服务端向客户端发送服务器DH参数以及服务器签名(Server Key Exchange)。

客户端向服务端发送客户端DH参数(Client Key Exchange)。

之后，客户端利用公钥验证服务器签名，客户端与服务器各自利用服务端DH参数、客户端DH参数生成预主密钥，再通过预主密钥、客户端随机数、服务端随机数生成主密钥(会话密钥)。最后握手完成，所有的消息都通过主密钥加密。

### 分析https的流程必要性

- 只用对称加密来实现
  - 分步骤分析,如果我们选择对称加密来加密数据,client和server都是用相同密钥来加密和解密,实现数据的安全性.但是中间存在两个问题
  - 密钥如何保证传输安全的问题
  - 服务端需要维护大量的密钥对
- 只用非对称加密来实现
  - 非对称加密和对称加密都有相同的问题,就是这个密钥如何传输的问题.
  - 需要维护大量的密钥对

所以我们单独使用其中一种是没有办法很好的实现的,所以https就是将两者结合起来,取其精华

- 只用对称加密来实现
  - 分步骤分析,如果我们选择对称加密来加密数据,client和server都是用相同密钥来加密和解密,实现数据的安全性.但是中间存在两个问题
  - 密钥如何保证传输安全的问题
  - 服务端需要维护大量的密钥对
- 只用非对称加密来实现
  - 非对称加密和对称加密都有相同的问题,就是这个密钥如何传输的问题.
  - 需要维护大量的密钥对

所以我们单独使用其中一种是没有办法很好的实现的,所以https就是将两者结合起来,取其精华。

**整个SSL or TLS 握手流程都是明文传输的,也没有办法进行加密,拦截者可以获取到公钥,random1和random2串,但是dh交换(或者RSA算法)得到的random3是没有办法被拦截者获取的.因此来保证数据传输的安全性。**

 核心问题：

如果client -> server : client Hello 被拦截,拦截者成为中间者,发送自己的证书,公钥,签名给client,如何处理?

答:如果拦截者发送自己的证书,那么client 在解析证书时,对比CA机构信息和域名信息,浏览器根本不会信任这个证书,也就不会信任这个链接,在chrome上访问指定域名(但是返回证书不是这个域名),会直接拒绝链接.即使拦截者自己签发了证书,域名信息对了,但是CA机构大多数内置在浏览器之内,还是不信任链接.

中间是否会被拦截者拦截到隐私数据,或者会被拦截者拿到对称加密密钥解析出数据?

答:我们经过上面的分析发现,拦截者只能拿到random1串和random2串,和非对称加密的公钥,并没有办法拿到RSA算法加密的random3串,也就没有办法拿到对称加密密钥.

拦截者是否能够成为DH算法中的中间者?如果成为中间者,是否可以和client交换获取密钥?

答:这个问题其实就是DH算法能否被破解的问题,答案,很难,当两个共有数都为大数时,基本不可能被破解.首先,拦截者可以获取共有数A和B,client的共有PBc和server的共有数PBs,但是还是很难计算出密钥,所以暂时来看基本很难破解.



