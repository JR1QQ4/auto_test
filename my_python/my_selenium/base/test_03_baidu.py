#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver
from time import sleep

url = "http://www.baidu.com"
driver = webdriver.Chrome()
driver.get(url)

# driver.find_element_by_id('kw').send_keys('Selenium')
# time.sleep(2)
# driver.find_element_by_id('kw').clear()
# driver.find_element_by_id('kw').send_keys('Appium')
# driver.find_element_by_id('su').click()

# driver.find_element_by_id('su').submit()

# size = driver.find_element_by_id('kw').size
# print('size: ', size)

# text = driver.find_element_by_id("bottom_layer").text
# print('text: ', text)

# isDisplayed = driver.find_element_by_id("kw").is_displayed()
# print('isDisplayed: ', isDisplayed)

# attribute = driver.find_element_by_id("kw").get_attribute('type')
# print('attribute: ', attribute)

# webElement = driver.find_element_by_id("kw")
# print(webElement.id)
# print(webElement.size)
# print(webElement.rect)
# print(webElement.tag_name)
# print(webElement.text)

webElement = driver.find_element_by_id("kw")
# webElement.send_keys()
# webElement.click()
# webElement.clear()
# webElement.get_attribute()
# webElement.is_selected()
# webElement.is_enabled()
# webElement.is_displayed()
# print(webElement.value_of_css_property("value"))
webElement.find_element()

# driver.find_element_by_link_text("新闻").click()
# window_handles = driver.window_handles
# while True:
#     for w in window_handles:
#         driver.switch_to.window(w)
#         sleep(2)


driver.quit()
