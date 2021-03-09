#!/usr/bin/python
# -*- coding:utf-8 -*-
import logging
import logging.handlers
import datetime

format_str = "【%(levelname)s】%(asctime)s - %(filename)s - %(module)s - %(lineno)d: %(message)s"
logging.basicConfig(
    filename="log.log",
    level=logging.INFO,
    format=format_str
)
logging.debug("debug")
logging.info("info")
logging.warning("warning")
logging.error("error")
logging.critical("critical")

logger = logging.getLogger("myLogger")
logger.setLevel(logging.DEBUG)

# rf_handler = logging.handlers.TimedRotatingFileHandler('all.log', when='midnight', interval=1, backupCount=7,
#                                                        atTime=datetime.time())
# rf_handler.setFormatter(logging.Formatter("%(asctime)s - %(levelname)s - %(message)s"))
# logger.addHandler(rf_handler)

fh_error = logging.FileHandler('error.log')
fh_error.setLevel(logging.ERROR)
fh_error.setFormatter(logging.Formatter("%(asctime)s - %(levelname)s - %(filename)s - %(module)s[%(lineno)d] : "
                                        "%(message)s"))

fh_all = logging.FileHandler('all.log')
fh_all.setLevel(logging.DEBUG)
fh_all.setFormatter(logging.Formatter("[%(levelname)s] - %(asctime)s - %(filename)s - %(module)s[%(lineno)d] : "
                                      "%(message)s"))

logger.addHandler(fh_error)
logger.addHandler(fh_all)

logger.debug("debug")
logger.info("info")
logger.warning("warning")
logger.error("error")
logger.critical("critical")
