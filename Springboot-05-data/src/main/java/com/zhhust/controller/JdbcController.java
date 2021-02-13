package com.zhhust.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class JdbcController {
    //xxxTemplate Springboot已经配置好的模板bean，拿来即用
    // 如：JdbcTemplate  自带了一些比较通用的CRUD方法
    //      RedisTemplate

    @Resource
    JdbcTemplate jdbcTemplate;

    @GetMapping("/getStudentList")
    public String getStudentList(){
        String sql="select * from student";
        List<Map<String, Object>> studentMaps = jdbcTemplate.queryForList(sql);//调用模板里的方法
        return studentMaps.toString();
    }

    @GetMapping("/addStudent")
    public String addStudent(){
        String sql="insert into student values('1007','吕德华',18)";
        jdbcTemplate.update(sql); //自动提交了事务
        return "addOK";
    }

    @GetMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable  String id){
        String sql="update student set age=? where id="+id;
        //封装对象
        Object[] object=new Object[1];
        object[0]=20;
        jdbcTemplate.update(sql,object);
        return "updateOK";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable String id){
        String sql="delete from student where id="+id;
        jdbcTemplate.update(sql);
        return "deleteOK";
    }
}
