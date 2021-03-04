#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver

# driver = webdriver.Chrome()
# driver.get('http://www.126.com')
# sleep(2)

# login_frame = driver.find_element_by_css_selector('iframe[id^="x-URS-iframe"]')
# driver.switch_to.frame(login_frame)
# driver.find_element_by_name("email").send_keys("username")
# driver.find_element_by_name("password").send_keys("password")
# driver.find_element_by_id("dologin").click()
# driver.switch_to.default_content()

import os

from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class TestCase(object):
    def __init__(self):
        self.driver = webdriver.Chrome()
        path = "file:///" + os.path.abspath("test.html")
        self.driver.get(path)

    def test(self):
        self.driver.find_element_by_id("btn").click()
        wait = WebDriverWait(self.driver, 5)
        loc = (By.ID, "id2")
        ele = wait.until(EC.presence_of_element_located(loc))
        print(ele)
        print(ele.text)
        print(self.driver.find_element_by_id("id2").text)


if __name__ == '__main__':
    case = TestCase()
    case.test()
    case.driver.quit()
