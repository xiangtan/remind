package com.fsmeeting.lucene.demo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.NIOFSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by think on 2017/7/1.
 */
public class TestSearcher {

    public static void searching(FSDirectory dir) throws ParseException, IOException {
        DirectoryReader directoryReader = DirectoryReader.open(dir);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

         Analyzer analyzer = new StandardAnalyzer();
        // Analyzer analyzer = new KeywordAnalyzer();
        Query query = MultiFieldQueryParser.parse(
                new String[]{"小米", "GPU米", "xiaomi"},
                new String[]{"brand", "gpuBrand", "manufacturer"},
                new BooleanClause.Occur[]{BooleanClause.Occur.MUST, BooleanClause.Occur.MUST, BooleanClause.Occur.SHOULD},
                analyzer);
        TopDocs topDocs = indexSearcher.search(query, 100);
        ScoreDoc[] hits = topDocs.scoreDocs;

        System.out.println("查询结果数：" + hits.length);

        for (int n = 0; n < hits.length; n++) {
            System.out.println("搜索相似度:" + hits[n].score);
            Document hitDoc = indexSearcher.doc(hits[n].doc);
            System.out.println("搜索的结果id：" + hitDoc.get("id"));
            System.out.println("搜索的结果brand：" + hitDoc.get("brand"));
            System.out.println("搜索的结果cpuBrand：" + hitDoc.get("cpuBrand"));
            System.out.println("搜索的结果gpuBrand：" + hitDoc.get("gpuBrand"));
            System.out.println("搜索的结果manufacturer：" + hitDoc.get("manufacturer"));
            System.out.println("--------------------------------");
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        FSDirectory dir = FSDirectory.open(Paths.get("Y:", "lucene", "feature"));
        searching(dir);
    }
}
