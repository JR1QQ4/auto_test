#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from my_selenium.my_page_object.pages.Main import Main


class TestAddMember:
    def setup(self):
        self.main = Main()

    def test_add_member(self):
        add_member = self.main.goto_add_member()
        add_member.add_member()
        assert "title" in add_member.get_member()
