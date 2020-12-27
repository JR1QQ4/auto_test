#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver

driver = webdriver.Chrome()
driver.get("http://www.baidu.com")

# 获取 cookie
cookie = driver.get_cookies()
print(cookie)

# 添加 cookie
driver.add_cookie({'name': 'key-aaaaaaa', 'value': 'value-bbbbbbb'})

for cookie in driver.get_cookies():
    print("%s -> %s" % (cookie['name'], cookie['value']))