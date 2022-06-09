#!/bin/bash
#查看镜像是否存在
serviceName=tjlms

#查询得到指定名称的容器ID
#容器ID
container=$(docker ps -aqf "name=${serviceName}")

#查询得到指定名称的镜像ID
#镜像ID
image=$(docker images -q --filter reference=${serviceName})

#如果查询结果不为空，先停容器在删除
#容器
if [  -n "$container" ]; then
 docker rm -f $(docker stop $container)
 echo "$serviceName容器停止删除成功.....！！！"
fi

#如果查询结果不为空，先删除镜像
#删除镜像
if [  -n "$image" ]; then
 docker rmi -f $image
 echo "$serviceName镜像删除成功.....！！！"
fi
