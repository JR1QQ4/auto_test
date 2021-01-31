#!/usr/bin/python
# -*- coding:utf-8 -*-
import csv
import codecs
import unittest
from time import sleep
from itertools import islice
from selenium import webdriver
import os
from parameterized import parameterized
from ddt import ddt, data, file_data, unpack

path_dir = os.path.dirname(os.path.dirname(__file__))


@ddt
class TestBaidu(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome()
        cls.base_url = "https://www.baidu.com"

        # cls.test_data = []
        # with codecs.open(path_dir + '\\test_data\\baidu_data.csv', 'r', 'utf_8_sig') as f:
        #     data = csv.reader(f)
        #     for line in islice(data, 1, None):
        #         cls.test_data.append(line)

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    def baidu_search(self, search_key):
        self.driver.get(self.base_url)
        self.driver.find_element_by_id("kw").send_keys(search_key)
        self.driver.find_element_by_id("su").click()
        sleep(3)

    # def test_search(self):
    #     with codecs.open(path_dir + '\\test_data\\baidu_data.csv', 'r', 'utf_8_sig') as f:
    #         data = csv.reader(f)
    #         for line in islice(data, 1, None):
    #             search_key = line[1]
    #             self.baidu_search(search_key)

    # def test_search_selenium(self):
    #     self.baidu_search(self.test_data[0][1])
    # def test_search_unittest(self):
    #     self.baidu_search(self.test_data[1][1])
    # def test_search_parameterized(self):
    #     self.baidu_search(self.test_data[2][1])

    # @parameterized.expand([
    #     ("case1", "selenium"),
    #     ("case2", "unittest"),
    #     ("case3", "parameterized"),
    # ])
    # def test_search(self, name, search_key):
    #     self.baidu_search(search_key)
    #     self.assertEqual(self.driver.title, search_key + "_百度搜索")

    # 参数化使用方式一
    # @data(["case1", "selenium"], ["case2", "ddt"], ["case3", "python"])
    # @unpack
    # def test_search1(self, case, search_key):
    #     print("第一组测试用例： ", case)
    #     self.baidu_search(search_key)
    #     self.assertEqual(self.driver.title, search_key + "_百度搜索")
    #
    # # 参数化使用方式二
    # @data(("case1", "selenium"), ("case2", "ddt"), ("case3", "python"))
    # @unpack
    # def test_search2(self, case, search_key):
    #     print("第二组测试用例： ", case)
    #     self.baidu_search(search_key)
    #     self.assertEqual(self.driver.title, search_key + "_百度搜索")
    #
    # # 参数化使用方式三
    # @data({"search_key": "selenium"}, {"search_key": "ddt"}, {"search_key": "python"})
    # @unpack
    # def test_search3(self, search_key):
    #     print("第三组测试用例： ", search_key)
    #     self.baidu_search(search_key)
    #     self.assertEqual(self.driver.title, search_key + "_百度搜索")

    # 参数化读取 JSON 文件
    @file_data(path_dir + '\\test_data\\ddt_data_file.json')
    def test_search4(self, search_key):
        print("第四组测试用例： ", search_key)
        self.baidu_search(search_key)
        self.assertEqual(self.driver.title, search_key + "_百度搜索")

    # 参数化读取 yaml 文件
    @file_data(path_dir + '\\test_data\\ddt_data_file.yaml')
    def test_search5(self, case):
        search_key = case[0]["search_key"]
        print("第五组测试用例： ", search_key)
        self.baidu_search(search_key)
        self.assertEqual(self.driver.title, search_key + "_百度搜索")


if __name__ == '__main__':
    unittest.main(verbosity=2)
