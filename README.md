# datasource-proxy Project

# 介绍
datasource-proxy是一个简单的数据库代理，可以实现打印sql语句、打印sql参数以及打印sql执行时间等功能。提供开关，可选择打印或不打印。


# 使用前提
暂时只支持springboot项目


# 使用方法
1.clone本项目
2.使用maven clean install命令将jar包安装到本地仓库
3.添加pom依赖
    <dependency>
    <groupId>com.wkp</groupId>
    <artifactId>datasource-proxy-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
    </dependency>
4.在application.properties文件中添加配置
data.source.proxy.enabled=true
5.如果需要关闭打印功能，修改data.source.proxy.enabled=false即可。