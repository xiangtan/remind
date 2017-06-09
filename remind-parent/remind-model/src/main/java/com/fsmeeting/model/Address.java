package com.fsmeeting.model;

import java.io.Serializable;

/**
 * @Description:
 * @Author: yicai.liu
 * @Date: 14:24 2017/6/9
 */
public class Address implements Serializable {

    private String country;
    private String province;
    private String city;
    private String zipCode;
    private String detailMsg;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }
}
