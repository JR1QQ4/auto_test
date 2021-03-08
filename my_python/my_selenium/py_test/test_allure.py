#!/usr/bin/python
# -*- coding:utf-8 -*-
import pytest
import allure


@pytest.fixture(scope="session")
def login():
    print("用户登录")


@allure.step("步骤1: 点xxx")
def step_1():
    print("step 1")


@allure.step("步骤2: 点xxx")
def step_2():
    print("step 2")


@allure.feature("编辑页面")
class TestEditPage(object):
    """编辑页面"""

    @allure.story("这是第一个xxx的用例")
    @allure.testcase("测试文章管理")
    def test_1(self, login):
        """用例描述: 先登陆，再去执行xxx"""
        step_1()
        step_2()
        print("xxx")

    @allure.story("打开a页面")
    @allure.testcase("测试发表文章")
    def test_2(self, login):
        """用例描述: 先登录，再去执行yyy"""
        print("yyy")


if __name__ == '__main__':
    # 注意生成测试报告 必须在命令行执行
    # $ pytest --alluredir ./report test_allure.py
    # $ allure serve ./report，启动 allure 查看报告
    pytest.main(['--alluredir', './report', 'test_allure.py'])
