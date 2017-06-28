package com.fsmeeting.lucene.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.NIOFSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by think on 2017/6/28.
 */
public class IndexCreater {

    public static void create(List<FeatureDevicesParams> featureDevicesParamsList) throws IOException {
        FSDirectory dir = NIOFSDirectory.open(Paths.get("Y:", "lucene", "feature"));
        //  Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new KeywordAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        IndexWriter indexWriter = new IndexWriter(dir, indexWriterConfig);

        for (FeatureDevicesParams item : featureDevicesParamsList) {
            Document document = new Document();
            if (item.getFeatureId() <= 0) {
                continue;
            }
            Field idField = new Field("id", String.valueOf(item.getFeatureId()), TextField.TYPE_STORED);
            String jsonString = JSON.toJSONString(item.getDeviceParams());
            System.out.println(jsonString);
            Field json = new Field("content", jsonString, TextField.TYPE_STORED);
        }
        Document document = new Document();
        String title = "天龙八部";
        String content = "被索引的内容,慕复";
        Field f1 = new Field("title", title, TextField.TYPE_STORED);
        Field f2 = new Field("content", content, TextField.TYPE_STORED);
        document.add(f1);
        document.add(f2);
        indexWriter.addDocument(document);
        indexWriter.close();

    }

    public static void main(String[] args) throws IOException {
        FeatureDevicesParams featureDevicesParams_1 = new FeatureDevicesParams();
        DeviceParams deviceParams_1 = new DeviceParams();
        featureDevicesParams_1.setFeatureId(1);
        featureDevicesParams_1.setDeviceParams(deviceParams_1);
        deviceParams_1.setBrand("小米");
        deviceParams_1.setCpuBrand("CPU米");

        FeatureDevicesParams featureDevicesParams_2 = new FeatureDevicesParams();
        DeviceParams deviceParams_2 = new DeviceParams();
        featureDevicesParams_2.setFeatureId(1);
        featureDevicesParams_2.setDeviceParams(deviceParams_2);
        deviceParams_2.setBrand("小米");
        deviceParams_2.setCpuBrand("CPU米");
        deviceParams_2.setGpuBrand("GPU米");
        deviceParams_2.setManufacturer("xiaomi");

        FeatureDevicesParams featureDevicesParams_3 = new FeatureDevicesParams();
        DeviceParams deviceParams_3 = new DeviceParams();
        featureDevicesParams_3.setFeatureId(1);
        featureDevicesParams_3.setDeviceParams(deviceParams_3);
        deviceParams_3.setBrand("小米");
        deviceParams_3.setCpuBrand("CPU米");
        deviceParams_3.setGpuBrand("GPU米");
        deviceParams_3.setManufacturer("xiaomi");
        List<FeatureDevicesParams> featureDevicesParamsList = Lists.newArrayList(featureDevicesParams_1, featureDevicesParams_2, featureDevicesParams_3);
        create(featureDevicesParamsList);
    }
}
