package com.gugu.gugumodel.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 储存学生信息的entity，感觉老师和学生的表一样
 * 不存放账号密码
 * @author ren
 */
public class StudentEntity {
    Long id;
    Byte isActive;
    String studentName;
    String account;
    String email;
    @JsonIgnore
    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}