package com.cn.cly.config;

/**
 * 利用spring 的Swagger2 自动创建resetful文档
 * 访问页面http://localhost:8080/swagger-ui.html
 * Created by chen on 2017/6/2.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))   //指定包
                .paths(PathSelectors.any()) //所有路径
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("接口文档API")
                .description("描述")
                .termsOfServiceUrl("http://chen.com.cn")
                .version("1.0")
                .build();
    }

}