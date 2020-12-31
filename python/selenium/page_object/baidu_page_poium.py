#!/usr/bin/python
# -*- coding:utf-8 -*-
from poium import Page, NewPageElement


class BaiduPage(Page):
    """百度 Page 层，百度页面封装操作到的元素"""
    search_input = NewPageElement(id_='kw', describe="搜索输入框")
    search_button = NewPageElement(id_='su', describe="搜索按钮")
