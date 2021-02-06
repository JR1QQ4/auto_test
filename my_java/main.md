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
 