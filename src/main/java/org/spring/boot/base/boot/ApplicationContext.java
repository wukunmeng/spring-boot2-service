/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.boot
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-11-29
 * Time : 下午4:49
 * ---------------------------------
 */
package org.spring.boot.base.boot;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot.base.hook.BeforeClosed;
import org.spring.boot.base.util.Commons;
import org.spring.boot.base.util.Constants;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.boot
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-11-29
 * Time : 下午4:49
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
@SpringBootApplication
@ComponentScan("org.spring.boot.base")
public class ApplicationContext {

    private static Logger logger = LoggerFactory.getLogger(ApplicationContext.class);

    private static org.springframework.context.ApplicationContext applicationContext;

    public static void main(String[] args) {
        try {
            SpringApplication spring = new SpringApplication(ApplicationContext.class);
            String zookeeper = System.getenv(Constants.ZOOKEEPER_CONFIG);
            logger.info("config from : {}", zookeeper);
            if (StringUtils.isNotBlank(zookeeper)) {
                String[] keys = Commons.remoteArgs();
                logger.info("read config:{}", JSONObject.toJSONString(keys, SerializerFeature.PrettyFormat));
                if(keys == null || keys.length == 0){
                    logger.info("run config:{}", JSONObject.toJSONString(args, SerializerFeature.PrettyFormat));
                    applicationContext = spring.run(args);
                } else if(keys != null && keys.length > 0){
                    String[] all = new String[args.length + keys.length];
                    System.arraycopy(args,0,all,0,args.length);
                    System.arraycopy(keys,0,all,args.length,keys.length);
                    logger.info("run config:{}", JSONObject.toJSONString(all, SerializerFeature.PrettyFormat));
                    applicationContext = spring.run(all);
                }
            } else {
                applicationContext = spring.run(args);
            }
            //参照 @PreDestroy
            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        applicationContext.getBean(BeforeClosed.class).beforeClosed();
                    } catch (NoUniqueBeanDefinitionException e){
                        //ignore
                    } catch (NoSuchBeanDefinitionException e){
                        //ignore
                    }
                }
            });
        } catch (Exception e){
            logger.error("Exception:" + e.getMessage(),e);
        }
    }
}
