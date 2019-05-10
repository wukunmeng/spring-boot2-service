/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.id
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-18
 * Time : 下午2:28
 * ---------------------------------
 */
package org.spring.boot.base.id;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Assert;
import org.junit.Test;
import org.spring.boot.base.BaseTest;

/**
 * Create with IntelliJ IDEA
 * Project name : spring-boot-service
 * Package name : org.spring.boot.base.id
 * Author : Wukunmeng
 * User : wukm
 * Date : 18-12-18
 * Time : 下午2:28
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class Id extends BaseTest {

    // 17 位加权因子
    private static final int[] RATIO_ARR = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    // 校验码列表
    private static final char[] CHECK_CODE_LIST = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    @Test
    public void id(){
        String id = "513433198206248625";
        Assert.assertTrue(id.length() == 18);
        int sum = 0;
        for(int i = 0; i < 17; i++) {
            int value = NumberUtils.toInt(String.valueOf(id.toCharArray()[i]));
            sum = sum + value * RATIO_ARR[i];
        }
        int mod = sum % 11;
        System.out.println(mod);
        System.out.println(sum);
        System.out.println(CHECK_CODE_LIST[mod]);
        System.out.println("地址码:" + id.substring(0,6));
        System.out.println("生日:" + id.substring(6,14));
        System.out.println("顺序码:" + NumberUtils.toInt(id.substring(14,17)));
        System.out.println(NumberUtils.toInt(id.substring(14,17)) % 2 == 0 ? "女":"男");
        Assert.assertEquals(String.valueOf(id.toCharArray()[17]), String.valueOf(CHECK_CODE_LIST[mod]));
    }

}
