#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from my_python.my_selenium.project_one.util import util

import pytest


class TestUserRegister(object):
    def setup_class(self):
        self.driver = webdriver.Chrome()
        self.driver.get("http://localhost:8080/jpress/user/register")
        self.driver.maximize_window()

    def teardown_class(self):
        self.driver.quit()

    def test_register_code_error(self):
        username = "test001"
        email = "test001@qq.com"
        pwd = "123456"
        confirm_pwd = "123456"
        captcha = "666"
        expected = "验证码不正确"

        self.driver.find_element_by_name("username").send_keys(username)
        self.driver.find_element_by_name("email").send_keys(email)
        self.driver.find_element_by_name("pwd").send_keys(pwd)
        self.driver.find_element_by_name("confirmPwd").send_keys(confirm_pwd)
        self.driver.find_element_by_name("captcha").send_keys(captcha)
        self.driver.find_element_by_class_name("btn").click()

        WebDriverWait(self.driver, 5).until(EC.alert_is_present())
        alert = self.driver.switch_to.alert

        assert alert.text == expected
        alert.accept()

        sleep(2)

    def test_register_code_right(self):
        username = util.get_random_str()
        email = username + "@qq.com"
        pwd = "123456"
        confirm_pwd = "123456"
        captcha = ""
        expected = "注册成功，点击确认进行登录。"

        self.driver.find_element_by_name("username").clear()
        self.driver.find_element_by_name("username").send_keys(username)
        self.driver.find_element_by_name("email").clear()
        self.driver.find_element_by_name("email").send_keys(email)
        self.driver.find_element_by_name("pwd").clear()
        self.driver.find_element_by_name("pwd").send_keys(pwd)
        self.driver.find_element_by_name("confirmPwd").clear()
        self.driver.find_element_by_name("confirmPwd").send_keys(confirm_pwd)
        captcha = util.get_code(self.driver, "captcha")
        self.driver.find_element_by_name("captcha").clear()
        self.driver.find_element_by_name("captcha").send_keys(captcha)
        self.driver.find_element_by_class_name("btn").click()

        WebDriverWait(self.driver, 5).until(EC.alert_is_present())
        alert = self.driver.switch_to.alert

        assert alert.text == expected
        alert.accept()

        sleep(2)


if __name__ == '__main__':
    pytest.main(['-s', 'test_user_register'])
