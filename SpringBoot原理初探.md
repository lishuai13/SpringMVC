# SpringBoot原理初探



## Starter启动器

pom文件点进去，发现有父依赖，主要是管理项目的资源过滤及插件！

```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.2.5.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

点进去，还有父依赖，这里才是真正管理SpringBoot应用里面所有依赖版本的地方，SpringBoot的版本控制中心；

```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.2.5.RELEASE</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
</parent>
```

以后我们导入依赖默认是不需要写版本；但是如果导入的包没有在依赖中管理着就需要手动配置版本了；

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

springboot-boot-starter-xxx：就是spring-boot的场景启动器

spring-boot-starter-web：帮我们导入了web模块正常运行所依赖的组件；

SpringBoot将所有的功能场景都抽取出来，做成一个个的starter （启动器），只需要在项目中引入这些starter即可，所有相关的依赖都会导入进来 ， 我们要用什么功能就导入什么样的场景启动器即可 ；我们未来也可以自己自定义 starter；

**

## 自动装配原理

```
//@SpringBootApplication 来标注一个主程序类
//说明这是一个Spring Boot应用
@SpringBootApplication
public class SpringbootApplication {
   public static void main(String[] args) {
     //以为是启动了一个方法，没想到启动了一个服务
      SpringApplication.run(SpringbootApplication.class, args);
   }
}
```

**@SpringBootApplication**

作用：标注在某个类上说明这个类是SpringBoot的主配置类 ， SpringBoot就应该运行这个类的main方法来启动SpringBoot应用；进入这个注解：可以看到上面还有很多其他注解！

```
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(...)
public @interface SpringBootApplication {
    // ......
}
```

**@ComponentScan**

作用：自动扫描并加载符合条件的组件或者bean ， 将这个bean定义加载到IOC容器中，结束

**@SpringBootConfiguration**

作用：SpringBoot的配置类 ，标注在某个类上 ， 表示这是一个SpringBoot的配置类；进入

**@Configuration**

说明这是一个配置类 ，配置类就是对应Spring的 xml 配置文件；进入

**@Component** 

这就说明，启动类本身也是Spring中的一个组件而已，负责启动应用，结束。

**@EnableAutoConfiguration**

开启自动配置功能以前我们需要自己配置的东西，而现在SpringBoot可以自动帮我们配置 ；@EnableAutoConfiguration告诉SpringBoot开启自动配置功能，这样自动配置才能生效；进入

**@AutoConfigurationPackage** 

自动配置包，进入

**@Import****(AutoConfigurationPackages.Registrar.****class****)**

*@import ：Spring底层注解@import ， 给容器中导入一个组件*

Registrar.class 作用：将主启动类的所在包及包下面所有子包里面的所有组件扫描到Spring容器 ，结束。

**@Import****(AutoConfigurationImportSelector.****class****)

自动配置导入选择器，那么它会导入哪些组件的选择器呢？我们点击去这个类看源码：

1、这个类中有一个这样的方法

```
// 获得候选的配置
protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
    //这里的getSpringFactoriesLoaderFactoryClass（）方法
    //返回的就是我们最开始看的启动自动导入配置文件的注解类；EnableAutoConfiguration
    List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
    Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.");
    return configurations;
}
```

2、这个方法又调用了  SpringFactoriesLoader 类的静态方法！我们进入SpringFactoriesLoader类loadFactoryNames() 方法  

```
    public static List<String> loadFactoryNames(Class<?> factoryClass, @Nullable ClassLoader classLoader) {
        String factoryClassName = factoryClass.getName();
        //这里它又调用了 loadSpringFactories 方法
        return (List)loadSpringFactories(classLoader).getOrDefault(factoryClassName, Collections.emptyList());
    }
```

3、这个方法又调用了 loadSpringFactories 方法

```
private static Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader) {
    //获得classLoader ， 我们返回可以看到这里得到的就是EnableAutoConfiguration标注的类本身
    MultiValueMap<String, String> result = (MultiValueMap)cache.get(classLoader);
    if (result != null) {
        return result;
    } else {
        try {
            //去获取一个资源 "META-INF/spring.factories"
            Enumeration<URL> urls = classLoader != null ? classLoader.getResources("META-INF/spring.factories") : ClassLoader.getSystemResources("META-INF/spring.factories");
            LinkedMultiValueMap result = new LinkedMultiValueMap();
 
 
            //将读取到的资源遍历，封装成为一个Properties
            while(urls.hasMoreElements()) {
                URL url = (URL)urls.nextElement();
                UrlResource resource = new UrlResource(url);
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                Iterator var6 = properties.entrySet().iterator();
 
 
                while(var6.hasNext()) {
                    Entry<?, ?> entry = (Entry)var6.next();
                    String factoryClassName = ((String)entry.getKey()).trim();
                    String[] var9 = StringUtils.commaDelimitedListToStringArray((String)entry.getValue());
                    int var10 = var9.length;
 
 
                    for(int var11 = 0; var11 < var10; ++var11) {
                        String factoryName = var9[var11];
                        result.add(factoryClassName, factoryName.trim());
                    }
                }
            }
            cache.put(classLoader, result);
            return result;
        } catch (IOException var13) {
            throw new IllegalArgumentException("Unable to load factories from location [META-INF/spring.factories]", var13);
        }
    }
}
```

4、发现一个多次出现的文件：spring.factories

![image.png](https://cdn.nlark.com/yuque/0/2020/png/1537108/1595843281512-93ec28f8-ca54-4e83-a0be-42bc36cd192b.png)

拿出其中的WebMvc自动配置类，可以看出这就是一个带有@Condition的JavaConfg文件，也就是一个“配置文件”

![image.png](https://cdn.nlark.com/yuque/0/2020/png/1537108/1595843415138-41750ed3-327b-44fd-aa5d-35db7caebf15.png)

**总结：自动配置真正实现是从classpath中搜寻所有的META-INF/spring.factories配置文件 ，并将其中对应的包下的配置项，通过反射实例化为对应标注了 @Configuration的JavaConfig形式的IOC容器配置类 ， 然后将这些都汇总成为一个实例并加载到IOC容器中，但是，因为所有的配置类都会有****@Condition（注解见其他笔记）条件，只有当条件满足时，配置类才会生效，而条件主要就是我们是否加入了它们的starter。

**



## 自动配置类

拿出一个自动配置类，你会发现他会包含以下标签

**@Configuration**：这是一个配置类

**@ConditionXXX**：这个自动装配类生效需要一定的条件

**@EnableConfigurationProperties**：绑定了一个属性文件

![image.png](https://cdn.nlark.com/yuque/0/2020/png/1537108/1595851552607-3197246b-5d3b-4060-8e37-4105942c73e5.png)

进入绑定的属性文件，你会发现一个熟悉的注解：@ConfigurationProperties（prefix="xxx"）

即绑定这个属性文件到我们可以自定义的yml文件，我们可以在这里定义自动配置类属性文件中的一些值

![image.png](https://cdn.nlark.com/yuque/0/2020/png/1537108/1595851853842-ed3e6ba0-ec6a-4f70-b626-91f481eceb44.png)**
**



**总结 ：根据当前不同的条件判断，决定这个配置类是否生效！**

- 一但这个配置类生效；这个配置类就会给容器中添加各种组件；
- 这些组件的属性是从对应的properties类中获取的，这些类里面的每一个属性又是和配置文件绑定的；
- 所有在配置文件中能配置的属性都是在xxxxProperties类中封装着；
- 当我们需要自定义这些值时，就能够在yml中使用对应的字段配置

**自动装配总结**

**1、SpringBoot启动会加载大量的自动配置类**

**2、我们看我们需要的功能有没有在SpringBoot默认写好的自动配置类当中；**

**3、我们再来看这个自动配置类中到底配置了哪些组件；（只要我们要用的组件存在在其中，我们就不需要再手动配置了，Starter开启）**

**4、给容器中自动配置类添加组件的时候，会从properties类中获取某些属性。我们只需要在配置文件中指定这些属性的值即可；**

**xxxxAutoConfigurartion：自动配置类；给容器中添加组件**

**xxxxProperties:封装配置文件中相关属性；**

**
**

**Tips：可在yml中使用debug： true来检测自动配置类是否生效**

在Positive matches这一栏中的既是生效的配置类（自动装配上了，我们就可以使用了）

**![image.png](https://cdn.nlark.com/yuque/0/2020/png/1537108/1595852827995-a1f5ba89-3268-4f26-92af-32f425207aa1.png)**

在Negative matches：这一栏中的既是没有生效的配置类，无法使用其功能

**![image.png](https://cdn.nlark.com/yuque/0/2020/png/1537108/1595852906932-fb8fc6a2-c6aa-46ab-9529-6e3bae299849.png)**