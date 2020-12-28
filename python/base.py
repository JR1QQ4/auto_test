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
# finally:
#     print("不管是否出现异常，都会被执行。")

# with open("./data_file/user_info.txt") as user_file:
#     data = user_file.readlines()
#     print(data)
# users = []
# for line in data:
#     user = line[:-1].split(":")
#     users.append(user)
# print(users)

# import csv
# import codecs
# from itertools import islice
# # codecs 是 Python 标准的模块编码和解码器
# # data = csv.reader(codecs.open('./data_file/user_info.csv', 'r', encoding='utf_8_sig'))
# data = csv.reader(open('./data_file/user_info.csv', 'r', encoding='utf_8'))
# users = []
# for line in islice(data, 1, None):
#     print(line)
#     users.append(line)

# from xml.dom.minidom import parse, Document
# dom = parse('./data_file/config.xml')
# root: Document = dom.documentElement
# tag_name = root.getElementsByTagName('platform')
# print(root)
# print(tag_name[0].firstChild.data)
# print(tag_name[1].firstChild.data)
# print(tag_name[2].firstChild.data)
# login_info = root.getElementsByTagName('login')
# username1 = login_info[0].getAttribute("username")
# password1 = login_info[0].getAttribute("password")
# username2 = login_info[1].getAttribute("username")
# password2 = login_info[1].getAttribute("password")
# print(username1)
# print(password1)
# print(username2)
# print(password2)

# import json
# with open("./data_file/user_info.json") as f:
#     data = f.read()
# user_list = json.loads(data)  # 将 str 类型转换为 list 类型
# print(user_list)



