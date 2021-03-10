#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium.webdriver.common.by import By

from my_python.my_selenium.project_one.testcases.d_page.base_page import BasePage
from time import sleep


class UserRegisterPage(BasePage):
    username_input = (By.NAME, 'username')
    email_input = (By.NAME, 'email')
    pwd_input = (By.NAME, 'pwd')
    confirm_pwd_input = (By.NAME, 'confirmPwd')
    captcha_input = (By.NAME, 'captcha')
    register_btn = (By.CLASS_NAME, 'btn')

    def __init__(self, driver):
        super(UserRegisterPage, self).__init__(driver)

    def goto_register_page(self):
        self.driver.get("http://localhost:8080/jpress/user/register")
        self.driver.maximize_window()

    def input_username(self, username):
        self.clear(*self.username_input)
        self.input_text(username, *self.username_input)

    def input_email(self, email):
        self.clear(*self.email_input)
        self.input_text(email, *self.email_input)

    def input_pwd(self, pwd):
        self.clear(*self.pwd_input)
        self.input_text(pwd, *self.pwd_input)

    def input_confirm_pwd(self, confirm_pwd):
        self.clear(*self.confirm_pwd_input)
        self.input_text(confirm_pwd, *self.confirm_pwd_input)

    def input_captcha(self, captcha):
        self.clear(*self.captcha_input)
        self.input_text(captcha, *self.captcha_input)

    def click_register_btn(self):
        self.click(*self.register_btn)
        sleep(2)
