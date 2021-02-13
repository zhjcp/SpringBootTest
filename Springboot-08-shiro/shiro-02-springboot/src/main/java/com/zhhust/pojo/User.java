package com.zhhust.pojo;

public class User {
    private String name;
    private String password;
    private Integer age;
    private String perms;

    public User() {
    }

    public User(String name, String password, Integer age, String perms) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.perms = perms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", perms='" + perms + '\'' +
                '}';
    }
}
