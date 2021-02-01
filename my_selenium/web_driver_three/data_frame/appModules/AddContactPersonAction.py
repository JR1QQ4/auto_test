#!/usr/bin/python
# -*- coding:utf-8 -*-
from my_selenium.web_driver_three.data_frame.pageObjects.HomePage import HomePage
from my_selenium.web_driver_three.data_frame.pageObjects.AddressBookPage import AddressBookPage

import traceback
import time


class AddContactPerson(object):
    def __init__(self):
        print("add contact person.")

    @staticmethod
    def add(driver, contactName, contactEmail, contactPhone):
        try:
            hp = HomePage(driver)
            hp.addressLink().click()
            time.sleep(3)
            apb = AddressBookPage(driver)
            apb.createContactPersonButton().click()
            if contactName:
                apb.createContactPersonName().send_keys(contactName)
            apb.createContactPersonEmail().send_keys(contactEmail)
            if contactPhone:
                apb.createContactPersonMobile().send_keys(contactPhone)
            apb.saveContactPerson().click()
        except Exception as e:
            print(traceback.print_exc())
            raise e


# if __name__ == '__main__':
#     from my_selenium.web_driver_three.data_frame.appModules.LoginAction import LoginAction
#     from selenium import webdriver
#     import time
#     driver = webdriver.Chrome(executable_path=r"C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe")
#     driver.get("https://mail.163.com/")
#     time.sleep(5)
#     LoginAction.login(driver, username="", password="")
#     time.sleep(5)
#     AddContactPerson.add(driver, "王麻子", "wangmazi@qq.com", "135xxxxxxx6")
#     time.sleep(3)
#     assert "王麻子" in driver.page_source
#     time.sleep(5)
#     driver.quit()


