#!/usr/bin/python
# -*- coding:utf-8 -*-
import pytest


@pytest.fixture()
def init():
    print("---init---")


def test01(init):
    print("---test01---")


def test02(init):
    print("---test01---")


if __name__ == '__main__':
    pytest.main(['-sv'])
