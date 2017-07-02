package com.fsmeeting.lucene.demo;

import java.io.Serializable;

/**
 * Description: 设备参数信息
 *
 * @Author:yicai.liu<虚竹子>
 */
public class DeviceParams implements Serializable {
    private String os;
    private String brand;
    private String vedioBrand;
    private String memoryBrand;
    private String manufacturer;
    private String cpuBrand;
    private String gpuBrand;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVedioBrand() {
        return vedioBrand;
    }

    public void setVedioBrand(String vedioBrand) {
        this.vedioBrand = vedioBrand;
    }

    public String getMemoryBrand() {
        return memoryBrand;
    }

    public void setMemoryBrand(String memoryBrand) {
        this.memoryBrand = memoryBrand;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCpuBrand() {
        return cpuBrand;
    }

    public void setCpuBrand(String cpuBrand) {
        this.cpuBrand = cpuBrand;
    }

    public String getGpuBrand() {
        return gpuBrand;
    }

    public void setGpuBrand(String gpuBrand) {
        this.gpuBrand = gpuBrand;
    }
}
