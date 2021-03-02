#!/usr/bin/python
# -*- coding:utf-8 -*-


def test():
    import subprocess
    p = subprocess.Popen("chromedriver")
    p.communicate()


if __name__ == '__main__':
    test()
