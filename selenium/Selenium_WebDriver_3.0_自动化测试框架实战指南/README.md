# Selenium WebDriver 3.0 自动化测试框架实战指南

> 《Selenium WebDriver 3.0 自动化测试框架实战指南》吴晓华 王晨昕 编著

## 第一篇 基础篇

### 第 1 章 Selenium 简介

Selenium 的前世今生:

- 2004 年在 Thought Works 公司，一个名为 Jason Huggins 的兄弟为了减少手工测试的工作量，自己实现了一套基于 JavaScript 的代码库。
这个代码库逐渐成为 Selenium Core，Selenium Core 为 Selenium Remote Control（RC）和 Selenium IDE 提供了核心基础能力
- 2006 年，Google 的工程师 Simon Stewart 开启了一个叫做 WebDriver 的项目，次项目可以直接让测试工具调用浏览器和操作系统本身提供的
内置方法，以此来绕过 JavaScript 环境的沙盒限制，此项目的目标就是为了解决 Selenium 无法突破浏览器沙盒的限制
- 2008 年，Selenium 和 WebDriver 这两个项目进行了合并，至此 Selenium 2.0 出现了，Selenium2 = Selenium1 + WebDriver
- 2016 年 10 月，Selenium 3 诞生，主要实现了把核心 API 跟客户端 driver 进行分离，同时去掉用得越来越少的 Selenium RC 功能

浏览器的 JavaScript 安全机制————同源策略:

- 浏览器访问了某个域名的网站后，浏览器会打开此网站的网页，获取到此网站的网页内容
- 网页内容种包含了要在网页里面执行的 JavaScript 语句或外部引用的 JavaScript 文件，浏览器会执行属于此域名下的 JavaScript 语句和
文件
    - 如果外部引用的 JavaScript 文件 URL 和当前网页的域名不一致，那么浏览器会拒绝执行此 JavaScript 文件种的代码
- 通过此方式，浏览器就可以防止一些恶意的 JavaScript 文件被加载到用户的浏览器中，起到一定的安全防护作用

WebDriver 与 Selenium 1.0:

- WebDriver 与 之前 Selenium 1.0 的 JavaScript 注入实现不同，直接利用了浏览器的内部接口来操作浏览器
    - 对于不同平台中的不同浏览器，必须依赖浏览器内部的 Native Component（原生组件）来实现把对 WebDriverAPI 调用转化为对浏览器
    内部接口的调用
- Selenium 1.0 采用 JavaScript 的合成事件来处理网页元素的操作，例如要单击某个页面元素，要先使用 JavaScript 定位到这个元素，然后
触发单击事件
- WebDriver 使用的是系统的内部接口或函数，首先找到这个元素的坐标位置，并在这个坐标点触发一个鼠标左键的单击操作
    - 由此可以看出，WebDriver 能更好地模拟真实的环境，但仅能测试那些可见的页面元素。也正因为这个区别，有些隐藏的页面元素是可以使用
     Selenium 1.0 进行操作的，而尝试使用 WebDriver 单击的某个隐藏的页面元素，将会引发 cannot clickable 的错误提示信息

Selenium 3 的新特性:

1. Selenium 3 去掉了 Selenium RC，这是 Selenium 3 最大的变化
2. Selenium 3 仅支持 Java 8 及以上版本
3. Selenium 3 不再提供默认浏览器支持，所有支持的浏览器均由浏览器官方提供支持，也就是由官方提供相应的 driver 进行支持，因此提高了
自动化测试的稳定性
    - Selenium 3.0 以前版本能直接启动 Firefox 浏览器，而 Selenium 3.0 版本开始需要下载 Firefox 官方提供的 geckodriver 驱动才能
    启动 Firefox 浏览器，并且 Firefox 浏览器必须是 48 版本以上
4. Selenium 3 通过 Apple 自己的 safaridriver 支持 MacOS 上的 Safari 浏览器。Safari 浏览器的驱动直接被集成到 Selenium Server 
上，也就是说想在 Safari 浏览器上执行自动化测试脚本，必须使用 Selenium Server 
5. Selenium 3 通过 Microsoft 官方提供的 Microsoft WebDriver 支持 Edge 浏览器。由此在 Windows 10 系统行就可以实现 Edge 浏览器
自动化测试，只需要在 https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/ 网址下载相应版本的驱动程序即可实现
6. Selenium 3 只提供支持 IE 9.0 及以上版本，早期版本也许还能工作，但不再提供支持

### 第 2 章 自动化测试那点事儿

敏捷开发和传统开发模式完全不同，它只会实现明确的要求，拥抱变化，使用自动化测试和重构的方式来响应不断变化的需求，实现每月、每周甚至
每天发布新版本，解决传统开发模式的很多问题

敏捷开发的核心理念是小步快跑，它具有如下 6 个特点:

1. 鼓励团队成员的面对面沟通，敏捷开发模式认为人和人的相互交流胜于任何流程和工具
2. 客户协作胜过合同谈判
3. 把工作重点放在可执行的程序上，而不是写大量的文档
4. 团队协作和团队鼓励，团队对产品的发布承担责任，明确团队的统一目标
5. 响应变化胜过遵循计划
6. 使用持续集成和自动化测试方法快速反馈项目质量，及时地适应新地需求，保证产品地正确性


在项目中实施自动化的 10 个最佳实践:

1. 在自动化测试实施前，建立可衡量和易达到的自动化测试实施目标，不要在初期制定过高的目标和期望
2. 选择适合公司普遍使用的测试工具，可以是一个工具或者一组工具，做出选择后需要针对选定工具进行深入研究
3. 分析测试项目的特点，编写适合项目特点的自动化测试框架，减少编写测试脚本的重复性和复杂性，降低其他测试人员编写自动化测试脚本的门槛
4. 聘用具备丰富开发经验的工程师承担测试框架的开发工作，并根据测试框架的推广程度进行不断优化
5. 在自动化测试工作开始大规模推广实施前，须在中小类型项目进行充分试点实施，充分评估实施自动化测试的风险和产出，总结试点实施中过的问
题和收益，并在后期推广过程中尽可能扬长避短
6. 获得开发团队的协作支持，提高开发代码的可测试性，降低自动化测试实施的难度
7. 在需求相对稳定的阶段，开始 UI 层级大规模自动化测试脚本的编写
8. 在测试过程中，使用局部自动化测试的实施策略
9. 全面提高自动化测试实施人员的技术素质
10. 定期做好自动化测试最佳实践的总结

### 第 3 章 自动化测试辅助工具

Firefox 浏览器 + Firebug 插件（2016年底就已经停止更新和维护，其功能已经整合到 Firefox 开发者工具中） + FirePath

### Selenium IDE

Firefox 浏览器 + Selenium IDE 插件（https://addons.mozilla.org/en-US/firefox/addon/selenium-ide/）

waitForText、assertText、verifyText

- waitForText 语句在测试执行时用来判断某些文本是否显示在界面中，若界面上显示了制定文本，程序会继续执行；若等待一段时间后，界面上未
显示制定文本，测试用例会被设定为执行失败状态，但是测试脚本依旧会继续执行
- assertText 语句在测试执行时用来判断界面上测某些文本是否和期望显示的文本一致，若一致则测试程序会继续执行；若不一致，测试用例会被
设定为执行失败状态，并且不再继续执行后续测试脚本
- verifyText 语句在测试执行时用来判断界面上显示的文本是否和期望显示的文本相一致，若一致则测试程序会继续执行；若不一致，测试用例会
设置为执行失败状态，但测试脚本会继续执行

### 第 5 章 搭建 Python 环境和 PyCharm 集成开发环境

python + pip + PyCharm

### 第 6 章 Selenium3（WebDriver）的安装

pip install selenium + WebDriver（对应浏览器的驱动程序driver）

### 第 7 章 单元测试框架的使用介绍

unittest

- test fixture（测试固件）
- test case（测试用例）
- test suite（测试套件）
- test runner（测试运行器）

### 第 8 章 页面元素定位方法

8 种定位方法

## 第二篇 实战应用篇

### 第 9 章 WebDriver 的多浏览器测试

webdriver.Ie(executable_path="c:\\IEDriverServer")
webdriver.Firefox(executable_path="c:\\geckodriver")
webdriver.Chrome(executable_path="c:\\chromedriver")

### 第 10 章 WebDriver API 详解

常用 API:

- 访问某个网址 driver.get
- 网页的前进和后退 back | forward
- 刷新当前网页 refresh
- 浏览器窗口最大化 maximize_window
- 获取并设置当前窗口的位置 get_window_position | set_window_position
- 获取并设置当前窗口的大小 set_window_size | get_window_size
- 获取页面的 Title 属性值 title
- 获取页面 HTML 源代码 page_source
- 获取当前页面的 URL 地址 current_url
- 获取与切换浏览器窗口句柄 current_window_handle | switch_to_window
- 获取页面元素的基本信息 element.tag_name | element.size
- 获取页面元素的文本内容 element.text
- 判断页面元素是否可见 element.is_displayed()
- 判断页面元素是否可操作 element.enabled()
- 获取页面元素的属性 element.get_attribute(属性名)
- 获取页面元素的 CSS 属性值 element.value_of_css_property(属性名)
- 清空输入框中的内容 input.clear()
- 在输入框中输入指定内容 input.send_keys(值)
- 单击按钮 button.click()
- 双击某个元素 action_chains.double_click(inputBox).perform()
- 操作单选下拉列表 select.find_elements_by_tag_name('option') | option.is_selected() | option.is_enabled()
- 断言单选列表选项值 assertListEqual(expect_optionList, actual_optionsList)
- 操作多选的选择列表 select_element.select_by_index(0) | .select_by_visible_text(值) | .select_by_value(值)
- 操作可以输入的下拉列表（输入的同时模拟按键） select_element.send_keys(Keys.ENTER)
- 操作单选框 is_selected()
- 操作复选框 is_selected()
- 断言页面源码中的关键字 assert u"值" in self.driver.page_source, u"页面源码中不存在该关键字！"
- 对当前浏览器窗口截屏 self.driver.get_screenshot_as_file(r"c:\screenPicture.png")
- 拖拽页面元素 ActionChains(driver).drag_and_drop(initial, target).perform() | .drag_and_drop_by_offset
- 模拟键盘单个按键操作 Keys.RETURN
- 模拟组合按键操作 ActionChains(driver).key_down(Keys.CONTROL),send_keys('x').key_up(Keys.CONTROL).perform() | 第三方模块
- 模拟鼠标右键 ActionChains(driver).context_click(element).perform()
- 模拟鼠标左键按下与释放  ActionChains(driver).click_and_hold(ele).perform() | .release(ele).perform()
- 保持鼠标悬停在某个元素上 ActionChains(driver).move_to_element(ele).perform()
- 判断页面元素是否存在 NoSuchElementException
- 隐式等待 driver.implicitly_wait(10)，隐式等待只需设置一次，然后它将在 driver 的整个生命周期都起作用
- 显式等待 WebDriverWait(driver, 10, 0.2).until(expected_conditions.期望的场景)
- 显示等待中期望的场景 wait.until(expected_conditions.element_to_be_clickable((By.XPATH, '//input/a')))
- 使用 Title 属性识别和操作新弹出的浏览器窗口
- 通过页面的关键内容识别和操作新浏览器窗口
- 操作 Frame 中的页面元素 driver.switch_to.default_content()
- 使用 Frame 中的 HTML 源码内容操作 Frame
- 操作 IFrame 中的页面元素 driver.switch_to.frame(frame)
- 操作 JavaScript 的 Alert 弹窗 driver.switch_to_alert() | alert.accept()
- 操作 JavaScript 的 confirm 弹窗 driver.switch_to_alert()
- 操作 JavaScript 的 prompt 弹窗 driver.switch_to_alert()
- 操作浏览器的 Cookie driver.get_cookies()
- 指定页面加载时间 driver.set_page_load_timeout(4)

### 第 11 章 WebDriver 高级应用

WebDriver API:

- 使用 JavaScript 操作页面元素 execute_script(JS 脚本)
- 操作 Web 页面的滚动条 execute_script(window.scrollTo() | element.scrollIntoView() | window.scrollBy())
- 在 Ajax 方式产生的浮动框中，单击选择包含某个关键字的选项 模拟键盘下箭头 | 模糊匹配
- 结束 Windows 中浏览器的进程 os.system("taskkill /F /iM firefox.exe"")
- 更改一个页面对象的属性值 execute_script("arguments[0].setAttribute(arguments[1], arguments[2])", ele, attr, value)
- 无人工干预地自动下载某个文件 profile.set_preference('browser.helperApps.neverToDisk', 'application/zip, 
application/octet-stream')
- 无人工干预地自动上传附件 模拟键盘上传 | 第三方工具 AutoIt 上传文件
- 右键另存为下载文件 win32api + win32con + Keys + ActionChains
- 操作日期控件 
- 启动带有用户配置信息的 Firefox 浏览器窗口 先生成用户自定义的 Firefox 浏览器配置文件 + set_preference + firefox_profile
- UI 对象库 
- 操作富文本框 send_keys()等 
- 精确比较截图图片 pip install pillow
- 高亮显示正在操作的页面元素 driver.execute_script("argument[0].setAttribute('style', arguments[1];", ele, 
"background: green; border: 2px solid red;")
- 浏览器中新开标签页(tab)

## 第三篇 自动化测试框架搭建篇

### 第 12 章 数据驱动测试

### 第 13 章 行为驱动测试

### 第 14 章 Selenium Grid 的使用

### 第 15 章 自动化测试框架的搭建及测试实战

## 第四篇 常见问题和解决方法

### 第 16 章 自动化测试常见问题和解决方法









