#!/usr/bin/python
# -*- coding:utf-8 -*-
import time

from selenium import webdriver

url = "http://www.baidu.com"
driver = webdriver.Chrome()
driver.get(url)

# driver.find_element_by_id('kw').send_keys('Selenium')
# time.sleep(2)
# driver.find_element_by_id('kw').clear()
# driver.find_element_by_id('kw').send_keys('Appium')
# driver.find_element_by_id('su').click()

# driver.find_element_by_id('su').submit()

size = driver.find_element_by_id('kw').size
print('size: ', size)

text = driver.find_element_by_id("bottom_layer").text
print('text: ', text)

isDisplayed = driver.find_element_by_id("kw").is_displayed()
print('isDisplayed: ', isDisplayed)

attribute = driver.find_element_by_id("kw").get_attribute('type')
print('attribute: ', attribute)


