package com.yunbei.app.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: mlc
 * @dat: 2016��6��12��
 * @Description: TODO
 */

@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {

	@Bean
	public ApiInfo requestApiInfo(@Value("${spring.swagger.apiinfo.title}") String title,
			@Value("${spring.swagger.apiinfo.description}") String description,
			@Value("${spring.swagger.apiinfo.version}") String version,
			@Value("${spring.swagger.apiinfo.termsOfServiceUrl}") String termsOfServiceUrl,
			@Value("${spring.swagger.apiinfo.contact}") String contact,
			@Value("${spring.swagger.apiinfo.license}") String license,
			@Value("${spring.swagger.apiinfo.licenseUrl}") String licenseUrl) {
		return new ApiInfo(title, description, version, termsOfServiceUrl, contact, license, licenseUrl);
	}

	@Bean
	public Docket requestDocket(ApiInfo apiInfo, @Value("${spring.swagger.docket.groupName}") String groupName,
			@Value("${spring.swagger.docket.pathMapping}") String pathMapping,
			@Value("${spring.swagger.docket.pathRegex}") String pathRegex) {

		return new Docket(DocumentationType.SWAGGER_2).groupName(groupName).useDefaultResponseMessages(false)
				.forCodeGeneration(true).pathMapping(pathMapping).select().paths(regex(pathRegex)).build()
				.apiInfo(apiInfo);
	}
}
