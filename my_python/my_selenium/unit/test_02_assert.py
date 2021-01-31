#!/usr/bin/python
# -*- coding:utf-8 -*-
import unittest


class TestAssert(unittest.TestCase):
    def setUp(self):
        print('----setUp-----')

    def tearDown(self):
        print("=======tearDown==========")

    @classmethod
    def setUpClass(cls):
        print("********setUpClass*********")

    @classmethod
    def tearDownClass(cls):
        print("+++++++++tearDownClass+++++++++")

    def test_equal_01(self):
        self.assertEqual(2 + 2, 4)
        self.assertEqual("python", "python")
        self.assertNotEqual('hello', 'python')

    def test_in_02(self):
        self.assertIn('hello', 'hello world')
        self.assertNotIn('hi', 'hello')

    def test_true_03(self):
        self.assertTrue(True)
        self.assertFalse(False)


if __name__ == '__main__':
    unittest.main()
