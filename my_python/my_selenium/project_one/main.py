#!/usr/bin/python
# -*- coding:utf-8 -*-
import unittest
from time import sleep

from selenium import webdriver

# from my_python.my_selenium.project_one.testcases.base.test_admin_login import TestAdminLogin
# from my_python.my_selenium.project_one.testcases.base.test_article import TestArticle
# from my_python.my_selenium.project_one.testcases.base.test_category import TestCategory
# from my_python.my_selenium.project_one.testcases.base.test_user_login import TestUserLogin
# from my_python.my_selenium.project_one.testcases.base.test_user_register import TestUserRegister

from my_python.my_selenium.project_one.testcases.a_unittest.test_admin_login import TestAdminLogin
from my_python.my_selenium.project_one.testcases.a_unittest.test_article import TestArticle
from my_python.my_selenium.project_one.testcases.a_unittest.test_category import TestCategory
from my_python.my_selenium.project_one.testcases.a_unittest.test_user_login import TestUserLogin
from my_python.my_selenium.project_one.testcases.a_unittest.test_user_register import TestUserRegister

from my_python.my_selenium.project_one.testcases.a_unittest import test_user_register

from my_python.my_selenium.project_one.util import util


def test_base():
    # case01 = TestUserRegister()
    # case01.test_register_code_error(False)
    # case01.test_register_code_right()

    # case02 = TestUserLogin()
    # case02.test_user_login_username_error(False)
    # case02.test_user_login_right()

    # case03 = TestAdminLogin()
    # case03.test_admin_login_code_error(False)
    # case03.test_admin_login_right()

    # login = TestAdminLogin()
    # login.test_user_login_by_cookie(False)
    # case04 = TestCategory(login)
    # case04.test_add_category_name_null(False)
    # case04.test_add_category_right()

    login = TestAdminLogin()
    login.test_user_login_by_cookie(False)
    case05 = TestArticle(login)
    # case05.test_delete_one_article_right()
    # case05.test_add_right()
    case05.test_delete_all_article_right()


def test_unittest_item():

    loader = unittest.TestLoader()
    suite = unittest.TestSuite()

    # suite.addTest(loader.loadTestsFromName('test_register_code_error', TestUserRegister))
    # suite.addTest(loader.loadTestsFromName('test_register_code_right', TestUserRegister))

    suite.addTest(loader.loadTestsFromTestCase(TestCategory))

    # suite.addTest(loader.loadTestsFromModule(test_user_register))

    # suite.addTest(TestUserRegister('test_register_code_error'))
    # suite.addTest(TestUserRegister('test_register_code_right'))

    runner = unittest.TextTestRunner(verbosity=2)
    runner.run(suite)


if __name__ == '__main__':
    print("----------main----------")

    # driver = webdriver.Chrome()
    # driver.get("http://localhost:8080/jpress/user/register")
    # driver.maximize_window()
    # sleep(2)
    #
    # print(util.get_code(driver, "captchaimg"))
    # driver.quit()

    # test_unittest_item()

    # htmltestrunner_py3
    # from my_python.my_selenium.unit_report.HwTestReport import HTMLTestReport
    #
    # loader = unittest.TestLoader()
    # suite = unittest.TestSuite()
    # suite.addTest(loader.loadTestsFromName('test_register_code_error', TestUserRegister))
    # suite.addTest(loader.loadTestsFromName('test_register_code_right', TestUserRegister))
    # with open('./reports/HwTestReportIMG.html', 'wb') as report:
    #     runner = HTMLTestReport(stream=report,
    #                             verbosity=2,
    #                             title='HwTestReport 测试',
    #                             description="带截图， 带饼图， 详细",
    #                             tester="Tony")
    #     runner.run(suite)
