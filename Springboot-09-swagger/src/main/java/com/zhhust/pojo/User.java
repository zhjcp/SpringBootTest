package com.zhhust.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

///仅当接口返回了某个实体类的实例，才会生成对应实体类的API文档
@ApiModel("用户实体类")
public class User {
    @ApiModelProperty("private权限的名字")
    private String private_name;
    @ApiModelProperty("private权限的密码")
    private String private_password;

    @ApiModelProperty("public权限的名字")
    public String public_name;
    @ApiModelProperty("public权限的密码")
    public String public_password;

    public User() {
    }

    public User(String private_name, String private_password) {
        this.private_name = private_name;
        this.private_password = private_password;
    }

    public User(String private_name, String private_password, String public_name, String public_password) {
        this.private_name = private_name;
        this.private_password = private_password;
        this.public_name = public_name;
        this.public_password = public_password;
    }

    public String getPrivate_name() {
        return private_name;
    }

    public void setPrivate_name(String private_name) {
        this.private_name = private_name;
    }

    public String getPrivate_password() {
        return private_password;
    }

    public void setPrivate_password(String private_password) {
        this.private_password = private_password;
    }

    public String getPublic_name() {
        return public_name;
    }

    public void setPublic_name(String public_name) {
        this.public_name = public_name;
    }

    public String getPublic_password() {
        return public_password;
    }

    public void setPublic_password(String public_password) {
        this.public_password = public_password;
    }

    @Override
    public String toString() {
        return "User{" +
                "private_name='" + private_name + '\'' +
                ", private_password='" + private_password + '\'' +
                ", public_name='" + public_name + '\'' +
                ", public_password='" + public_password + '\'' +
                '}';
    }
}
