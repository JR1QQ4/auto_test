#!/usr/bin/python
# -*- coding:utf-8 -*-
import unittest
from selenium import webdriver


class TestBaidu(unittest.TestCase):
    @classmethod
    def setUpClass(cls) -> None:
        cls.imgs = []
        cls.driver = webdriver.Chrome()

    @classmethod
    def tearDownClass(cls) -> None:
        print(cls.imgs)
        cls.driver.quit()

    def get_screenshot(self):
        self.imgs.append(self.driver.get_screenshot_as_base64())
        return True

    def test_baidu_search(self):
        self.driver.get("https://www.baidu.com")
        self.get_screenshot()
        self.driver.find_element_by_id('kw').send_keys(u'百度一下')
        self.get_screenshot()
        self.driver.find_element_by_id('su').click()
        self.get_screenshot()

    def test_baidu_assert_ok(self):
        self.driver.get("https://www.baidu.com")
        hao123 = self.driver.find_element_by_xpath('//*[@id="u1"]/a[2]').text
        print(hao123)
        self.get_screenshot()
        self.assertEqual(hao123, 'hao123')

    def test_baidu_assert_ok_noimg(self):
        self.driver.get("https://www.baidu.com")
        news = self.driver.find_element_by_xpath('//*[@id="u1"]/a[1]').text
        print(news)
        self.assertEqual(news, u"新闻")

    def test_baidu_assert_faile(self):
        self.driver.get("https://www.baidu.com")
        self.get_screenshot()
        news = self.driver.find_element_by_xpath('//*[@id="u1"]/a[1]').text
        print(news)
        self.get_screenshot()
        self.driver.find_element_by_xpath('//*[@id="u1"]/a[1]').click()
        self.get_screenshot()
        self.assertEqual(news, 'hao123')

    def test_baidu_assert_error(self):
        self.driver.get("https://www.baidu.com")
        self.get_screenshot()
        raise EnvironmentError('Current environment can not testing!')
