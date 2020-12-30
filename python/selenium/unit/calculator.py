#!/usr/bin/python
# -*- coding:utf-8 -*-


class Calculator:
    """用于完成两个数的加减乘除"""
    def __init__(self, a: int, b: int):
        self.a = int(a)
        self.b = int(b)

    def add(self):
        return self.a + self.b

    def sub(self):
        return self.a - self.b

    def mul(self):
        return self.a * self.b

    def div(self):
        return self.a / self.b
