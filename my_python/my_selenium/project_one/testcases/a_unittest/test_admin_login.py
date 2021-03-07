#!/usr/bin/python
# -*- coding:utf-8 -*-
import unittest
from time import sleep

from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from my_python.my_selenium.project_one.util import util


class TestAdminLogin(unittest.TestCase):

    @classmethod
    def setUpClass(cls) -> None:
        cls.driver = webdriver.Chrome()
        cls.driver.get("http://localhost:8080/jpress/admin/login")
        cls.driver.maximize_window()

    @classmethod
    def tearDownClass(cls) -> None:
        cls.driver.quit()

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

        self.assertEqual(alert.text, expected)
        alert.accept()

        sleep(2)

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

        self.assertEqual(self.driver.title, expected)

        sleep(2)

    # @unittest.skip("直接跳过测试")
    @classmethod
    def admin_login_by_cookie(cls):
        """使用 Cookie 进行登录"""
        cls.driver = webdriver.Chrome()
        cls.driver.get("http://localhost:8080/jpress/admin/login")
        cls.driver.maximize_window()
        cls.driver.delete_all_cookies()
        cookie_1 = {
            "name": "csrf_token",
            "value": "9277db8f44cd4691a93e264a85d097fa",
        }
        cookie_2 = {
            "name": "_jpuid",
            "value": "MDNjZTYwODI4NjAyN2UxNmVhZTFkMzFlZGMzZWRiOTAjMTYxNTEwOTg5Njg3NyMxNzI4MDAjTVE9PQ=="
        }
        cls.driver.add_cookie(cookie_1)
        cls.driver.add_cookie(cookie_2)
        cls.driver.get('http://localhost:8080/jpress/admin/index')

