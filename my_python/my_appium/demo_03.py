#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

import pytest
from appium import webdriver
from appium.webdriver import WebElement
from selenium.webdriver.common.by import By

_max_num = 3
_error_num = 0
_black_list = [
    (By.XPATH, "//*[@text='确认']"),
    (By.XPATH, "//*[@text='确定']"),
    (By.ID, "com.xueqiu.android:id/iv_close")
]

caps = dict()
caps["platformName"] = "android"
caps["deviceName"] = "mumu"
caps["appPackage"] = "com.xueqiu.android"
caps["appActivity"] = ".view.WelcomeActivityAlias"
caps["noReset"] = False
driver = webdriver.Remote('http://127.0.0.1:4723/wd/hub', caps)
driver.implicitly_wait(3)


def find(driver, locator, value: str = None):
    element: WebElement
    global _error_num
    global _max_num
    try:
        if isinstance(locator, tuple):
            element = driver.find_element(*locator)
        else:
            element = driver.find_element(locator, value)
        _error_num = 0
        return element
    except Exception as e:
        driver.implicitly_wait(1)
        if _error_num > _max_num:
            raise e
        _error_num += 1
        for ele in _black_list:
            elements = driver.find_elements(*ele)
            if len(elements) > 0:
                elements[0].click()
                return find(driver, locator, value)
        raise e


def find_get_text(driver, locator, value: str = None):
    element: WebElement
    global _error_num
    global _max_num
    try:
        element = driver.find_element(*locator).text if isinstance(locator, tuple) \
            else driver.find_element(locator, value).text
        _error_num = 0
        return element
    except Exception as e:
        driver.implicitly_wait(3)
        if _error_num > _max_num:
            raise e
        _error_num += 1
        for ele in _black_list:
            elements = driver.find_elements(*ele)
            if len(elements) > 0:
                elements[0].click()
                return find(driver, locator, value)
        raise e


def test_search():
    # find(driver, By.ID, "post_status").click()
    # find(driver, By.ID, "tv_search").click()

    # driver.find_element(By.ID, "com.xueqiu.android:id/post_status").click()

    find(driver, By.ID, "com.xueqiu.android:id/post_status").click()
    find(driver, By.ID, "com.xueqiu.android:id/tv_search").click()

    sleep(3)

    driver.quit()
