#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

import time

URL = "https://mail.163.com/"
DRIVER_PATH = r"C:\Program Files (x86)\Google\Chrome\Application\chromedriver.exe"

USERNAME = "chenjunrenyx@163.com"
PASSWORD = "mail18428308932"

# 创建 Chrome 浏览器的实例
driver = webdriver.Chrome(executable_path=DRIVER_PATH)
# 设置隐式等待时间为 10 秒
driver.implicitly_wait(10)
# 最大化浏览器窗口
driver.maximize_window()

# 访问 163 邮箱登录网页
driver.get(URL)
# 暂停 3 秒，以便邮箱登录页面加载完成
time.sleep(3)
# 获取到 iframe
login_iframe = driver.find_element_by_css_selector('iframe[id^="x-URS-iframe"]')
# 切换 iframe
driver.switch_to.frame(login_iframe)
# 输入用户名
driver.find_element_by_css_selector("input[name='email']").send_keys(USERNAME)
# 输入密码
driver.find_element_by_css_selector("input[name='password']").send_keys(PASSWORD)
# 点击登录
driver.find_element_by_css_selector("#dologin").click()

# 等待登录成功后的页面加载完成
time.sleep(3)
# 单击“通讯录”按钮
driver.find_element_by_css_selector('li[title="通讯录"]').click()
# 新建联系人
driver.find_element_by_xpath("//span[contains(text(), '新建联系人')]/..").click()
# 填入姓名
driver.find_element_by_css_selector("#input_N").send_keys('张三')
# 填入邮箱
driver.find_element_by_css_selector("#iaddress_MAIL_wrap input").send_keys('admin@163.com')
# 填写手机
driver.find_element_by_css_selector("#iaddress_TEL_wrap input").send_keys("18123456789")
# 确认
driver.find_element_by_xpath('//div[@class="nui-msgbox-ft"]//span/..').click()
time.sleep(2)

# 退出
# driver.quit()