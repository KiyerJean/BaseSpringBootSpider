package com.yunshan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfigurer extends WebMvcConfigurationSupport{

	 @Autowired
	 private MyConfig myConfig;
	//支持跨域
//	@Override
//	protected void addCorsMappings(CorsRegistry registry) {
//		// TODO Auto-generated method stub
//		registry.addMapping("/**")
//				.allowCredentials(true)
//				.allowedHeaders("*")
//				.allowedOrigins("*")
//				.allowedMethods("*");
//	}
	private CorsConfiguration corsConfig() {
	    CorsConfiguration corsConfiguration = new CorsConfiguration();
	    /* 请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等）
	    */
	    corsConfiguration.addAllowedOrigin("*");
	    corsConfiguration.addAllowedHeader("*");
	    corsConfiguration.addAllowedMethod("*");
	    corsConfiguration.setAllowCredentials(true);
	    corsConfiguration.setMaxAge(3600L);
	    return corsConfiguration;
	}
	@Bean
	public CorsFilter corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfig());
	    return new CorsFilter(source);
	}
    //这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    
    	//将所有/static/** 访问都映射到目录下
        registry.addResourceHandler("/static/**")
        .addResourceLocations("classpath:/static/")
        .addResourceLocations("classpath:/META-INF/resources/")
        .addResourceLocations("classpath:/resources/")
        .addResourceLocations("classpath:/public/");
       
        registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/static/");
        
        registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
       .addResourceLocations("classpath:/META-INF/resources/webjars/");
      
        //将所有上传文件/uploads/** 访问都映射到application.properties配置的目录下
        registry.addResourceHandler("/uploads/**")
        .addResourceLocations("file:"+myConfig.getUpload_path());
    }
 
    //这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	
        super.addInterceptors(registry);

    }

    
}
