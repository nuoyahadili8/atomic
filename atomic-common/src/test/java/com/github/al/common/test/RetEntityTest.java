package com.github.al.common.test;

import com.github.al.common.web.entity.RetEntity;
import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/10 20:57
 * @Modified By:
 */
public class RetEntityTest {

    @Test
    public void test() {
        RetEntity ok = RetEntity.ok();

        Assert.assertEquals(ok.getMessage(), "操作成功.");

        System.out.println(RetEntity.build(122, false, "fail"));

        Map<String, Object> body = Maps.newHashMap();
        body.put("field1", "xxxx");
        body.put("field2", "asdasd1");
        String jsonString = RetEntity.ok("ok").setBody(body).toString();
        System.out.println("jsonString = " + jsonString);

        RetEntity convert = RetEntity.convert(jsonString);
        Map convertBody = (Map) convert.getBody();
        System.out.println(convertBody.get("field1"));
        Assert.assertEquals(convertBody.get("field1"), "xxxx");
    }
}
