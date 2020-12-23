#!/usr/bin/python
# -*- coding:utf-8 -*-

# 格式化代码
# name = 'tom'
# age = 27
# print("name is : " + name + ", age is : " + str(age))
# print("name is : %s, age is : %d" % (name, age))
# print("name is : {0}, age is : {1}".format(name, age))

# tup = ("Hi!",)
# print(tup * 3)

# 同一项目，不同目录下包导入问题
# import sys
# from os.path import dirname, abspath
# project_path = dirname(dirname(abspath(__file__)))
# sys.path.append(project_path + "\\module")  # moudule 换成需要添加包的目录名
# print(project_path)
# print(sys.path)

# 异常捕获
# try:
#     with open('abc.txt') as f:
#         print(f.readlines())
# except Exception as e:  # 所有异常类的基类，但继承自 BaseException 类
#     print(e)
# except BaseException as msg:  # 新的所有异常类的基类
#     print(msg)


