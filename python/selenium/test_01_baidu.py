#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver

# 使用 By 定位元素
from selenium.webdriver.common.by import By

# chrome_driver_path = r'C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe'
# driver = webdriver.Chrome(executable_path=chrome_driver_path)


driver = webdriver.Chrome()
driver.get("https://www.baidu.com")

driver.find_element_by_id('kw').send_keys('Selenium')
driver.find_element_by_id('su').click()

# 使用 By 定位元素
# driver.find_element(By.ID, 'su').click()

sleep(3)

# 定位一组元素
texts = driver.find_elements_by_xpath("//div[contains(@class, 'c-container')]/h3/a")
print(len(texts))
for t in texts:
    print(t.text)

# driver.quit()
