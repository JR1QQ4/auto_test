#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import ctime, sleep

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import NoSuchElementException


# driver = webdriver.Chrome()
# url = "http://www.baidu.com"
# driver.get(url)

# 1.WebDriverWait
# element: WebElement = WebDriverWait(driver, 5, .5).until(
#     EC.visibility_of_element_located((By.ID, "kw")))
# element.send_keys('selenium')


# EC.presence_of_element_located(locator='')
# EC.visibility_of_element_located(locator='')
# EC.visibility_of(element)
# EC.invisibility_of_element_located(locator='')

# 2.is_displayed
# print(ctime())
# for i in range(10):
#     try:
#         el = driver.find_element_by_id('kw22')
#         print(i)
#         if el.is_displayed():
#             break
#     except BaseException as e:
#         pass
#     sleep(1)
# else:
#     print('time out')
# print(ctime())

# 3.隐式等待
# driver.implicitly_wait(3)
# try:
#     print(ctime())
#     driver.find_element_by_id('kw22').send_keys('selenium')
# except NoSuchElementException as e:
#     print(e)
# finally:
#     print(ctime())


class TestCase(object):
    def __init__(self):
        self.driver = webdriver.Chrome()
        self.driver.get("http://www.baidu.com")

    def test_sleep(self):
        self.driver.find_element_by_id("kw").send_keys("Selenium")
        sleep(2)
        self.driver.find_element_by_id("su").click()
        sleep(3)
        self.driver.quit()

    def test_implicitly(self):
        self.driver.implicitly_wait(10)
        self.driver.find_element_by_id("kw").send_keys("Selenium")
        self.driver.find_element_by_id("su").click()
        self.driver.quit()

    def test_wait(self):
        wait = WebDriverWait(self.driver, 2)
        print(wait.until(EC.title_is("百度一下，你就知道")))

        # wait.until(EC.title_is())
        # wait.until(EC.title_contains())
        # wait.until(EC.presence_of_element_located())
        # wait.until(EC.visibility_of_element_located())
        # wait.until(EC.visibility_of())
        # wait.until(EC.presence_of_all_elements_located())
        # wait.until(EC.visibility_of_all_elements_located())
        # wait.until(EC.text_to_be_present_in_element())
        # wait.until(EC.text_to_be_present_in_element_value())
        # wait.until(EC.frame_to_be_available_and_switch_to_it())
        # wait.until(EC.invisibility_of_element_located())
        # wait.until(EC.element_to_be_clickable())
        # wait.until(EC.staleness_of())
        # wait.until(EC.element_to_be_selected())
        # wait.until(EC.element_selection_state_to_be())
        # wait.until(EC.element_located_selection_state_to_be())
        # wait.until(EC.alert_is_present())
        # wait.until(EC.url_contains())

        self.driver.find_element_by_id("kw").send_keys("Selenium")
        self.driver.find_element_by_id("su").click()
        self.driver.quit()


if __name__ == '__main__':
    case = TestCase()
    # case.test_sleep()
    # case.test_implicitly()
    case.test_wait()
