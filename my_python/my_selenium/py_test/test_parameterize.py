#!/usr/bin/python
# -*- coding:utf-8 -*-
import pytest
import math


@pytest.mark.skip
@pytest.mark.parametrize(
    "books, exponent, expected",
    [(2, 2, 4),
     (2, 3, 8),
     (1, 9, 1),
     (3, 1, 9),
     (0, 9, 0)],
    ids=["case1", "case2", "case3", "case4", "case5"]
)
def test_pow(books, exponent, expected):
    assert math.pow(books, exponent) == expected


data1 = [
    pytest.param(1, 2, 3, id="(a + b):pass"),
    pytest.param(4, 5, 10, id="(a + b):fail"),
]


def add(a, b):
    return a + b


@pytest.mark.parametrize("a, b, expected", data1)
def test_parameterize_1(a, b, expected):
    assert add(a, b) == expected


if __name__ == '__main__':
    # pytest.main(['-k', 'pow'])
    # pytest.main(['-q'])
    # pytest.main(['-x'])
    pytest.main(['-s', 'test_parameterize.py'])
