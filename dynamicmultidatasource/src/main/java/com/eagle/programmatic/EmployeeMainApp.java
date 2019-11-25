package com.eagle.programmatic;

import com.eagle.programmatic.Dao.impl.EmployeeDaoImpl;
import com.eagle.programmatic.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @author qinlinsen
 */
public class EmployeeMainApp {

    public static void doTest(EmployeeDaoImpl employeeDao){

//        List employees = employeeDao.listEmployees();
//        employees.forEach(System.out::println);
        employeeDao.create("李定国", 689, 123L);

        System.out.println("成功");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.scan("com.eagle.programmatic");
        //启动spring container
        ctx.start();
        EmployeeDaoImpl employeeDao = ctx.getBean(EmployeeDaoImpl.class);
        doTest(employeeDao);
        //关闭spring container
        ctx.close();
    }

}
