package com.zhhust.dao;

import com.zhhust.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/*
* 作用；这个项目暂时未整合数据库
*       这个类用于模拟数据库的 部门表
* */
@Component
public class DepartmentDao {
    private static Map<Integer, Department> departmentMap;//用Map模拟数据库的表

    static {
        //创建部门表
        departmentMap=new HashMap<>();
        //初始化数据
        departmentMap.put(101,new Department(101,"教学部"));
        departmentMap.put(102,new Department(102,"教研部"));
        departmentMap.put(103,new Department(103,"人事部"));
        departmentMap.put(104,new Department(104,"公关部"));
        departmentMap.put(105,new Department(105,"保安部"));
        departmentMap.put(106,new Department(106,"小卖部"));
    }

    //获得所有部门信息
    public Collection<Department> getAllDepartments(){
            return departmentMap.values();
    }

    //通过id查询部门
    public Department searchDepartmentById(Integer id){
        return departmentMap.get(id);
    }
}
