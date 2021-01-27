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
























     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     













































