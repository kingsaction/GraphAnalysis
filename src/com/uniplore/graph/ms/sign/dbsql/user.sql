-- mysql 
-- 普通用户表信息
create table user (
  id int primary key auto_increment,    -- 唯一的id去标识不同的用户，存放到数据库时采用MD5对其进行加密
  email varchar(20),                    -- 邮箱，在登录时使用
  user_name varchar(20),                -- 用户名，需要校验，规定用户名在6位到20位之间
  pwd varchar(32),                      -- 密码，在存入数据库时采用MD5进行加密，，原本密码在6到20位之间，加密后全部为32位
  account_state boolean,                -- 判断用户的用户名和密码所在的账户是否已经激活，发送激活邮件
  create_time  datetime,                -- 账户的创建时间
  active_time  datetime,                -- 账户的激活时间
  active_code  varchar(50)              -- 账户的激活码，使用系统自带的UUID
)