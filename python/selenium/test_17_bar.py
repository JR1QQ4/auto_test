#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver
from selenium.webdriver import ActionChains
from selenium.common.exceptions import UnexpectedAlertPresentException

# driver = webdriver.Chrome()
# driver.get('https://sc.chinaz.com/jiaobendemo.aspx?downloadid=2017111056145')
#
# result_iframe = driver.find_element_by_id('iframe')
# driver.switch_to.frame(result_iframe)
#
# slider = driver.find_elements_by_class_name('slide-to-unlock-handle')[0]
# action = ActionChains(driver)
# action.click_and_hold(slider).perform()  # 按下左键
#
# for index in range(3):
#     try:
#         action.reset_actions()  # 清空所有准备好的Action
#         action.move_by_offset(100, 0).perform()
#     except UnexpectedAlertPresentException:
#         break
#     finally:
#         sleep(.1)
#
# # 打印警告框提示
# success_text = driver.switch_to.alert.text
# print(success_text)
# driver.switch_to.alert.accept()
#
# sleep(3)

opt = webdriver.ChromeOptions()
opt.add_experimental_option('w3c', False)
driver = webdriver.Chrome(chrome_options=opt)
driver.get('https://www.jq22.com/yanshi4976')

# driver.maximize_window()
# driver.implicitly_wait(10)

# result_frame = driver.find_element_by_id('iframe')
driver.switch_to.frame('iframe')
driver.find_element_by_id('appDate').click()

sleep(1)

dwwos = driver.find_elements_by_class_name("dwwo")
year = dwwos[0]
month = dwwos[1]
day = dwwos[2]

action = webdriver.TouchActions(driver)
# flick_element(on_element, xoffset, yoffset, speed)
action.scroll_from_element(year, 0, 2).perform()
action.scroll_from_element(month, 0, 3).perform()
action.scroll_from_element(day, 0, 6).perform()
