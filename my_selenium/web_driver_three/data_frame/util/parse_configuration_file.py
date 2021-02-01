#!/usr/bin/python
# -*- coding:utf-8 -*-
from my_selenium.web_driver_three.data_frame.config.VarConfig import pageElementLocatorPath

from configparser import ConfigParser


class ParseConfigFile(object):
    def __init__(self):
        self.cf = ConfigParser()
        self.cf.read(pageElementLocatorPath, encoding="utf-8")

    def getItemsSection(self, sectionName):
        optionsDict = dict(self.cf.items(sectionName))
        return optionsDict

    def getOptionValue(self, sectionName, optionName):
        value = self.cf.get(sectionName, optionName)
        return value


# if __name__ == '__main__':
#     pc = ParseConfigFile()
#     print(pc.getItemsSection("163mail_login"))
#     print(pc.getOptionValue("163mail_login", "loginPage.frame"))
