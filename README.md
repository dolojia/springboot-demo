# springboot-mybatis
##### 此项目属于spring boot入门初级
##### 本次主要做了与mybatis的整合
#####  主要功能:
 * 项目正常启动
 * 使用mybatis-generator逆向工程来生成相应的po和mapper
 * 使用mybatis能够连接数据库并查询
 
##### 新增集成log4j2                       --2017-05-25

##### 异步打印日志

##### 新增myBatis分页插件pageHelper        --2017-05-31

##### 集成spring thymeleaf、security       --2017-06-09

##### 集成Swagger2构建RESTful API文档           --2017-06-11

##### 新增Excel导出功能

## Maven镜像

使用国外的Maven仓库速度很慢、甚至连接不上，很容易出错。可以使用aliyun的Maven镜像仓库。

修改maven的settings.xml文件，mirrors部分使用以下配置：

	<mirrors>
	  <mirror>
	    <id>central</id>
	    <mirrorOf>*</mirrorOf>
	    <name>Nexus Aliyun</name>
	    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
	  </mirror>
	</mirrors>