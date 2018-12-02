package servlets;

public class keyword {
    
	private int id;
	private String keyWord;
	
	public keyword(int id, String keyWord) {
		super();
		this.id = id;
		this.keyWord = keyWord;
	}

	public keyword(String keyWord) {
		super();
		this.keyWord = keyWord;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Override
	public String toString() {
		return "keyword [id=" + id + ", keyWord=" + keyWord + "]";
	}
	
	
}
