## 软件测试

### 理论知识

Bug：格蕾丝·赫柏(Grace Murray Hopper)使用的Mark Ⅱ由于继电器中有一只死掉的蛾子而出现故障。后来"bug"(小虫)和"debug"(除虫)成了计算机领域中特指莫明其妙的"错误"和"排除错误"的专用词汇。

软件测试：为了发现软件中可能存在的错误而运行软件的过程。存在错误、发现错误、运行软件

Bug的三种状态：

- Fault：静态存在于软件中的缺陷
- Error：在运行过程中运行到Fault，出现的一种中间状态
- Failure：失效，Error一直传播到软件外面，用户能观测到这行为

PIE MODEL模型：执行fault -> 出现Error(可能不出现中间状态) -> Failure(可能不出现)

软件测试：为了发现错误而执行软件的过程；从无限的输入中寻找有限的测试用例，并用这些有限的测试用例进行动态验证的方法；为了满足特定的需求规约而对软件进行分析和评价的过程。

测试用例：输入、期望输出、环境要素

测试：为了发现错误而执行软件的过程
调试：发现了错误去修改的过程

确认：是个外部过程，用户来判断软件的质量、功能等是否符合要求
验证：更多是一个内部的过程，开发组织内部对软件测试查看是否符合规约

常见的测试技术分类：

- 基于直觉/经验的测试技术：个人经验、探索测试
- 基于输入域的测试技术：等价类、边界值、随机测试、组合测试
- 基于代码的测试技术：代码抽象的方法、基于控制流、基于数据流
- 基于错误的测试技术：错误猜测法、变异测试
- 基于模型的测试技术：决策表、有限状态机、形式化规约、工作流模型
- 基于使用的测试技术
- 基于应用特性的测试技术

覆盖率：

- 结构覆盖：语句覆盖率、分支覆盖率
- 逻辑覆盖：判定覆盖率、条件覆盖率、判定条件覆盖率、条件组合覆盖率
- 路径覆盖：路径覆盖率；循环路径，基本路径测试
- 数据流覆盖

程序环路复杂性计算方法(3种)：
(1)流程图中区域的数量对应于环形复杂度
(2)给定流图G的环形复杂度V(G)，定义为V(G)=E-N+2，E是图中边的数量，N是图中节点的数量
(3)V(G)=P+1，P是流图G中的判定节点数

### Linux

单引号不转义，双引号会转义

#### grep、sed、awk

grep: 根据用户指定的模式(pattern)对目标文本进行过滤，显示被模糊匹配到的行

- `grep -n root passwd.txt`，显示行号
- `grep -v root passwd.txt`，反选
- `grep ^root passwd.txt`，`grep root$ passwd.txt`，开始与结尾
- `echo "a b cd abcdef" | grep -o "\babcdef\b"`，查找 abcdef
- `echo "aaafff" | grep -E "f{3}"`，`-E` 扩展正则，加了才能匹配 aaafff
- `grep -Eo '^([0-9]*\.){3}[0-9]*' nginx.log | sort | uniq -c | sort -nr | head -3`，统计前三的 ip 地址

sed: 是流编辑器，一次处理一行内容，行存储在模式空间中

- `sed -e '1 a drink tea' passwd.txt`，a 标识添加，1 表示行号，drink tea 表示追加的文本，源文件不会改变，可以使用重定向保存
- `sed -e '2i newline' passwd.txt`，在第 2 行前面插入 newline，`-e` 后面接的就是需要执行的脚本
- `sed -e 's/root/hello/g' passwd.txt`，全局替换，把 root 替换成 hello
- `sed -e '2,5c No 2-5 number' passwd.txt`，第 2 行到第 5 行会被 No 2-5 number 取代
- `sed -i "50,60d" passwd.txt`，`-i` 会修改源文件，删除50到60行的内容并保存修改后的文件
- `sed -n '/root/p' passwd.txt`，会打印包含 root 的行
- `sed 's#topics/[0-9]*#topics/number#g' nginx.log`，`#`是为了区分需要匹配的内容，把 `topics/[0-9]*` 替换成 `topics/number`
    - 等价于 `sed 's//topics/[0-9]*//topics/number//g' nginx.log`

awk: 把文件逐行的读入，以空格为默认分隔符将每行切片，切开的部分再进行后续处理

- 把行作为输入，并赋值给`$0` -> 将行切段，从 `$1` 开始 -> 对行匹配正则/执行动作 -> 打印内容
- 常用参数:
	- FILENAME，awk 浏览的文件名
	- BEGIN，处理文本之前要执行的操作
	- END，处理文本之后要执行的操作
	- FS，设置输入域分隔符，等价于命令行 -F 选项
	- NF，浏览器记录的域的个数（列数）
	- NR，已读的记录数（行数）
	- OFS，输出域分隔符; ORS，输出记录分隔符; RS，控制记录分隔符
	- `$0` 表示一整行内容; `$1` 表示第一个匹配到的内容，即索引从 1 开始
- `awk -F: '/root/ {print $0}' passwd.txt`，搜索文件中包含 root 的所有行，并按照 `:` 把匹配到的内容分割，打印每行
- `awk -F: '/root/ {print $7}' passwd.txt`，搜索文件中包含 root 的所有行，并按照 `:` 把匹配到的内容分割，打印内容中的第 7 段
- `awk -F: 'NR==2{print $0}' passwd.txt`，打印文件中第 2 行的内容
- `awk -F: 'BEGIN {print "BEGIN awk"} {print $1, $7}' passwd.txt`，打印标题 BEGIN awk，然后打印匹配到的第 1 列和第 7 列
- `echo "111 222|333 444|555 666" | awk 'BEGIN{RS="|"} {print $0}'`，默认会有 `-F ""` 空行分隔符，这里自定义分隔符为 |
- `awk '$9~/404|500/' nginx.log | wc -l`，匹配第 9 列的内容
- `awk '{print $1}' nginx.log | sed ':2;N;s/\n/|/g;t2'`，t2 表示跳转到 t2 标记处，2 代表一个标记，N 读取换行符

应用:

- `ps -aux | grep test.sh | awk '{cmd="kill -9 "$2;system(cmd)"}'`，杀死指定进程
- `ps -aux | grep test.sh | grep 61993657 | awk '{cmd="kill -9 "$2;system(cmd)"}'`，杀死指定进程，61993657 是自己的 id
- `for i in {1..20};do top -n 1 -p 1 | grep systemd | awk '{print $11}';done`，打印指定 top

#### shell 及其他

curl

- `curl -o tmp.html www.baidu.com`，保存响应内容
- `curl -v http://www.baidu.com`，输出通信的整个过程
- `curl -s http://www.baidu.com`，不输出错误和进度信息
- `curl -x 127.0.0.1:8888 www.baidu.com`，指定一个代理端口
- `-G`，使用 get 请求
- `-d`，指定请求数据，请求体，`-d "login=123"`
- `-X GET`，指定请求方式
- `curl -x 127.0.0.1:8888 -d 'user=1234' -0 tmp www.baidu.com`，以 post 方式通过代理查看百度


```shell
#!/bin/bash
a=10
if [ $a -gt 30 ];then 
  echo `expr 10 \* 20`
elif [[ $a < 10 ]];then 
  echo "a="$a""
else
  echo "a=$a"
  echo "all: "$*""
  echo "count: "$#""
  echo "0?1 - "$?""
fi

int=1
while(( $int<=5 ))
do
  echo $int
  let "int++"
done

for i in $(cat passwd.txt);do echo "a_"$i;done

# 以下为抽奖程序，使用之前需要有一个包含多个名称的文件
#!/bin/bash
# 筛选出一个人
rand() {
  # 从文件中读取所有人的信息，用 .. 代替空格
  local seeds = `while read line;do echo ${line// /..};done < tmp.txt`
  local count = 0
  # 不停的筛选，知道只剩一个同学为止
  while [[ $count != 1 ]];do
    # 从 seeds 中筛选一部分人；进行猜拳操作，如果是1，进行下一轮；如果是0，则淘汰
    seeds = `for seed in $seeds;do (( $RONDOM % 2 )) && echo $seed;done`
    count = `echo "$seeds" | wc -l`
  done
  if [[ $seeds == "" ]];then
    rand
  else
    echo $seeds
  fi
}
# 筛选出多个人
res() {
  # for i in {1..10};do
  for((i=0;i<$1;i++));do
    tmp = `rand`
    # 去重
    while [[ `is_repeat $tmp` == 0 ]];do
      tmp = `rand`
    done
    arrs[$i]=$tmp
  done
  echo ${arrs[@]}
}
# 判断是否重复
is_repeat() {
  for arr in ${arr[@]};do
    # 如果相同，输出返回0
    if [[ $arr == $1 ]];then
      echo 0;
      return 0;
    fi
  done
  echo 1
}
# 把脚本外的值传递给 res 函数
res $1
```

### MySQL

#### 环境搭建

1.下载: `https://dev.mysql.com/downloads/mysql/`
2.将下载好的文件解压，新建一个存放数据的目录 `/data/` 和 `my.ini` 配置文件，有则不需要创建
3.配置环境变量，并测试
4.执行初始化命令: 完成后会生成一个临时密码，需要记下临时密码； `$ mysqld --initialize --user=mysql --console`
5.将 MySQL 添加到服务中: `$ mysqld -install`
6.启动服务: `$ net start mysql`
7.登录 MySQL: `mysql -u root -p`，然后输入上面生成的临时密码
8.修改密码:
    - `$ user mysql;` 
    - `$ alert user 'root'@'localhost' identified with mysql_native_password by '12345678';`，这个是用于 Navicat
    - `$ alert user 'root'@'localhost' identified by '12345678';`
    - `$ flush privileges;`

```ini
[mysqld]
# 服务端
port=3306
# 设置 mysql 的安装目录
basedir=C:\\ZZZZZZ\\mysql-5.7.29-winx64
# 设置 mysql 数据的存放目录
datadir=C:\\ZZZZZZ\\mysql-5.7.29-winx64\\data
# 允许最大连接数
max_connection=200
# 允许连接失败的次数，这是为了防止有人从该主机试图攻击数据库系统
max_connect_error=10
# 服务端使用的字符集默认为 utf8
character_set_server=utf8
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
[mysql]
# 设置 mysql 客户端默认字符集
character_set_server=utf8

[client]
# 设置 mysql 客户端连接服务端时默认使用的端口和字符集
port=3306
default-character-set=utf8
```

#### 基本语法

```mysql
# 数据库 DDL
创建数据库 CREATE {DATABASE | SCHEMA} [IF NOT EXISTS] db_name [[DEFAULT] CHARACTER SET [=] charset_name];
修改编码方式 ALTER {DATABASE | SCHEMA} db_name [DEFAULT] CHARACTER SET [=] character_name;
删除数据库 DROP {DATABASE | SCHEMA} [IF EXISTS] db_name

# 数据表
CREATE TABLE [IF NOT EXISTS] tbl_name(
  字段名称 字段类型 [UNSIGNED | ZEROFILL] [NOT NULL] [DEFAULT 默认值] [[PRIMARY] KEY] [UNIQUE [KEY]]
)ENGINE=INNODB CHARSET=UTF8 AUTO_INCREMENT=数值;
修改表名 ALTER TABLE tbl_name RENAME [TO | AS] new_name 或者 RENAME TABLE tbl_name TO new_name
添加字段 ALTER TABLE tbl_name ADD 字段名称 字段类型 [完整性约束条件] [FIRST | AFTER 字段名称]
删除字段 ALTER TABLE tbl_name DROP 字段名称，或者 ALTER TABLE tbl_name DROP 字段1,DROP 字段2,...;
修改字段 ALTER TABLE tbl_name MODIFY 字段名称 [完整性约束条件] [FIRST | AFTER 字段名称]
修改字段名称 ALTER TABLE tbl_name CHANGE 旧字段名称 新字段名称 字段类型 [完整性约束条件] [FIRST | AFTER 字段名称]
添加删除默认值 ALTER TABLE tbl_name ALTER 字段名称 SET DEFAULT 默认值 ， ALTER TABLE tbl_name ALTER 字段名称 DROP DEFAULT
添加主键 ALTER TABLE tbl_name ADD [CONSTRAINT [symbol]] PRIMARY KEY [index_type] (字段名称, ...)
删除主键 ALTER TABLE tbl_name DROP PRIMARY KEY;
添加唯一 ALTER TABLE tbl_name ADD [CONSTRAINT [symbol]] UNIQUE [INDEX | KEY] [索引名称](字段名称, ...)
删除唯一 ALTER TABLE tbl_name DROP {INDEX | KEY} index_name
修改表的存储引擎 ALTER TABLE tbl_name ENGINE=存储引擎名称
设置自增长的值 ALTER TABLE tbl_name AUTO_INCREMENT=值
删除数据表 DROP TABLE [IF EXISTS] tbl_name[, tbl_name...]

# 数组的操作 DML
不指定具体的字段名 INSERT [INTO] tbl_name VALUES|VALUE(值,...)
列出指定字段 INSERT [INTO] tbl_name(字段名称1, ...) VALUES|VALUE(值1,...)
同时插入多条记录 INSERT [INTO] tbl_name[(字段名称 ...)] VALUES(值 ...), (值 ...)...
通过 SET 形式插入记录 INSERT [INTO] tnl_name SET 字段名称=值,...
将查询结果插入到表中 INSERT [INTO] tbl_name[(字段名称, ...)] SELECT 字段名称 FROM tbl_name [WHERE 条件]
更新数据 UPDATE tbl_name SET 字段名称=值,... [WHERE 条件] [ORDER BY 字段名称] [LIMIT 限制条数]
删除数据 DELETE FROM tbl_name [WHERE 条件] [ORDER BY 字段名称] [LIMIT 条数]; 不会重置AUTO_INCREMENT的值
清空数据 TRUNCATE [TABLE] tbl_name

# 查询数据操作 DQL
SELECT select_expr[,select_expr...] 
[
  FROM table_references
  [WHERE 条件]
  [GROUP BY {col_name | position} [ASC | DESC],...分组]
  [HAVING 条件 对分组结果进行二次筛选]
  [ORDER BY {col_name | position} [ASC | DESC],...排序]
  [LIMIT 限制显示条数]
]
SELECT id,sex,GROUP_CONCAT(username) FROM cms_user GROUP BY sex;
SELECT u.id,u.username,u.regTime,a.proName,u.sex,COUNT(*) AS totalUsers,GROUP_CONCAT(username)
FROM cms_user AS u
LEFT JOIN
provinces AS a
ON u.proId=a.id
WHERE sex='女'
GROUP BY a.proName
HAVING COUNT(*)>=1
ORDER BY u.id DESC
LIMIT 0,2;

# 索引
CREATE TABLE tbl_name(
字段名称 字段类型 [完整性约束条件],
...,
[UNIQUE | FULLTEXT |SPATIAL] INDEX | KEY [索引名称](字段名称[(长度)]
[ASC | DESC])
);
ALTER TABLE tbl_name ADD [UNIQUE | FULLTEXT | SPATIAL] INDEX 索引名称(字段名称[(长度)] [ASC | DESC]);
DROP INDEX 索引名称 ON tbl_name; ALTER TABLE tbl_name DROP INDEX 索引名称
```

#### 常见语句

常见语句:

1. 分页: `select * from table_name order by id asc limit 10 offset 0;`
2. 去重: `select distinct gender from employees;`
3. 统计: `select count(*), dept_no from dept_maager group by dept_no HAVING count(*) > 3;`

### Redis








