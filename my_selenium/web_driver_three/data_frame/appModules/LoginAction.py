#!/usr/bin/python
# -*- coding:utf-8 -*-
from my_selenium.web_driver_three.data_frame.pageObjects.LoginPage import LoginPage


class LoginAction(object):
    def __init__(self):
        print('login...')

    @staticmethod
    def login(driver, username, password):
        try:
            login = LoginPage(driver)
            login.switchToFrame()
            login.userNameObj().send_keys(username)
            login.passwordObj().send_keys(password)
            login.loginButton().click()
            login.switchToDefaultFrame()
        except Exception as e:
            raise e


# if __name__ == '__main__':
#     from selenium import webdriver
#     import time
#     driver = webdriver.Chrome(executable_path=r'C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe')
#     driver.get("https://mail.163.com/")
#     time.sleep(5)
#     LoginAction.login(driver, username="", password="")
#     time.sleep(5)
#     assert "未读邮件" in driver.page_source
#     driver.quit()

