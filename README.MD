# Spring-Boot2-Service

---

## 编译

>* 需要gradle进行项目的编译

```shell
gradle dist   //部署
```

```shell
gradle dist -x test  //跳过测试
```

## 部署
>* 运行部署脚本前需要修改脚本内的远程服务器参数
```shell
deploy.sh
```

## 启动脚本
```shell
server start    //启动
server stop     //停止
server restart  //重启
```