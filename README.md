# 说明
## 构建环境
maven: 3.8.1
jdk : Java version: 11.0.13



## 项目启动
mvn  test package
docker compose -f docker-compose.yml up


## 条件
需要配置keycloak相关配置
1. keycloak.auth-server-url
2. keycloak.clent.id