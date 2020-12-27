#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver

driver = webdriver.Chrome()
driver.get('http://www.baidu.com')

driver.save_screenshot("./baidu_img.png")

driver.close()
