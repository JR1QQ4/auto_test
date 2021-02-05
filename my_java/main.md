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
