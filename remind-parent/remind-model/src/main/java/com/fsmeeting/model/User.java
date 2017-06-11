package com.fsmeeting.model;

import java.io.Serializable;

/**
 * @Description:
 * @Author: yicai.liu
 * @Date: 14:21 2017/6/9
 */
public class User implements Serializable {

    private long id;
    private String username;
    private String pwd;
    private String phoneNum;
    private int age;
    private Address address;

    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", email='" + email + '\'' +
                '}';
    }
}
