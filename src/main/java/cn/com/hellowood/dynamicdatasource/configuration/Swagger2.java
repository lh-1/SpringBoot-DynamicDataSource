package cn.com.hellowood.dynamicdatasource.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class Swagger2 {

//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.didispace.web"))
//                .paths(PathSelectors.any())
//                .build();
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("testDescription")
                .termsOfServiceUrl("http://www.baidu.com/")
                .contact("攻城狮DD")
                .version("1.0")
                .build();
    }

    /**
     * swagger文档生成配置
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Bean
    public Docket rpcApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("rpcapi").genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false).forCodeGeneration(true).pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select().paths(or(regex("/.*")))// 过滤的接口
                .build().apiInfo(apiInfo());
    }

//    private ApiInfo rpcApiInfo() {
//        return new ApiInfo("leona api", // 大标题
//                "LEONA's REST API", // 小标题
//                "1.0.0", // 版本
//                "NO terms of service", new Contact("hyson.han", "", ""), // 作者
//                "", // 链接显示文字
//                ""// 网站链接
//        );
//    }

}
