#!/usr/bin/python
# -*- coding:utf-8 -*-
import unittest
import random


# class TestSequenceFunctions(unittest.TestCase):
#     def setUp(self) -> None:
#         self.seq = range(10)
#     def testChoice(self):
#         element = random.choice(self.seq)
#         self.assertTrue(element in self.seq)
#     def testSample(self):
#         with self.assertRaises(ValueError):
#             random.sample(self.seq, 20)
#         for element in random.sample(self.seq, 5):
#             self.assertTrue(element in self.seq)
#
# class TestDictValueFormatFunctions(unittest.TestCase):
#     def setUp(self) -> None:
#         self.seq = list(range(10))
#     def test_shuffle(self):
#         random.shuffle(self.seq)
#         self.seq.sort()
#         self.assertEqual(self.seq, list(range(10)))
#         self.assertRaises(TypeError, random.shuffle, (1, 2, 3))


class MyClass(object):
    @classmethod
    def sum(cls, a, b):
        return a + b
    @ classmethod
    def sub(cls, a, b):
        return a - b

class MyTest(unittest.TestCase):
    @classmethod
    def setUpClass(cls) -> None:
        print("--------setUpClass")
    @classmethod
    def tearDownClass(cls) -> None:
        print("-------------tearDownClass")
    def setUp(self) -> None:
        self.a = 3
        self.b = 1
        print("++++setUp")
    def tearDown(self) -> None:
        print("++++tearDown")
    @unittest.skip('skipping')  # 无条件忽略
    def testMul(self):
        print('testMul..........')
    def testSum(self):
        print('testSum..........')
        self.assertEqual(MyClass.sum(self.a, self.b), 4, 'test sum fail')
    def testSub(self):
        print('testSub..........')
        self.assertEqual(MyClass.sub(self.a, self.b), 2, 'test sub fail')


if __name__ == '__main__':
    unittest.main(verbosity=2)

    # 修改执行顺序
    # suite = unittest.TestSuite()
    # suite.addTest(MyClass("testSum"))
    # suite.addTest(MyClass("testSub"))
    # runner = unittest.TextTestRunner()
    # runner.run(suite)