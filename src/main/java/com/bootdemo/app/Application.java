package com.bootdemo.app;


import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.bootdemo.amqp.SenderService;
import com.bootdemo.task.executor.DemoAsyncTask;
import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "com.bootdemo.repo")
@EnableSwagger2
@ComponentScan(basePackages={"com.bootdemo"})
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	
	@Autowired
	SenderService senderService;

	@Bean
	public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().pathMapping("/")
				/*.directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)*/
				.alternateTypeRules(newRule(
						typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
						typeResolver.resolve(WildcardType.class)))
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,
						newArrayList(new ResponseMessageBuilder().code(500).message("500 message")
								.responseModel(new ModelRef("Error")).build()))
				/*.securitySchemes(newArrayList(apiKey())).securityContexts(newArrayList(securityContext()))
				.enableUrlTemplating(true)*/
				/*.globalOperationParameters(newArrayList(new ParameterBuilder().name("someGlobalParameter")
						.description("Description of someGlobalParameter").modelRef(new ModelRef("string"))
						.parameterType("query").required(true).build()))*/
				.tags(new Tag("Demo Service", "All apis relating to demo"))
				/*.additionalModels(typeResolver.resolve(AdditionalModel.class))*/;
	}

	@Autowired
	private TypeResolver typeResolver;

	/*private ApiKey apiKey() {
		return new ApiKey("mykey", "api_key", "header");
	}*/

	/*private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/anyPath.*"))
				.build();
	}*/

/*	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return newArrayList(new SecurityReference("mykey", authorizationScopes));
	}*/

/*	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration("test-app-client-id", "test-app-client-secret", "test-app-realm", "test-app",
				"apiKey", ApiKeyVehicle.HEADER, "api_key",
				","  scope separator );
	}*/

	/*@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration("validatorUrl", // url
				"none", // docExpansion => none | list
				"alpha", // apiSorter => alpha
				"schema", // defaultModelRendering => schema
				false, // enableJsonEditor => true | false
				true); // showRequestHeaders => true | false
	}*/

}
