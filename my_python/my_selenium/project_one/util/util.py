#!/usr/bin/python
# -*- coding:utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


def get_code_from_ai(pic_path='', ak='', sk=''):
    """
    用百度 AI 文字识别，识别图片中的文字；
    通用文字识别（高精度版）API 500次/天免费
    通用文字识别（标准版）API 50000次/天免费
    :param pic_path: 识别的图片路径
    :param ak: 后台的身份验证
    :param sk: 后台的密码标识
    :return: 识别到的字
    """
    import requests
    import base64

    # client_id 为官网获取的AK， client_secret 为官网获取的SK
    host = 'https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&' \
           'client_id=' + ak + '&client_secret=' + sk
    response = requests.get(host)
    access_token = ''
    if response:
        # print(response.json())
        access_token = response.json()['access_token']
    # print(access_token)

    # 通用文字识别（标准版）: "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic"
    # 通用文字识别（高精度版）: "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic"
    # 网络图片文字识别: https://aip.baidubce.com/rest/2.0/ocr/v1/webimage
    # request_url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic"
    request_url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic"
    # request_url = "https://aip.baidubce.com/rest/2.0/ocr/v1/numbers"
    # 二进制方式打开图片文件
    f = open(pic_path, 'rb')
    img = base64.b64encode(f.read())

    params = {"image": img}
    request_url = request_url + "?access_token=" + access_token
    headers = {'content-type': 'application/x-www-form-urlencoded'}
    response = requests.post(request_url, data=params, headers=headers)
    if response:
        print(response.json())
        try:
            words_result = response.json()['words_result']
        except KeyError:
            return ''
        else:
            if len(words_result) != 0:
                return words_result[0]['words']
        finally:
            print("--------get_code_from_ai-------")


def get_code(driver, id):
    """
    根据验证码元素位置获取验证码信息
    :param driver: 浏览器驱动
    :param id: 验证码元素的id
    :return: 验证码
    """
    import os
    import time
    from PIL import Image

    t = time.time()
    path = os.path.dirname(os.path.dirname(__file__)) + "\\screenshots"
    picture_name1 = path + "\\" + str(t) + ".png"

    driver.save_screenshot(picture_name1)
    ce = driver.find_element_by_id(id)

    # print(ce)
    # print(ce.location)
    # print(ce.size)

    # 如何系统的显示设置，设置了不是 100% 缩放，那么需要乘以比例系数
    left = ce.location['x']
    top = ce.location['y']
    right = ce.size['width'] + left
    bottom = ce.size['height'] + top

    # print(left, top, right, bottom)

    im = Image.open(picture_name1)
    dpr = driver.execute_script("return window.devicePixelRatio")
    code_loc = (left * dpr, top * dpr, right * dpr, bottom * dpr)
    img = im.crop(code_loc)

    t = time.time()
    picture_name2 = path + "\\" + str(t) + ".png"
    img.save(picture_name2)

    ak = ''
    sk = ''
    verified_code = get_code_from_ai(picture_name2, ak, sk)

    return verified_code


def get_random_str(str_len=8):
    """
    获取由数字和字母组成的指定长度的随机字符串
    :param str_len: 字符串的长度
    :return: 随机字符串
    """
    import string
    import random

    random_str = ''.join(random.sample(string.ascii_letters + string.digits, str_len))
    return random_str


def save_cookie(driver, path):
    import pickle

    with open(path, 'wb') as file_handler:
        cookies = driver.get_cookies()
        print(cookies)
        pickle.dump(cookies, file_handler)


def load_cookie(driver, path):
    import pickle

    with open(path, 'rb') as cookies_file:
        cookies = pickle.load(cookies_file)
        for cookie in cookies:
            driver.add_cookie(cookie)


if __name__ == '__main__':
    print("--------util.util---------")

    # 测试百度 API 接口
    # ak = ''
    # sk = ''
    # pic_path = r""
    # code = get_code_from_ai(pic_path=pic_path, ak=ak, sk=sk)
    # print("code: " + code)

    # 测试 pytesseract 和 PIL 识别图片文字
    # import pytesseract
    # from PIL import Image
    #
    # pic_path = r""
    # im = Image.open(pic_path)
    # img = im.crop((1, 1, im.width, im.height))
    # content = pytesseract.image_to_string(img)
    # print(content)

    # 测试随机字符串
    # print(get_random_str())

    # 测试 pickle 处理二进制文件
    # import pickle
    #
    # # a_dict = {'da': 111, 2: [23, 1, 4], '23': {1: 2, 'd': 'sad'}}
    # # with open("test.pickle", "wb") as file:
    # #     pickle.dump(a_dict, file)
    #
    # with open("test.pickle", "rb") as file:
    #     result = pickle.load(file)
    #     print(result)

    import time
    driver = webdriver.Chrome()
    driver.get("http://localhost:8080/jpress/user/register")
    driver.maximize_window()
    time.sleep(2)
    id = "captchaimg"
    print("code: " + get_code(driver, id))
    driver.quit()

    # driver = webdriver.Chrome()
    # driver.get("http://localhost:8080/jpress/user/register")
    # driver.maximize_window()
    # img = driver.find_element_by_id("captchaimg")
    # driver.get_screenshot_as_file("test.png")
    # driver.save_screenshot("test1.png")
    # img.screenshot("img.png")
    # driver.quit()
