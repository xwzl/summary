在 ik 分词器的 config/IKAnalyzer.cfg.xml 文件中可以指定远程扩展字典

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
	<comment>IK Analyzer 扩展配置</comment>
	<!--用户可以在这里配置自己的扩展字典 -->
	<entry key="ext_dict">custom/mydict.dic;custom/single_word_low_freq.dic</entry>
	 <!--用户可以在这里配置自己的扩展停止词字典-->
	<entry key="ext_stopwords">custom/ext_stopword.dic</entry>
 	<!--用户可以在这里配置远程扩展字典 -->
	<entry key="remote_ext_dict">location</entry>
 	<!--用户可以在这里配置远程扩展停止词字典-->
	<entry key="remote_ext_stopwords">http://xxx.com/xxx.dic</entry>
</properties>
```
其中 location 是指一个 url，比如 http://yoursite.com/customDict.txt，该请求只需满足以下两点即可完成分词热更新。
1. 该 http 请求需要返回两个头部(header)，一个是 Last-Modified，一个是 ETag，这两者都是字符串类型，只要有一个发生变化，该插件就会去抓取新的分词进而更新词库。
2. 该 http 请求返回的内容格式是一行一个分词，换行符用 \n 即可。

可以将需自动更新的热词放在一个 UTF-8 编码的 .txt 文件里，放在 nginx 下，只要更新这个 txt 文档，ik 分词器就会读取新的分词规则，从而做到不停止 es 从而在线更新分词规则。

# elasticsearch安全认证配置

    [root@tbds-172-27-0-174 elasticsearch]# bin/elasticsearch-certutil ca ##生成证书，直接全部回车到最后
    
    [root@tbds-172-27-0-174 elasticsearch]# bin/elasticsearch-certutil cert --ca elastic-stack-ca.p12 ##生成密钥直接全部回车到最后

拷贝证书相关文件到其他ES节点，所有ES节点都需要拷贝

创建证书存放目录，与配置文件中的xpack.security.transport.ssl.keystore.path能对应上
    cat elasticsearch.yml
    
    cluster.name: es-test
    node.name: node-1
    path.data: /home/elk/data
    path.logs: /home/elk/logs
    network.host: 192.168.2
    http.port: 9200
    discovery.seed_hosts: ["192.168.222.52", "192.168.222.51"]
    cluster.initial_master_nodes: ["node-1", "node-2"]
    http.cors.enabled: true
    http.cors.allow-origin: "*"
    http.cors.allow-headers: Authorization
    xpack.security.enabled: true
    xpack.security.transport.ssl.enabled: true
    xpack.security.transport.ssl.verification_mode: certificate
    xpack.security.transport.ssl.keystore.path: certs/elastic-certificates.p12
    xpack.security.transport.ssl.truststore.path: certs/elastic-certificates.p12

登录所有ES节点，切换到es用户，启动ES服务

    [root@tbds-172-27-0-174 elasticsearch]# bin/elasticsearch-setup-passwords interactive ##手动设置密码方式
    
    [root@tbds-172-27-0-174 elasticsearch]# bin/elasticsearch-setup-passwords auto ##自动生成

# x-pack

进入 elasticsearch 容器
    
    # 随机生成密码
    ./bin/elasticsearch-setup-passwords auto
    
    # 生成指定指定密码
    ./bin/elasticsearch-setup-passwords interactive
