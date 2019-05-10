/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.interceptor
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-11
 * Time : 下午3:07
 * ---------------------------------
 */
package org.spring.boot.base.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.interceptor
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-11
 * Time : 下午3:07
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class LoggerInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle:" + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle:{}", request.getRequestURI());
        JSONObject headers = new JSONObject();
        for(String key:response.getHeaderNames()){
            headers.put(key,response.getHeader(key));
        }
        logger.info("response-header:" + headers.toJSONString());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion:" + request.getRequestURI());
    }
}
