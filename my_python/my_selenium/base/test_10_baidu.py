#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver


# driver = webdriver.Chrome()
# driver.get('http://www.baidu.com')
# sleep(2)
# # 打开搜索设置
# driver.find_element_by_id('s-usersetting-top').click()
# driver.find_element_by_link_text('搜索设置').click()
# sleep(2)
# # 保存设置
# driver.find_element_by_class_name('prefpanelgo').click()
# # 获取警告框
# alert = driver.switch_to.alert
# # 获取警告框提示信息
# print(alert.text)
# sleep(2)
# # 确认警告框
# alert.accept()

class TestCase(object):
    def __init__(self):
        self.driver = webdriver.Chrome()
        self.driver.get("http://www.baidu.com")

    def test1(self):
        sleep(3)
        title = self.driver.execute_script("return document.title;")
        print(title)
        self.driver.execute_script("alert('{0}');".format(title))
        sleep(3)
        self.driver.switch_to.alert.accept()

    def test2(self):
        self.driver.find_element_by_id("kw").send_keys("selenium")
        self.driver.find_element_by_id("su").click()
        sleep(2)
        js = "window.scrollTo(0, document.body.offsetHeight);"
        self.driver.execute_script(js)
        sleep(2)


if __name__ == '__main__':
    case = TestCase()
    # case.test1()
    case.test2()
    case.driver.quit()
