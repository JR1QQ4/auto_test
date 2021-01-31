#!/usr/bin/python
# -*- coding:utf-8 -*-
import sys
import pytest
from time import sleep
from os.path import dirname, abspath

sys.path.insert(0, dirname(dirname(abspath(__file__))))
from page.baidu_page import BaiduPage


class TestSearch:
    """百度搜索"""

    def test_baidu_search_case(self, browser, base_url):
        """ 百度搜索： pytest """
        page = BaiduPage(browser)
        page.get(base_url)
        page.search_input = "pytest"
        sleep(2)
        page.search_button.click()
        sleep(2)
        assert browser.title == "pytest_百度搜索"


class TestSearchSettings:
    """百度搜索设置"""

    def test_baidu_search_setting(self, browser, base_url):
        """ 百度搜索设置 """
        page = BaiduPage(browser)
        page.get(base_url)
        page.settings.click()
        page.search_setting.click()
        sleep(2)
        page.save_setting.click()
        alert_text = page.get_alert_text
        sleep(2)
        page.accept_alert()
        assert alert_text == "已经记录下您的使用偏好"


# if __name__ == '__main__':
#     pytest.main(["-v", "-s", "test_baidu.py"])
#     # pytest.main(["-v", "-s", "test_baidu.py::TestSearch::test_baidu_search_case"])
#     # pytest.main(["-v", "-s", "test_baidu.py::TestSearch"])
#     # pytest.main(["-v", "-s", "test_baidu.py::TestSearchSettings"])
