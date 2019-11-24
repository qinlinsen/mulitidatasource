package com.eagle.dynamicmultidatasource.controller;

import com.eagle.dynamicmultidatasource.bean.BeanOne;
import com.eagle.dynamicmultidatasource.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author qinlinsen
 */
@RestController
public class HelloController {
    @Resource
    BeanOne beanOne;

    @RequestMapping("/hello")
    public String getBeanOne() {
        String address = beanOne.getAddress();
        String name = beanOne.getName();
        return address + name;
    }

}
