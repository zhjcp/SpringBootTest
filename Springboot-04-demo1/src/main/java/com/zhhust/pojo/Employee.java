package com.zhhust.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//员工
@Data
@NoArgsConstructor
public class Employee {
        private Integer id;
        private String name;
        private Integer gender; //0:女 1：男
        private String email;
        private Date hireDate; //入职时间
        private Department department;

        public Employee(Integer id, String name, Integer gender, String email, Department department) {
                this.id = id;
                this.name = name;
                this.gender = gender;
                this.email = email;
                this.hireDate = new Date(); //日期使用默认自动生成的
                this.department = department;
        }
}
