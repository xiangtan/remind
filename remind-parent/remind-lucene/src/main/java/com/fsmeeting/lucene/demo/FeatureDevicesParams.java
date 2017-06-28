package com.fsmeeting.lucene.demo;

import java.io.Serializable;

/**
 * Created by think on 2017/6/28.
 */
public class FeatureDevicesParams implements Serializable {

    private int featureId;

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
}
