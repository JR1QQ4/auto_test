#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver
from my_selenium.web_driver_three.data_frame.appModules.LoginAction import LoginAction

import time


def testMailLogin():
    try:
        driver = webdriver.Chrome(executable_path=r'C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe')
        driver.get("https://mail.163.com/")
        driver.implicitly_wait(30)
        driver.maximize_window()
        time.sleep(5)
        LoginAction.login(driver, "", "")
        time.sleep(5)
        assert "未读邮件" in driver.page_source
    except Exception as e:
        raise e
    finally:
        driver.quit()


# if __name__ == '__main__':
#     testMailLogin()
#     print("登陆邮箱成功！")
