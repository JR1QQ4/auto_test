#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium.webdriver import Remote, DesiredCapabilities

# driver = Remote()  # SessionNotCreatedException 错误
# driver = Remote(desired_capabilities=DesiredCapabilities.CHROME.copy())
# driver.get("http://www.baidu.com")
# driver.quit()

# 引用远程浏览器配置
# driver = Remote(command_executor='http://192.168.183.1:4444/wd/hub',
#                 desired_capabilities=DesiredCapabilities.FIREFOX.copy())
# driver.get("http://www.baidu.com")
