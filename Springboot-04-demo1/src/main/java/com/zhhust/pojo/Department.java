package com.zhhust.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//部门
@Data // Getter、Setter、HashCode、ToString
@NoArgsConstructor //无参构造
@AllArgsConstructor //有参构造
public class Department {
    private Integer id;
    private String departName;
}
