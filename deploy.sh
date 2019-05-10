#!/bin/sh
# Linux下部署项目到服务器
# 适用于spring-boot项目
# require jre/jdk 1.8+
# copy from by <wukunmeng@gmail.com>
# modify by wukunmeng

#远程服务器用户名
user=server
#远程服务ssh服务端口
port=22
#远程服务器IP地址
host=192.168.4.25
#远程服务器项目部署目录
path=/home/server/app/spring-boot-service

#本地目录
app_home=$(cd $(dirname $0); pwd)
echo "application home dir: $app_home"

#本地JDK版本
java -version

#本地gradle版本
gradle -v

#清除本地build目录
gradle clean

#编译
gradle dist

rsync -avzP --delete --exclude 'log' \
    --no-perms --no-owner --no-group \
    ${app_home}/build/dist/ -e "ssh -p $port" $user@$host:$path

ssh -p $port $user@$host "/bin/bash --login $path/server restart"

