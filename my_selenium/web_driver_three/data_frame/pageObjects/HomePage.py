#!/usr/bin/python
# -*- coding:utf-8 -*-
from my_selenium.web_driver_three.data_frame.util.parse_configuration_file import ParseConfigFile
from my_selenium.web_driver_three.data_frame.util.ObjectMap import *


class HomePage(object):
    def __init__(self, driver):
        self.driver = driver
        self.parseCF = ParseConfigFile()

    def addressLink(self):
        try:
            locate_type, locator = self.parseCF.getOptionValue("163mail_homepage", "homePage.addressbook").split(">")
            elementObj = get_element(self.driver, locate_type, locator)
            return elementObj
        except Exception as e:
            raise e
