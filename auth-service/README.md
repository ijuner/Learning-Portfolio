auth-service：负责 OAuth2 登录认证、JWT 签发

user-service：用户注册、信息管理（MongoDB）

log-service：消费 Kafka 日志消息

api-gateway（后期）：统一入口，转发请求

Project: Maven

Language: Java

Spring Boot: 3.x+

Dependencies:

Spring Web

Spring Security

OAuth2 Authorization Server

Spring Boot DevTools

Lombok

MongoDB（user-service）

Spring for Apache Kafka（log-service）

Validation


Plus：
Java 17 + Spring Boot

MongoDB

Maven 

Docker 

OpenShift CLI (oc) and Web deploy