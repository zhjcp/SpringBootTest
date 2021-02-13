package com.zhhust;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot05DataApplicationTests {

    @Resource
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        // 查看Springboot默认使用的数据源：com.zaxxer.hikari.HikariDataSource
        //      配置Druid之后：class com.alibaba.druid.pool.DruidDataSource
        System.out.println(dataSource.getClass());

        //获取数据库连接
        Connection connection = dataSource.getConnection();

        //关闭数据库连接
        connection.close();
    }

}
