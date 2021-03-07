#!/usr/bin/python
# -*- coding:utf-8 -*-
import unittest
from time import sleep

from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class TestArticle(unittest.TestCase):
    def __init__(self, login):
        unittest.TestCase.__init__(self)
        self.login = login

    @classmethod
    def tearDownClass(cls) -> None:
        cls.login.driver.quit()

    def test_add_right(self):
        title = "我的文章"
        content = "我的文章内容"
        expected = "文章保存成功。"

        self.login.driver.find_element_by_xpath('//*[@id="sidebar-menu"]/li[4]/a/span[1]').click()
        sleep(1)
        self.login.driver.find_element_by_xpath('//*[@id="sidebar-menu"]/li[4]/ul/li[2]/a').click()
        sleep(1)

        self.login.driver.find_element_by_name("article.title").send_keys(title)

        input_frame = self.login.driver.find_element_by_xpath('//*[@id="cke_1_contents"]/iframe')
        self.login.driver.switch_to.frame(input_frame)
        sleep(1)

        self.login.driver.find_element_by_xpath("/html/body").send_keys(content)

        self.login.driver.switch_to.default_content()

        self.login.driver.find_element_by_xpath('//*[@id="form"]/div/div[2]/div[1]/div/button[1]').click()

        loc = (By.CLASS_NAME, "toast-message")
        WebDriverWait(self.login.driver, 5).until(EC.visibility_of_element_located(loc))
        msg = self.login.driver.find_element(*loc).text

        self.assertEqual(msg, expected)

    def test_delete_one_article_right(self):
        self.login.driver.find_element_by_xpath('//*[@id="sidebar-menu"]/li[4]/a/span[1]').click()
        sleep(1)
        self.login.driver.find_element_by_xpath('//*[@id="sidebar-menu"]/li[4]/ul/li[1]/a').click()
        sleep(1)

        link_ele = self.login.driver.find_element_by_xpath(
            '/html/body/div/div/section[3]/div/div/div/div[2]/table/tbody/tr[2]/td[2]/strong/a')
        ActionChains(self.login.driver).move_to_element(link_ele).perform()
        sleep(1)

        old_article_num = len(self.login.driver.find_elements_by_class_name("jp-actiontr"))

        del_ele = self.login.driver.find_element_by_xpath(
            '/html/body/div/div/section[3]/div/div/div/div[2]/table/tbody/tr[2]/td[2]/div/div/a[3]')
        del_ele.click()
        sleep(1)

        new_article_num = len(self.login.driver.find_elements_by_class_name("jp-actiontr"))

        self.assertEqual(old_article_num, new_article_num + 1)

    def test_delete_all_article_right(self):
        self.login.driver.find_element_by_xpath('//*[@id="sidebar-menu"]/li[4]/a/span[1]').click()
        sleep(1)
        self.login.driver.find_element_by_xpath('//*[@id="sidebar-menu"]/li[4]/ul/li[1]/a').click()
        sleep(1)

        link = self.login.driver.find_element_by_xpath(
            '/html/body/div/div/section[3]/div/div/div/div[2]/table/tbody/tr[1]/th[1]/input')
        link.click()

        self.login.driver.find_element_by_id('batchDel').click()

        WebDriverWait(self.login.driver, 5).until(EC.alert_is_present())
        alert = self.login.driver.switch_to.alert
        alert.accept()

        sleep(1)

        article_num = self.login.driver.find_elements_by_class_name("jp-actiontr")
        self.assertEqual(len(article_num), 0)
