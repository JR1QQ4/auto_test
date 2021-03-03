#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver
from pprint import pprint

driver = webdriver.Chrome()

url = "http://www.baidu.com"
driver.get(url)

# 控制浏览器

# 控制大小
# print("设置浏览器宽 480、高 800 显示")
# driver.set_window_size(480, 800)

# 控制浏览器后退、前进
# news_url = "http://news.baidu.com"
# print("now access %s" % news_url)
# driver.get(news_url)
# print("back to %s " % url)
# driver.back()
# print("forward to %s " % news_url)
# driver.forward()

# 模拟浏览器刷新
# driver.refresh()

# Selenium WebDriver 属性
name = driver.name
current_url = driver.current_url
title = driver.title
page_source = driver.page_source
current_window_handle = driver.current_window_handle
windows_handles = driver.window_handles

pprint("name={}, current_url={}, title={}".format(name, current_url, title), indent=2)
# pprint("page_source=" + page_source)
pprint(current_window_handle)
pprint(windows_handles)

driver.quit()
