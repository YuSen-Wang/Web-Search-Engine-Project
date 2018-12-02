package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ApacheHttpRestClient2 {

	public static void main(String[] args) throws ParseException {
		String output = null;
		String output2 = null;
		try {

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(
			"https://api.nytimes.com/svc/search/v2/articlesearch.json?query=NEW+YORK&api-key=4a0b85ba5d334115a3ef565347ec3963");
		getRequest.addHeader("accept", "application/json");

		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(
                         new InputStreamReader((response.getEntity().getContent())));

		
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			output2 = output; 
		}
		
		
		httpClient.getConnectionManager().shutdown();

	  } catch (ClientProtocolException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();
	  }
		
		
		JSONParser parser = new JSONParser(); 
		JSONObject jsonObject = (JSONObject) parser.parse(output2);
	
		String status = (String) jsonObject.get("status");
		JSONObject compose = (JSONObject) jsonObject.get("response");
		System.out.println("response: " + compose);
		JSONArray docs = (JSONArray) compose.get("docs");
		System.out.println("docs: " + docs);
		Iterator itr = docs.iterator();
		while (itr.hasNext()) {
			Object slide = itr.next();
			JSONObject jsonObject2 = (JSONObject) slide;
			String info = (String) jsonObject2.get("snippet");
			
			System.out.println("yes "+info);
		}
		
	}	

}