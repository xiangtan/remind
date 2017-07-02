package com.fsmeeting.lucene.demo;

import com.alibaba.fastjson.JSON;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by think on 2017/6/28.
 */
public class TestCreater {

    public static void create(List<FeatureDevicesParams> featureDevicesParamsList) throws IOException {
        FSDirectory dir = FSDirectory.open(Paths.get("Y:", "lucene", "feature"));
        Analyzer analyzer = new StandardAnalyzer();
        //  Analyzer analyzer = new KeywordAnalyzer();
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
            Field brandField = new StringField("brand", String.valueOf(item.getDeviceParams().getBrand()), Field.Store.YES);
            Field cpuField = new StringField("cpuBrand", String.valueOf(item.getDeviceParams().getCpuBrand()), Field.Store.YES);
            Field gpuField = new StringField("gpuBrand", String.valueOf(item.getDeviceParams().getGpuBrand()), Field.Store.YES);
            Field manufacturerField = new StringField("manufacturer", String.valueOf(item.getDeviceParams().getManufacturer()), Field.Store.YES);

            document.add(idField);
            document.add(brandField);
            document.add(cpuField);
            document.add(gpuField);
            document.add(manufacturerField);
            indexWriter.addDocument(document);
        }
        indexWriter.close();

    }

    public static void main(String[] args) throws IOException {
        List<FeatureDevicesParams> featureDevicesParamsList = DB.getFeatureDevicesParamsList();
        //key,value的形式表现Field，并加载到Document
        create(featureDevicesParamsList);
    }
}
