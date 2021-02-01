#!/usr/bin/python
# -*- coding:utf-8 -*-
import logging.config
import logging
from my_selenium.web_driver_three.data_frame.config.VarConfig import parentDirPath

# 日志配置文件路径
log_path = parentDirPath + "\\config\\logger.conf"
# 读取日志配置文件
logging.config.fileConfig(log_path)
# 选择一个日志格式
logger = logging.getLogger("example01")


def debug(message):
    logger.debug(message)


def info(message):
    logger.info(message)


def warning(message):
    logger.warning(message)


# if __name__ == '__main__':
#     debug("我是 debug")
#     info("我是 info")
#     warning("我是 warning")
