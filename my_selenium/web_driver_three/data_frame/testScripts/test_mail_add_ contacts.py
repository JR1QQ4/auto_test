#!/usr/bin/python
# -*- coding:utf-8 -*-


from selenium import webdriver
from my_selenium.web_driver_three.data_frame.pageObjects.LoginPage import LoginPage

import time


def testMailLogin():
    try:
        driver = webdriver.Chrome(executable_path=r'C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe')
        driver.get("https://mail.163.com/")
        driver.implicitly_wait(30)
        driver.maximize_window()
        login = LoginPage(driver)
        login.switchToFrame()
        login.userNameObj().send_keys("")
        login.passwordObj().send_keys("")
        login.loginButton().click()
        login.switchToDefaultFrame()
        time.sleep(5)
        print(driver.page_source)
        assert '未读邮件' in driver.page_source
    except Exception as e:
        raise e
    finally:
        driver.quit()


# if __name__ == '__main__':
#     testMailLogin()
#     print("登陆邮箱成功！")
