
1. 检查mysql数据库的字符集, 都设置为utf-8
	show variables like '%character%';
+--------------------------+----------------------------+
| Variable_name            | Value                      |
+--------------------------+----------------------------+
| character_set_client     | utf8                       |
| character_set_connection | utf8                       |
| character_set_database   | utf8                       |
| character_set_filesystem | binary                     |
| character_set_results    | utf8                       |
| character_set_server     | utf8                       |
| character_set_system     | utf8                       |
| character_sets_dir       | /usr/share/mysql/charsets/ |
+--------------------------+----------------------------+

2. 指定Workspace的编码为utf-8
	Window -> Preferences -> General -> Workspace -> Text file encoding里的Other -> UTF-8

3. jdk1.8  tomcat8.5版本

4. 执行database.sql脚本，创建数据库

5. 对于js文件报错解决办法,项目右击,选择MyEclipse点击Exclude From Validation,然后再点击Run validation

6. 修改server.xml,在Host下添加
	<Context docBase="/home/arex/program/apache-tomcat-8.5.16/appdata" reloadable="true"  path="/upload"/>
	<Context docBase="Blog" reloadable="true"  path=""/>
   修改Engin标签的defaultHost属性为自己的域名,ex:
   	<Engine name="Catalina" defaultHost="www.arexstorm.com">
   修改Host标签的name属性和Engin标签的defaultHost的属性相同，ex
     <Host name="www.arexstorm.com"  appBase="webapps" unpackWARs="true" autoDeploy="true">
	创建手动创建appdata/Blog/img/uploadImage文件夹

7. 配置deploy.properties
	配置serverhost为服务器的公网IP
	defaultAvatarURL为指定默认的头像
	upload指定上传路径  ex:/home/arex/program/apache-tomcat-8.5.16/appdata
	uploadPath指定访问上传文件的访问path  和 server.xml中保持一致  ex:/upload