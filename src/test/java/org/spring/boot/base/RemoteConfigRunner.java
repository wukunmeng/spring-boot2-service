/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-11
 * Time : 下午4:30
 * ---------------------------------
 */
package org.spring.boot.base;

import org.junit.runners.model.InitializationError;
import org.spring.boot.base.util.Commons;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-11
 * Time : 下午4:30
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class RemoteConfigRunner extends SpringJUnit4ClassRunner {

    {
        String[] args = Commons.remoteArgs();
        if (args != null) {
            for (String item : args) {
                String[] par = item.split("=");
                String key = par[0];
                String value = par[1];
                System.setProperty(key.replaceAll("--", ""), value);
            }
        }
    }


    /**
     * Construct a new {@code SpringJUnit4ClassRunner} and initialize a
     * {@link TestContextManager} to provide Spring testing functionality to
     * standard JUnit tests.
     *
     * @param clazz the test class to be run
     * @see #createTestContextManager(Class)
     */
    public RemoteConfigRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }
}
