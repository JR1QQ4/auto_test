#!/usr/bin/python
# -*- coding:utf-8 -*-
import os
import unittest

from ddt import ddt, data, unpack, file_data


def get_data():
    test_data = [{'name': 'zs', 'age': 20}, {'name': 'ls', 'age': 23}]
    return test_data


@ddt
class MyTestCase(unittest.TestCase):
    @data(1, 2, 3)
    def test1(self, value):
        """读取元组数组-单组元素"""
        print(value)

    @data((1, 2, 3), (4, 5, 6))
    def test2(self, value):
        """读取元组数组-多组元素"""
        print(value)

    @data((1, 2, 3), (4, 5, 6))
    @unpack
    def test3(self, value1, value2, value3):
        """拆分数据"""
        print(value1, value2, value3)

    @data([{'name': 'tom', 'age': 20}, {'name': 'kite', 'age': 30}])
    def test4(self, value):
        """列表"""
        print(value)

    @data({'name': 'tom', 'age': 20}, {'name': 'kite', 'age': 30})
    def test5(self, value):
        """字典"""
        print(value)

    @data({'name': 'tom', 'age': 20}, {'name': 'kite', 'age': 30})
    @unpack
    def test6(self, name, age):
        """字典拆分"""
        print(name, age)

    testdata = [{'name': 'tom', 'age': 20}, {'name': 'kite', 'age': 30}]

    @data(get_data())
    def test7(self, value):
        print(value)


if __name__ == '__main__':
    unittest.main()
