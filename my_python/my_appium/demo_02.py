#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep

import appium
from appium import webdriver

# 定义运行环境
from appium.webdriver.common.mobileby import MobileBy
from appium.webdriver.common.touch_action import TouchAction
from appium.webdriver.extensions.android.gsm import GsmCallActions
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait

desired_caps = {
    "platformName": "Android",
    "deviceName": "OPPO",  # 设备名称
    "platformVersion": "6.0.1",  # android系统版本号
    "appPackage": "com.xueqiu.android",  # app包名
    "appActivity": "com.xueqiu.android.view.WelcomeActivityAlias",  # 启动 Activity
    # "appPackage": "com.android.settings",  # app包名
    # "appActivity": "com.android.settings.Settings",  # 启动 Activity，每个页面都是一个 activity，一般指定首页或者欢迎页
    'automationName': 'Uiautomator2',  # 使用Uiautomator2，默认的
    'noRest': "true"  # 第一次关闭弹窗后，第二次就不会有了；第一次登录后，第二次就不会再要求登录
}
driver = webdriver.Remote('http://127.0.0.1:4723/wd/hub', desired_caps)  # 4723 是 appium-server 的监听端口
driver.implicitly_wait(10)

WebDriverWait(driver, 5).until(expected_conditions.visibility_of_element_located((MobileBy.ID, "com.android.settings:id/title")))

el1 = driver.find_element_by_id("com.xueqiu.android:id/tv_search")
el1.click()
el2 = driver.find_element_by_id("com.xueqiu.android:id/search_input_text")
el2.click()
el2.send_keys("alibaba")
el3 = driver.find_element_by_xpath(
    "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]")
el3.click()


driver.quit()

