package com.grove.tree.common;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrJDriver {
	public static String url = "http://localhost:8983/solr/tree";
//	public static String url = "http://localhost:8983/solr/board";
	public static SolrClient solr = new HttpSolrClient(url);
}
