#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver

# driver = webdriver.Chrome()
# driver.implicitly_wait(10)
# driver.get("http://www.baidu.com")
#
# # 获得百度搜索窗口句柄
# search_window = driver.current_window_handle
#
# driver.find_element_by_link_text("登录").click()
# driver.find_element_by_link_text("立即注册").click()
#
# # 获取当前所有打开的窗口句柄
# all_handles = driver.window_handles
#
# print(all_handles)
#
# # 进入注册窗口
# for handle in all_handles:
#     if handle != search_window:
#         driver.switch_to.window(handle)
#         print(driver.title)
#         driver.find_element_by_name("userName").send_keys("username")
#         driver.find_element_by_name("phone").send_keys("138xxxxxxxx")
#         sleep(2)
#
# # 回到搜索窗口
# driver.switch_to.window(search_window)
# print(driver.title)


from selenium.webdriver import ActionChains
from selenium.webdriver.common.keys import Keys


class TestCase(object):
    def __init__(self):
        self.driver = webdriver.Chrome()
        self.driver.get("http://www.baidu.com")

    def test(self):
        ActionChains(self.driver).click(on_element=None)
        ActionChains(self.driver).click_and_hold(on_element=None)
        ActionChains(self.driver).context_click(on_element=None)
        ActionChains(self.driver).double_click(on_element=None)
        ActionChains(self.driver).drag_and_drop(source=None, target=None)
        ActionChains(self.driver).drag_and_drop_by_offset(source=None, xoffset=0, yoffset=0)
        ActionChains(self.driver).key_down(value=Keys, element=None)
        ActionChains(self.driver).key_up(value=Keys, element=None)
        ActionChains(self.driver).move_by_offset(xoffset=0, yoffset=0)
        ActionChains(self.driver).move_to_element(to_element=None)
        ActionChains(self.driver).move_to_element_with_offset(to_element=None, xoffset=0, yoffset=0)



