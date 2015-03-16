package com.telenav.hadoop.MapReduce;

import org.junit.Assert;
import org.junit.Test;


public class StringTest {

    @Test
    public void test() {
        String source =
                "http://en.ce.cn/main/latest/201503/09/t20150309_4759264.shtml";
        Assert.assertTrue(source.contains("://"));
        Assert.assertEquals(0, source.indexOf("http"));
        Assert.assertEquals(4, source.indexOf("://"));
        Assert.assertEquals(15, source.indexOf("/", 7));
        Assert.assertEquals("en.ce.cn", source.substring(4 + 3, 15));

        source =
                "https://en.ce.cn/main/latest/201503/09/t20150309_4759264.shtml";
        Assert.assertEquals(0, source.indexOf("http"));
        Assert.assertEquals(5, source.indexOf("://"));
        Assert.assertEquals(16, source.indexOf("/", 8));
        


    }

}
