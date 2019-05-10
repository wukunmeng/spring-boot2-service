/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.filter
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-11
 * Time : 下午3:14
 * ---------------------------------
 */
package org.spring.boot.base.filter;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.spring.boot.base.util.Commons;
import org.spring.boot.base.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.filter
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-11
 * Time : 下午3:14
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class SystemLogMDCFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("FilterConfig:{}",filterConfig == null?"null":filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uuid = requestId((HttpServletRequest) request);
        if (StringUtils.isBlank(uuid)){
            uuid = UUID.randomUUID().toString();
        }
        MDC.put(Constants.X_REQUEST_ID, uuid);
        logger.info("start-request-process-for:{}", uuid);
        printHeaders((HttpServletRequest)request);
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        httpServletResponse.addHeader(Constants.X_REQUEST_ID, uuid);
        chain.doFilter(request, httpServletResponse);
        MDC.remove(Constants.X_REQUEST_ID);
    }

    private void printHeaders(HttpServletRequest request){
        logger.info("request-url:{}", request.getRequestURI());
        logger.info("request-method:{}", request.getMethod());
        JSONObject headers = new JSONObject();
        Enumeration<String> moreHeaders = request.getHeaderNames();
        while (moreHeaders.hasMoreElements()){
            String name = moreHeaders.nextElement();
            headers.put(name,request.getHeader(name));
        }
        logger.info("request-header:{}", headers.toString());
    }

    @Override
    public void destroy() {
        logger.info("destroy-filter:{}", Commons.formatNow());
    }

    private String requestId(HttpServletRequest request){
        String requestId = request.getHeader(Constants.X_REQUEST_ID);
        if(StringUtils.isBlank(requestId)) {
            return  null;
        }
        return requestId.trim();
    }
}
