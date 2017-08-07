package com.gop.util;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;

@Component
public class GopLuceneSearchUtility {

	private IndexWriter indexWriter;
	private Analyzer analyzer;
	private Directory index;
	private IndexWriterConfig config;
	private IndexReader reader;
	private QueryParser queryParser;
	private IndexSearcher indexSearcher;
	private Directory refreshIndex;
	
	public GopLuceneSearchUtility() throws IOException{
		analyzer = new StandardAnalyzer(Version.LUCENE_47);
		config = new IndexWriterConfig(Version.LUCENE_47, analyzer);
		queryParser = new QueryParser(Version.LUCENE_47, "key", analyzer);
		queryParser.setDefaultOperator(QueryParser.AND_OPERATOR);
		refreshIndex = new RAMDirectory();
	}
	
	public IndexReader getIndexReader(){
		return reader;
	}

	public Analyzer getAnalyzer() {
		return analyzer;
	}

	public QueryParser getQueryParser() {
		return queryParser;
	}
	
	public Document createDocument(String key, String json, float boost) throws IOException{
		Document document = new Document();
		TextField keyField = new TextField("key", key, Field.Store.YES);
		keyField.setBoost(boost);
		document.add(keyField);
		document.add(new StringField("json", json, Field.Store.YES));
		return document;
	}
	
	public void openWriter() throws IOException {
		indexWriter = new IndexWriter(refreshIndex, config.clone());
	}
	
	public void closeWriter() throws IOException {
		indexWriter.commit();
		indexWriter.close();
	}
	
	public void openSearcher() throws IOException{
		if(refreshIndex == null)
			refreshIndex = new RAMDirectory();
		index = new RAMDirectory(refreshIndex, null);
		reader = DirectoryReader.open(index);
		indexSearcher = new IndexSearcher(reader);
	}
	
	public void closeSearcher() throws IOException{
		indexSearcher = null;
		index.close();;
	}
	/*public void closeSearcher() throws IOException {
		indexWriter.close();
		index = new RAMDirectory(refreshIndex, null);
		reader = DirectoryReader.open(index);
		indexSearcher = new IndexSearcher(reader);
		refreshIndex.close();
		refreshIndex = null;
	}*/

	public IndexWriter getIndexWriter() {
		return indexWriter;
	}

	public IndexSearcher getIndexSearcher() {
		return indexSearcher;
	}
}
