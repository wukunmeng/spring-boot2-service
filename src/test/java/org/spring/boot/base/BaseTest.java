/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-11
 * Time : 下午4:25
 * ---------------------------------
 */
package org.spring.boot.base;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot.base.boot.ApplicationContext;
import org.spring.boot.base.config.SystemWebMvcConfigurer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-11
 * Time : 下午4:25
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 * 普通配置文件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        ApplicationContext.class,
        SystemWebMvcConfigurer.class
})
public abstract class BaseTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
