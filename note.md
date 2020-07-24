#关于SpringMVC的笔记
##目录
demo:第一个springMVC项目

demo-annotation:注解版SpringMVC

servlet-demo:回归servlet

##注意事项
1.maven操作会出现重置jdk版本的问题，解决
```xml
<build>
        <plugins>
            <!-- 设置jdk版本 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>11.0.2</source>
                    <target>11.0.2</target>
                    <encoding>utf-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
2.Maven由于它的约定大于配置，我们之后可能遇到我们写的配置文件，无法被导出或者生效的问题，解决方案：
```xml
<build>
   <resources>
       <resource>
           <directory>src/main/java</directory>
           <includes>
               <include>**/*.properties</include>
               <include>**/*.xml</include>
           </includes>
           <filtering>false</filtering>
       </resource>
       <resource>
           <directory>src/main/resources</directory>
           <includes>
               <include>**/*.properties</include>
               <include>**/*.xml</include>
           </includes>
           <filtering>false</filtering>
       </resource>
   </resources>
</build>
```
2.出现404问题，需要检查路径书写错误以及打包没有加入依赖的问题

