package com.fsmeeting.lucene.demo;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 特性设备参数信息
 *
 * @Author:yicai.liu<虚竹子>
 */
public class FeatureDevicesParams implements Serializable {

    private int id;

    private int featureId;

    private Date created;

    private DeviceParams deviceParams;


    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public DeviceParams getDeviceParams() {
        return deviceParams;
    }

    public void setDeviceParams(DeviceParams deviceParams) {
        this.deviceParams = deviceParams;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
