# 基于 Java 的测试

## Java 基础

### IO 流

IO 流分类:

1. 字节流，一般是操作文本文件、图片、视频、音频文件的读写
    - Reader
        - **BufferedReader**
        - InputStreamReader -> FileReader
        - StringReader
        - CharArrayReader
        - FilterReader -> PushbackReader
    - Weiter
        - **BufferedWriter**
        - OutputStreamWriter
        - PrintWriter
        - StringWriter
        - PipedWriter
        - CharArrayWriter
        - FilterWriter
2. 字符流，一般是操作文本文件的读写
    - InputStream
        - **FileInputStream**
        - FilterInputStream -> BufferedInputStream、DataInputStream、PushbackInputStream
        - ObjectInputStream
        - PipedInputStream
        - SequenceInputStream
        - ByteArrayInputStream
    - OutputStream
        - **FileOutputStream**
        - FilterOutputStream -> BufferedOutputStream、DataOutputStream、PrintStream
        - ObjectOutputStream
        - PipedOutputStream
        - ByteOutputStream
  
### 反射

反射就是根据类的字节码 class 文件获取一个类的细节，包括构建出来、通过对象去调用方法、访问属性

为什么要用反射？
答: 可以将要创建的对象，要调用的方法写到配置文件，然后通过反射来完成调用，从而降低了代码的耦合性

反射调用方法的实现步骤:

1. 获取类的字节码: 
    - 通过类名去获取: Class<Test> clazz = Test.class;
    - 通过 Class.forName("xx.xx.Test") 方式: Class<Test> clazz = (Class<Test>)Class.forName("xx.xx.Test");
2. 通过字节码去创建对象
    - Test t = clazz.newInstance()，可能出现 NoSuchMethodException(找不到此方法)、InstantiationException(实例化异常) 错误
        - NoSuchMethodException，不能调用有 "private" 修饰的
        - InstantiationException，源对象需要有 "无参构造函数"
    - 反射调用带参构造函数
        - Constructor c = clazz.getConstructor(parameterTypes);
        - c.newInstance(initargs);
3. 反射得到要调用的方法对象 method
    - `Mthod [] m = clazz.getMethods();` 获取当前类的所有 public 方法
    - `Mthod m = clazz.getMethod(name, parameterTypes);` 根据方法名和参数类型获取对应的方法
        - Method mm = clazz.getMethod("setName", String.class);
    - method.getName() 获取方法名
    - method.getParameterTypes() 获取方法的参数类型
    - method.getReturnType() 获取返回值类型
    - method.getParameterCount() 获取方法的参数个数
4. 通过反射调用方法
    - m.invoke(obj, initargs);
        - mm.invoke(t, "张三");

### 修饰符

Java 修饰符主要分为两大类:

1. 访问修饰符，常用: public、protected、默认修饰符、private
2. 非访问修饰符，常用: static、final、abstract、

### TestNG

eclipse 集成 TestNG 步骤:

1. features\org.testing.eclipse_6.9.8.201510130443 文件夹放到 eclipse 的 features 目录下
2. plugins\org.testing.eclipse_6.9.8.201510130443 文件夹放到 eclipse 的 plugins 目录下
3. 重启 eclipse
4. 验证是否集成成功: new -> TestNG class
    - TestNG 使用 @Test 注解或标签来进行测试

 利用 TestNG来设计测试用例执行流程:
 
 1. 编写 testng.xml
 2. 设计套件 <suite>，所谓的套件将所有的测试类整理在一起，形成一套测试用例
    -  执行测试套件，其实执行的是套件中定义的每个测试集底下的所有测试类中的所有测试方法
 3. 设计 <test>，测试集，就是测试类的集合，一般我们可以吧一个模块当做一个 test 测试集
 4. 设计 <classes>，在 test 下的所有测试类
 5. 设计 <class>，对应的相关测试类，name 属性指定测试类的路径
 6. 执行，右键运行 testng.xml
 
 ```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<!-- 测试套件 -->
<suite name="SuiteName" verbose="1">
    <!-- 测试集 -->
    <test name = "allTestsInAClass" >
        <!-- 测试类集合 -->
        <classes>
            <!-- 对应的每个测试类 -->
            <class name="my_testng.Test01" />
            <class name="my_testng.Test02" />
        </classes>
    </test>
</suite>
```
 
 TestNG 常用注解:
 
 1. 配置类型注解
    - @BeforeSuite/@AfterSuite，在某个测试套件（suite）开始之前运行/所有测试方法执行之后运行
    - @BeforeTest/@AfterTest，在某个测试（test）开始之前运行/所有测试类中的测试方法执行之后运行
    - @BeforeClass/@AfterClass，在某个测试类（class）开始之前运行/所有测试方法执行之后运行
    - @BeforeMethod/@AfterMethod，在某个测试方法（method）之前运行/之后运行
 2. 非配置类型的注解
    - @Test，标记测试方法    
    - TestNG 有两种方式向测试方法传递参数:
        - @Parameter，标记参数化测试（数据驱动测试）
            - 利用 testng.xml 定义 <parameter>
                - 使用之前需要声明 DTD，<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
            - @Parameters(value = {"browserName", "parameterName"})
        - 利用 DataProviders，数据提供者，同时提供多组测试数据，需要提供数据提供者的名称，默认是方法名
            - @Test(dataProvider = "方法名")
    - 依赖测试: @Test(dependsOnMethods = "依赖的方法名")
    - 忽略测试: @Test(enabled = false)
    - 超时测试: @Test(timeOut = 1)，会报错 org.testng.internal.thread.ThreadTimeoutException
    - 分组测试: @Test(groups = {"组名"})
        - 需要在 testng.xml 中指定 <groups><run><include name="组名" /></run></groups>
            - 有两种方式使用组 groups，一种是定义 <suite> 全局的组 groups，另一种是定义属于某个 <test> 的组 groups
 
 ## 接口测试
 
 客户端接口调用框架: HttpClient
 
 - HttpClient 是一个实现 http 协议的客户端接口调用技术，可以通过它来 mock 模拟测试工具发起接口请求，完成接口调用
    - 集成 httpclient-4.5.2 依赖: <dependency><groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId><version>4.5.2</version></dependency>
- Api 调用步骤:
    1. 填写接口地址
    2. 指定接口请求方式
    3. 准备数据
    4. 准备请求头数据（不需要就不设置）
    5. 发起请求，获取接口响应数据（状态吗、响应报文或某些特殊的响应数据）
    6. 处理数据
	
## Jenkins

环境搭建:

- Windows:
	- 1.下载 war 包，jenkins.war
	- 2.部署到 tomcat，把下载好的 war 包移动到 .\webapps\ 目录下
	- 3.访问 `http://localhost:8080/jenkins/`
	
配置 Jenkins:

- 全局工具配置:
	- JDK 安装: 别名 jdk，选择本地路径
	- Maven 安装: 去掉自动安装，选择本地路径
- 插件管理
	- 高级:
		- 升级站点 URL: `https://mirrors.tuna.tsinghua.edu.cn/jenkins/updates/update-center.json`
	- 邮件配置，Email Extension插件
		- 勾选了 Use SSL，需要更改 SMTP Port 端口为 465
		- 在测试邮件发送功能时，需要把"系统管理员邮件地址"设为发送人的地址
		
邮件模板:

```
<hr>
(本邮件是程序自动下发的，请勿回复！)<br><hr>
项目名称: $PROJECT_NAME <br><hr>
项目编号: $BUILD_NUMBER <br><hr>
svn版本号: ${SVN_REVISION} <br><hr>
构建状态: $BUILD_STATUS <br><hr>
触发原因: ${CAUSE} <br><hr>
测试报告: <a href="http://127.0.0.1:8887/html/index.html">http://127.0.0.1:8887/html/index.html</a>
<br><hr>
构建日志地址: <a href="${BUILD_URL}console">${BUILD_URL}console</a> <br><hr>
构建地址: <a href="$BUILD_URL">$BUILD_URL</a> <br><hr>
构建报告: <a href="${BUILD_URL}testReport">${BUILD_URL}testReport</a> <br><hr>
变更集: 
```

常见问题:

1. 部署 jenkins 服务器出现 Please wait while Jenkins is getting ready to work ... 一直进不去该怎么办？
	- 把 $user.home/.jenkins/hudson.model.UpdateCenter.xml 文件中的 url 地址修改为国内镜像`https://mirrors.tuna.tsinghua.edu.cn/jenkins/updates/update-center.json`

创建一个 Maven 项目:

- 首先需要安装 maven integration 插件
- 构建一个maven项目:
	- Build:
		- Root POM: pom.xml
		- Goals and options: test，等价于执行命令 $mvn test












