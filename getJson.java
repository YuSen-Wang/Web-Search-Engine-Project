package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class getJson {
	
	private static String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";
	private static String apikey = "4a0b85ba5d334115a3ef565347ec3963";
	public static List<response> Responses;
	
	public static String  getUrl(String query){
		String request = url + "?query=" + query + "&api-key=" + apikey;
		return request;
		//https://api.nytimes.com/svc/search/v2/articlesearch.json?query=O.J.+Simpson&api-key=4a0b85ba5d334115a3ef565347ec3963
	}
	
	public static void getSnippet (String url) throws ParseException{
		String output = null;
		String output2 = null;
		
		Responses = new ArrayList<>();
		try {

		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(
			url);
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
			String webURL = (String) jsonObject2.get("web_url");
			response myrespose = new response(webURL, info);
			Responses.add(myrespose);
			//System.out.println(info);
			
		}
		
	}	
		
 }
 
