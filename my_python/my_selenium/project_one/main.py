#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver

from my_python.my_selenium.project_one.testcases.base.test_admin_login import TestAdminLogin
from my_python.my_selenium.project_one.testcases.base.test_user_login import TestUserLogin
from my_python.my_selenium.project_one.testcases.base.test_user_register import TestUserRegister
from my_python.my_selenium.project_one.util import util

if __name__ == '__main__':
    print("----------main----------")

    # driver = webdriver.Chrome()
    # driver.get("http://localhost:8080/jpress/user/register")
    # driver.maximize_window()
    # sleep(2)
    #
    # print(util.get_code(driver, "captchaimg"))
    # driver.quit()

    # case01 = TestUserRegister()
    # case01.test_register_code_error(False)
    # case01.test_register_code_right()

    # case02 = TestUserLogin()
    # case02.test_user_login_username_error(False)
    # case02.test_user_login_right()

    # case03 = TestAdminLogin()
    # case03.test_admin_login_code_error(False)
    # case03.test_admin_login_right()
