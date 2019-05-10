/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-11
 * Time : 下午4:29
 * ---------------------------------
 */
package org.spring.boot.base;

import org.junit.runner.RunWith;
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
 * Time : 下午4:29
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 * 从zookeeper读取配置
 */
@RunWith(RemoteConfigRunner.class)
@SpringBootTest(classes = {
        ApplicationContext.class,
        SystemWebMvcConfigurer.class
})
public abstract class BaseRemoteTest {
}
