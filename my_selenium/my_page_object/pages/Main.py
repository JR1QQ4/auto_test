#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium.webdriver.common.by import By

from my_selenium.my_page_object.pages.AddMember import AddMember
from my_selenium.my_page_object.pages.BasePage import BasePage


class Main(BasePage):
    _base_url = "https://www.baidu.com"

    def goto_add_member(self):
        self.find(By.ID, "menu_contacts").click()

        # 自定义显示等待
        # def wait_add_member(x):
        #     elements_len = len(self.finds(By.CSS_SELECTOR, "#user"))
        #     if elements_len <= 0:
        #         self.find(By.CSS_SELECTOR, "div>a:nth-child(2)").click()
        #     return elements_len > 0
        #
        # self.wait_for_elem(wait_add_member)

        locator = (By.CSS_SELECTOR, "div>a:nth-child(2)")
        self.wait_for_click(locator)
        self.find(*locator).click()
        return AddMember(self._driver)
