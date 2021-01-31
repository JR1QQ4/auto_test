#!/usr/bin/python
# -*- coding:utf-8 -*-
import unittest

# import os
# path_dir = os.path.dirname(__file__)
# test_dir = 'unit'
# test_dir_path = os.path.join(path_dir, test_dir)

import os
import sys
base_path = os.getcwd()
sys.path.append(base_path)
test_dir_path = base_path + '/unit'

# test_suite = unittest.TestLoader().discover(test_dir_path, pattern='test*.py')
test_suite = unittest.defaultTestLoader.discover(test_dir_path, pattern='test*.py')
runner = unittest.TextTestRunner(verbosity=2)
runner.run(test_suite)
