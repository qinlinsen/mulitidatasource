package com.eagle.dynamicmultidatasource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

public class DynamicmultidatasourceApplicationTests {

    @Test
    public void contextLoads() {
        HashSet<String> set = new HashSet<>();
        set.add("String");
        String s = DataAccessUtils.singleResult(set);
        System.out.println(s);
        set.add("st");
        String s1 = DataAccessUtils.singleResult(set);
        System.out.println(s1);
    }

}
