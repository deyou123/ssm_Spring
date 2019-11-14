# Eclipse 转IDEA读取配置属性文件的路径问题

现在我们使用的是IntelliJ IDEA，idea对这些配置的文件方式很明显和eclipse是不同的。在idea中有一个 [Content Roots](https://www.jetbrains.com/help/idea/2016.2/configuring-content-roots.html)的概念。需要为每一个folder配置相应的Content Roots。Content Roots包括resources、sources、tests等。

- java文件夹被标注为Sources，那么下面的所有子文件夹均为Sources，编译后生成.class文件。

- Resources文件夹被标注为Resources，那么在该文件夹下的配置文件在编译时会自动复制到编译文件夹中。

# 解决方案
那么对于idea来说，就有以下几种解决方案。

1. 将配置文件放到Resources文件夹中，在代码中添加配置文件的虚拟路径。选中文件，右键-Copy Reference，就可以获取到文件的虚拟路径。
2. 把配置文件放在 **com.zdy.ssm.config** 包中，修改config文件夹的Content Roots为Resources，那么编译后，代码也能识别出来。

以上两种解决方案适用于make project方式来编译项目。

针对Maveng工程,在pom 文件中配置

```xml
 <build>
    <finalName>springmvc-study</finalName>
    <resources>
        <resource>
            <directory>${basedir}/src/main/java</directory>
            <includes>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
        </resource>
        <resource>
            <directory>${basedir}/src/main/resources</directory>
        </resource>
    </resources>
 </build>
```



  这样不用对idea设置，也可以使用maven的编译命令编译项目，并复制配置文件到编译好的文件夹中。


  