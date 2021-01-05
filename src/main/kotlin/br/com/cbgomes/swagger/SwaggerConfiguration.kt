package br.com.cbgomes.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfiguration{

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.cbgomes"))
                .build()
                .apiInfo(metaData());
    }

    private fun metaData(): ApiInfo? {
        return ApiInfoBuilder()
                .title("Spring Boot REST API with kotlin")
                .description("\"Building api rest with spring boot and kotlin\"")
                .version("1.0.0")
                .license("CBGF License Version 1.0")
                .licenseUrl("https://www.linkedin.com/in/carlos-barbosa-gomes-filho-b0463542/")
                .build()
    }

}
