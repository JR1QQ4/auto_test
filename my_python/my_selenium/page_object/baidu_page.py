#!/usr/bin/python
# -*- coding:utf-8 -*-
from python.selenium.page_object.base import BasePage


class BaiduPage(BasePage):
    url = "http://www.baidu.com"

    def search_input(self, search_key):
        self.by_id("kw").send_keys(search_key)

    def search_button(self):
        self.by_id('su').click()
