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

# import smtplib
# from email.mime.multipart import MIMEMultipart
# from email.mime.text import MIMEText
# # 邮箱服务器地址
# smtp_addr = ""
# # 发件人邮箱地址和令牌
# send_user = ""
# send_pwd = ""
# # 收件人邮箱地址
# receive_user = ""
# # 发送邮件主题
# subject = '我是 Subject'
# # 编写 HTML 类型的邮件正文
# msg = MIMEMultipart()
# body = MIMEText('Subject: Solong.\nDear Alice, so long and thanks for all the fish. Sincerely, Bob', 'plain', 'utf-8')
# msg['Subject'] = subject
# msg['From'] = send_user
# msg['To'] = receive_user
# msg.attach(body)
# # 带附件
# with open('python.md', 'rb') as f:
#     send_att = f.read()
# att = MIMEText(send_att, 'text/x-markdown', 'utf-8')
# # att['Content-Type'] = 'application/x-genesis-rom'
# att['Content-Type'] = 'application/octet-stream'
# att["Content-Disposition"] = 'attachment; filename="python.md"'
# msg.attach(att)
# # 发送邮件
# smtp = smtplib.SMTP(host=smtp_addr, port=25)
# smtp.login(send_user, send_pwd)
# smtp.sendmail(send_user, [send_user, receive_user], msg.as_string())
# smtp.quit()

# import yagmail
# yagmail.SMTP(user='', password='', host='')\
#     .send(to='', subject='subject', contents='This is the body',
#           attachments=[''])

# import os
# import xlrd  # pip install xlrd==1.2.0
# excel_path = os.path.dirname(os.path.abspath(__file__)) + "/data_file/user_info.xlsx"
# book = xlrd.open_workbook(excel_path)
# print("The number of worksheets is {0}".format(book.nsheets))
# print("Worksheet name(s): {0}".format(book.sheet_names()))
# sh = book.sheet_by_index(0)
# print("{0} {1} {2}".format(sh.name, sh.nrows, sh.ncols))
# print("Cell D30 is {0}".format(sh.cell_value(rowx=1, colx=1)))
# for rx in range(sh.nrows):
#     print(sh.row(rx))
# print("=" * 20)
# for row in range(sh.nrows):
#     for col in range(sh.ncols):
#         print(sh.cell_value(row, col))

# import pymysql.cursors
# connection = pymysql.connect(host='localhost',
#                              user='chen',
#                              password='chen',
#                              database='world',
#                              cursorclass=pymysql.cursors.DictCursor)
# with connection:
#     # with connection.cursor() as cursor:
#     #     # Create a new record
#     #     sql = "INSERT INTO `users` (`email`, `password`) VALUES (%s, %s)"
#     #     cursor.execute(sql, ('webmaster@python.org', 'very-secret'))
#     # # connection is not autocommit by default. So you must commit to save
#     # # your changes.
#     # connection.commit()
#     with connection.cursor() as cursor:
#         # Read a single record
#         sql = "select * from city limit 5;"
#         cursor.execute(sql)
#         result = cursor.fetchone()
#         results = cursor.fetchall()
#         print(result)
#         print(results)
