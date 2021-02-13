package com.zhhust.dao;

import com.zhhust.pojo.Department;
import com.zhhust.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*
 * 作用；这个项目暂时未整合数据库
 *       这个类用于模拟数据库的 员工表
 * */
@Component
public class EmployeeDao {
    private static Map<Integer, Employee> employeeMap;//用Map模拟数据库的表
    private Integer autoAddId=1007;//模拟自增的主键

    @Resource
    DepartmentDao departmentDao;

    static {
        //创建部门表
        employeeMap =new HashMap<>();
        //初始化数据
        employeeMap.put(1001,new Employee(1001,"AA",0,"1880776761@qq.com",new Department(101,"教学部")));
        employeeMap.put(1002,new Employee(1002,"BB",1,"1880776762@qq.com",new Department(102,"教研部")));
        employeeMap.put(1003,new Employee(1003,"CC",0,"1880776763@qq.com",new Department(103,"人事部")));
        employeeMap.put(1004,new Employee(1004,"DD",1,"1880776764@qq.com",new Department(104,"公关部")));
        employeeMap.put(1005,new Employee(1005,"EE",0,"1880776765@qq.com",new Department(105,"保安部")));
        employeeMap.put(1006,new Employee(1006,"FF",1,"1880776766@qq.com",new Department(106,"小卖部")));
    }

    //增加员工
    public void addEmployee(Employee employee){
        if(employee.getId()==null){
            //自增的主键
            employee.setId(autoAddId++);
        }
        //根据用户输入的部门id找到相应部门
        employee.setDepartment(departmentDao.searchDepartmentById(employee.getDepartment().getId()));
        //保存
        employeeMap.put(employee.getId(),employee);
    }

    //修改员工
    public void updateEmployee(Employee employee){
        //根据用户输入的部门id找到相应部门
        employee.setDepartment(departmentDao.searchDepartmentById(employee.getDepartment().getId()));
        //保存
        employeeMap.put(employee.getId(),employee);
    }

    //获得所有员工信息
    public Collection<Employee> getAllEmployees(){
        return employeeMap.values();
    }

    //通过id查询远攻
    public Employee searchEmployeeById(Integer id){
        return employeeMap.get(id);
    }

    //删除员工信息
    public void deleteEmployeeById(Integer id){
        employeeMap.remove(id);
    }
}
