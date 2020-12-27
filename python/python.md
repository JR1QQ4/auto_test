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

from selenium.webdriver.support.select import Select

Select 类: 用于定位<select>标签
select_by_value(): 通过 value 值定位下拉选项
select_by_visible_text(): 通过 text 值定位下拉选项
select_by_index(): 根据下拉选项的索引进行选择。第一个选项为 0，第二个选项为 1

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

get_cookies(): 获得所有 Cookie
get_cookie(name): 返回字典中 key 为"name"的 Cookie
add_cookie(cookie_dict): 添加 Cookie
delete_cookie(name,optionsString): 删除名为 OpenString 的 Cookie
delete_all_cookies():  删除所有 Cookie

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


### appium

appium(移动端的 Selenium 自动化测试工具) = application + Selenium
appium 支持 iOS 和 Android 平台上的原生应用(App)、Web 应用(浏览器访问的应用)以及混合应用(原生代码封装网页视图)
appium 类库封装了标准 Selenium 客户端类库，为用户提供常见的 JSON 格式的 Selenium 命令，以及额外的移动设备控制相关命令
appium 客户端实现了移动端和 WebDriver 的协议；appium 服务器定义了官方协议的扩展，例如，安装/卸载 App等

































