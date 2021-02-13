package com.zhhust.mapper;

import com.zhhust.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
//@Mapper：这个注解表示这是一个mybatis的Mapper类（代替了之前在mybatis.xml中Mapper文件映射的配置）
//替代方式1：在主启动类用注解配置扫描包  如：@MapperScan("com.zhhust.mapper")
//代替方式2：使用mybatis自己的mybatis.xml配置文件（不推荐，mybatis单独用时才这样）
@Repository
//@Repository: dao层
public interface StudentMapper {
    //C
    int addStudent(Student student);
    //R
    List<Student> queryAllStudents();
    //R by Id
    Student queryStudentById(@Param("id") Integer id);
    //U
    int upstateStudent(Student student);
    //D
    int deleteStudentById(@Param("id") Integer id);
}
