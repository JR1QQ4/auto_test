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

#### 存储过程:

查看存储过程: `SHOW PROCEDURE STATUS WHERE db='数据库名';`
查看当前数据库下的存储过程的列表: `SELECT specific_name FROM mysql.proc;`
删除存储过程: `DROP PROCEDURE 存储过程名字`
语句结束符: `delimiter 需要定义的符号`

```mysql
CREATE PROCEDURE p_vartest1(IN p_var INT)
BEGIN
SELECT p_var;
SET p_var=p_var+1;
SELECT p_var;
END~
-- 创建一个名为@P的变量，没有@符号是不能创建成功的
SET @P=3~
-- 调用存储过程，并传递参数
CALL p_vartest1(@P)~
-- 查看调用存储过程后的@P值，是否有变化
SELECT @P;

CREATE PROCEDURE p_if(in age INT)
BEGIN
IF age>=18 && age<30 THEN 
    SELECT '成年人';
ELSEIF age>=30 && age<60 THEN 
    SELECT '中年人';
ELSE 
    SELECT '老年人';
END IF;
END~

CREATE PROCEDURE p_case(in salaryId INT)
BEGIN
DECLARE addS INT;
CASE salaryId
WHEN 1001 THEN SET addS = 1500;
WHEN 1002 THEN SET addS = 2000;
WHEN 1003 THEN SET addS = 2500;
ELSE SET addS=1000;
END CASE;
UPDATE salaries SET salary=addS WHERE id=salaryId;
END~

CREATE PROCEDURE p_while()
BEGIN
DECLARE i INT DEFAULT 1;
DECLARE result INT DEFAULT 0;
WHILE i<=100 DO
  SET result=result+i;
  SET i=i+1;
END WHILE;
SELECT result;
END~
```

#### 日志

generic_log: 所有的 sql 查询日志
slow log: 查过预设的 long_query_time 阈值的 sql 记录，慢查询日志
输出操作步骤: `\T C:\Develop\mysql1.txt` 输出日志文件到 mysql1.txt ，结束日志`\t`

```mysql
show variables like 'log_output';
show variables like '%query%%';
show variables like '%_log%';

SHOW CREATE TABLE mysql.general_log;
SET GLOBAL general_log='ON';  # 开启日志记录

SHOW CREATE TABLE mysql.slow_log;
SET GLOBAL slow_query_log='ON';  # 开启慢查询日志
SET long_query_time=0.01;  # 设置慢查询触发时间，超过这个时间就是慢查询
show variables like '%long_query%';


```

#### 常见语句

常见语句:

1. 分页: `select * from table_name order by id asc limit 10 offset 0;`
2. 去重: `select distinct gender from employees;`
3. 统计: `select count(*), dept_no from dept_maager group by dept_no HAVING count(*) > 3;`
4. EXPLAIN 关键字后接 SQL 语句: `explain sql`，分析你的查询语句或是表结构的性能
    - 包括: 表的读取顺序、数据读取操作的操作类型、哪些索引可以使用、哪些索引被实际使用、表之间的引用、每张表有多少行被优化器查询
    - id 越大优先级越高，越先被执行
    - possible_keys 查询中可能用到的索引； key 查询中实际用到的索引； key_len 索引的长度
    - rows 扫描的行数； type 访问类型，ALL（全表扫描）、index（索引全扫描）、range（索引的范围扫描）
5.事务: `begin` 开始事务，`commit` 提交事务，`rollback` 事务回滚
6.备份:
    - `mysqldump -u username -p --databases dbname1 dbname2 >backup.sql`
    - `mysqldump -u username -p -all-databases > bakcupName.sql`
    - `mysql -u root -p [dbname] < backupName.sql`
    - `source backupName.sql`

### Redis

1.Redis 简介: 完全开源免费的高性能 Key-value 数据库

- 支持数据的持久化，可以将内存中的数据保存在磁盘中，重启的时候可以再次加载进行使用
- 不仅仅支持简单的 key-value 类型的数据，同时还提供 list、set、zset、hash 等数据结构的存储
- 支持数据的备份，即 master-slave 模式的数据备份
- 性能极高，Redis 能读的速度是 110000 次/s，写的速度是 81000 次/s
- Redis 的所有操作都是原子性的，就是要么成功执行要么失败完全不执行。单个操作是原子性的。多个操作也支持事务，通过 MULTI 和 EXEC 指令包起来
- 支持 publish/subscribe，通知，key 过期等等特性

2.环境搭建:
 
- 下载安装: `https://github.com/microsoftarchive/redis/releases`
- 启动服务端: `$ redis-server.exe redis.windows.conf`

3.基本数据结构:

- String: 是二进制安全的，就是 redis 的 string 可以包含任何数据。比如 jpg 图片或序列化的对象，最大能存储 512MB
- Hash: 一个 string 类型的 key 和 value 的映射表，适合用于存储对象
- List: 按照插入顺序排序，你可以添加一个元素到列表的头部（左边）或者尾部（右边）
- Set: 无序集合，通过哈希表实现的，所以添加、删除、查找的复杂度都是 O(1)
- Sorted Set: 有序集合每个元素都会关联一个 double 类型的分数，redis 正是通过分数来为集合中的成员进行从小到大的排序；zset 的成员
是唯一的，但分数（score）却可以重复

4.连接 redis-server，前提是开启服务端:

- 打开客户端: `$ redis-cli.exe -h 127.0.0.1 -p 6379`，`-h` 后面是服务器ip，`-p` 服务器端口号

5.语法:

- 通用命令：`keys`，`dbsize`，`exists key`；`del key [jey ...]`，`expire key seconds`，`type key`
- 字符串: 缓存，计数器，分布式锁等
    - `$get key | $set key value | $del key`，获取 | 设置 | 删除  **重要**
    - `$incr key`，key自增1，如果key不存在，自增后get(key)=1
    - `$decr key`，key自减1，如果key不存在，自减后get(key)=-1
    - `$setnx key value`，key不存在，才设置 <-> `$set key value`，不管key是否存在，都设置
    - `$mget key1 key2 key3...`，批量获取key，原子操作 <-> `$mset key1 value1 key2 value2 key3 value3...`，批量设置key-value
    - `$getset key newvalue`，设置返回旧的value
    - `$append key value`，将value追加到旧的value
    - `$strlen key`，返回字符串的长度(注意中文，UTF8一个中文两个长度)
    - `$getrange key start end`，获取字符串指定下标所有的值
    - `$setrange key index value`，设置指定下标所有对应的值
- 哈希: key(字符串) -> field(属性名)和value，Mapmap，key-keyvalue
    - `$hset key field value`，设置hash key对应field的value，`$hset user:1:info age 30` **重要**
    - `$hget key field`，获取hash key对应的field的value  **重要**
    - `$hdel key field`，删除hash key对应field的value
    - `$hexists key field`，判断hash key是否有field
    - `$hlen key`，获取hash key field的数量`
    - `$hmget key field field2... fieldN`，批量获取hash key的一批field对应的值
    - `$hmset key field1 value1 field2 value2... fieldN valueN`，批量设置hash key的一批field value
    - `$hgetall key`，返回hash key对应所有的field和value`  **重要**
    - `$hvals key`，返回hash key对应所有field的value
    - `$hkeys key`，返回hash key对应所有field`
    - `$hsetnx key field value`，设置hash key对应field的value(如field已经存在，则失败)
    - `$hincrby key field intCounter`，hash key对应的field的value自增intCounter
- 列表: key -> elements，有序队列，可重复
    - `$rpush key value1 value2... valueN`，从列表右段插入值(1-N个)  **重要**
    - `$lpush key value1 value2... valueN`，从列表左段插入值(1-N个)  **重要**
    - `$insert key <before|after> value newValue`，在list指定的值前|后插入newValue
    - `$lpop key`，从列表左侧弹出一个item
    - `$rpop key`，从列表右侧弹出一个item
    - `$lrem key count value`，根据count值，从列表中删除所有与value相等的项
        - count>0，从左到右，删除最多count个value相等的项；
        - count<0，从右到左，删除最多Math.abs(count)个value相等的项
        - count=0，删除所有value相等的项
    - `$ltrim key start end`，按照索引范围修剪列表，删除其他的
    - `$lrange key start end(包含end)`，获取列表指定索引范围所有item **重要**
    - `$lindex key index`，获取列表指定索引的item
    - `$llen key`，获取列表长度
    - `$lset key index newValue`，设置列表指定索引值为newValue
    - `$blpop key timeout`，lpop阻塞版本，timeout时阻塞超时时间，timeout=0永远不阻塞
    - `$brpop key timeout`，rpop阻塞版本，timeout时阻塞超时时间，timeout=0永远不阻塞
- 集合: key -> values，无序，不允许重复元素，支持集合间操作（交集、并集、区分）
    - `$sadd key element`，项集合key添加element(如果element已经存在，添加失败)
    - `$srem key element`，将集合key中的element移除掉
    - `$scard user:1:follow = 4`，计算集合大小
    - `$sismember user:1:follow it = 1(存在)`，判断it是否在集合中
    - `$srandmember user:1:follow count = his`，从集合中随机挑count个元素，不会破环集合
    - `$spop user:1:follow = sports`，从集合中随机弹出一个元素
    - `$smembers user:1:follow = music his sports it`，获取集合所有元素  **重要**
    - `$sdiff user:1:follow user:2:follow music his`，差集
    - `$sinter user:1:follow user:2:follow it sports`，交集
    - `$sunion user:1:follow user:2:follow it music his sports news ent`，并集
    - `$<sdiff | sinter | sunion> + store destkey...`，将差集、交集、并集结果保存在destkey中
- 有序集合: key -> score、value，score可以重复
    - `$zadd key score element...`，添加score和element，`$zadd zset1 1 zd1 2 zd2 3 zd3`
    - `$zrem key score element...`，删除元素
    - `$zscore key element`，返回元素的分数
    - `$zincrby key increScore element`，增加或减少元素的分数
    - `$zcard key`，返回元素的总个数
    - `$zrank key element`，返回元素的排名
    - `$zrange key 0 -1 [withscores]`，打印所有score和元素  **重要**
    - `$zrange key start end [WITHSCORES]`，返回指定索引范围内的升序元素[分值]
    - `$zrangescore key minScore maxScore [WITHSCORES]`，返回指定分数范围内的升序元素[分值]
    - `$zcount key minScore maxScore`，返回有序集合内在指定分数范围内的个数
    - `$zremrangebyrank key start end`，删除指定范围内的升序元素
    - `$zremrangebyscore key minScore maxScore`，删除指定分数内的升序元素
    - `$zrangebyscore zset1 0 2`，获取指定范围内的元素，2 表示两个
    - zrevrank | zrevrange | zrevrangebyscore | zinterstore | zunionstore

使用:

# java
Java客户端：Jedis
Maven依赖：
<dependency>
  <groupId>redis.clients</groupId>
  <artifactId>jedis</artifactId>
  <version>2.9.0</version>
  <type>jar</type>
  <scope>compile</scope>
</dependency>
Jedis直连：
Jedis jedis = new Jedis("127.0.0.1", 6379);
Jedis.auth("password");  // 修改 redis.windows.conf， 注释修改为requirepass password
jedis.set("hello", "world");
String value = jedis.get("hello");
jedis.incr("counter");
jedis.hset("myhash", "f1", "v1");  jedis.hset("myhash", "f2", "v2");
jedis.hgetAll("myhash");
jedis.rpush("mylist", "1");  jedis.rpush("mylist", "2");  jedis.rpush("mylist", "3");
jedis.lrange("mylist", 0, -1);
jedis.sadd("myset", "a");  jedis.sadd("myset", "b");
jedis.smembers("myset");
jedis.zadd("myzset", 99, "tom");  jedis.zadd("myzset", 66, "peter");  jedis.zadd("myzset", 33, "james");
jedis.zrangeWithScores("myzset", 0, -1);
Jedis连接池：需要GenericObjectPoolConfig的jar包
poolConfig = new GenericObjectPoolConfig();
JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379)

### MongoDB

1.环境搭建: `https://www.mongodb.com/try/download/community` 下载zip文件，解压创建 data 目录
2.把bin目录配到环境变量中，运行`$ mongod.exe --dbpath data路径`（指定数据存放的路径）
3.在浏览器中输入 `http://localhost:27017/` 出现 It looks like you are trying to access MongoDB over HTTP on the native driver port. 表示安装成功   

常用命令:

- 运行 MongoDB 服务器: `$ mongod --dbpath xxx`
- 连接 MongoDB: `$ mongo`
- 查看帮助: `> help`，查看当前数据库，`> show dbs`
- 切换数据库: `> use db_name`，如果没有就会自动创建
- 往数据库中插入一条数据: `> db.db_name.insert({"key", "value"})`
- 删除数据库: `> db.dropDatabase()`，需要先切换到要删除的数据库中，`> use db_name`
- 集合: 集合相当于一张表，集合可以有多个文档
    - 创建集合: `>  db.createCollection(name, {size: ..., capped: ..., max: ...})`
    - 查看已经创建的集合: `> show collections`
    - 删除集合: `> db.collection_name.drop()`
    - 文档: 每插入一个文档，都会有一个独一无二的 id
        - 插入文档时，MongoDB 自动创建集合: `> db.collection_name.insert({"key":"value"})`
        - 查看插入的内容: `> db.collection_name.find().pretty()`，pretty可以使得输出美观，可以不添加
        - 插入复杂文档: `> doc = ({"id":"123","info":["zhangsan", "25"]}) > db.col2.insert(doc)`，先声明变量后插入
        - find 查找条件:
            - 等于: `> db.collection_name.find({"key":"value"})`，发现键为key值为value的
            - 小于 | 大于: `> db.collection_name.find({"key":{$lt:value}})`，大于就是把 `lt` 换成 `gt`
            - 不等于: `> db.collection_name.find({"key":{$ne:value}})`
            - 小于或等于: `> db.collection_name.find({"key":{$lte:value}})`
            - 大于或等于: `> db.collection_name.find({"key":{$gte:value}})`
        - 替换文档: `>  db.mycol1.update( query, <update object or pipeline>[, upsert_bool, multi_bool] )`
            - upsert_bool: 如果不存在 update 的记录，是否插入，默认为 false
            - multi_bool: 把按条件查出来多条记录全部更新，默认为 false
            - 原来的字段被替换: `> db.col3.update({"key1":"value1"}, {"key":"value"})`
            - 原来的字段被修改: `> db.col3.update({"key":"value"}, {$set:{"key":"value2"}})`
            - 修改多个字段: `> db.col.update({'title':'MongoDB 教程'},{$set:{'title':'MongoDB'}},{multi:true})`
        - 删除文档:
            - 删除多个文档: `> db.col3.remove({"key":"value"})`，会把匹配到的文档全部字段删除
            - 删除一个文档: `> db.col3.remove({"key":"value"}, 1)`
        - 修改操作符:
            - 修改: `> db.col.update({"key":"value", {$inc:{"age":"5"}}})`，把key为value的文档的age增加5
            - 更新: `> db.col3.update({"key":"value"}, {$set:{"key":"value2"}})`
            - 追加: `> db.col3.update({"key":"value"}, {$push:{"key3":"value3"}})`






















