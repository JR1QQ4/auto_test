#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver

driver = webdriver.Chrome()
driver.implicitly_wait(10)
driver.get("http://www.baidu.com")

# 获得百度搜索窗口句柄
search_window = driver.current_window_handle

driver.find_element_by_link_text("登录").click()
driver.find_element_by_link_text("立即注册").click()

# 获取当前所有打开的窗口句柄
all_handles = driver.window_handles

print(all_handles)

# 进入注册窗口
for handle in all_handles:
    if handle != search_window:
        driver.switch_to.window(handle)
        print(driver.title)
        driver.find_element_by_name("userName").send_keys("username")
        driver.find_element_by_name("phone").send_keys("138xxxxxxxx")
        sleep(2)

# 回到搜索窗口
driver.switch_to.window(search_window)
print(driver.title)





