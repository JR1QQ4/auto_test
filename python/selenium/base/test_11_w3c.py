#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver
from selenium.webdriver.support.select import Select

driver = webdriver.Chrome()
driver.get('https://www.w3school.com.cn/tiy/t.asp?f=html_select')

driver.implicitly_wait(5)

result_frame = driver.find_element_by_id('iframeResult')
driver.switch_to.frame(result_frame)

sel = driver.find_element_by_css_selector('select')

Select(sel).select_by_value('volvo')
print(Select(sel).all_selected_options[0].text)

sleep(2)

Select(sel).select_by_visible_text('Audi')
print(Select(sel).all_selected_options[0].text)

sleep(2)

Select(sel).select_by_index(2)
print(Select(sel).all_selected_options[0].text)



