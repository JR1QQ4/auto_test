#!/usr/bin/python
# -*- coding:utf-8 -*-

from selenium.webdriver.support.ui import WebDriverWait


def get_element(driver, locate_type, locator):
    try:
        element = WebDriverWait(driver, 30).until(
            lambda x: x.find_element(by=locate_type, value=locator)
        )
        return element
    except Exception as e:
        raise e


def get_elements(driver, locate_type, locator):
    try:
        element = WebDriverWait(driver, 30).until(
            lambda x: x.find_elements(by=locate_type, value=locator)
        )
        return element
    except Exception as e:
        raise e


# if __name__ == '__main__':
#     from selenium import webdriver
#     driver = webdriver.Chrome(executable_path=r'C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe')
#     driver.get('http://www.baidu.com')
#     # searchBox = get_element(driver, 'css selector', '#su')
#     searchBox = get_element(driver, 'id', 'su')
#     print(searchBox.tag_name)
#     aList = get_elements(driver, 'tag name', 'a')
#     print(len(aList))
#     driver.quit()
