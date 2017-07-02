package com.fsmeeting.lucene.demo;

import com.google.common.collect.Lists;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Description: 模拟DB数据
 *
 * @Author:yicai.liu<虚竹子>
 */
public class DB {

    public static List<FeatureDevicesParams> getFeatureDevicesParamsList() {
        FeatureDevicesParams featureDevicesParams_1 = new FeatureDevicesParams();
        DeviceParams deviceParams_1 = new DeviceParams();
        featureDevicesParams_1.setId(1);
        featureDevicesParams_1.setFeatureId(1);
        featureDevicesParams_1.setCreated(Calendar.getInstance().getTime());
        featureDevicesParams_1.setDeviceParams(deviceParams_1);
        deviceParams_1.setBrand("小米");
        deviceParams_1.setCpuBrand("CPU米");

        FeatureDevicesParams featureDevicesParams_2 = new FeatureDevicesParams();
        DeviceParams deviceParams_2 = new DeviceParams();
        featureDevicesParams_2.setId(2);
        featureDevicesParams_2.setFeatureId(1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, -2);
        featureDevicesParams_2.setCreated(calendar2.getTime());
        featureDevicesParams_2.setDeviceParams(deviceParams_2);
        deviceParams_2.setBrand("小米");
        deviceParams_2.setCpuBrand("CPU米");
        deviceParams_2.setGpuBrand("GPU米");
        deviceParams_2.setManufacturer("xiaomi");

        FeatureDevicesParams featureDevicesParams_3 = new FeatureDevicesParams();
        DeviceParams deviceParams_3 = new DeviceParams();
        featureDevicesParams_3.setId(3);
        featureDevicesParams_3.setFeatureId(1);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.add(Calendar.DAY_OF_MONTH, -3);
        featureDevicesParams_3.setCreated(calendar3.getTime());
        featureDevicesParams_3.setDeviceParams(deviceParams_3);
        deviceParams_3.setBrand("小米");
        deviceParams_3.setCpuBrand("CPU米");
        deviceParams_3.setGpuBrand("GPU米");
        deviceParams_3.setManufacturer("xiaomi");

        FeatureDevicesParams featureDevicesParams_4 = new FeatureDevicesParams();
        DeviceParams deviceParams_4 = new DeviceParams();
        featureDevicesParams_4.setId(4);
        featureDevicesParams_4.setFeatureId(1);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.add(Calendar.DAY_OF_MONTH, -4);
        featureDevicesParams_4.setCreated(calendar4.getTime());
        featureDevicesParams_4.setDeviceParams(deviceParams_4);
        deviceParams_4.setBrand("小米");
        deviceParams_4.setCpuBrand("CPU米");
        deviceParams_4.setGpuBrand("GPU米");
        List<FeatureDevicesParams> featureDevicesParamsList = Lists.newArrayList(
                featureDevicesParams_1,
                featureDevicesParams_2,
                featureDevicesParams_3,
                featureDevicesParams_4);
        return featureDevicesParamsList;
    }
}
