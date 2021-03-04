#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time

from selenium.webdriver.support.select import Select

driver = webdriver.Chrome()

# url = "http://www.baidu.com"
# driver.get(url)
# driver.find_element_by_id("kw").send_keys("selenium")
# t = 1
#
# time.sleep(t)
# driver.find_element_by_id("kw").send_keys(Keys.SPACE)
# time.sleep(t)
# driver.find_element_by_id("kw").send_keys("教程1")
#
# time.sleep(t)
# driver.find_element_by_id("kw").send_keys(Keys.BACK_SPACE)
#
# time.sleep(t)
# driver.find_element_by_id("kw").send_keys(Keys.CONTROL, 'a')
#
# time.sleep(t)
# driver.find_element_by_id("kw").send_keys(Keys.CONTROL, 'x')
#
# time.sleep(t)
# driver.find_element_by_id("kw").send_keys(Keys.CONTROL, 'v')
#
# time.sleep(t)
# driver.find_element_by_id("su").send_keys(Keys.ENTER)

# select
# driver.get("http://sahitest.com/demo/selectTest.htm")
# selectElement:Select = driver.find_element_by_id("s3Id")
# selectElement.select_by_value()
# selectElement.select_by_index()
# selectElement.select_by_visible_text()
# selectElement.deselect_by_index()
# selectElement.deselect_by_value()
# selectElement.deselect_by_visible_text()
# selectElement.deselect_all()
# selectElement.options
# selectElement.all_selected_options
# selectElement.first_selected_options
