#!/usr/bin/python
# -*- coding:utf-8 -*-
from python.selenium.unit.calculator import Calculator
import unittest


# def test_add():
#     c = Calculator(3, 5)
#     result = c.add()
#     assert result == 8, '加法运算失败'
# def test_sub():
#     c = Calculator(7, 2)
#     result = c.sub()
#     assert result == 5, '减法运算失败!'
# def test_mul():
#     c = Calculator(3, 3)
#     result = c.mul()
#     assert result == 10, '乘法运算失败!'
# def test_div():
#     c = Calculator(6, 2)
#     result = c.div()
#     assert result == 3, '除法运算失败!'
# if __name__ == '__main__':
#     test_add()
#     test_sub()
#     test_mul()
#     test_div()

# class TestCalculator(unit.TestCase):
#     def test_add(self):
#         c = Calculator(3, 5)
#         result = c.add()
#         self.assertEqual(result, 8, '加法运算失败')
#     def test_sub(self):
#         c = Calculator(7, 2)
#         result = c.sub()
#         self.assertEqual(result, 5, '减法运算失败')
#     def test_mul(self):
#         c = Calculator(3, 3)
#         result = c.mul()
#         self.assertEqual(result, 10, '乘法运算失败')
#     def test_div(self):
#         c = Calculator(6, 2)
#         result = c.div()
#         self.assertEqual(result, 3, '除法运算失败')
# if __name__ == "__main__":
#     unit.main()

# class TestCalculator(unit.TestCase):
#     def setUp(self) -> None:
#         print("test start : ")
#     def tearDown(self) -> None:
#         print("test end")
#     def test_add(self):
#         c = Calculator(3, 5)
#         result = c.add()
#         self.assertEqual(result, 8, '加法运算失败')
#     def test_sub(self):
#         c = Calculator(7, 2)
#         result = c.sub()
#         self.assertEqual(result, 5, '减法运算失败')
#     def test_mul(self):
#         c = Calculator(3, 3)
#         result = c.mul()
#         self.assertEqual(result, 10, '乘法运算失败')
#     def test_div(self):
#         c = Calculator(6, 2)
#         result = c.div()
#         self.assertEqual(result, 3, '除法运算失败')
# if __name__ == '__main__':
#     # 创建测试套件
#     suit = unit.TestSuite()
#     suit.addTest(TestCalculator("test_add"))
#     suit.addTest(TestCalculator("test_sub"))
#     suit.addTest(TestCalculator("test_mul"))
#     suit.addTest(TestCalculator("test_div"))
#     # 创建测试运行器
#     runner = unit.TextTestRunner()
#     runner.run(suit)

# class TestAdd(unit.TestCase):
#     def test_add_integer(self):
#         c = Calculator(3, 5)
#         self.assertEqual(c.add(), 8)
#     def test_add_decimals(self):
#         c = Calculator(3.2, 5.5)
#         self.assertEqual(c.add(), 8)
#     def test_add_string(self):
#         c = Calculator("7", "9")
#         self.assertEqual(c.add(), 16)
# class TestSub(unit.TestCase):
#     def test_sub_integer(self):
#         c = Calculator(3, 5)
#         self.assertEqual(c.sub(), -2)
#     def test_sub_decimals(self):
#         c = Calculator(3.2, 5.5)
#         self.assertEqual(c.sub(), -2)
#     def test_sub_string(self):
#         c = Calculator("7", "9")
#         self.assertEqual(c.sub(), 2)
# if __name__ == '__main__':
#     unit.main()