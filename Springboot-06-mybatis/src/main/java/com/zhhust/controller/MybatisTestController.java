package com.zhhust.controller;

import com.zhhust.mapper.StudentMapper;
import com.zhhust.pojo.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/*
* 直接调用mybatis的一般是业务层，这里为了方便测试，直接写在控制层
* */
@RestController
public class MybatisTestController {
    //自动装配spring实现的UserMapper接口实现类
    @Resource
    StudentMapper studentMapper;

    @GetMapping("/queryAllStudents")
    public String queryAllStudents(){
        List<Student> students = studentMapper.queryAllStudents();
        return students.toString();
    }

    @GetMapping("/updateStudent")
    public String updateStudent(Student student){
        studentMapper.upstateStudent(student);
        return "upstateOK";
    }
}
