#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from python.selenium.page_object.baidu_page import BaiduPage
import unittest
from selenium import webdriver


class TestBaiduPage(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    def test_baidu_search_case1(self):
        page = BaiduPage(self.driver)
        page.open()
        page.search_input("selenium")
        page.search_button()
        sleep(2)
        self.assertEqual(page.get_title(), "selenium_百度搜索")

    # def test_baidu_search_case2(self):
    #     self.driver.get(self.base_url)
    #     bd = BaiduPage(self.driver)
    #     bd.search_input("unittest")
    #     bd.search_button()


if __name__ == '__main__':
    unittest.main(verbosity=2)
