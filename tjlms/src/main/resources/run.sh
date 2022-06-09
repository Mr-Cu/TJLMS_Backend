#!/bin/bash
#查看镜像是否存在
serviceName=tjlms

#先暂停并删除容器和镜像
sh stop.sh

docker build -t ${serviceName} .

docker run -d --name=${serviceName}  -p 9080:9080 ${serviceName} \
--net=host --restart=always
