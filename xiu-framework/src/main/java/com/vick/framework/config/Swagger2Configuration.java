package com.vick.framework.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
@ConditionalOnProperty(prefix = "swagger2", value = {"enable"}, havingValue = "true")
public class Swagger2Configuration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vick"))
                .paths(PathSelectors.any())
                .build();
        //.ignoredParameterTypes(CompanySysUserModel.class)
        //.ignoredParameterTypes(MobileUserModel.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "XIU API",
                "xiu academic record management system",
                "0.0.1",
                null,
                ApiInfo.DEFAULT_CONTACT,
                null,
                null,
                new ArrayList()
        );

    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                true,
                false,
                1,
                1,
                ModelRendering.MODEL,
                false,
                DocExpansion.LIST,
                null,
                null,
                OperationsSorter.ALPHA,
                false,
                TagsSorter.ALPHA,
                null);
    }

}
