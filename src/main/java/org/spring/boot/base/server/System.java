/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.server
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-11-29
 * Time : 下午5:44
 * ---------------------------------
 */
package org.spring.boot.base.server;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.boot.base.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.server
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-11-29
 * Time : 下午5:44
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
@RestController
@RequestMapping(value = "")
public class System {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResourceLoader resourceLoader;

    @ResponseBody
    @RequestMapping(value = "/_version")
    public Object index(){
        String metaFile = "classpath:version.me";
        try {
            Resource resource = resourceLoader.getResource(metaFile);
            if (resource == null){
                logger.warn("not found file:{}", metaFile);
                return ImmutableBiMap.of("ok", true);
            }
            Map<String,String> maps = Maps.newHashMap();

            List<String> lines =
                    FileUtils.readLines(resource.getFile(), Constants.CHARSET_UTF_8);
            if(lines != null) {
                lines.forEach((String v) -> {
                    if(v != null && !v.isEmpty()){
                        String[] ps = v.split(":");
                        if(ps.length != 0){
                            maps.put(ps[0],v.substring(ps[0].length() + 1));
                        }
                    }
                });
            }
            return maps;
        } catch (IOException e) {
            logger.warn("read file:{}, exception:{}", metaFile, e.getMessage());
        }
        return ImmutableBiMap.of("ok", true, "referer", HttpHeaders.REFERER);
    }
}
