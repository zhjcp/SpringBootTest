package com.zhhust.pojo;

import org.springframework.beans.factory.annotation.Value;

public class Dog {
    //@Value("旺财")
    //  这是Spring的注解方式，需要在实体类中改动（不推荐）
    // 但在Springboot中我们可以用yaml或properties文件代替这种做法！（推荐）
    private String name;
    //@Value("2")
    private int age;

    public Dog() {
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
