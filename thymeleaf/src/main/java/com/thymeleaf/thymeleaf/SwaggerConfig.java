package com.thymeleaf.thymeleaf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket ProductApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .apiInfo(productApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.thymeleaf.thymeleaf"))
                .build();
    }

    private ApiInfo productApiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("API页面").description("接口文档").version("1.0").build();
        return apiInfo;
    }
}
