#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver
from selenium.webdriver.chrome.options import Options

from my_selenium.web_driver_three.data_frame.appModules.LoginAction import LoginAction
from my_selenium.web_driver_three.data_frame.appModules.AddContactPersonAction import AddContactPerson
from my_selenium.web_driver_three.data_frame.util.parse_excel import ParseExcel
from my_selenium.web_driver_three.data_frame.config.VarConfig import *

import time
import traceback


excelObj = ParseExcel()
excelObj.loadWorkBook(dataFilePath)


def LaunchBrowser():
    # 创建 Chrome 浏览器的一个 Options 实例对象
    chrome_options = Options()
    # 向 Options 实例中添加禁用扩展插件的设置参数项
    chrome_options.add_argument("--disable-extensions")
    # 添加屏蔽 --ignore-certificate-errors 提示信息的设置参数项
    chrome_options.add_experimental_option('excludeSwitches', ["ignore-certificate-errors"])
    # 添加浏览器最大化的设置参数项，已启动就最大化
    chrome_options.add_argument("--start-maximized")
    # 启动带有自定义设置的 Chrome 浏览器
    # driver = webdriver.Chrome(chrome_options=chrome_options,
    #                           executable_path=r'C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe')
    driver = webdriver.Chrome(options=chrome_options,
                              executable_path=r'C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe')
    driver.get("https://mail.163.com/")
    time.sleep(3)
    return driver


def test163MailAddContacts():
    try:
        # 根据 Excel 文件中 sheet 名称获取此 sheet 对象
        userSheet = excelObj.getSheetByName("163账号")
        # 获取 163 账号 sheet 中是否执行
        isExecuteUser = excelObj.getColumn(userSheet, account_isExecute)
        # 获取 163 账号 sheet 中的数据表列
        dataBookColumn = excelObj.getColumn(userSheet, account_dataBook)
        print("测试为 163 邮箱添加联系人执行开始...")
        print("isExecuteUser:", isExecuteUser)

        for idx, i in enumerate(isExecuteUser[1:]):
            if i.value == "y":  # 表示要执行
                # 获取第 i 行的数据
                userRow = excelObj.getRow(userSheet, idx + 2)
                # 获取第 i 行中的用户名
                username = userRow[account_username - 1].value
                # 获取第 i 行中的密码
                password = str(userRow[account_password - 1].value)
                print("------------Login Info------------")
                print(username, password)

                # 创建浏览器实例对象
                driver = LaunchBrowser()

                # 登录 163 邮箱
                LoginAction.login(driver, username, password)
                time.sleep(3)
                # 获取为第 i 行中用户添加的联系人数据表 sheet 名
                dataBookName = dataBookColumn[idx + 1].value
                # 获取对应的数据表对象
                dataSheet = excelObj.getSheetByName(dataBookName)
                # 获取联系人数据表中是否执行列对象
                isExecuteData = excelObj.getColumn(dataSheet, contacts_isExecute)
                print("------------Sheet Info------------")
                print("dataBookName:", dataBookName)
                print("dataSheet:", dataSheet)
                print("isExecuteData:", isExecuteData)

                contactNum = 0  # 记录添加成功联系人个数
                isExecuteNum = 0  # 记录需要执行联系人个数
                for id, data in enumerate(isExecuteData[1:]):
                    # 循环遍历是否执行添加联系人列，
                    # 如果被设置为添加，则进行联系人添加操作
                    if data.value == "y":
                        # 如果第 id 行的联系人被设置为执行，则 isExecuteNum 自增 1
                        isExecuteNum += 1
                        # 获取联系人表第 id+2 行对象
                        rowContent = excelObj.getRow(dataSheet, id+2)
                        # 获取联系人姓名
                        contactPersonName = rowContent[contacts_contactPersonName - 1].value
                        # 获取联系人邮箱
                        contactPersonEmail = rowContent[contacts_contactPersonEmail - 1].value
                        # 获取联系人手机号
                        contactPersonMobile = rowContent[contacts_contactPersonMobile - 1].value
                        # 获取联系人备注信息
                        contactPersonComment = rowContent[contacts_contactPersonComment - 1].value
                        # 添加联系人成功后，断言的关键字
                        assertKeyWord = rowContent[contacts_assertKeyWords - 1].value
                        print("------------contactPerson Info------------")
                        print(contactPersonName, contactPersonEmail, assertKeyWord)
                        print(contactPersonMobile, contactPersonComment)
                        # 执行新建联系人操作
                        AddContactPerson.add(driver, contactPersonName, contactPersonEmail, contactPersonMobile)
                        time.sleep(2)
                        # 在联系人工作表中写入添加联系人执行时间
                        excelObj.writeCellCurrentTime(dataSheet, rowNo=id+2, colNo=contacts_runtime)
                        try:
                            assert assertKeyWord in driver.page_source
                        except AssertionError as e:
                            excelObj.writeCell(dataSheet, "failed", rowNo=id+2, colNo=contacts_testResult, style="red")
                        else:
                            excelObj.writeCell(dataSheet, "pass", rowNo=id+2, colNo=contacts_testResult, style="green")
                            contactNum += 1
                print("contactNum = %s, isExecuteNum = %s" %(contactNum, isExecuteNum))
                if contactNum == isExecuteNum:
                    # 如果成功添加的联系人数与需要添加的联系人数相等
                    # 说明给第 i 个用户添加联系人测试用例执行成功
                    # 在 163 账号工作表中写入成功信息，否则写入失败信息
                    excelObj.writeCell(userSheet, "pass", rowNo=idx+2, colNo=account_testResult, style="green")
                    print("为用户 %s 添加 %d 个联系人，测试通过！" %(username, contactNum))
                else:
                    excelObj.writeCell(userSheet, "failed", rowNo=idx+2, colNo=account_testResult, style="red")
            else:
                print("用户 %s 被设置为忽略执行!" % excelObj.getCellOfValue(userSheet, rowNo=idx+2,
                                                                  colNo=account_username))
    except Exception as e:
        print("数据驱动框架主程序发生异常，异常信息为：")
        # 打印异常堆栈信息
        print(traceback.print_exc())


if __name__ == '__main__':
    test163MailAddContacts()
    print("登录 163 邮箱成功！")
