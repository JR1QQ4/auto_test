#!/usr/bin/python
# -*- coding:utf-8 -*-
from appium import webdriver

# 定义运行环境
desired_caps = {
    "platformName": "Android",
    "deviceName": "emulator-5554",  # 设备名称
    "platformVersion": "10.0",  # android系统版本号
    "appPackage": "com.miui.calculator",  # app包名
    "appActivity": "com.miui.calculator.cal.CalculatorActivity",  # 启动launch Activity
    'automationName': 'Uiautomator2'  # 使用Uiautomator2
}
driver = webdriver.Remote(command_executor='http://127.0.0.1:4726/wd/hub',
                          desired_capabilities=desired_caps)
# driver.find_element_by_id("com.android.calculator2:id/digit_1").click()
# driver.find_element_by_id("com.android.calculator2:id/op_add").click()
# driver.find_element_by_id("com.android.calculator2:id/digit_2").click()
# driver.find_element_by_id("com.android.calculator2:id/eq").click()
driver.quit()
