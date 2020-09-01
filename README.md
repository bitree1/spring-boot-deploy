# spring-boot-deploy
Springboot +maven+thymeleaf项目热部署,打包发布配置
https://blog.csdn.net/bitree1/article/details/108338205

###热部署配置过程：
* 1.spring.thymeleaf.cache=false 关闭页面缓存
* 2.在xml中定义devtools


     <dependency>
      	 <groupId>org.springframework.boot</groupId>
      	 <artifactId>spring-boot-devtools</artifactId>
      </dependency>

     
 在maven-plugin中增加

         <configuration>
           <fork>true</fork>
         </configuration>
* 3.修改idea的设置，如果是eclipse第三部可省略

 	file->setting->build->complier->build project auto.... 勾选
 	
    ctrl+shift+alt+/ 输入registry -》确保 complier.automake.... 被勾选（默认就是选中的）


###jar包的配置

  * 1.利用 mvn package 打成jar
  
  * 2.利用java -jar xxx.jar 启动应用
  
  * 3.在同级目录下添加配置文件，应用生效
  
  * 4.在linux环境下利用 nohup java -jar xxx.jar >> xxx.log &  命令后台运行

  * 5.使用tail -f xxx.log 监控日志文件



####War的配置

* 1.package : jar -> war

* 2.增加pom ，在打包的时候不去加载内嵌tomcat的jar

    
     <dependency>
      <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-tomcat</artifactId>
     //provided 在编译时这个Jar存在，但在打包和运行时，不将jar放到运行环境中
     //complie   在编译、运行。发布的时候jar包都存在，这是一个默认是
     //runtime  运行时，在本地编译时不用这个jar，在运行发布的时候将其加载放置到运行环境中
	 <scope>provided</scope>
	 </dependency>
	 
* 3.原有入口类的main方法实效

    SpringBootServletInitializer的作用就是在tomcat启动的时候，执行内置的configure方法，将其托管给Spring Boot
    
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
     
        System.out.println("War包启动");
        
        return builder.sources(DeployApplication.class);
        }