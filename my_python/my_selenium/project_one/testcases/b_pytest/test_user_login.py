#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

import pytest


class TestUserLogin(object):
    def setup_class(self):
        self.driver = webdriver.Chrome()
        self.driver.get("http://localhost:8080/jpress/user/login")
        self.driver.maximize_window()

    def teardown_class(self):
        self.driver.quit()

    def test_user_login_username_error(self):
        username = "test001"
        pwd = "123456"
        expected = "用户名不正确。"

        self.driver.find_element_by_name("user").send_keys(username)
        self.driver.find_element_by_name("pwd").send_keys(pwd)
        self.driver.find_element_by_class_name("btn").click()

        WebDriverWait(self.driver, 5).until(EC.alert_is_present())
        alert = self.driver.switch_to.alert

        assert alert.text == expected
        alert.accept()

        sleep(2)

    def test_user_login_right(self):
        username = "admin"
        pwd = "123456"
        expected = "用户中心"

        self.driver.find_element_by_name("user").clear()
        self.driver.find_element_by_name("user").send_keys(username)
        self.driver.find_element_by_name("pwd").clear()
        self.driver.find_element_by_name("pwd").send_keys(pwd)
        self.driver.find_element_by_class_name("btn").click()

        WebDriverWait(self.driver, 5).until(EC.title_is(expected))

        assert self.driver.title == expected

        sleep(2)


if __name__ == '__main__':
    pytest.main(['-sv', 'test_user_login.py'])
