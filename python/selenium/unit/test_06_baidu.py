#!/usr/bin/python
# -*- coding:utf-8 -*-
import unittest
from time import sleep
from selenium import webdriver


class TestBaidu(unittest.TestCase):
    @classmethod
    def setUpClass(cls) -> None:
        cls.driver = webdriver.Chrome()
        cls.base_url = "http://www.baidu.com"

    # def setUp(self) -> None:
    #     self.driver = webdriver.Chrome()
    #     self.base_url = "http://www.baidu.com"
    #     self.title = ''

    def baidu_search(self, search_key):
        self.driver.get(self.base_url)
        self.driver.find_element_by_id("kw").send_keys(search_key)
        self.driver.find_element_by_id("su").click()
        sleep(2)

    def test_search_key_selenium(self):
        search_key = "selenium"
        self.baidu_search(search_key)
        self.assertEqual(self.driver.title, "selenium_百度搜索")

    def test_search_key_unittest(self):
        search_key = "unittest"
        self.baidu_search(search_key)
        self.assertEqual(self.driver.title, "unittest_百度搜索")

    # def tearDown(self):
    #     self.driver.quit()

    @classmethod
    def tearDownClass(cls) -> None:
        cls.driver.quit()


if __name__ == '__main__':
    unittest.main()
