package com.eagle.dynamicmultidatasource;

import com.eagle.dynamicmultidatasource.register.CustomerBeanDefinitionRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(CustomerBeanDefinitionRegister.class)
@SpringBootApplication
public class DynamicmultidatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicmultidatasourceApplication.class, args);
    }

}
