package com.zhhust.controller;

import com.fasterxml.jackson.databind.node.POJONode;
import com.zhhust.dao.DepartmentDao;
import com.zhhust.dao.EmployeeDao;
import com.zhhust.pojo.Department;
import com.zhhust.pojo.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;

@Controller
public class EmployeeController {

    @Resource
    EmployeeDao employeeDao;
    @Resource
    DepartmentDao departmentDao;

    //查询所有员工
    @RequestMapping("/searchAllEmployees")
    public String searchAllEmployees(Model model){
        Collection<Employee> allEmployees = employeeDao.getAllEmployees();
        model.addAttribute("allEmployees",allEmployees);
        return "/employee/list";
    }

    /*
    * 下面是一个restful风格的设计，同一个地址，但是分别对应 GET、POST请求！
    *       当然，也可以直接写两个不同的请求地址。
    * */
    //跳转到添加员工页面
    @GetMapping("/addEmployee")
    public String toAddEmployee(Model model){
        Collection<Department> allDepartments = departmentDao.getAllDepartments();
        model.addAttribute("allDepartments",allDepartments);
        return "/employee/addEmployee";
    }
    //提交要添加的员工信息
    @PostMapping("/addEmployee")
    public String  submitEmployeeAdd(Employee employee){
        //执行添加工作
        //工作完成，重定向到员工信息界面
        employeeDao.addEmployee(employee);
        return "redirect:/searchAllEmployees";
    }

    //跳转到修改员工界面
    @RequestMapping("/toUpdateEmployee/{id}")
    public String toUpdateEmployee(@PathVariable Integer id, Model model){
        Employee employee = employeeDao.searchEmployeeById(id);
        model.addAttribute("employee",employee);
        Collection<Department> allDepartments = departmentDao.getAllDepartments();
        model.addAttribute("allDepartments",allDepartments);
        return "/employee/updateEmployee";
    }
    //提交已修改的员工信息
    @RequestMapping(value = "/updateEmployee",method = RequestMethod.POST)
    public String submitUpdate(Employee employee){
        employeeDao.updateEmployee(employee);
        return "redirect:/searchAllEmployees";
    }

    //删除员工
    @RequestMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Integer id){
        employeeDao.deleteEmployeeById(id);
        return "redirect:/searchAllEmployees";
    }
}
