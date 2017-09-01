package com.grove.tree.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class SolrSearcher {
	
	 public JSONArray auto(String query) {
	        query=makeQuery(query);
	        
	        StringBuffer sb = new StringBuffer();
	        JSONObject jObj = null;
	        JSONArray resultArr = new JSONArray();
	        try {
	            String url="http://localhost:8983/solr/tree/select?q=*%3A*&wt=json&indent=true&facet=true&facet.field=title&facet.prefix="+query+"&facet.limit=10&fl=score";
//	        	String url="http://localhost:8983/solr/board/select?q=*%3A*&wt=json&indent=true&facet=true&facet.field=title&facet.prefix="+query+"&facet.limit=10&fl=score";
	            URL uri = new URL(url);
	            URLConnection conn = uri.openConnection();
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	 
	            String str = "";
	 
	            while ((str = br.readLine()) != null) {
	                sb.append(str);
	            }
	 
	            JSONParser parser = new JSONParser();
	            jObj = (JSONObject) parser.parse(sb.toString());
	            JSONObject jObj2=(JSONObject)jObj.get("facet_counts");
	            JSONObject jObj3=(JSONObject)jObj2.get("facet_fields");
	            JSONArray jarr=(JSONArray)jObj3.get("title");
	        
	            for(int i=0;i<jarr.size();i+=2) {
	                resultArr.add(jarr.get(i));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return resultArr;
	    }
	 
	    private String makeQuery(String query) {
	        try {
	            query=URLEncoder.encode(query, "utf-8");
	        } catch (UnsupportedEncodingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return query;
	    }
}
