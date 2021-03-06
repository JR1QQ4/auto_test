#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from my_python.my_selenium.project_one.util import util


class TestAdminLogin(object):
    def __init__(self):
        self.driver = webdriver.Chrome()
        self.driver.get("http://localhost:8080/jpress/admin/login")
        self.driver.maximize_window()

    def test_admin_login_code_error(self, flag=True):
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
        self.tear_down(flag)

    def test_admin_login_right(self, flag=True):
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
        self.tear_down(flag)

    def test_user_login_by_cookie(self, flag=True):
        """使用 Cookie 进行登录"""
        self.driver.delete_all_cookies()
        cookie_1 = {
            "name": "csrf_token",
            "value": "21c5be73382f4792962f19150ff2b6d9",
        }
        cookie_2 = {
            "name": "_jpuid",
            "value": "ODgyMjg3M2FhMmFhZjYzYTJjOWFmMGI4YjRlMjQ2ZGYjMTYxNTA1NzA4NDMyOSMxNzI4MDAjTVE9PQ=="
        }
        self.driver.add_cookie(cookie_1)
        self.driver.add_cookie(cookie_2)
        self.driver.get('http://localhost:8080/jpress/admin/index')

        self.tear_down(flag)

    def tear_down(self, flag=True):
        if flag:
            self.driver.quit()
        else:
            pass
