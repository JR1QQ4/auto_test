#!/usr/bin/python
# -*- coding:utf-8 -*-
import time
import unittest
import os

path_dir = os.path.dirname(__file__)
timer = time.strftime("%Y-%m-%d_%H_%M-%S")
report_file = path_dir + "\\test_report\\" + timer + ".html"
test_dir = path_dir + "\\" + "test_case"
report_template_path = path_dir + "\\report_template.html"

test_suite = unittest.defaultTestLoader.discover(test_dir)

if __name__ == '__main__':
    # from python.selenium.unit_report.HwTestReport import HTMLTestReport
    # with open('./test_report/HwTestReportIMG.html', 'wb') as report:
    #     runner = HTMLTestReport(stream=report,
    #                             verbosity=2,
    #                             title='HwTestReport 测试',
    #                             description="带截图， 带饼图， 详细",
    #                             tester="Johnny")
    #     runner.run(test_suite)

    # from HtmlTestRunner import HTMLTestRunner
    # import getpass
    # template_args = {
    #     "user": getpass.getuser()
    # }
    # with open(report_file, 'w', encoding='utf-8') as f:
    #     runner = HTMLTestRunner(output='./test_report',
    #                             stream=f,
    #                             report_title="百度测试报告",
    #                             template=report_template_path,
    #                             template_args=template_args)
    #     runner.run(test_suite)

    from python.selenium.unit_report.HTMLTestRunner import HTMLTestRunner
    with open(report_file, 'w', encoding='utf-8') as f:
        runner = HTMLTestRunner(stream=f,
                                title="百度测试报告",
                                description="运行环境： Windows 10, Chrome 浏览器")
        runner.run(test_suite)

