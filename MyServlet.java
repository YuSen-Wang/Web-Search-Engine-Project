package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.simple.parser.ParseException;



/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   private keywordDbUtil keywordDbUtil;
	
	@Resource(name="jdbc/web_keyword_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		//create our db util ... and pass in the conn pool / datasource
		try {
			keywordDbUtil = new keywordDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*try {
			listKeywords(request,response);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		String thekeyword = request.getParameter("url");
		try {
			addKeywords(request,response);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String Url = getJson.getUrl(thekeyword);
		try {
			getJson.getSnippet(Url);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		   request.setAttribute("feed_list", getJson.Responses);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
	        dispatcher.forward(request, response);	
		
		
	      
	
	}

	private void addKeywords(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// read student info from form data
				String keyword = request.getParameter("url");
				
				// create a new  object
				keyword theKeyword = new keyword(keyword);
				
				// add keyword to the database
				keywordDbUtil.addkeyword(theKeyword);
				
				// send back to main page
				//listKeywords(request, response);
				
			}

	private void listKeywords(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  //get object from db util 
  		List<keyword> keywords = keywordDbUtil.getKeywords();
  		
  		// add it to the request 
  		request.setAttribute("STUDENT_LIST", keywords);
  		
  		// send to JSP page (view)
  		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-keywords.jsp");
  		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			listKeywords(request,response);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
