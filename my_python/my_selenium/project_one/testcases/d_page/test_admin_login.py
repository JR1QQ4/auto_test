#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from my_python.my_selenium.project_one.util import util

import pytest


class TestAdminLogin(object):
    def setup_class(self):
        self.driver = webdriver.Chrome()
        self.driver.get("http://localhost:8080/jpress/admin/login")
        self.driver.maximize_window()

    # def teardown_class(self):
    #     sleep(3)
    #     self.driver.quit()

    admin_login_data = [
        ("0", "chen", "chen", "666", "验证码不正确，请重新输入"),
        ("1", "chen", "chen", "", "JPress后台")
    ]

    @pytest.mark.skip
    @pytest.mark.parametrize('flag, username, pwd, captcha, expected', admin_login_data)
    def test_admin_login(self, flag, username, pwd, captcha, expected):
        self.driver.find_element_by_name("user").clear()
        self.driver.find_element_by_name("user").send_keys(username)
        self.driver.find_element_by_name("pwd").clear()
        self.driver.find_element_by_name("pwd").send_keys(pwd)

        if captcha != "666":
            captcha = util.get_code(self.driver, "captchaImg")
        self.driver.find_element_by_name("captcha").clear()
        self.driver.find_element_by_name("captcha").send_keys(captcha)
        self.driver.find_element_by_class_name("btn").click()

        if flag == "0":
            WebDriverWait(self.driver, 5).until(EC.alert_is_present())
            alert = self.driver.switch_to.alert
            assert alert.text == expected
            alert.accept()
        elif flag == "1":
            WebDriverWait(self.driver, 5).until(EC.title_is(expected))
            assert self.driver.title == expected

        sleep(2)

    @pytest.mark.dependency(name='admin_login')
    def test_user_login_by_cookie(self):
        """使用 Cookie 进行登录"""
        self.driver.delete_all_cookies()
        cookie_1 = {
            "name": "csrf_token",
            "value": "c22de79af68342eebbbe4a6fca918585",
        }
        cookie_2 = {
            "name": "_jpuid",
            "value": "ZTViOTM2ZjgzZTA0MDI2NWFiYjc1NThjOGUxOTM3MTYjMTYxNTMxOTY3MTM1NiMxNzI4MDAjTVE9PQ=="
        }
        self.driver.add_cookie(cookie_1)
        self.driver.add_cookie(cookie_2)
        self.driver.get('http://localhost:8080/jpress/admin/index')


if __name__ == '__main__':
    pytest.main(['-sv', 'test_admin_login.py'])
