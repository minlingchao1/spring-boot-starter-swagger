# 所需要的依赖

```
	compile('io.springfox:springfox-swagger-ui:2.4.0')
	compile('io.springfox:springfox-swagger2:2.4.0')
```

# 配置文件
- api信息
- 摘要信息

```

@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {

	/**
	 * api详情
	 * 
	 * @param title
	 * @param description
	 * @param version
	 * @param termsOfServiceUrl
	 * @param contact
	 * @param license
	 * @param licenseUrl
	 * @return
	 */
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

	/**
	 * 摘要
	 * 
	 * @param apiInfo
	 * @param groupName
	 * @param pathMapping
	 * @param pathRegex
	 * @return
	 */
	@Bean
	public Docket requestDocket(ApiInfo apiInfo, @Value("${spring.swagger.docket.groupName}") String groupName,
			@Value("${spring.swagger.docket.pathMapping}") String pathMapping,
			@Value("${spring.swagger.docket.pathRegex}") String pathRegex) {

		return new Docket(DocumentationType.SWAGGER_2).groupName(groupName).useDefaultResponseMessages(false)
				.forCodeGeneration(true).pathMapping(pathMapping).select().paths(regex(pathRegex)).build()
				.apiInfo(apiInfo);
	}
}

```
在application.yml中配置相关的信息

```
spring:
  swagger:
    apiinfo:
       title: Spring Boot 测试
       version: 1.0
       description: Spring Boot 测试
       termsOfServiceUrl: www.dingdongcloud.com
       contact: 杭州云贝网络科技有限公司
       license: 云贝
       licenseUrl: www.dingdongcloud.com  
    docket:
       groupName: test
       pathMapping: /
       pathRegex: /.*
```
