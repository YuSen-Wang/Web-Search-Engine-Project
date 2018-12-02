package servlets;

public class response {

	private String url;
	private String snippet;
	public response(String url, String snippet) {
		this.url = url;
		this.snippet = snippet;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		snippet = snippet;
	}
	
	
}
