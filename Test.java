package servlets;

import org.json.simple.parser.ParseException;

public class Test {

	public static void main(String[] args) throws ParseException {
		String Url = getJson.getUrl("Boston");
		System.out.println(Url);
		getJson.getSnippet(Url);
		System.out.println(getJson.Responses.get(0).getUrl());
		System.out.println(getJson.Responses.get(1).getUrl());
		System.out.println(getJson.Responses.get(1).getSnippet());
	}

}
