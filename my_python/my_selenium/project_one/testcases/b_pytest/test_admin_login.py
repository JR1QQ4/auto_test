#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from my_python.my_selenium.project_one.util import util

import pytest


class TestAdminLogin(object):
    @classmethod
    def setup_class(cls):
        cls.driver = webdriver.Chrome()
        cls.driver.get("http://localhost:8080/jpress/admin/login")
        cls.driver.maximize_window()

    @classmethod
    def teardown_class(cls):
        cls.driver.quit()

    @pytest.mark.skip()
    def test_admin_login_code_error(self):
        username = "chen"
        pwd = "chen"
        captcha = "666"
        expected = "验证码不正确，请重新输入"

        self.driver.find_element_by_name("user").send_keys(username)
        self.driver.find_element_by_name("pwd").send_keys(pwd)
        self.driver.find_element_by_name("captcha").send_keys(captcha)

        self.driver.find_element_by_class_name("btn").click()

        WebDriverWait(self.driver, 5).until(EC.alert_is_present())
        alert = self.driver.switch_to.alert

        assert alert.text == expected
        alert.accept()

        sleep(2)

    @pytest.mark.skip()
    def test_admin_login_right(self):
        username = "chen"
        pwd = "chen"
        captcha = ""
        expected = "JPress后台"

        self.driver.find_element_by_name("user").clear()
        self.driver.find_element_by_name("user").send_keys(username)
        self.driver.find_element_by_name("pwd").clear()
        self.driver.find_element_by_name("pwd").send_keys(pwd)
        self.driver.find_element_by_name("captcha").clear()
        captcha = util.get_code(self.driver, "captchaImg")
        self.driver.find_element_by_name("captcha").send_keys(captcha)
        self.driver.find_element_by_class_name("btn").click()

        WebDriverWait(self.driver, 5).until(EC.title_is(expected))

        assert self.driver.title == expected

        sleep(2)

    @pytest.mark.dependency(name='admin_login')
    def test_admin_login_by_cookie(self):
        """使用 Cookie 进行登录"""
        # self.driver = webdriver.Chrome()
        # self.driver.get("http://localhost:8080/jpress/admin/login")
        # self.driver.maximize_window()
        self.driver.delete_all_cookies()
        cookie_1 = {
            "name": "csrf_token",
            "value": "e12fe88ee17c4af78e6bd8598ab87ac7",
        }
        cookie_2 = {
            "name": "_jpuid",
            "value": "NzQxMTI5MGVjMGQ5OGZlNDhhNWY2ODUxZTNlNDJhYmQjMTYxNTE5MzY5NDk4NiMxNzI4MDAjTVE9PQ=="
        }
        self.driver.add_cookie(cookie_1)
        self.driver.add_cookie(cookie_2)
        self.driver.get('http://localhost:8080/jpress/admin/index')


if __name__ == '__main__':
    pytest.main(['test_admin_login.py'])
