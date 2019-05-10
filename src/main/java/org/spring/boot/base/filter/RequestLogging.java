/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.filter
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-11
 * Time : 下午3:32
 * ---------------------------------
 */
package org.spring.boot.base.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.filter
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-11
 * Time : 下午3:32
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class RequestLogging extends AbstractRequestLoggingFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public RequestLogging(){
        setIncludeClientInfo(true);
        setIncludePayload(true);
        setIncludeQueryString(true);
        setMaxPayloadLength(1024 * 100);

        setBeforeMessagePrefix("request:[");
        setBeforeMessageSuffix("]");
        setAfterMessagePrefix("request[");
        setAfterMessageSuffix("]");
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.info("before request logging:{}", message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.info("after request logging:{}", message);
    }
}
