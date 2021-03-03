#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

from selenium import webdriver

# 使用 By 定位元素
from selenium.webdriver.common.by import By


# chrome_driver_path = r'C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe'
# driver = webdriver.Chrome(executable_path=chrome_driver_path)


# driver = webdriver.Chrome()
# driver.get("https://www.baidu.com")

# driver.find_element_by_id()
# driver.find_element_by_class_name()
# driver.find_element_by_css_selector()
# driver.find_element_by_xpath()
# driver.find_elements_by_tag_name()
# driver.find_element_by_link_text()
# driver.find_element_by_partial_link_text()
# driver.find_element_by_name()
# driver.find_element()


# driver.find_element_by_id('kw').send_keys('Selenium')
# driver.find_element_by_id('su').click()

# print(driver.title)
# print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>")
# sleep(3)
# print(driver.title)

# 使用 By 定位元素
# driver.find_element(By.ID, 'su').click()

# sleep(3)

# 定位一组元素
# texts = driver.find_elements_by_xpath("//div[contains(@class, 'c-container')]/h3/a")
# print(len(texts))
# for t in texts:
#     print(t.text)

# driver.quit()

class TestLocation(object):
    def __init__(self):
        self.driver = webdriver.Chrome()
        self.driver.get("http://www.baidu.com")
        self.driver.maximize_window()
        sleep(1)

    def test_id(self):
        element = self.driver.find_element_by_id("kw")
        element.send_keys('Selenium')
        print(type(element))
        sleep(1)
        self.driver.find_element_by_id('su').click()
        self.tear_down()

    def test_name(self):
        # 存在多个 name 时，返回第一个
        self.driver.find_element_by_name("wd").send_keys("Selenium")
        sleep(1)
        self.driver.find_element_by_id('su').click()
        self.tear_down()

    def test_link_text(self):
        self.driver.find_element_by_id("kw").send_keys("Selenium")
        self.driver.find_element_by_id("su").click()
        self.driver.find_element_by_link_text("百度首页").click()
        self.tear_down()

    def test_partial_link_text(self):
        self.driver.find_element_by_id("kw").send_keys("Selenium")
        self.driver.find_element_by_id("su").click()
        self.driver.find_element_by_partial_link_text("首页").click()
        self.tear_down()

    def test_xpath(self):
        self.driver.find_element_by_xpath("//*[@id='kw']").send_keys("Selenium")
        self.driver.find_element_by_id("su").click()
        self.tear_down()

    def test_tag_name(self):
        input_element = self.driver.find_element_by_tag_name("input")
        print(input_element)
        self.tear_down()

    def test_css_selector(self):
        self.driver.find_element_by_css_selector("#kw").send_keys("Selenium")
        self.driver.find_element_by_id("su").click()
        self.tear_down()

    def test_class_name(self):
        self.driver.find_element_by_class_name("s_ipt").send_keys("Selenium")
        self.driver.find_element_by_id("su").click()
        self.tear_down()

    def test_all(self):
        self.driver.find_element(By.ID, value="kw").send_keys("Selenium")
        self.driver.find_element_by_id("su").click()
        self.tear_down()

    def get_element(self, *loc):
        element = self.driver.find_element(*loc)
        return element

    def tear_down(self):
        sleep(3)
        self.driver.quit()


if __name__ == '__main__':
    location = TestLocation()

    # location.test_id ()
    # location.test_name()
    # location.test_link_text()
    # location.test_partial_link_text()
    # location.test_xpath()
    # location.test_tag_name()
    # location.test_css_selector()
    # location.test_class_name()
    # location.test_all()

    loc = (By.ID, 'kw')
    loc2 = (By.ID, 'su')
    location.get_element(*loc).send_keys("Selenium")
    location.get_element(*loc2).click()
    location.tear_down()
