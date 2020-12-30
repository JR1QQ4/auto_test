#!/usr/bin/python
# -*- coding:utf-8 -*-
import unittest

def setUpModule():
    print("test module start >>>>>>>>>>>>")

def tearDownModule():
    print("test module emd >>>>>>>>>>>")

class TestBdd(unittest.TestCase):
    def setUp(self):
        print("test TestBdd setUp:")
    def test_ccc(self):
        print("test ccc")
    def test_aaa(self):
        print("test aaa")

class TestAdd(unittest.TestCase):
    def setUp(self):
        print("test TestAdd setUp:")
    def test_bbb(self):
        print("test bbb")

if __name__ == '__main__':
    # unittest.main(verbosity=2)

    suite = unittest.TestSuite()
    suite.addTest(TestBdd("test_aaa"))
    suite.addTest(TestBdd("test_ccc"))
    suite.addTest(TestAdd("test_bbb"))
    runner = unittest.TextTestRunner()
    runner.run(suite)






