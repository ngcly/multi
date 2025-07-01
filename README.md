# multi
spring-boot，gradle多模块多启动类项目

root-project/  
│  
├── api/             # DTO 和接口 (供其他内部系统调用)   
├── core/            # 业务逻辑与领域（Domain）模型   
├── persistence/     # 数据库访问（例如 JPA、Repository）   
├── web/             # REST Controller、Spring Boot main 类   
└── shared/          # 公共的工具类、枚举和异常  


```mermaid
graph TD;
    web-->core;
    web-->api;
    core-->api;
    core-->shared;
    core-->persistence;
    persistence-->shared;