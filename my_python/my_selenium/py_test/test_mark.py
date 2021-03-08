#!/usr/bin/python
# -*- coding:utf-8 -*-
import pytest


@pytest.mark.do
def test01():
    print('test01----')


@pytest.mark.undo
def test02():
    print('test01----')
