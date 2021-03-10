#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.select import Select
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

from my_python.my_selenium.project_one.testcases.c_ddt.test_admin_login import TestAdminLogin

import pytest


class TestCategory(object):
    def setup_class(self):
        self.login = TestAdminLogin()

    def teardown_class(self):
        self.login.driver.quit()

    category_data = [
        ("0", "", "python", "test", "分类名称不能为空"),
        ("1", "test", "python", "test", "分类名称不能为空")
    ]

    @pytest.mark.dependency(depends=["admin_login"], scope="module")
    @pytest.mark.parametrize("flag, name, parent, slug, expected", category_data)
    def test_add_category(self, flag, name, parent, slug, expected):
        sleep(2)

        if flag == "0":
            # self.login.driver.get("http://localhost:8080/jpress/admin/")
            self.login.driver.find_element_by_xpath('//*[@id="sidebar-menu"]/li[4]/a/span[1]').click()
            sleep(1)
        self.login.driver.find_element_by_xpath('//*[@id="sidebar-menu"]/li[4]/ul/li[3]/a').click()
        sleep(1)

        eles = self.login.driver.find_elements_by_class_name("jp-actiontr")
        old_len = len(eles)

        self.login.driver.find_element_by_name('category.title').clear()
        self.login.driver.find_element_by_name('category.title').send_keys(name)

        parent_category_ele = self.login.driver.find_element_by_name('category.pid')
        Select(parent_category_ele).select_by_visible_text(parent)

        self.login.driver.find_element_by_name('category.slug').clear()
        self.login.driver.find_element_by_name('category.slug').send_keys(slug)

        self.login.driver.find_element_by_xpath(
            '/html/body/div/div/section[2]/div/div[1]/div/form/div[2]/div/div/button').click()

        sleep(2)

        if flag == '0':
            loc = (By.CLASS_NAME, 'toast-message')
            WebDriverWait(self.login.driver, 5).until(EC.visibility_of_element_located(loc))
            msg = self.login.driver.find_element(*loc).text

            assert msg == expected
        elif flag == '1':
            eles = self.login.driver.find_elements_by_class_name("jp-actiontr")
            new_len = len(eles)

            print("old_len: " + str(old_len) + ", new_len: " + str(new_len))
            assert old_len + 1 == new_len

        sleep(2)


if __name__ == '__main__':
    pytest.main(['-sv', 'test_category.py'])
