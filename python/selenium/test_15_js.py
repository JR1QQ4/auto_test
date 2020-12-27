#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver

driver = webdriver.Chrome()
driver.get('http://www.baidu.com')

driver.set_window_size(800, 600)
driver.find_element_by_id("kw").send_keys("selenium")
driver.find_element_by_id("su").click()

sleep(3)

# 通过 JavaScript 设置浏览器窗口的滚动条位置
js = '''
document.documentElement.scrollTo(100, 500);
'''
driver.execute_script(js)

driver.get('https://www.runoob.com/try/try.php?filename=tryhtml_textarea')
result_frame = driver.find_element_by_id('iframeResult')
driver.switch_to.frame(result_frame)
text = "selenium 自动化测试"
js = "document.getElementsByTagName('textarea')[0].value='" + text + "';"
driver.execute_script(js)
