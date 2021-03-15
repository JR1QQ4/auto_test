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

- （1）任务测试明确，不会频繁变动（√）
- （2）每日构建后的测试验证
- （3）比较频繁的回归测试
- （4）软件系统界面稳定，变动少
- （5）需要在多平台上运行的相同测试案例、组合遍历型的测试， 以及大量的重复任务
- （6）软件维护周期长（√）
- （7）项目进度压力不太大
- （8）被测软件系统开发较为规范，能够保证系统的可测试性
- （9）具备大量的自动化测试平台
- （10）测试人员具备较强的编程能力
- （11）自动化测试脚本可重复使用（√）

Selenium 1.0 组成:

- Selenium IDE，嵌入在 Firefox 浏览器的插件，可实现简单的浏览器操作的录制与回放功能；后续版本不一样
- Selenium Grid，自动化测试辅助工具，可实现在多态机器上或异构环境中测试用例
- Selenium RC，分为 Client Libraries 和 Selenium Server，前者用于编写测试脚本和负责控制后者的库，后者负责控制浏览器行为
    - Selenium Server 主要分为三部分：Selenium Core(JS函数集合)、Launcher(启动浏览器)、Http Proxy(浏览器的代理设置)

Selenium 2.0 = Selenium 1.0 + WebDriver(RC的替代品，通过原生浏览器支持或者浏览器扩展来直接控制浏览器)

Selenium 3.0 = Selenium 2.0 - Selenium RC，只支持Java 8以上版本，使用前需要下载和设置浏览器驱动，支持 IE 9.0 以上版本

#### Selenium WebDriver 工作原理

以打车为例来理解以下 WebDriver 的工作原理，当我们打车时，会有三个角色:

- 乘客: 他/她告诉出租车司机去哪里，大概怎么走
    - 工程师写的自动化测试代码: 自动化测试代码发送请求给浏览器的驱动
- 出租车司机: 他按照成客的要求来操纵出租车
    - 浏览器的驱动: 它来解析这些自动化测试的代码，解析后把它们发送给浏览器
- 出租车: 出租车按照司机的操控完成真正的行驶，把乘客送到目的地
    - 浏览器: 执行浏览器驱动发来的指令，并最终完成工程师想要的操作

API  <-->  驱动(Server)  <-->  浏览器:

- API 和 驱动 之间是一个 C/S 架构，它们之间的数据传输格式是 JSON，通信协议是 HTTP

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

定位一组元素: 把 element 换成 elements 就是定位一组元素，例如，`find_elements_by_id()`

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

- 控制浏览器窗口大小: `driver.set_window_size(width, height)`
- 控制浏览器后退、前进: `driver.back()`、`driver.forward()`
- 模拟浏览器刷新: `driver.forward()`

#### WebDriver 中的常用方法

- clear(): 清除文本
- send_keys(value): 模拟按键输入
- click(): 单击元素
- submit(): 提交表单
- size: 返回元素的尺寸
- text: 获取元素的文本
- get_attribute(name): 获得属性值
- is_displayed(): 设置该元素是否用户可见

#### 鼠标操作：

from selenium.webdriver import ActionChains

在 WebDriver 中， 与鼠标操作相关的方法都封装在 ActionChains 类中:

- perform(): 执行 ActionChains 类中存储的所有行为
- context_click(): 右击
- double_click(): 双击
- drag_and_drop(): 拖动
- move_to_element(): 鼠标悬停

#### 键盘操作

from selenium.webdriver.common.keys import Keys

send_keys()方法可以用来模拟键盘输入，包括按键，甚至是组合键:

- send_keys(Keys.BACK_SPACE): 删除键（BackSpace）
- send_keys(Keys.SPACE): 空格键（Space）
- send_keys(Keys.TAB): 制表键（Tab）
- send_keys(Keys.ESCAPE): 回退键（Esc）
- send_keys(Keys.ENTER): 回车键（Enter）
- send_keys(Keys.CONTROL,'a'): 全选（Ctrl+a）
- send_keys(Keys.CONTROL,'c'): 复制（Ctrl+c）
- send_keys(Keys.CONTROL,'x'): 剪切（Ctrl+x）
- send_keys(Keys.CONTROL,'v'): 粘贴（Ctrl+v）
- send_keys(Keys.F1) send_keys(Keys.F12): 键盘 F1 到 键盘 F12

#### 获得验证信息

在进行 Web 自动化测试中，用得最多的几种验证信息是 title、 current_url 和 text:

- title: 用于获取当前页面的标题
- current_url: 用于获取当前页面的 URL
- text: 用于获取当前页面的文本信息

#### 设置元素等待

1.显式等待是 WebDriver 等待某个条件成立则继续执行，否则在达到最大时长时抛出超时异常(TimeoutException):

- 包: `from selenium.webdriver.support.ui import WebDriverWait`
- 格式: `WebDriverWait(driver, timeout超时时间, poll_frequency=0.5检测的间隔时间, ignored_exceptions=None异常信息)`
- WebDriverWait()一般与 until()或 until_not()方法配合使用: `until(method, message=″)`、`until_not(method, message=″)`
    - method 一般是判定元素存在，使用包: `from selenium.webdriver.support import expected_conditions as EC`
    - expected_conditions 类提供的预期条件判断方法: 
        - title_is: 判断当前页面的标题是否等于预期
        - title_contains: 判断当前页面的标题是否包含预期字符串
        - presence_of_element_located: 判断元素是否被加在 DOM 树里，并不代表该元素一定可见
        - visibility_of_element_located: 判断元素是否可见（可见代表元素非隐藏，并且元素的宽和高都不等于 0）
        - visibility_of: 与上一个方法作用相同，上一个方法的参数为定位，该方法接收的参数为定位后的元素
        - presence_of_all_elements_located: 判断是否至少有一个元素存在于 DOM 树中
        - text_to_be_present_in_element: 判断某个元素中的 text 是否包含预期的字符串
        - text_to_be_present_in_element_value: 判断某个元素的 value 属性是否包含预期的字符串
        - frame_to_be_available_and_switch_to_it: 判断该表单是否可以切换进去，返回 True 则切换进去，否则返回 False
        - invisibility_of_element_located: 判断某个元素是否不在 DOM 树中或不可见
        - element_to_be_clickable: 判断某个元素是否可见并且是可以点击的
        - staleness_of: 等到一个元素从 DOM 树中移除
        - element_to_be_selected: 判断某个元素是否被选中，一般用在下拉列表中
        - element_selection_state_to_be: 判断某个元素的选中状态是否符合预期
        - element_located_selection_state_to_be: 与上一个方法相同，只是上一个参数为定位后的元素，该方法接收的参数为定位
        - alert_is_present: 判断页面上是否存在 alert

2.is_displayed()判断元素是否可见: `element.is_displayed()`

3. implicitly_wait()方法可用来实现隐式等待: `driver.implicitly_wait(10)`
异常处理: `from selenium.common.exceptions import NoSuchElementException`、`except NoSuchElementException as e`

4.time: `time.sleep(3)`

#### 多表单切换

在 frame/iframe 表单嵌套页面的应用中，使用 switch_to.frame()切换表单: 

- `login_frame = driver.find_element_by_css_selector('iframe[id^="x-URS-iframe"]')`
- `driver.switch_to.frame(login_frame)`

通过 switch_to.default_content()回到最外层的页面

#### 多窗口切换

WebDriver 提供的 switch_to.window()方法可以实现在不同的窗口间切换:

- current_window_handle: 获得当前窗口句柄
- window_handles: 返回所有窗口的句柄到当前会话
- switch_to.window(): 切换到相应的窗口

#### 警告框处理

在 WebDriver 中处理 JavaScript 生成的 alert、 confirm 和 prompt 十分简单，具体做法是，首先使用 switch_to.alert()方法定位，
然后使用 text、 accept、 dismiss、 send_keys 等进行操作:

- text: 返回 alert、 confirm、 prompt 中的文字信息
- accept(): 接受现有警告框
- dismiss(): 解散现有警告框
- send_keys(): 在警告框中输入文本（如果可以输入的话）

#### 下拉框处理

- from selenium.webdriver.support.select import Select
- Select 类: 用于定位<select>标签
- select_by_value(): 通过 value 值定位下拉选项
- select_by_visible_text(): 通过 text 值定位下拉选项
- select_by_index(): 根据下拉选项的索引进行选择。第一个选项为 0，第二个选项为 1

#### 上传文件

在 Web 页面中一般通过以下两种方式实现文件上传:

- 普通上传: 将本地文件路径作为一个值放在 input 标签中，通过 form 表单将这个值提交给服务器，即通过 send_keys()实现文件上传
- 插件上传: 一般是指基于 Flash、 JavaScript 或 Ajax 等技术实现的上传功能，可以使用 AutoIt 来实现

#### 下载文件

火狐浏览器下载文件:

```python
import os
from selenium import webdriver
fp = webdriver.FirefoxProfile()
fp.set_preference("browser.download.folderList", 2)  # 0 表示默认路径，2 表示指定路径
fp.set_preference("browser.download.dir", os.getcwd())  # 保存的目录
fp.set_preference("browser.helperApps.neverAsk.saveToDisk","binary/octet-stream")  # 下载文件的类型
driver = webdriver.Firefox(firefox_profile=fp)  # 传入设置项
driver.get("https://pypi.org/project/selenium/#files")
driver.find_element_by_partial_link_text("selenium-3.141.0.tar.gz").click()
```

Chrome 浏览器下载文件: 

```python
import os
from selenium import webdriver
options = webdriver.ChromeOptions()
prefs = {'profile.default_content_settings.popups': 0,
'download.default_directory': os.getcwd()}  # 0 表示禁止弹出下载窗口，后面表示下载目录
options.add_experimental_option('prefs', prefs)
driver = webdriver.Chrome(chrome_options=options)
driver.get("https://pypi.org/project/selenium/#files")
driver.find_element_by_partial_link_text("selenium-3.141.0.tar.gz").click()
```

#### 操作 Cookie

- get_cookies(): 获得所有 Cookie
- get_cookie(name): 返回字典中 key 为"name"的 Cookie
- add_cookie(cookie_dict): 添加 Cookie
- delete_cookie(name,optionsString): 删除名为 OpenString 的 Cookie
- delete_all_cookies():  删除所有 Cookie

#### 调用 JavaScript

WebDriver 提供了 execute_script()方法来执行 JavaScript 代码:

- 调整浏览器滚动条位置: `driver.execute_script(window.scrollTo(左边距,上边距));`

textarea 文本框中输入内容:

```python
text = "input text"
js = "document.getElementById('id').value='" + text + "';"
driver.execute_script(js)
```

#### 处理 HTML5 视频播放

driver.execute_script("return arguments[0].currentSrc;", video)
driver.execute_script("arguments[0].play()", video)

currentSrc 返回当前音频/视频的 URL。如果未设置音频/视频，则返回空字符串
load()、 play()和 pause() 控制视频的加载、 播放和暂停

#### 滑动解锁

```python
from time import sleep
from selenium import webdriver
from selenium.webdriver import ActionChains
from selenium.common.exceptions import UnexpectedAlertPresentException

driver = webdriver.Chrome()
driver.get('https://sc.chinaz.com/jiaobendemo.aspx?downloadid=2017111056145')

result_iframe = driver.find_element_by_id('iframe')
driver.switch_to.frame(result_iframe)

slider = driver.find_elements_by_class_name('slide-to-unlock-handle')[0]
action = ActionChains(driver)
action.click_and_hold(slider).perform()  # 按下左键

for index in range(3):
    try:
        action.reset_actions()
        action.move_by_offset(100, 0).perform()
    except UnexpectedAlertPresentException:
        break
    finally:
        sleep(.1)

# 打印警告框提示
success_text = driver.switch_to.alert.text
print(success_text)
driver.switch_to.alert.accept()
from selenium.common.exceptions import UnexpectedAlertPresentException
driver = webdriver.Chrome()
driver.get("https://www.helloweba.com/demo/2017/unlock/")
# 定位滑动块
slider = driver.find_elements_by_class_name("slide-to-unlock-handle")[0]
action = ActionChains(driver)
action.click_and_hold(slider).perform()  # 单击并按下鼠标左键
for index in range(200):
    try:
        action.move_by_offset(2, 0).perform()  # 移动鼠标
    except UnexpectedAlertPresentException:
        break
    action.reset_actions()
    sleep(0.1) # 等待停顿时间
# 打印警告框提示
success_text = driver.switch_to.alert.text  # 重置 action
print(success_text)
```

#### 窗口截图

WebDriver 提供了截图函数 save_screenshot ()，可用来截取当前窗口:

```python
from selenium import webdriver
driver = webdriver.Chrome()
driver.get('http://www.baidu.com')
# 截取当前窗口， 指定截图图片的保存位置
driver.save_screenshot("./files/baidu_img.png")
```

#### 关闭窗口

除了 quit()方法，WebDriver 还提供了 close()方法，用来关闭当前窗口

#### Selenium Grid

Selenium Grid 分为两个版本： Grid1 和 Grid2，Grid2 不再提供单独的 jar 包，其功能已经集成到 Selenium Server 中

Selenium Server 环境配置:

1. 下载 Selenium Server，地址`https://www.selenium.dev/downloads/`，得到 selenium-server-standalone-xxx.jar 文件
2. 配置 Java 环境
3. 运行 Selenium Server，`java -jar selenium-server-standalone-xxx.jar`

Selenium Grid 工作原理:

- Grid 分布式测试的建立是由一个 Hub（主节点）和若干个 node（代理节点）组成的
    - Hub 用来管理各个 node 的注册和状态信息，接收远程客户端代码的请求调用，把请求的命令转发给 node 来执行
- 使用 Grid 远程执行测试代码与直接运行 Selenium 是一样的，只是环境启动的方式不一样，需要同时启动一个 Hub 和至少一个 node
    - `$ java -jar selenium-server-standalone-x.xx.x.jar -role hub`，Hub 默认端口号为 4444
    - `$ java -jar selenium-server-standalone-x.xx.x.jar -role node -port 5556`，node 默认端口号为 5555，node 可多次运行
- 通过浏览器访问 Grid 的控制台，地址为 http://127.0.0.1:4444/grid/console

如果想在其他主机上启动 node，则必须满足以下要求:

- 本地 Hub 所在主机与远程 node 所在主机之间可以用 ping 命令通信
- 远程主机必须安装 Java 环境，因为需要运行 Selenium Server
- 远程主机必须安装测试用例需要的浏览器及驱动文件，驱动文件需要设置环境变量
- 步骤:
    - (1)启动本地 Hub 所在主机（IP 地址为 172.16.10.66）: `java -jar selenium-server-standalone-x.xx.x.jar -role hub`
    - (2)启动远程 node 所在主机（IP 地址为 172.16.10.34）: 
        - `java -jar selenium-x.xx.x.jar -role node -port 5555 -hub http://172.16.10.66:4444/grid/register`

```python
# 使用 selenium server 测试，需要先运行: $ java -jar .\selenium-server-standalone-3.141.59.jar
from selenium.webdriver import Remote, DesiredCapabilities
# 必须传入 desired_capabilities 参数，否则会报 SessionNotCreatedException 错误
driver = Remote(desired_capabilities=DesiredCapabilities.CHROME.copy())
driver.get("http://www.baidu.com")
driver.quit()
```

Grid 执行过程:

1. 启动 Hub 日志: `$ java -jar selenium-server.jar -role hub`
2. 启动 node 日志: `$ java -jar selenium-server.jar -role node -port 5555`
3. 执行测试脚本，会看到 Hub 和 node 的日志
    - Hub 获得创建 session 请求，判断浏览器是什么
    - node 给 Driver 发送请求，由 Driver 驱动对应浏览器启动，并生成 session ID，在 driver.quit()时，删除该 session ID

创建远程节点:

- 通过 Windows 命令提示符（或在 Linux 终端）启动一个 Hub: `$ java -jar selenium-server-standalone-3.13.0.jar -role hub`
- 启动远程节点以一台 Ubuntu 主机为例: `java -jar selenium.jar -role node -hub http://192.168.183.1:4444/grid/register`

### 自动化测试模型

- 库(Library): 面向对象的代码组织形成的库叫类库，面向过程的代码组织形成的库叫函数库
- 框架(Framework): 为解决一个或一类问题而开发的产品，用户一般只需使用框架提供的类或函数，即可实现全部功能
- 工具(Tools): 提供了更高层次的封装，屏蔽了底层的代码，提供了单独的操作界面供用户使用。例如， UFT（QTP）

自动化测试模型：

- 线性测试: 通过录制或编写对应用程序的操作步骤会产生相应的线性脚本，每个线性脚本相对独立，单纯地模拟用户完整的操作场景
- 模块化与类库: 把重复的操作单独封装成公共模块
- 数据驱动测试: 数据的改变驱动自动化测试的执行， 最终引起测试结果的改变，把数据驱动所需要的测试数据参数化
- 关键字驱动测试: 表驱动测试或基于动作字测试，把自动化操作封装为“关键字”，避免测试人员直接接触代码，例如 Robot Framework

模块化与参数化

### unittest 单元测试框架

单元测试框架功能：

- 提供测试用例组织和执行
- 提供丰富的断言方法
- 提供丰富的日志

需要测试的代码 calculator.py:

```python
class Calculator:
    def __init__(self, a: int, b: int):
        self.a = int(a)
        self.b = int(b)
    def add(self):
        return self.a + self.b
    def sub(self):
        return self.a - self.b
    def mul(self):
        return self.a * self.b
    def div(self):
        return self.a / self.b
```

测试代码 test_calculator.py:

```python
import unittest
from calculator import Calculator
class TestCalculator(unittest.TestCase):
    def test_add(self):
        c = Calculator(3, 5)
        result = c.add()
        self.assertEqual(result, 8, '加法运算失败')
    def test_sub(self):
        c = Calculator(7, 2)
        result = c.sub()
        self.assertEqual(result, 5, '减法运算失败')
    def test_mul(self):
        c = Calculator(3, 3)
        result = c.mul()
        self.assertEqual(result, 10, '乘法运算失败')
    def test_div(self):
        c = Calculator(6, 2)
        result = c.div()
        self.assertEqual(result, 3, '除法运算失败')
if __name__ == "__main__":
    unittest.main()
```

unittest 编写测试用例规则:

- 创建的测试类，必须要继承 unittest 模块的 TestCase 类
- 创建的测试方法，必须以 test 开头
- 调用 unittest 的 main()来执行测试用例
- 测试结果中，"."表示运行通过的，"F"表示运行失败的，"E"表示运行错误的， "s"表示运行跳过的测试用例

重要的概念:

- Test Case 是最小的测试单元，用于检查特定输入集合的特定返回值，unittest 提供了 TestCase 基类，测试类必须继承该类
- Test Suite 测试套件是测试用例、测试套件或两者的集合，用于组装一组要运行的测试
- Test Runner 是一个组件，用于协调测试的执行并向用户提供结果，可以使用图形界面、文本界面或返回特殊值来展示执行测试的结果
- Test Fixture 代表执行一个或多个测试所需的环境准备，以及关联的清理动作，例如 setUp()/tearDown()等

```python
import unittest
from my_python.my_selenium.project_one.testcases.base import test_user_register
from my_python.my_selenium.project_one.testcases.base.test_user_register import TestUserRegister

# 1. 创建加载器
loader = unittest.TestLoader()
# 2. 创建测试套件
suite = unittest.TestSuite()

# 3.1 通过方法名、类名添加测试套件 
# suite.addTest(loader.loadTestsFromName('test_register_code_error', TestUserRegister))
# suite.addTest(loader.loadTestsFromName('test_register_code_right', TestUserRegister))

# 3.2 通过测试类名添加测试套件
suite.addTest(loader.loadTestsFromTestCase(TestCategory))

# 3.3 通过测试模块添加测试套件
# suite.addTest(loader.loadTestsFromModule(test_user_register))

# 3.4 直接添加测试套件
# suite.addTest(TestUserRegister('test_register_code_error'))
# suite.addTest(TestUserRegister('test_register_code_right'))

# 4. 创建运行组件
runner = unittest.TextTestRunner(verbosity=2)
# 4.1 运行测试套件
runner.run(suite)
```

断言方法：

- assertEqual(a, b) 与 assertNotEqual(a, b)
- assertTrue(x) 与 assertFalse(x)
- assertIs(a, b) 与 assertIsNot(a, b)
- assertIsNone(x) 与 assertIsNotNone(x)
- assertIn(a, b) 与 assertNotIn(a, b)
- assertIsInstance(a, b) 与 assertNotIsInstance(a, b)

#### 执行范围与执行顺序

unittest 中的 TestLoader 类提供的 discover()方法可以从多个文件中查找测试用例: 

- defaultTestLoader 类，可以使用其子类或方法创建实例: `discover(start_dir， pattern='test*.py'， top_level_dir=None)`
    - start_dir: 待测试的模块名或测试用例目录
    - pattern='test*.py': 测试用例文件名的匹配原则。此处匹配文件名以"test"开头的".py"类型的文件，星号"*"表示任意多个字符
    - top_level_dir=None: 测试模块的顶层目录，如果没有顶层目录，则默认为 None
    - `unittest.TestLoader().discover()`和`unittest.defaultTestLoader.discover()`都可以

unittest 测试用例的执行顺序：

- 默认根据 ASCII码 的顺序加载测试用例的(数字与字母的顺序为 0~9，A~Z，a~z)
- 不是根据创建顺序从上到下执行的；想让某个测试文件先执行，可以在命名上加以控制
- discover()方法和 main()方法的执行顺序是一样的
- 测试套件 TestSuite 类，通过 addTest()方法按照一定的顺序来加载测试用例

unittest 提供了跳过测试和预期失败的装饰器，可以修饰类和方法:

- @unittest.skip(reason): 无条件地跳过装饰的测试，需要说明跳过测试的原因
- @unittest.skipIf(condition, reason): 如果条件为真，则跳过装饰的测试
- @unittest.skipUnless(condition, reason): 当条件为真时，执行装饰的测试
- @unittest.expectedFailure(): 不管执行结果是否失败，都将测试标记为失败

Fixture 看作夹心饼干外层的两片饼干，这两片饼干就是 setUp/tearDown，unittest 还提供了更大范围的 Fixture:
 
- setUpModule/tearDownModule: 在整个模块的开始与结束时被执行
- setUpClass/tearDownClass: 在测试类的开始与结束时被执行，需要通过@classmethod 进行装饰，方法的参数为 cls
- setUp/tearDown: 在测试用例的开始与结束时被执行

#### 测试报告

HTMLTestRunner 是 unittest 的一个扩展，它可以生成易于使用的 HTML 测试报告

```python
import unittest
import os
import time
from my_python.my_selenium.unit_report.HTMLTestRunner import HTMLTestRunner
path_dir = os.path.dirname(__file__)
timer = time.strftime("%Y-%m-%d_%H_%M-%S")
report_file = path_dir + "\\report\\" + timer + ".html"
test_dir = path_dir + "\\" + "test_case"
test_suite = unittest.defaultTestLoader.discover(test_dir)
with open(report_file, 'w', encoding='utf-8') as f:
    runner = HTMLTestRunner(stream=f,
                            title="百度测试报告",
                            description="运行环境： Windows 10, Chrome 浏览器")
    runner.run(test_suite)
```

Parameterized 是 Python 的一个参数化库，同时支持 unittest、 Nose 和 pytest 单元测试框架

```python
import unittest
from time import sleep
from selenium import webdriver
from parameterized import parameterized

class TestBaidu(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        cls.base_url = "https://www.baidu.com"
    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()
    def baidu_search(self, search_key):
        self.driver.get(self.base_url)
        self.driver.find_element_by_id("kw").send_keys(search_key)
        self.driver.find_element_by_id("su").click()
        sleep(3)
    @parameterized.expand([
        ("case1", "selenium"),
        ("case2", "unittest"),
        ("case3", "parameterized"),
    ])
    def test_search(self, name, search_key):
        self.baidu_search(search_key)
        self.assertEqual(self.driver.title, search_key + "_百度搜索")
if __name__ == '__main__':
    unittest.main(verbosity=2)
```

DDT（Data-Driven Tests）数据驱动测试，ddt 模块是针对 unittest 单元测试框架设计的扩展库。允许使用不同的测试数据来运行一个测试用例
，并展示为多个测试用例

```python
import unittest
from time import sleep
from selenium import webdriver
from ddt import ddt, data, file_data, unpack
import os
path_dir = os.path.dirname(os.path.dirname(__file__))
@ddt
class TestBaidu(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        cls.base_url = "https://www.baidu.com"
    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()
    def baidu_search(self, search_key):
        self.driver.get(self.base_url)
        self.driver.find_element_by_id("kw").send_keys(search_key)
        self.driver.find_element_by_id("su").click()
        sleep(3)

    # 参数化使用方式一
    @data(["case1", "selenium"], ["case2", "ddt"], ["case3", "python"])
    @unpack
    def test_search1(self, case, search_key):
        print("第一组测试用例： ", case)
        self.baidu_search(search_key)
        self.assertEqual(self.driver.title, search_key + "_百度搜索")

    # 参数化使用方式二
    @data(("case1", "selenium"), ("case2", "ddt"), ("case3", "python"))
    @unpack
    def test_search2(self, case, search_key):
        print("第二组测试用例： ", case)
        self.baidu_search(search_key)
        self.assertEqual(self.driver.title, search_key + "_百度搜索")

    # 参数化使用方式三
    @data({"search_key": "selenium"}, {"search_key": "ddt"}, {"search_key": "python"})
    @unpack
    def test_search3(self, search_key):
        print("第三组测试用例： ", search_key)
        self.baidu_search(search_key)
        self.assertEqual(self.driver.title, search_key + "_百度搜索")
    
    # 参数化读取 JSON 文件
    @file_data(path_dir + '\\test_data\\ddt_data_file.json')
    def test_search4(self, search_key):
        print("第四组测试用例： ", search_key)
        self.baidu_search(search_key)
        self.assertEqual(self.driver.title, search_key + "_百度搜索")

    # 参数化读取 yaml 文件，需要先下载 yaml 文件解释器，pip install PyYAML 
    @file_data(path_dir + '\\test_data\\ddt_data_file.yaml')
    def test_search5(self, case):
        search_key = case[0]["search_key"]
        print("第五组测试用例： ", search_key)
        self.baidu_search(search_key)
        self.assertEqual(self.driver.title, search_key + "_百度搜索")
if __name__ == '__main__':
    unittest.main(verbosity=2)
```

自动发送邮件，SMTP（Simple Mail Transfer Protocol）是简单邮件传输协议，smtplib 模块提供了简单的 API 用来实现发送邮件功能：

```python
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
# 邮箱服务器地址
smtp_addr = ""
# 发件人邮箱地址和令牌
send_user = ""
send_pwd = ""
# 收件人邮箱地址
receive_user = ""
# 发送邮件主题
subject = '我是 Subject'
# 编写 HTML 类型的邮件正文
msg = MIMEMultipart()
body = MIMEText('Subject: Solong.\nDear Alice, so long and thanks for all the fish. Sincerely, Bob', 'plain', 'utf-8')
msg['Subject'] = subject
msg['From'] = send_user
msg['To'] = receive_user
msg.attach(body)
# 带附件
with open('python.md', 'rb') as f:
    send_att = f.read()
att = MIMEText(send_att, 'text/x-markdown', 'utf-8')
# att['Content-Type'] = 'application/x-genesis-rom'  # 指定附件内容类型
att['Content-Type'] = 'application/octet-stream'  # 表示二进制流
att["Content-Disposition"] = 'attachment; filename="python.md"'  # 指定附件的文件名
msg.attach(att)
# 发送邮件
smtp = smtplib.SMTP(host=smtp_addr, port=25)
smtp.login(send_user, send_pwd)
smtp.sendmail(send_user, [send_user, receive_user], msg.as_string())
smtp.quit()
```

使用 yagmail 发送邮件，`pip install yagmail`:

```python
import yagmail
# user 发送人，password 令牌，host SMTP地址
# to 收件人，subject 主题，contents 内容，attachments 附件
yagmail.SMTP(user='', password='', host='').send(to='', subject='subject', contents='body',attachments=[''])
```

### Page Object

Page Object 是 UI 自动化测试项目开发实践的最佳设计模式之一，它的主要特点体现在对界面交互细节的封装上，使测试用例更专注于业务的操作
，从而提高测试用例的可维护性。

尽管该术语是 page 对象， 但并不意味着需要针对每个页面建立一个这样的对象。例如，页面上有重要意义的元素可以独立为一个 page 对象。 
经验法则的目的是通过给页面建模，使其对应用程序的使用者变得有意义。

Page Object 应该遵循以下原则进行开发：

- Page Object 应该易于使用
- 有清晰的结构，如 PageObjects 对应页面对象， PageModules 对应页面内容
- 只写测试内容， 不写基础内容
- 在可能的情况下防止样板代码
- 不需要自己管理浏览器
- 在运行时选择浏览器，而不是类级别
- 不需要直接接触 Selenium

poium 是一个基于 Selenium/appium 的 Page Object 测试库，最大的特点是简化了 Page 层元素的定义:

```python
# pip install poium
import unittest
from selenium import webdriver
from poium import Page, NewPageElement
from time import sleep
class BaiduPage(Page):
    """百度 Page 层，百度页面封装操作到的元素"""
    search_input = NewPageElement(id_='kw', describe="搜索输入框")
    search_button = NewPageElement(id_='su', describe="搜索按钮")
class TestBaidu(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()
    def test_baidu_search_case(self):
        page = BaiduPage(self.driver)
        page.get("https://www.baidu.com")
        page.search_input = "selenium"
        page.search_button.click()
        sleep(3)
        self.assertEqual(page.get_title, "selenium_百度搜索")
if __name__ == '__main__':
    unittest.main(verbosity=2)
```
 
### pytest 单元测试框架

pytest 是一个第三方单元测试框架， 更加简单、灵活，而且提供了更加丰富的扩展，弥补了 unittest 在做 Web 自动化测试时的一些不足

- 安装: `$ pip install pytest`
- 运行:
    - 命令行运行: `$ pytest 参数 参数值`，方便集成 Jenkins
        - pytest 运行时参数，`$ pytest --help` 或者 `$ pytest -h` 查看帮助:
            - "-s" 参数用于关闭捕捉，从而输出打印信息，用于显示测试函数中 print() 函数输出
            - "-v" 参数用于增加测试用例冗长，用于显示每个测试函数的执行结果
            - "-q" 只显示整体测试结果
            - "-x, --exitfirst" 在第一个错误或测试失败时立即退出 
            - `pytest -k add test_assert.py`，运行名称中包含某字符串的测试用例
            - `pytest -q test_assert.py`，减少测试的运行冗长
            - `pytest -x test_fail.py`，如果出现一条测试用例失败，则退出测试
            - `pytest ./test_dir`，运行测试目录
            - `pytest test_fixtures_02.py::TestMultiply::test_numbers_5_6`，指定特定类或方法执行
        - `$ pytest --setup-show test.py` 会显示执行顺序
    - main 函数中运行: `pytest.main(['-sv', 'test_01.py'])` 或者 `pytest.main(['-s', '-v', 'test_01.py'])`
- 断言: 使用的 python 自带的断言 `assert`
    - 测试相等: `assert add(3, 4) == 7`，unittest 用 assertEqual()
    - 测试不相等: `assert add(17, 22) != 50`
    - 测试大于或等于: `assert add(17, 22) <= 50`
    - 测试小于或等于: `assert add(17, 22) >= 38`
    - 测试包含与不包含: `assert b in a`、`assert b not in a`
    - 判断是否为 True: `assert is_prime(13)`、`assert not is_prime(4)`、`assert is_prime(6) is not True`
- 安装: pytest 安装时默认在...\Python37\Scripts\目录下生成 pytest.exe，因此能在 cmd 中运行 pytest 命令：
    - 规范: 测试文件和测试函数必须以 test 开头；测试类以 Test 开头，并且不能带有 init 方法

#### 标记

pytest 标记:

- 查找测试策略:
    - 默认情况下，pytest 会递归查找当前目录下所有以 test 开始或结尾的 python 脚本
    - 并执行文件内的所有以 test 开头或结束的函数和方法
- 标记测试函数: 由于某些原因(如 test_func2 的功能尚未开发完成)，我们只想执行指定的测试函数，在 pytest 中有几种方法可以解决
    - 第一种，显示指定函数名，通过 `::` 标记，如 `test_no_mark.py::test_func1` 只执行 test_no_mark.py 中的 test_func1
    - 第二种，使用模糊匹配，使用 `-k` 选项标记，如 `$ pytest -k func1 test_no_mark.py`
    - 第三种，使用 `pytest.mark` 在函数上进行标记，步骤:
        - 1.注册标签名，通过 `.ini` 配置文件，格式如下
        - 2.给用例打上标记
        - 3.运行 `pytest -m do -s test_mark.py`

```shell script
[pytest]
markers =
    do: need test
    undo: do not need test
```

#### Fixture

Fixture 通常用来对测试方法、测试函数、测试类和整个测试文件进行初始化或还原测试环境:

- 模块级别和函数级别的 Fixture:
    - setup_module/teardown_module: 在当前文件中，在所有测试用例执行之前与之后执行，优先级1
    - setup_function/teardown_function: 在每个测试函数之前与之后执行，优先级2
    - setup/teardown: 在每个测试函数之前与之后执行。这两个方法同样可以作用于类方法，优先级3
- 类级别和方法级别的 Fixture
    - setup_class/teardown_class: 在当前测试类的开始与结束时执行，需要指定为类方法 `@classmethod`
    - setup_method/teardown_method: 在每个测试方法开始与结束时执行
    - setup/teardown: 在每个测试方法开始与结束时执行，同样可以作用于测试函数

```python
import pytest
@pytest.fixture()
def init():
    print("---init---")
def test01(init): 
    print("---test01---")
def test02(init):
    print("---test01---")
if __name__ == '__main__':
    pytest.main(['-sv'])

@pytest.fixture(scope="module")
def open():
  print("打开浏览器")
  
  yield  # 解决测试方法后销毁清除数据

  print("执行 teardown")
  print("最后关闭浏览器")

@pytest.fixture(autouse=True)  # 应用在每个测试方法中
def init():
  print("打开浏览器")
```

#### 参数化

pytest 本身是支持参数化的，不需要额外安装插件:

- 使用的工具就是 `@pytest.mark.parametrize(argnames, argvalues)`
    - argnames 参数的名称，对应函数的名称，多个参数使用 `逗号` 隔开
    - argvalues 参数的值，可以接受的参数类型有: 列表、元组、字典
    - 还可以设置 id
    - indirect=True，把传入的字符串当成函数

```python
import pytest
import math
@pytest.mark.skip
@pytest.mark.parametrize(
    "books, exponent, expected",  # 参数的名称，对应测试函数中的参数
    [(2, 2, 4),  # 参数列表
     (2, 3, 8),
     (1, 9, 1),
     (0, 9, 0)],
    ids=["case1", "case2", "case3", "case4"]  # 默认为None，测试用例的名称，个数必须和参数列表个数一致
)
def test_pow(base, exponent, expected):
    assert math.pow(base, exponent) == expected
data1 = [
    pytest.param(1, 2, 3, id="(a + b):pass"),
    pytest.param(4, 5, 10, id="(a + b):fail"),
]
def add(a, b):
    return a + b
@pytest.mark.parametrize("a, b, expected", data1)
def test_parameterize_1(a, b, expected):
    assert add(a, b) == expected
if __name__ == '__main__':
    pytest.main(['-sv'])  # 增加测试用例冗长
```

#### 测试报告

allure:

- 安装: `$ pip install allure-pytest`
- 下载 allure，并配置环境变量用于命令行使用: 
    - `http://dl.bintray.com/qameta/generic/io/qameta/allure/allure/2.7.0/`
    - `https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/`
- 运行:
    - 生成测试报告 必须在命令行执行；首先切换到项目的目录，然后再来执行
    - 生成只能在线运行的测试报告: `$ pytest --alluredir ./report`
    - 展示只能在线运行的测试报告: `$ allure serve ./report`，不是 server 是 serve
    - 生成最终版的测试报告: `$ allure generate ./report`，会生成一个 allure-report 的文件夹
    - 运行查看最终版的测试报告: `$ allure open -h 127.0.0.1 -p 8888 ./allure-report`
    
| 使用方法 | 参数值 | 参数说明 |
| ---- | ---- | ---- |
| @allure.epic() | epic描述 | 敏捷里面的概念，定义史诗，往下是feature |
| @allure.feature() | 模块名称 | 功能点的描述，往下是story |
| @allure.story() | 用户故事 | 用户故事，往下是title |
| @allure.title(用例的标题) | 用例的标题 | 重命名html报告名称 |
| @allure.testcase() | 测试用例的链接地址 | 对应功能测试用例系统里面的链接 |
| @allure.issue() | 缺陷 | 对应缺陷管理系统里面的链接 |
| @allure.description() | 用例描述 | 测试用例的描述 |
| @allure.step() | 操作步骤 | 测试用例的步骤 |
| @allure.severity() | 用例等级 | blocker，critical，normal，minor，trivial |
| @allure.link() | 链接 | 定义一个链接，在测试报告展现 |
| @allure.attachment() | 附件 | 报告添加附件 |

```python
import pytest
import allure
@pytest.fixture(scope="session")
def login():
    print("用户登录")
@allure.step("步骤1: 点xxx")
def step_1():
    print("step 1")
@allure.step("步骤2: 点xxx")
def step_2():
    print("step 2")
@allure.feature("编辑页面")
class TestEditPage(object):
    """编辑页面"""
    @allure.story("这是第一个xxx的用例")
    @allure.testcase("测试文章管理")
    def test_1(self, login):
        """用例描述: 先登陆，再去执行xxx"""
        step_1()
        step_2()
        print("xxx")
    @allure.story("打开a页面")
    @allure.testcase("测试发表文章")
    def test_2(self, login):
        """用例描述: 先登录，再去执行yyy"""
        print("yyy")
if __name__ == '__main__':
    # 注意生成测试报告 必须在命令行执行
    # $ pytest --alluredir ./report test_allure.py
    # $ allure serve ./report，启动 allure 查看报告
    pytest.main(['--alluredir', './report', 'test_allure.py'])
```

其他方式生成测试报告: 

1. 生成 JUnit XML 文件: `$ pytest ./test_dir --junit-xml=./report/log.xml`
2. 生成在线测试报告: `> pytest ./test_dir --pastebin=all`

conftest.py 是 pytest 特有的本地测试配置文件:

```python
# 创建 test_project/conftest.py 测试配置文件
import pytest
# 设置测试钩子
@pytest.fixture()
def test_url():
    return "http://www.baidu.com"

# 创建 test_project/test_sub.py 测试用例文件
def test_baidu(test_url):
    print(test_url)
```

#### pytest 扩展

pytest-html 可以生成 HTML 格式的测试报告:

- 安装: `$ pip install pytest-html`
- 运行: `$ pytest ./ --html=./report/result.html`

pytest-rerunfailures 可以在测试用例失败时进行重试:

- 安装: `$ pip install pytest-rerunfailures`
- 运行: `$ pytest -v test_rerunfailures.py --reruns 3 --reruns-delay 2`

pytest-assume 多条断言有失败也都运行

- 安装: `$ pip install pytest-assume`
- 执行: `$ pytest.assume(1 == 4)`，替换原来的断言

pytest-xdist 多线程并行与分布式执行

- 安装: `$ pip install pytest-xdist`
- 多个 CPU 并行执行用例，直接加 `-n 3`就是并行数量

pytest-parallel 扩展可以实现测试用例的并行运行:

- 安装: `$ pip ins tall pytest-parallel`，新版本可能存在问题，可回退版本 `pip install pytest-parallel==0.0.10`
- 运行: `pytest -q test_parallel.py --tests-per-worker auto`，Windows 下自动分配线程数

pytest-dependency 解决依赖问题:

- 安装 `$ pip install pytest-dependency`
- 使用:
    - 设置为依赖 `@pytest.mark.dependency(name='admin_login')`
    - 添加依赖 `@pytest.mark.dependency(depends=["admin_login"], scope="module")`

#### 日志

logging: `import logging`

- logging 模块定义的常用函数:
    - logging.debug()、logging.info()、logging.warning()、logging.error()、logging.critical()
    - logging.log(level, msg, *args, **kwargs)、logging.basicConfig(**kwargs)
- logging 模块的四大组件:
    - loggers: 提供应用程序代码直接使用的接口
    - handlers: 用于将日志记录发送到指定的目的位置
    - filters: 提供更细颗粒的日志过滤功能，用于决定哪些日志将会被输出（其它的日志记录将会被忽略）
    - formatters: 用于控制日志信息的最终输出格式

logging.basicConfig() 函数说明(参数名称-描述):

- filename 指定日志输出目标文件的文件名，指定该设置项后日志信息就不会输出到控制台了
- filemode 指定日志文件的打开模式，默认为 `a`，需要注意的是，该选项要在 filename 指定时才有效
- format 指定日志格式字符串，即指定日志输出时所包含的字段信息以及它们的顺序；logging 模块定义的格式字段下面会列出
- level 指定日志器的日志级别
- stream 指定日志输出目标 stream，如 sys.stdout、sys.stderr 以及网络 stream；需要说明的是，stream 和 filename 不能同时提供
- style Python3.2 中新添加的配置项；指定 format 格式字符串的风格，可取值 `%`、`(` 和 `$`，默认 `%`
- handlers Python3.3 中新添加的配置项；该选项如果被指定，它应该是一个创建了多个 Handlers 的可迭代对象，这些 handler 将会被添加到 
root logger；需要说明的是: filename、stream 和 handlers 这三个配置项只能有一个存在，不能同时出现 2 个或 3 个，否则会引发异常

logging 模块的格式字符串(使用格式-描述):

- %(asctime)s 日志事件发生的时间-人类可读时间，如 2003-07-08 16:49:45.896
- %(created)f 日志事件发生的时间-时间戳，就是当时调用 time.time() 函数返回的值
- %(relativeCreated)d 日志事件发生的时间相对于 logging 模块加载时间的相对毫秒数
- %(msecs)d 日志事件发生事件的毫秒部分
- %(levelname)s 该日志记录的文字形式的日志级别
- %(levelno)s 该日志记录的数字形式的日志级别 (10, 20, 30, 40, 50)
- %(name)s 所使用的日志器名称；默认是 root，因为默认使用的是 rootLogger
- %(message)s 日志记录的文本内容，通过 `msg % args` 计算得到的
- %(pathname)s 调用日志记录函数的源码文件的全路径
- %(filename)s pathname 的文件名部分，包含文件后缀
- %(module)s filename 的名称部分，不包含后缀
- %(lineno)d 调用日志记录函数的源代码所在的行号
- %(funcName)s  调用日志记录函数的函数名
- %(process)d 进程 ID
- %(processName)s 进程名称
- %(thread)d 线程 ID
- %(threadName)s 线程名称

Logger 类相关方法:

- Logger.setLevel() 设置日志器将会处理的日志消息的最低严重级别
- Logger.addHandler() 和 Logger.removeHandler() 为该 logger 对象添加和移除一个 handler 对象
- Logger.addFilter() 和 Logger.removeFilter() 为该 logger 对象添加和移除一个 filter 对象
- Logger.debug(),Logger.info(),Logger.warning(),Logger.error(),Logger.critical() 创建一个与它们的方法名对应等级的日志记录
- Logger.exception() 创建一个类似与 Logger.error() 的日志消息
- Logger.log() 需要获取一个明确的日志 level 参数来创建一个日志记录

```python
import logging
import logging.handlers
import datetime
# 方法一: 基础版本
# format_str = "【%(levelname)s】%(asctime)s - %(filename)s - %(module)s - %(lineno)d: %(message)s"
# logging.basicConfig(
#     filename="log.log",
#     level=logging.INFO,
#     format=format_str
# )
# logging.debug("debug")
# logging.info("info")
# logging.warning("warning")
# logging.error("error")
# logging.critical("critical")

# 方法二： 使用 Logger 对象
logger = logging.getLogger("myLogger")
logger.setLevel(logging.DEBUG)
# rf_handler = logging.handlers.TimedRotatingFileHandler('all.log', when='midnight', interval=1, backupCount=7,
#                                                       atTime=datetime.time())
# rf_handler.setFormatter(logging.Formatter("%(asctime)s - %(levelname)s - %(message)s"))
# logger.addHandler(rf_handler)
fh_error = logging.FileHandler('error.log')
fh_error.setLevel(logging.ERROR)
fh_error.setFormatter(logging.Formatter("%(asctime)s - %(levelname)s - %(filename)s - %(module)s[%(lineno)d] : %(message)s"))
fh_all = logging.FileHandler('all.log')
fh_all.setLevel(logging.DEBUG)
fh_all.setFormatter(logging.Formatter("[%(levelname)s] - %(asctime)s - %(filename)s - %(module)s[%(lineno)d] : %(message)s"))
logger.addHandler(fh_error)
logger.addHandler(fh_all)
logger.debug("debug")
logger.info("info")
logger.warning("warning")
logger.error("error")
logger.critical("critical")
```

#### 构建 Web 自动化测试项目

相比 unittest 单元测试框架， pytest 更适合用来做 UI 自动化测试，它提供了以下功能:

1. 在 unittest 中，浏览器的启动或关闭只能基于测试方法或测试类；pytest 可以通过 conftest.py 文件配置全局浏览器的启动或关闭， 
整个自动化测试项目的运行只需启动或关闭一次浏览器即可，这将大大节省测试用例执行时间
2. 测试用例运行失败截图。 unittest 本身是不支持该功能的，pytest-html 可以实现测试用例运行失败自动截图，只需在 conftest.py 中
做相应的配置即可
3. 测试用例运行失败重跑。 UI 自动化测试的稳定性一直是难题，虽然可以通过元素等待来增加稳定性，但有很多不可控的因素（如网络不稳定） 
会导致测试用例运行失败，pytest-rerunfailures 可以轻松实现测试用例运行失败重跑

命名与设计规范:

- 对于 page 层的封装存放于 page/目录，命名规范为"xxx_page.py"
- 对于测试用例的编写存放于 test_dir/目录，命名规范为"test_xxx.py"
- 每一个功能点对应一个测试类，并且以"Test"开头，如"TestLogin"、"TestSearch"等
- 在一个测试类下编写功能点的所有的测试用例，如"test_login_user_null"、"test_login_pawd_null"、"test_login_success"等

依赖库说明：

- selenium: Web UI 自动化测试
- pytest: Python 第三方单元测试框架
- pytest-html: pytest 扩展，生成 HTML 格式的测试报告
- pytest-rerunfailures: pytest 扩展，实现测试用例运行失败重跑
- click: 命令行工具开发库，使用过后不能直接运行 pytest，而是`python run_tests.py -m run`或`python run_tests.py -m debug`
- poium: 基于 Selenium/appium 的 Page Object 测试库

### Jenkins 持续集成

持续集成（Continuous Integration， CI）: 

- 软件集成就是把多种软件的功能集成到一个软件里，或者把软件的各部分组合在一起
- 持续集成是一种软件开发实践，即团队开发成员经常集成他们的工作，每次集成都通过自动化构建（包括编译、发布、自动化测试）来验证

Jenkins 是基于 Java 开发的一种持续集成工具，在使用 Jenkins 之前需要配置 Java 环境

环境搭建:

- 下载 Tomcat: 是针对 Java 的一个开源中间件服务器（容器），基于 Java 的 Web 项目可以通过 Tomcat 运行
    - 通常需要将 Web 项目放到 webapps/目录下。进入 bin/目录，双击 startup.bat 文件， 启动 Tomcat 服务器，运行 Web 项目
- 下载 Jenkins
    - 解压缩之后得到 jenkins.msi 文件，双击进行安装，将其安装到 Tomcat 的 webapps 目录下
    - 安装完成后会自动启动 Tomcat，并通过默认浏览器打开网址 http://localhost:8080/；也可到 Tomcat bin/目录启动 startup.bat
    - 管理员初始密码在 ...webapps\Jenkins\secrets\initialAdminPassword 中
    - 安装默认插件后，创建管理员账号 

Jenkins 的基本使用:

- 创建一个构建任务:
    - 点击左上角新建任务，选择 "构建一个自由风格的软件项目"，在 "构建" 中添加需要执行的操作
    - 创建之后，点击 "立即构建"
    - 在 "构建历史" 中查看 "控制台输出" 可以看到构建的过程；Jenkins 默认的执行目录在安装 Jenkins 的目录
    - 构建完成之后，可以单击 "Configure" 选项，重新配置任务
- 安装插件:
    - 在 Jenkins 首页，单击右侧的"系统管理" → "插件管理"选项

Selenium 自动化项目配置:

- 配置 Git/GitHub:
    - 进入 Jenkins 首页，单击"系统管理" -> "全局工具配置"选项，找到 Git 选项
        - 在"Path to Git executable"选项中填写 Git 可执行文件的本地路径 C:\Program Files\Git\bin\git.exe
    - 鉴权: Manage Jenkins - Configure System 添加一个 GitHub，https://github.com/settings/tokens/new 生成 AccessToken
    - 回到 task 中的配置，勾选"Github 项目"并填写"项目 URL"
    - 在"Source Code Management"中勾选"Git"选项
        - Repository URL: 填写 GitHub 项目地址
        - Branch Specifier (blank for 'any'): 设置项目分支，默认为 master 分支
        - Repository browser: 源码库浏览，默认为 Auto
    - 在"Build Triggers"中勾选"轮询 SCM"选项，通过轮询的方式检测 Git 仓库的更新，并执行构建任务
        - Schedule：设置轮询时间。“H/2 * * * * ”表示每两分钟检查一次项目是否有新提交的代码，如果有，就将新提交的代码拉取到本地
        - 保存之后，即可向项目仓库提交代码，通过 SCM 轮询检查项目更新并拉取代码
        - task 首页会多出一个"Git 轮询日志"选项，单击查看 Git 轮询日志
- 配置项目运行:
    - 通过前面的配置并执行构建，项目代码已经拉取到 Jenkins 目录（…\Jenkins\workspace\Simple task\）
    - 打开 task 首页，单击"Configure"选项，重新配置任务，修改构建命令: `python run_tests.py`
    - *如果不能运行 webdriver 打开浏览器，需要禁用 jenkins 服务，使用命令行运行 jenkins，重新构建*
- 配置 HTML 报告: 在项目中，通过 pytest-html 插件可以生成 HTML 报告
    - 下载插件 HTML Publisher
    - 找到"Build"选项，单击"Add build setup"按钮，勾选"Execute system Groovy script"选项，在"Groovy script"中添加:
        - This project is parameterized(参数化): `System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "")`
    - 在"构建后操作"中添加"Publish HTML reports":
        - HTML directory to archive：用于指定测试报告目录，这里设置为 test_report
        - Index page[s]：指定测试报告的索引页面，这里配置为**/report.html，表示匹配某目录下的 report.html 文件
        - Keep past HTML reports：是否保存旧的 HTML 报告
        - Include files：配置文件，根据提示填写**/*.html
- 配置构建统计
    - 在"构建后操作"中添加"Publish JUnit test result report"
        - 测试报告（XML）: 指定测试报告目录下面的 XML 文件，如/test_report/**/*.xml
- 配置自动发送邮件
    - "系统管理" →"系统设置" ->"Jenkins Location"，设置"系统管理员邮件地址"和"Extended E-mail Notification"
        - SMTP server: 邮箱 SMTP 服务地址，如 126 邮箱服务地址为 smtp.126.com
        - Default user E-mail suffix: 邮箱后缀， 126 邮箱后缀为@126.com
        - Use SMTP Authentication: 勾选使用 SMTP 认证
        - User Name: 发送邮件的用户名
        - Password： 客户端授权码，并非邮箱登录使用的密码，请自行查找资料设置客户端授权码
        - Use SSL: 是否使用 SSL 连接 SMTP 服务器， 默认勾选
        - SMTP port: SMTP 端口，默认为 25，465
    - 在"构建后操作"中添加"Editable Email Notification"
        - Project Recipient List：接收构建结果的邮件列表
        - Default Subject：邮件标题，根据 Jenkins 任务填写
        - Default Content：邮件正文
        - Attachments：邮件附件，这里可以指定测试报告的目录
        - Attach Build Log：附加构建日志
        - Triggers： 选择触发邮件发送规则， 可以选择每次发送， 也可以选择当任务失败时发送
        - Send To： 指定发送邮件的对象 

```xml
# 邮箱正文
（本邮件自动下发，请勿回复！） <br/>
构建项目： $PROJECT_NAME <br/>
构建版本： # $BUILD_NUMBER <br/>
构建状态： $BUILD_STATUS <br/>
执行用例数： ${TEST_COUNTS} <br/>
成功用例数： ${TEST_COUNTS, var="pass"} <br/>
失败用例数： ${TEST_COUNTS, var="fail"} <br/>
跳过用例数： ${TEST_COUNTS, var="skip"} <br/>
合计： ${TEST_COUNTS, var="total"} <br/>

Check console output at $BUILD_URL to view the results.
```

### appium

appium(移动端的 Selenium 自动化测试工具) = application + Selenium:

- appium 支持 iOS 和 Android 平台上的原生应用(App)、Web 应用(浏览器访问的应用)以及混合应用(原生代码封装网页视图)
- appium 类库封装了标准 Selenium 客户端类库，为用户提供常见的 JSON 格式的 Selenium 命令，以及额外的移动设备控制相关命令
- appium 客户端实现了移动端和 WebDriver 的协议；appium 服务器定义了官方协议的扩展，例如，安装/卸载 App等
- 移动应用类型主要分为以下几类: Native App(原生应用)、Mobile Web App(移动 Web 应用)、Hybrid App(混合应用)

appium 基于客户端/服务器架构，服务器执行给定顺序的动作:

1. 从客户端接收连接并启动会话
2. 侦听发出的命令
3. 执行这些命令
4. 返回命令执行状态

XCUITest：

- XCUITest 是苹果于 iOS 9.3 版本推出的自动化框架，9.3 以下版本 appium 使用 Apple 的 UIAutomation 库
    - 典型用法: `automationName： XCUITest`
- UIAutomation 库与移动设备或模拟器内运行的 bootstrap.js 进行通信，执行由 appium 客户端收到的命令

UIAutomator2:

- UIAutomator2 是基于 Android 的自动化框架，允许用户构建和运行 UI 测试
    - 典型用法: `automationName： uiautomator2`
- 在 appium 1.6 版本中，appium 为 UIAutomator2 提供支持，appium 使用 appium-android-bootstrap 模块与 UIAutomator2 进行交互
- 当 appium 客户端请求创建新的 AndroidDriver 会话时，appium 客户端会将所需的功能传递给 appium 节点服务器
    - 首先， UIAutomator2 驱动程序模块创建会话
    - 然后，在连接的 Android 设备上安装 UIAutomator2 服务器 apk
    - 接着启动 Netty 服务器，在 Netty 服务启动后， UIAutomator2 服务器在设备上侦听请求并做出响应

appium 的工作过程:

- appium Client: 针对主流的编程语言分别开发了相应的 appium 测试库
- appium Server: appium 需要在 PC 上启动一个 Server，监听客户端自动化测试的运行，并将请求发送到对应的移动设备或模拟器中运行
    - appium Server 项目已经停止更新，由 appium Desktop 替代
- 移动设备: 移动设备用于运行 appium 自动化测试的环境

appium 环境搭建: Windows 10 + Android 模拟器 + appium Desktop + python-client

- Android Studio 是 Android 应用的集成开发工具，可以通过 Android SDK 创建 Android 模拟器来运行 appium 自动化测试
    - Android SDK 提供了工具，如 adb 可以用于连接 PC 与 Android 手机/模拟器，UIAutomatorViewer 可以帮助定位 Android 元素
    - Android SDK（Software Development Kit，软件开发工具包）提供了 Android API 库和开发工具构建，可用来测试和调试应用程序
    - 在安装 Android 开发环境之前，需要先安装 Java 开发环境
        - 勾选"Android SDK"和"Android SDK Platform"选项，并通过"Android SDK Location"选项设置 Android SDK 的安装路径
    - 可以使用 Android ADT Bundle 替代
- Android Studio 的安装与使用
    - 安装: "Do not import settings"->"Don't send"->Cancel->custom->开始安装(可能出现 Intel HAXM 错误)
    - 使用: 第一次加载比较慢，会自动下载 Gradle -> 右上角创建一个虚拟机 -> 创建完成运行即可
    - 创建虚拟机出现 Troubleshoot，需要安装 Intel HAXM，之前只是下载到了 SDK 安装目录中，需要在 SDK\extras\intel 下点击安装
    - 创建虚拟机之后不能打开，可能是虚拟机保存路径(AVD)含有中文，需要创建 ANDROID_SDK_HOME 环境变量并指明 AVD 虚拟机目录，再重启
- 配置环境变量:
    - 假设 Android SDK 安装路径为 D:\android\SDK，则环境变量名设为 ANDROID_HOME，变量值 D:\android\SDK
    - 接着在 Path 变量中添加，;%ANDROID_HOME%\platform-tools;%ANDROID_HOME%\tools;
    - 在命令提示符下输入"adb"命令查看是否配置成功

*不打开 Android Studio 使用命令行启动模拟器，需要使用 SDK 中的工具:* 

- 首先查看模拟器列表: `emulator.exe -list-avds`，或者使用 `android.bat list avd`
- 其次启动模拟器: 
    - `emulator.exe -netdelay none -netspeed full -avd 虚拟机名称`，netdelay 延迟，netspeed 限速
        - 不要使用 tools 中的 emulator，可能出现 PANIC: Missing emulator engine program for 'x86' CPU.
            - 具体原因可能是 AS 会默认推荐下载带Google APIs的x86 Images，需要重新下载不带 Google APIs的x86 Images
        - 使用 emulator 目录下的 emulator 就能启动

appium:

- appium Desktop，默认显示监控的 Host 和 Port，默认为 0.0.0.0:4723
    - 另外一种通过命令方式安装 appium: 
        - `$ npm install -g cnpm --registry=https://registry.npm.taobao.org`
        - `$ npm config set registry https://registry.npm.taobao.org`
        - `$ npm config get registry`  `cnpm install -g appium`
- appium Client 支持多种编程语言
    - 通过 pip 命令安装 appium 测试库: `$ pip install Appium-Python-Client`

第一个 appium 测试:

- 首先，启动 Android 模拟器
- 其次，使用"adb devices"命令检查是否能监听到 Android 模拟器
- 接下来，启动 appium Desktop
- 最后，通过 Python 编写 appium 自动化测试脚本

```shell script
adb kill-server # 关闭连接
adb devices # 查看连接设备
adb connect 127.0.0.1:7555 # 连接模拟器
adb -s cf27456f shell # 指定连接设备使用命令
adb install test.apk # 安装应用，--install-location location：使用以下某个值来设置安装位置
adb install -r demo.apk #重新安装现有应用，保留其数据
adb uninstall cn.com.test.mobile #卸载应用，需要指定包
adb uninstall -k cn.com.test.mobile #卸载app 但保留数据和缓存文件
adb shell pm list packages #列出手机装的所有app 的包名

adb shell pm clear cn.com.test.mobile #清除应用数据与缓存
adb shell am start -n cn.com.test.mobile/.ui.SplashActivity #启动应用
adb shell am force-stop cn.com.test.mobile #强制停止应用
adb shell dumpsys package #包信息Package Information
adb shell dumpsys meminfo #内存使用情况Memory Usage

adb logcat #查看日志
adb logcat | grep -i displayed  # 打开app执行命令可以看到 Activity
adb logcat -c #清除log 缓存
adb shell dmesg #查看内核日志
adb get-serialno #获取序列号
adb shell top -m 10 #查看占用内存前10 的app
adb push <local> <remote> #从本地复制文件到设备
adb pull <remote> <local> #从设备复制文件到本地
adb shell input keyevent <keycode> #使用ADB命令模拟按键/输入
adb bugreport #查看bug 报告
adb help #查看ADB 帮助
adb shell wm size 480x1024 #将分辨率修改为 480px * 1024px
adb shell wm density 160 #屏幕密度修改为 160dpi
adb exec-out screencap -p > img.png #老版本无exec-out命令，只适合于新版的截图
adb shell screencap -p /sdcard/img.png #老版本截图先保存在设备端
adb pull /sdcard/img.png #通过pull拷贝到本地
adb reboot recovery #刷机，重启到 Recovery 模式，在设备的 Recovery 界面上操作进入 Apply update-Apply from ADB
adb sideload <path-to-update.zip> #通过 adb 上传和更新系统
adb reboot #重启，从 Recovery 重启到 Android
adb reboot bootloader #重启到 Fastboot 模式
adb shell ifconfig # 查看手机IP地址
adb shell settings get secure android_id # 查看Android_id

adb shell pm list packages -3 #列出除了系统应用的第三方应用包名
# 获取已安装应用Activity类名
adb logcat ActivityManager:I *:s | findstr "cmp" # 后启动目标应用
# 第一个cmp=com.netease.dwrg/.Launcher则表示：应用包名/应用Activity类名，完整Activity名=com.netease.dwrg.Launcher
adb shell getprop ro.product.model # 查看手机型号
adb shell getprop ro.product.name # 查看手机设备名 deviceName
adb shell getprop ro.build.version.release #查看 Android 系统版本 platformVersion

# 获取 app 信息
adb shell dumpsys activity top # 获取当前界面元素
adb shell dumpsys activity activities  # 获取任务列表，使用过的 activity
# app入口
adb logcat | findstr -i displayed # 方法一
aapt dump badging mobike.apk | grep launchable-activity # 方法二 
apkanalyzer # 最新版本的 sdk 中才有
# 启动应用
adb shell am start -W -n appName/appActivity -S
adb shell dumpsys window | findstr mCurrentFocus # 正在运行应用包名
adb shell dumpsys window | findstr mCurrent # 当前正在运行页面 Activity 名字

adb shell pm list packages -s # 列出系统应用的所有包名
adb shell pm list packages | findstr browser # 查看浏览器包名，假如是 com.android.browser
adb shell pm dump com.android.browser | grep version # 查看浏览器版本信息
adb shell pm dump com.android.chrome | grep version # 查看谷歌浏览器版本信息

appium -g 日志文件名称.log | 具体文件夹  # 保存日志 
```

appium 在启动时， 需要提供 Desired Capabilities，由客户端生成并发送给服务器(appium Desktop)，告诉服务器 App 运行的环境:

```python
from appium import webdriver
desired_caps = {
    'deviceName': 'Android Emulator', #启动的设备、 真机或模拟器，如 iPhone Simulator、 Android Emulator、Galaxy S4 等
    'automationName': 'Uiautomator2', #使用的自动化引擎，如 appium（默认）或 Selendroid（兼容 Android API 17 以下）
    'platformName': 'Android', #使用的移动平台，如 iOS 或 Android
    'platformVersion': '7.0', #指定平台的系统版本，如 Android 平台，版本为 7.0
    'appPackage': 'com.android.calculator2', #被测试 App 的 Package 名， 如 com.example.android.myApp 等
    'appActivity': '.Calculator', #被测试 App 的 Activity 名， 如 Calculator、 MainActivity、 .Settings 等
    'noReset': True, #在会话前重置应用状态。当设置为 True 时，会跳过安装指引；默认为 false
}
driver = webdriver.Remote(command_executor='http://localhost:4723/wd/hub', desired_capabilities=desired_caps)
```

获取 appPackage 和 appActivity:

- 方式一: 通过 adb 工具抓取日志进行分析
    - 首先，运行`adb logcat>D:/log.txt`命令，将 adb 抓取的日志写入 D:/log.txt 文件
    - 其次，在 Android 模拟器或设备中打开要测试的 App，并做一些操作
    - 然后，按快捷键 Ctrl+c 结束日志的捕捉
    - 最后，打开 D:/log.txt 文件，搜索"Displayed"关键字， 查找 App 的 Package 和 Activity
- 方式二: 通过 aapt 查看信息，在 SDK 的 build-tools 目录下、
    - 该工具既可以查看、 创建、 更新 zip 格式的文档附件（zip、 jar、 apk）， 也可以将资源文件编译成二进制文件
    - 命令: `aapt dump badging D:\appium\apk\mzbbs\com.meizu.flyme.flymebbs_40000003.apk`

控件定位，appium 继承了 Selenium 的定位方法:

- 扩展的定位:
    - ios_uiautomation: find_element_by_ios_uiautomation()
    - ios_predicate: ind_element_by_ios_predicate()
    - ios_class_chain: find_element_by_ios_class_chain()
    - android_uiautomator: find_element_by_android_uiautomator()
        - `d.find_element_by_android_uiautomator('new UiSelector().resourceId("com.android.calculator2:id/digit")')`
        - `driver.find_element_by_android_uiautomator('new UiSelector().className("android.widget.Button")')`
    - android_viewtag: find_element_by_android_viewtag()
    - android_datamatcher: find_element_by_android_data_matcher()
    - accessibility_id: find_element_by_accessibility_id()，取控件的 content-desc 属性
    - image: find_element_by_image()
    - custom: find_element_by_custom()
- 可以借助 Android SDK 自带的 UI Automator Viewer 工具对 Android 设备式模拟器中的控件进行定位
- resource-id: `driver.find_element_by_id("com.android.calculator2:id/formula")`
- class: `driver.find_elements_by_class_name("android.widget.Button")`
- xpath: `driver.find_element_by_xpath("//android.widget.Button[contains(@text,'7')]")`
- 其他定位:
    - 在 Web App 下，或者 Hybrid App 的 WebView 组件下使用的方法:
        - driver.find_element_by_name()
        - driver.find_element_by_tag_name()
        - driver.find_element_by_link_text()
        - driver.find_element_by_partial_link_text()
        - driver.find_element_by_css_selector()

三种经典等待方式:

- 强制等待: sleep
- 隐式等待（全局性）: `driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)`，`driver.implicitly_wait(10)
- 显示等待（等待某个元素）: `WebDriverWait(driver, 5).until(expected_conditions.visibility_of_element_located((MobileBy.ID, "com.android.settings:id/title")))`

appium 的常用 API:

- 应用操作:
    - 安装应用: install_app()，`driver.install_app("D:\\android\\apk\\ContactManager.apk")`
    - 卸载应用: remove_app()，`driver.remove_app('com.example.android.apis')`
    - 关闭应用: driver.close_app()
    - 启动应用: driver.launch_app()
    - 检查应用是否已经安装: `driver.is_app_installed('com.example.android.apis')`
    - 将应用置于后台: `driver.background_app(10)`
    - 应用重置，类似清除应用缓存: `driver.reset()`
- 上下文操作:
    - 可用上下文: driver.contexts
    - 当前上下文: driver.current_context
    - 切换上下文: driver.switch_to.context('NATIVE_APP'),driver.switch_to.context('WEBVIEW_1')
- 键盘操作:
    - 入字符串: driver.find_element_by_name("Name").send_keys("jack")
    - 模拟按键: driver.keyevent(36)
- 触摸操作:
    - 单击控件: tap(self, element=None, x=None, y=None, count=1)
        - 获取元素: el=driver.find_element_by_android_uiautomator('text("Name")')
        - 点击: TouchAction(driver).tap(el).release().perform()
    - 长按控件: long_press(self, el=None, x=None, y=None, duration=1000)
    - 移动: move_to(self, el=None, x=None, y=None)
    - 暂停: wait(self, ms=0)
- 特有操作:
    - 熄屏: driver.lock(seconds=3) # 熄屏 3s
    - 获取当前 package，仅支持 Android: `driver.current_package`
    - 获取当前 activity: driver.current_activity
    - 收起虚拟键盘: driver.hide_keyboard()
    - 获取屏幕宽高: driver.get_window_size()
    - 拉取文件，从真机或模拟器中拉取文件: driver.pull_file(self, path)
    - 推送文件: push_file(self, path, base64data)
