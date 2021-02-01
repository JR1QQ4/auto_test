#!/usr/bin/python
# -*- coding:utf-8 -*-
from my_selenium.web_driver_three.data_frame.util.ObjectMap import *
from my_selenium.web_driver_three.data_frame.util.parse_configuration_file import ParseConfigFile

from selenium import webdriver


class LoginPage(object):
    def __init__(self, driver: webdriver):
        self.driver = driver

        self.parseCF = ParseConfigFile()
        self.loginOPtions = self.parseCF.getItemsSection("163mail_login")
        # print(self.loginOPtions)

    def switchToFrame(self):
        try:
            locate_type, locator = self.loginOPtions["loginPage.frame".lower()].split(">")
            # frame = get_element(self.driver, 'css selector', 'iframe[id^="x-URS-iframe"]')
            frame = get_element(self.driver, locate_type, locator)
            self.driver.switch_to.frame(frame)
        except Exception as e:
            raise e

    def switchToDefaultFrame(self):
        self.driver.switch_to.default_content()

    def userNameObj(self):
        try:
            locate_type, locator = self.loginOPtions["loginPage.username".lower()].split(">")
            # elementObj = get_element(self.driver, "xpath", '//input[@name="email"]')
            elementObj = get_element(self.driver, locate_type, locator)
            return elementObj
        except Exception as e:
            raise e

    def passwordObj(self):
        try:
            locate_type, locator = self.loginOPtions["loginPage.password".lower()].split(">")
            elementObj = get_element(self.driver, locate_type, locator)
            return elementObj
        except Exception as e:
            raise e

    def loginButton(self):
        try:
            locate_type, locator = self.loginOPtions["loginPage.loginButton".lower()].split(">")
            elementObj = get_element(self.driver, locate_type, locator)
            return elementObj
        except Exception as e:
            raise e


# if __name__ == '__main__':
#     from selenium import webdriver
#     driver = webdriver.Chrome(executable_path=r'C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe')
#     driver.get("https://mail.163.com/")
#     import time
#     time.sleep(5)
#     login = LoginPage(driver)
#     login.switchToFrame()
#     login.userNameObj().send_keys("")
#     login.passwordObj().send_keys("")
#     login.loginButton().click()
#     login.switchToDefaultFrame()
#     time.sleep(5)
#     driver.quit()

