#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver

driver = webdriver.Chrome()
driver.get('http://www.baidu.com')

sleep(2)

# 打开搜索设置
driver.find_element_by_id('s-usersetting-top').click()
driver.find_element_by_link_text('搜索设置').click()

sleep(2)

# 保存设置
driver.find_element_by_class_name('prefpanelgo').click()

# 获取警告框
alert = driver.switch_to.alert

# 获取警告框提示信息
print(alert.text)

sleep(2)

# 确认警告框
alert.accept()


