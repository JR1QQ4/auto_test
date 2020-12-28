#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver


# driver = webdriver.Chrome()
# driver.get("https://mail.163.com/")
# # 登录
# sleep(2)
# login_frame = driver.find_element_by_css_selector("iframe[id^='x-URS-iframe']")
# driver.switch_to.frame(login_frame)
# driver.find_element_by_name("email").clear()
# driver.find_element_by_name("email").send_keys("chenjunrenyx")
# driver.find_element_by_name("password").clear()
# driver.find_element_by_name("password").send_keys("mail18428308932")
# driver.find_element_by_id("dologin").click()
# # 登录之后的动作
# sleep(5)
# # 退出
# driver.find_element_by_id('spnUid').click()
# driver.find_element_by_class_name('kt1').click()

class Mail():
    def __init__(self, driver: webdriver):
        self.driver = driver

    def login(self, username="chenjunrenyx", password="mail18428308932"):
        login_frame = self.driver.find_element_by_css_selector("iframe[id^='x-URS-iframe']")
        self.driver.switch_to.frame(login_frame)
        self.driver.find_element_by_name("email").clear()
        self.driver.find_element_by_name("email").send_keys(username)
        self.driver.find_element_by_name("password").clear()
        self.driver.find_element_by_name("password").send_keys(password)
        self.driver.find_element_by_id("dologin").click()

    def logout(self):
        self.driver.find_element_by_id('spnUid').click()
        self.driver.find_element_by_class_name('kt1').click()

# driver = webdriver.Chrome()
# driver.get("https://mail.163.com/")
# mail = Mail(driver)
# mail.login()
# sleep(5)
# mail.logout()

driver = webdriver.Chrome()
driver.get("https://mail.163.com/")
mail = Mail(driver)

# # 登录账号为空
# mail.login("", "")
# # 用户名为空
# mail.login("", "password")
# # 密码为空
# mail.login("username", "")
# # 用户名/密码错误
# mail.login("error", "error")
# # 管理员登录
# mail.login("admin", "admin123")

# with open("../../data_file/user_info.txt") as user_file:
#     data = user_file.readlines()
#     print(data)
# users = []
# for line in data:
#     user = line[:-1].split(":")
#     users.append(user)
# print(users)
# # 用户名为空
# mail.login(users[0][0], users[0][1])
# # 密码为空
# mail.login(users[1][0], users[1][1])
# # 用户名/密码错误
# mail.login(users[2][0], users[2][1])
# # 管理员登录 admin
# mail.login(users[3][0], users[3][1])



