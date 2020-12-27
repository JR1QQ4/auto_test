#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time

driver = webdriver.Chrome()

url = "http://www.baidu.com"
driver.get(url)

driver.find_element_by_id("kw").send_keys("selenium")

t = 1

time.sleep(t)
driver.find_element_by_id("kw").send_keys(Keys.SPACE)
time.sleep(t)
driver.find_element_by_id("kw").send_keys("教程1")

time.sleep(t)
driver.find_element_by_id("kw").send_keys(Keys.BACK_SPACE)

time.sleep(t)
driver.find_element_by_id("kw").send_keys(Keys.CONTROL, 'a')

time.sleep(t)
driver.find_element_by_id("kw").send_keys(Keys.CONTROL, 'x')

time.sleep(t)
driver.find_element_by_id("kw").send_keys(Keys.CONTROL, 'v')

time.sleep(t)
driver.find_element_by_id("su").send_keys(Keys.ENTER)