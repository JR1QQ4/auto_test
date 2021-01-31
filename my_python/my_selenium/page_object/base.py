#!/usr/bin/python
# -*- coding:utf-8 -*-
import time

from selenium.webdriver.remote.webdriver import WebDriver


class BasePage:
    """基础 Page 层，封装一些常用方法"""
    def __init__(self, driver: WebDriver):
        self.driver = driver

    def open(self, url=None):
        """打开页面"""
        if url is None:
            self.driver.get(self.url)
        else:
            self.driver.get(url)

    def by_id(self, id_):
        """id 定位"""
        return self.driver.find_element_by_id(id_)

    def by_name(self, name):
        """name 定位"""
        return self.driver.find_element_by_name(name)

    def by_class(self, class_name):
        """class 定位"""
        return self.driver.find_element_by_class_name(class_name)

    def by_xpath(self, xpath):
        """XPath 定位"""
        return self.driver.find_element_by_xpath(xpath)

    def by_css(self, css):
        """CSS 定位"""
        return self.driver.find_element_by_css_selector(css)

    def get_title(self):
        """获取 title"""
        return self.driver.title

    def get_text(self, xpath):
        """获取页面 text，仅使用 XPath 定位"""
        return self.by_xpath(xpath).text

    def js(self, script):
        """执行 JavaScript 脚本"""
        self.driver.execute_script(script)
