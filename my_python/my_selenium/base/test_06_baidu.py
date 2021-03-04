#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver
from selenium.webdriver.common.alert import Alert

driver = webdriver.Chrome()

# url = "http://www.baidu.com"
# driver.get(url)
#
# print('Before search================')
#
# title = driver.title
# print("title: " + title)
#
# now_url = driver.current_url
# print("URL: " + now_url)
#
# driver.find_element_by_id("kw").send_keys("selenium")
# driver.find_element_by_id("su").click()
#
# sleep(2)
#
# print('After search================')
#
# title = driver.title
# print("title: " + title)
#
# now_url = driver.current_url
# print("URL: " + now_url)
#
# # 获取搜索结果条数
# num = driver.find_element_by_class_name('nums').text
# print("result: " + num)

# alertElement: Alert = driver.switch_to.alert
# alertElement.accept()
# alertElement.dismiss()
# alertElement.send_keys()
# alertElement.text

