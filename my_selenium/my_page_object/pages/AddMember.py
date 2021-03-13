#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium.webdriver.common.by import By

from my_selenium.my_page_object.pages.BasePage import BasePage


class AddMember(BasePage):
    def add_member(self):
        self.find(By.ID, "username").send_keys("username")
        self.find(By.ID, "acctid").send_keys("123456")
        return True

    def update_page(self):
        content: str = self.find(By.CSS_SELECTOR, ".ww_pageNav_info_text").text
        return [int(x) for x in content.split("/", 1)]

    def get_member_plus(self, value):
        self.wait_for_click((By.CSS_SELECTOR, ".ww_checkbox"))

        cur_page, total_page = self.update_page()
        while True:
            elements = self.finds(By.CSS_SELECTOR, ".member")
            for element in elements:
                if value == element.get_attribute("title"):
                    return True
            cur_page = self.update_page()[0]
            if cur_page == total_page:
                return False
            self.find(By.CSS_SELECTOR, ".next_page").click()

    def get_member(self,):
        self.wait_for_click((By.CSS_SELECTOR, ".ww_checkbox"))
        elements = self._driver.finds(By.CSS_SELECTOR, ".member")
        return [element.get_attribute("title") for element in elements]
