#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver

from selenium.webdriver import ActionChains

url = "http://www.baidu.com"
driver = webdriver.Chrome()
driver.get(url)

# 定位到要悬停的元素
more = driver.find_element_by_class_name('s-top-more-btn')
# 对定位到的元素执行鼠标悬停操作
ActionChains(driver).move_to_element(more).perform()

b_map = driver.find_element_by_link_text("地图")
ActionChains(driver).double_click(b_map).perform()

b_news = driver.find_element_by_link_text("新闻")
b_news.click()
