#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver

driver = webdriver.Chrome()

url = "http://www.baidu.com"
driver.get(url)

# 控制浏览器

# 控制大小
print("设置浏览器宽 480、高 800 显示")
driver.set_window_size(480, 800)

# 控制浏览器后退、前进
# news_url = "http://news.baidu.com"
# print("now access %s" % news_url)
# driver.get(news_url)
# print("back to %s " % url)
# driver.back()
# print("forward to %s " % news_url)
# driver.forward()

# 模拟浏览器刷新
driver.refresh()
