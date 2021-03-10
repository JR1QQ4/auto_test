#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver


class BasePage(object):
    def __init__(self, driver):
        self.driver = driver

    def get_element(self, *loc):
        return self.driver.find_element(*loc)

    def input_text(self, text, *loc):
        self.get_element(*loc).send_keys(text)

    def click(self, *loc):
        self.get_element(*loc).click()

    def get_title(self):
        return self.driver.title

    def clear(self, *loc):
        self.get_element(*loc).clear()
