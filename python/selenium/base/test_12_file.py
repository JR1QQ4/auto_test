#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver
import os

driver = webdriver.Chrome()
driver.get('https://www.runoob.com/try/try.php?filename=tryhtml5_input_type_file')

driver.implicitly_wait(3)

result_frame = driver.find_element_by_id('iframeResult')
driver.switch_to.frame(result_frame)

file_path = os.path.abspath('test_01_baidu.py')

ele = driver.find_element_by_css_selector('input[type="file"]')
ele.send_keys(file_path)
