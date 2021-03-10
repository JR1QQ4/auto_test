#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from my_python.my_selenium.project_one.testcases.d_page.user_register_page import UserRegisterPage
from my_python.my_selenium.project_one.util import util

import pytest


class TestUserRegister(object):
    def setup_class(self):
        self.driver = webdriver.Chrome()
        self.driver.get("http://localhost:8080/jpress/user/register")
        self.driver.maximize_window()
        self.register_page = UserRegisterPage(self.driver)

    def teardown_class(self):
        self.driver.quit()

    login_data = [
        ('test001', 'test001@qq.com', '123456', '123456', '666', '验证码不正确'),
        (util.get_random_str(), util.get_random_str() + '@qq.com', '123456', '123456', '', '验证码不正确'),
    ]

    @pytest.mark.parametrize('username, email, pwd, confirm_pwd, captcha, expected', login_data)
    def test_register_code_error(self, username, email, pwd, confirm_pwd, captcha, expected):
        self.register_page.input_username(username)
        self.register_page.input_email(email)
        self.register_page.input_pwd(pwd)
        self.register_page.input_confirm_pwd(confirm_pwd)

        if captcha != '666':
            captcha = util.get_code(self.driver, "captcha")
        self.register_page.input_captcha(captcha)

        self.register_page.click_register_btn()

        WebDriverWait(self.driver, 5).until(EC.alert_is_present())
        alert = self.driver.switch_to.alert

        assert alert.text == expected
        alert.accept()

        sleep(2)


if __name__ == '__main__':
    pytest.main(['-sv', 'test_user_register.py'])
