#!/usr/bin/python
# -*- coding:utf-8 -*-
from time import sleep
from selenium import webdriver

driver = webdriver.Chrome()
driver.get("https://videojs.com/")

video = driver.find_element_by_id("preview-player_html5_api")
print(video)

# 返回播放文件地址
url = driver.execute_script("return arguments[0].currentSrc;", video)
print(url)


# 播放视频
print("start")
driver.execute_script("arguments[0].play()", video)

# 播放 15s
sleep(15)

# 暂停视频
print("stop")
driver.execute_script("arguments[0].pause()", video)
