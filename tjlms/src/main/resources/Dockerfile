#指定基础镜像
FROM java:8
#定义匿名数据卷
VOLUME /data
#指定工作目录
WORKDIR /workdir
#将本地文件添加到容器中，不会自动解压文件，也不能访问网络资源
COPY tjlms.jar /tjlms.jar
#设置端口
EXPOSE 9080
#配置容器，使其可执行化
ENTRYPOINT ["java","-Xmx512m","-Xms512m","-jar","/tjlms.jar"]
#配置时区，不然会发现打包到docker中启动的容器日志里的时间是差8个小时的
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone
