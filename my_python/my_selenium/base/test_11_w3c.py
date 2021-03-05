#!/usr/bin/python
# -*- coding:utf-8 -*-
import os
from time import sleep, strftime, localtime, time

from selenium import webdriver
from selenium.webdriver.support.select import Select


# driver = webdriver.Chrome()
# driver.get('https://www.w3school.com.cn/tiy/t.asp?f=html_select')
# driver.implicitly_wait(5)
# result_frame = driver.find_element_by_id('iframeResult')
# driver.switch_to.frame(result_frame)
# sel = driver.find_element_by_css_selector('select')
# Select(sel).select_by_value('volvo')
# print(Select(sel).all_selected_options[0].text)
# sleep(2)
# Select(sel).select_by_visible_text('Audi')
# print(Select(sel).all_selected_options[0].text)
# sleep(2)
# Select(sel).select_by_index(2)
# print(Select(sel).all_selected_options[0].text)


class TestCase(object):
    def __init__(self):
        self.driver = webdriver.Chrome()
        self.driver.get("http://www.baidu.com")

    def test(self):
        ele = self.driver.find_element_by_id("kw")
        ele.send_keys("Selenium")
        self.driver.find_element_by_id("su").click()

        sleep(2)

        self.driver.execute_script("document.getElementById('kw').style.border='2px solid red';")

        st = strftime("%Y-%m-%d-%H-%M-%S", localtime(time()))
        file_name = st + ".png"

        self.driver.get_screenshot_as_file(os.path.dirname(os.path.abspath(__file__)) + '/' + file_name)
        sleep(3)

        # self.driver.save_screenshot()
        # self.driver.get_screenshot_as_base64()
        # self.driver.get_screenshot_as_file()
        # self.driver.get_screenshot_as_png()

        self.driver.switch_to.default_content()


if __name__ == '__main__':
    case = TestCase()
    case.test()
    case.driver.quit()
