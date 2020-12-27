#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import ctime, sleep

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import NoSuchElementException

driver = webdriver.Chrome()

url = "http://www.baidu.com"
driver.get(url)

# 1.WebDriverWait
element: WebElement = WebDriverWait(driver, 5, .5).until(
    EC.visibility_of_element_located((By.ID, "kw")))
element.send_keys('selenium')

# EC.presence_of_element_located(locator='')
# EC.visibility_of_element_located(locator='')
# EC.visibility_of(element)
# EC.invisibility_of_element_located(locator='')

# 2.is_displayed
# print(ctime())
# for i in range(10):
#     try:
#         el = driver.find_element_by_id('kw22')
#         print(i)
#         if el.is_displayed():
#             break
#     except BaseException as e:
#         pass
#     sleep(1)
# else:
#     print('time out')
# print(ctime())

# 3.隐式等待
# driver.implicitly_wait(3)
# try:
#     print(ctime())
#     driver.find_element_by_id('kw22').send_keys('selenium')
# except NoSuchElementException as e:
#     print(e)
# finally:
#     print(ctime())

