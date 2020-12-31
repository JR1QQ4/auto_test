#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from python.selenium.page_object.baidu_page_poium import BaiduPage
import unittest
from selenium import webdriver


class TestBaidu(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    def test_baidu_search_case(self):
        page = BaiduPage(self.driver)
        page.get("https://www.baidu.com")
        page.search_input = "selenium"
        page.search_button.click()
        sleep(3)
        self.assertEqual(page.get_title, "selenium_百度搜索")


if __name__ == '__main__':
    unittest.main(verbosity=2)
