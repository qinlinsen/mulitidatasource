package com.eagle.programmatic.Dao.impl;

import com.eagle.programmatic.Dao.EmpDao;
import com.eagle.programmatic.entity.Employee;
import com.eagle.programmatic.util.EmployeeMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmpDao {

    @Resource
    private JdbcTemplate jdbcTemplateObject;

    /**
     * @param jdbcTemplateObject the jdbcTemplateObject to set
     */
    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }
    @Resource
    private PlatformTransactionManager transactionManage;

    public void setTransactionManager(
            PlatformTransactionManager transactionManager) {
        this.transactionManage = transactionManager;
    }

    @Override
    public void create(String name, Integer age, Long salary) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManage.getTransaction(def);
        try {
            String SQL = "INSERT INTO Employee (name, age, salary) VALUES (?, ?, ?)";
            jdbcTemplateObject.update(SQL, new Object[]{name, age, salary});
            transactionManage.commit(status);
            System.out.println("Created Record Name = " + name + " Age = " + age + " Salary = " + salary);
            // to simulate the exception.
            throw new RuntimeException("simulate Error condition");

        } catch (Exception e) {
            System.out.println("Error in creating record, rolling back");
            transactionManage.rollback(status);
            throw e;
        }
    }

    @Override
    public Employee getEmployee(Integer empid) {
        String SQL = "SELECT * FROM Employee WHERE empid = ?";
        Employee employee = (Employee) jdbcTemplateObject.queryForObject(SQL, new Object[]{empid}, new EmployeeMapper());
        return employee;
    }

    @Override
    public List listEmployees() {
        String SQL = "SELECT * FROM Employee";
        List employees = (List) jdbcTemplateObject.query(SQL, new EmployeeMapper());
        return employees;
    }

    @Override
    public void delete(Integer empid) {
        String SQL = "DELETE FROM Employee WHERE empid = ?";
        jdbcTemplateObject.update(SQL, new Object[]{empid});
        System.out.println("Deleted Record with EMPID = " + empid);
    }

    @Override
    public void update(Integer empid, Integer age) {
        String SQL = "UPDATE Employee SET age = ? WHERE empid = ?";
        jdbcTemplateObject.update(SQL, new Object[]{age, empid});
        System.out.println("Updated Record with EMPID = " + empid);
    }
}

