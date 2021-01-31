#!/usr/bin/python
# -*- coding:utf-8 -*-
from util.ObjectMap import *

class LoginPage(object):
    def __init__(self, driver):
        self.driver = driver

    def switchToFrame(self):
        self.driver