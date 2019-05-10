/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.config
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-11
 * Time : 下午3:04
 * ---------------------------------
 */
package org.spring.boot.base.config;

import org.spring.boot.base.filter.RequestLogging;
import org.spring.boot.base.filter.SystemLogMDCFilter;
import org.spring.boot.base.interceptor.LoggerInterceptor;
import org.spring.boot.base.util.Constants;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;

/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.config
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-11
 * Time : 下午3:04
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
@Configuration
public class SystemWebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor());
        super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean getLoggingFilter(){
        FilterRegistrationBean log = new FilterRegistrationBean();
        log.setFilter(new SystemLogMDCFilter());
        log.setOrder(1);
        return log;
    }

    @Bean
    public FilterRegistrationBean getRequestFilter(){
        FilterRegistrationBean log = new FilterRegistrationBean();
        log.setFilter(new RequestLogging());
        log.setOrder(2);
        return log;
    }

    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory
                simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(30000);
        simpleClientHttpRequestFactory.setReadTimeout(30000);
        restTemplate.setRequestFactory(simpleClientHttpRequestFactory);
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName(Constants.CHARSET_UTF_8)));
        return restTemplate;
    }
}
