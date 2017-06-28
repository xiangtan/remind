package com.fsmeeting;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try {
            // 创建索引
            FSDirectory dir = NIOFSDirectory.open(Paths.get("Y://", "lucene"));
          //  Analyzer analyzer = new StandardAnalyzer();
            Analyzer analyzer = new KeywordAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            IndexWriter indexWriter = new IndexWriter(dir, indexWriterConfig);
            Document document = new Document();
            String title = "天龙八部";
            String content = "被索引的内容,慕复";
            Field f1 = new Field("title", title, TextField.TYPE_STORED);
            Field f2 = new Field("content", content, TextField.TYPE_STORED);
            document.add(f1);
            document.add(f2);
            indexWriter.addDocument(document);
            indexWriter.close();

            // 二、搜索
            DirectoryReader directoryReader = DirectoryReader.open(dir);
            IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

            QueryParser parser = new QueryParser("content", analyzer);
            Query query = parser.parse("被索引的内容,慕");

            TopDocs topDocs = indexSearcher.search(query, null, 100);
            ScoreDoc[] hits = topDocs.scoreDocs;

            System.out.println("查询结果数：" + hits.length);

            for (int n = 0; n < hits.length; n++) {
                Document hitDoc = indexSearcher.doc(hits[n].doc);
                System.out.println("搜索的结果title：" + hitDoc.get("title"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Hello World!");
    }
}
