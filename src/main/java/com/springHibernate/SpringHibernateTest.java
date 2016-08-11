package com.springHibernate;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/3/7.
 */
public class SpringHibernateTest {

    private ApplicationContext ctx=null;

    {
        ctx=new ClassPathXmlApplicationContext("hibernate.xml");
    }
    @Test
    public void test() throws SQLException {
        DataSource dataSource= ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

}
