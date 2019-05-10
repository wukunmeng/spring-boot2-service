/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.util
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-11-29
 * Time : 下午4:50
 * ---------------------------------
 */
package org.spring.boot.base.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.util
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-11-29
 * Time : 下午4:50
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public abstract class Commons {

    public static String formatNow(){
        return DateFormatUtils.format(new Date(), Constants.DATE_TIME_FORMAT_PATTERN);
    }

    public static String[] remoteArgs() {
        String connectString = System.getenv(Constants.ZOOKEEPER_CONFIG);
        if (StringUtils.isBlank(connectString)) {
            System.out.println(String.format("can not find zookeeper config : %s", Constants.ZOOKEEPER_CONFIG));
            return null;
        }
        CuratorFramework client =
                CuratorFrameworkFactory.newClient(connectString, new RetryNTimes(10, 1000));
        client.start();
        String[] args = readConfigFromZooKeeper(client);
        return args;
    }

    private static String[] readConfigFromZooKeeper(CuratorFramework client) {
        StringBuilder key = new StringBuilder(Constants.APPLICATION_CONFIG);
        key.append("/");
        key.append(System.getenv(Constants.SERVER_ENV));
        try {
            Stat stat = client.checkExists().forPath(key.toString());
            if (stat != null) {
                String data = new String(client.getData().forPath(key.toString()));
                if (StringUtils.isBlank(data)) {
                    System.out.println(String.format("can not load data from : %s", key.toString()));
                } else {
                    Properties config = Commons.loadProperty(data);
                    System.out.println(String.format("load zookeeper config : %s", JSONObject.toJSONString(config)));
                    if(config != null && config.size() > 0){
                        String[] args = new String[config.size()];
                        AtomicInteger index = new AtomicInteger(0);
                        config.forEach((Object k, Object v) -> {
                            args[index.get()] = "--" + k + "=" + v;
                            index.set(index.get() + 1);
                        });
                        return args;
                    }
                }
            } else {
                System.out.println(String.format("%s not exist in zookeeper", key.toString()));
            }
        } catch (Exception e) {
            //ignore
            System.out.println(String.format("%s can not read from zookeeper", key.toString()));
        }
        return null;
    }

    public static Properties loadProperty(String source){
        Properties property = new Properties();
        try {
            property.load(new StringReader(source));
        } catch (IOException e){
            System.out.println(String.format("can not convert properties from : %s", source));
        }
        return property;
    }

}
