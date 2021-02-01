#!/usr/bin/python
# -*- coding:utf-8 -*-
from my_selenium.web_driver_three.data_frame.util.parse_configuration_file import ParseConfigFile
from my_selenium.web_driver_three.data_frame.util.ObjectMap import *


class AddressBookPage(object):
    def __init__(self, driver):
        self.driver = driver
        self.parseCF = ParseConfigFile()
        self.addContactsOptions = self.parseCF.getItemsSection("163mail_addContactPage")

    def createContactPersonButton(self):
        try:
            locate_type, locator = self.addContactsOptions["addContactPage.createContactsBtn".lower()].split(">")
            elementObj = get_element(self.driver, locate_type, locator)
            return elementObj
        except Exception as e:
            raise e

    def createContactPersonName(self):
        try:
            locate_type, locator = self.addContactsOptions["addContactPage.contactPersonName".lower()].split(">")
            elementObj = get_element(self.driver, locate_type, locator)
            return elementObj
        except Exception as e:
            raise e

    def createContactPersonEmail(self):
        try:
            locate_type, locator = self.addContactsOptions["addContactPage.contactPersonEmail".lower()].split(">")
            elementObj = get_element(self.driver, locate_type, locator)
            return elementObj
        except Exception as e:
            raise e

    def createContactPersonMobile(self):
        try:
            locate_type, locator = self.addContactsOptions["addContactPage.contactPersonMobile".lower()].split(">")
            elementObj = get_element(self.driver, locate_type, locator)
            return elementObj
        except Exception as e:
            raise e

    def saveContactPerson(self):
        try:
            locate_type, locator = self.addContactsOptions["addContactPage.saveContactPerson".lower()].split(">")
            elementObj = get_element(self.driver, locate_type, locator)
            return elementObj
        except Exception as e:
            raise e