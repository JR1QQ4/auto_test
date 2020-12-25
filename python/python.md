# 基于 Python 的测试

Python 哲学:
Beautiful is better than ugly. 优美胜于丑陋
Explicit is better than implicit. 明了胜于晦涩
Simple is better than complex. 简单胜过复杂
Complex is better than complicated. 复杂胜过凌乱
Flat is better than nested. 扁平胜于嵌套
Sparse is better than dense. 间隔胜于紧凑
Readability counts. 可读性很重要
Special cases aren't special enough to break the rules. 即使假借特例的实用性之名，也不能违背这些原则
Although practicality beats purity. 虽然实用性次于纯度
Errors should never pass silently. 错误不应该被无声地忽略
Unless explicitly silenced. 除非明确的沉默
In the face of ambiguity, refuse the temptation to guess. 当存在多种可能时，不要尝试去猜测
There should be one-and preferably only one-obvious way to do it. 应该有一个，最好只有一个，很明显可以做到这一点
Although that way may not be obvious at first unless you're Dutch. 虽然这种方式可能不容易，除非你是 Python 之父
Now is better than never. 现在做总比不做好
Although never is often better than *right* now. 虽然过去从未比现在好
If the implementation is hard to explain, it's a bad idea. 如果这个实现不容易解释，那么它肯定是个坏主意
If the implementation is easy to explain, it may be a good idea. 如果这个实现容易解释，那么它很可能是个好主意
Namespaces are one honking great idea -- let's do more of those! 命名空间是一种绝妙的理念，应当多加利用

## Selenium 3自动化测试

### Selenium

分层的自动化测试:

- 测试金字塔：Unit(金字塔下面70%) -> Service(20%) ->UI(金字塔上面10%)
- 数据处理层(Unit单元测试) -> 业务逻辑层(Service模块&Web接口测试) -> UI界面层(UI&JS自动化测试)

适合自动化测试的项目:
（1）任务测试明确，不会频繁变动（√）
（2）每日构建后的测试验证
（3）比较频繁的回归测试
（4）软件系统界面稳定，变动少
（5）需要在多平台上运行的相同测试案例、组合遍历型的测试， 以及大量的重复任务
（6）软件维护周期长（√）
（7）项目进度压力不太大
（8）被测软件系统开发较为规范，能够保证系统的可测试性
（9）具备大量的自动化测试平台
（10）测试人员具备较强的编程能力
（11）自动化测试脚本可重复使用（√）

Selenium 1.0 组成:

- Selenium IDE，嵌入在 Firefox 浏览器的插件，可实现简单的浏览器操作的录制与回放功能；后续版本不一样
- Selenium Grid，自动化测试辅助工具，可实现在多态机器上或异构环境中测试用例
- Selenium RC，分为 Client Libraries 和 Selenium Server，前者用于编写测试脚本和负责控制后者的库，后者负责控制浏览器行为
    - Selenium Server 主要分为三部分：Selenium Core(JS函数集合)、Launcher(启动浏览器)、Http Proxy(浏览器的代理设置)

Selenium 2.0 = Selenium 1.0 + WebDriver(RC的替代品，通过原生浏览器支持或者浏览器扩展来直接控制浏览器)

Selenium 3.0 = Selenium 2.0 - Selenium RC，只支持Java 8以上版本，使用前需要下载和设置浏览器驱动，支持 IE 9.0 以上版本

#### 测试环境搭建

1.安装 Python: Python 版本选择 x86(32位)与x64(64位)，勾选"Add Python 3.7 to PATH"
Windows 测试，cmd下输入"python"

2.安装 Selenium，确保 pip(管理 Python 第三方包的)已经在...\Python37\Scripts\pip.exe下，以及目录添加到"环境变量"的"PATH"下面
使用"pip"安装 Selenium 包：`pip install selenium`
pip 的常用命令：`pip install selenium==3.11.0`、`pip install -U selenium`、`pip show selenium`、` pip uninstall selenium`

3.配置 ChromeDriver: 找到 chromedriver.exe 复制到 chrome 的安装目录 application 下，并添加到环境变量中
如果更新过 chrome，可能需要把 chromedriver.exe 放到 C:\Users\...\AppData\Local\Google\Chrome\Application 下
webdriver.Chrome(executable_path=存放位置)，如果上述步骤没有把 chromedriver 添加到环境变量，需要指定位置
ChromeDriver 放在 chrome 目录下是为了方便管理，放在其他地方也行
同理，可以配置 GeckoDriver（Firefox）、IEDriverServer（IE）、OperaDriver（Opera）、MicrosoftWebDriver（Edge）

#### WebDriver API

常用的 8 种元素定位方法:

- id 定位 → find_element_by_id()
- name 定位 → find_element_by_name()
- tag 定位 → find_element_by_tag_name()
- class 定位 → find_element_by_class_name()
- link_text → find_element_by_link_text()
- partial_link_text 定位 → find_element_by_partial_link_text()
- XPath 定位 → find_element_by_xpath()
    - 绝对路径定位: `/html/body/div/div/div/div/from/span/input`
    - 利用元素属性定位: `//input[@id='kw']`、`//*[@name='wd']`、`//input[@autocomplete='off']`、`//input[@type='submit']`
    - 层级与属性结合: `//span[@class='s_ipt_wr']/input`
    - 使用逻辑运算符: `//input[@id='kw' and @class='s_ipt']`
    - 使用 contains 方法: `//span[contains(@calss,'s_ipt_wr')]/input`
    - 使用 text() 方法: `//a[text(),'新闻')]`、`//a[contains(text(),'一个很长的')]`
- CS_selector 定位 → find_element_by_css_selector()，CSS 定位速度比 XPath定位速度快
    - class 选择器: `.classname`
    - id 选择器: `#id`
    - 标签选择器(元素选择器): `element`
    - 通配符选择器: `*`
    - 复合选择器(交集、并集、后代、子元素、同级): `ele.class`、`.class, ele`、`.class ele`、`.class>ele`、`div + input`
    - 伪类选择器: `:link | :visited | :hover | :active`、`:first-child | :last-child | :nth-child(n) | nth-last-child(n)`
    - 属性选择器: `div[class]`、`div[class^=font]`、`div[class$=footer]`、`div[class*=font]`
    - 伪元素选择器: `E::first-letter`、`E::first-line`、`E::selection`、`E::after`、`E::before`

使用 By 定位元素: find_element(由 By 提供的定位的类型, 定位的值)，上述定位元素的 8 种方法实际上是调用的此方法

```
find_element(By.ID,"kw")
find_element(By.NAME,"wd")
find_element(By.CLASS_NAME,"s_ipt")
find_element(By.TAG_NAME,"input")
find_element(By.LINK_TEXT,"新闻")
find_element(By.PARTIAL_LINK_TEXT,"新")
find_element(By.XPATH,"//*[@class='bg s_btn']")
find_element(By.CSS_SELECTOR,"span.bg s_btn_wr>input#su")
```

#### 控制浏览器

控制浏览器窗口大小: `driver.set_window_size(width, height)`
控制浏览器后退、前进: `driver.back()`、`driver.forward()`
模拟浏览器刷新: `driver.forward()`

#### WebDriver 中的常用方法

clear(): 清除文本
send_keys(value): 模拟按键输入
click(): 单击元素
submit(): 提交表单
size: 返回元素的尺寸
text: 获取元素的文本
get_attribute(name): 获得属性值
is_displayed(): 设置该元素是否用户可见

### appium

appium(移动端的 Selenium 自动化测试工具) = application + Selenium
appium 支持 iOS 和 Android 平台上的原生应用(App)、Web 应用(浏览器访问的应用)以及混合应用(原生代码封装网页视图)
appium 类库封装了标准 Selenium 客户端类库，为用户提供常见的 JSON 格式的 Selenium 命令，以及额外的移动设备控制相关命令
appium 客户端实现了移动端和 WebDriver 的协议；appium 服务器定义了官方协议的扩展，例如，安装/卸载 App等

































