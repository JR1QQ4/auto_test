#!/usr/bin/python
# -*- coding:utf-8 -*-
import os
import time

from PIL import Image


def get_code_from_ai(pic_path='', ak='', sk=''):
    """
    用百度 AI 文字识别，识别图片中的文字；
    通用文字识别（高精度版）API 500次/天免费
    通用文字识别（标准版）API 50000次/天免费
    :param pic_path:
    :param ak:
    :param sk:
    :return:
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
    print(access_token)

    request_url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic"
    # 二进制方式打开图片文件
    f = open(pic_path, 'rb')
    img = base64.b64encode(f.read())

    params = {"image": img}
    request_url = request_url + "?access_token=" + access_token
    headers = {'content-type': 'application/x-www-form-urlencoded'}
    response = requests.post(request_url, data=params, headers=headers)
    if response:
        print(response.json())
        words_result = response.json()['words_result']
        if len(words_result) != 0:
            return words_result[0]['words']
        return ''
    return ''


def get_code(driver, id):
    """
    根据验证码元素位置获取验证码信息
    :param driver: 浏览器驱动
    :param id: 验证码元素的id
    :return: 验证码
    """
    t = time.time()
    path = os.path.dirname(os.path.dirname(__file__)) + "\\screenshots"
    picture_name1 = path + "\\" + str(t) + ".png"

    driver.save_screenshot(picture_name1)
    ce = driver.find_element_by_id(id)

    left = ce.location['x']
    top = ce.location['y']
    right = ce.size['width'] + left
    height = ce.size['height'] + top

    im = Image.open(picture_name1)
    img = im.crop((left, top, right, height))

    t = time.time()
    picture_name2 = path + "\\" + str(t) + ".png"
    img.save(picture_name2)

    ak = ''
    sk = ''
    verified_code = get_code_from_ai(picture_name2, ak, sk)

    return verified_code


if __name__ == '__main__':
    # ak = ''
    # sk = ''
    # pic_path = r""
    # code = get_code_from_ai(pic_path=pic_path, ak=ak, sk=sk)
    # print("code: " + code)

    # import pytesseract
    #
    # pic_path = r""
    # im = Image.open(pic_path)
    # img = im.crop((1, 1, im.width, im.height))
    # content = pytesseract.image_to_string(img)
    # print(content)
