#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver

# chrome_driver_path = r'C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe'
# driver = webdriver.Chrome(executable_path=chrome_driver_path)

driver = webdriver.Chrome()
driver.get("https://www.baidu.com")

driver.find_element_by_id('kw').send_keys('Selenium')
driver.find_element_by_id('su').click()

driver.quit()



