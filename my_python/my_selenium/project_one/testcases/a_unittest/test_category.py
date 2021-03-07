#!/usr/bin/python
# -*- coding:utf-8 -*-
import unittest
from time import sleep

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.select import Select
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class TestCategory(unittest.TestCase):
    @classmethod
    def admin_login_by_cookie(cls):
        """使用 Cookie 进行登录"""
        cls.driver = webdriver.Chrome()
        cls.driver.get("http://localhost:8080/jpress/admin")
        cls.driver.maximize_window()
        cls.driver.delete_all_cookies()
        cookie_1 = {
            "name": "csrf_token",
            "value": "21c5be73382f4792962f19150ff2b6d9",
        }
        cookie_2 = {
            "name": "_jpuid",
            "value": "ODgyMjg3M2FhMmFhZjYzYTJjOWFmMGI4YjRlMjQ2ZGYjMTYxNTA1NzA4NDMyOSMxNzI4MDAjTVE9PQ=="
        }
        cls.driver.add_cookie(cookie_1)
        cls.driver.add_cookie(cookie_2)
        cls.driver.get('http://localhost:8080/jpress/admin/index')

        return cls.driver

    # def __init__(self, method_name='runTest'):
    #     self = self.admin_by_cookie()
    #
    #     # unittest.TestCase.__init__(self, method_name)
    #     super(TestCategory, self).__init__(method_name)

    @classmethod
    def setUpClass(cls) -> None:
        cls.driver = cls.admin_login_by_cookie()
        sleep(5)
        print(cls.driver)

    def test_add_category_right(self):
        self.driver.get("http://localhost:8080/jpress/admin/")

        name = "test"
        parent = "python"
        slug = "test"

        self.driver.find_element_by_xpath('//*[@id="sidebar-menu"]/li[4]/a/span[1]').click()
        sleep(1)
        self.driver.find_element_by_xpath('//*[@id="sidebar-menu"]/li[4]/ul/li[3]/a').click()
        sleep(1)

        eles = self.driver.find_elements_by_class_name("jp-actiontr")
        old_len = len(eles)

        self.driver.find_element_by_name('category.title').clear()
        self.driver.find_element_by_name('category.title').send_keys(name)

        parent_category_ele = self.driver.find_element_by_name('category.pid')
        Select(parent_category_ele).select_by_visible_text(parent)

        self.driver.find_element_by_name('category.slug').clear()
        self.driver.find_element_by_name('category.slug').send_keys(slug)

        self.driver.find_element_by_xpath(
            '/html/body/div/div/section[2]/div/div[1]/div/form/div[2]/div/div/button').click()

        sleep(2)
        eles = self.driver.find_elements_by_class_name("jp-actiontr")
        new_len = len(eles)

        print("old_len: " + str(old_len) + ", new_len: " + str(new_len))
        self.assertEqual(old_len + 1, new_len)

    @classmethod
    def tearDownClass(cls) -> None:
        cls.driver.quit()

    def test_add_category_name_null(self):
        name = ""
        parent = "python"
        slug = "test"
        expected = "分类名称不能为空"

        self.driver.find_element_by_xpath('//*[@id="sidebar-menu"]/li[4]/a/span[1]').click()
        sleep(1)
        self.driver.find_element_by_xpath('//*[@id="sidebar-menu"]/li[4]/ul/li[3]/a').click()
        sleep(1)

        self.driver.find_element_by_name('category.title').send_keys(name)

        parent_category_ele = self.driver.find_element_by_name('category.pid')
        Select(parent_category_ele).select_by_visible_text(parent)

        self.driver.find_element_by_name('category.slug').send_keys(slug)

        self.driver.find_element_by_xpath(
            '/html/body/div/div/section[2]/div/div[1]/div/form/div[2]/div/div/button').click()

        loc = (By.CLASS_NAME, 'toast-message')
        WebDriverWait(self.driver, 5).until(EC.visibility_of_element_located(loc))
        msg = self.driver.find_element(*loc).text

        self.assertEqual(msg, expected)
        sleep(2)
