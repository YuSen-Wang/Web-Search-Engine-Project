package servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;




public class keywordDbUtil {

private DataSource dataSource;
	
	public keywordDbUtil(DataSource theDataSource){
		this.dataSource = theDataSource;
	}
	
	public List<keyword> getKeywords() throws Exception{
		
		List<keyword> keywords = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		

		try {
			// get a connection 
			myConn = dataSource.getConnection();
			// create sql statement 
			String sql = "select * from keyword order by key_word";
			
			myStmt = myConn.createStatement();
			// execute query 
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while(myRs.next()){
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String mykeyword = myRs.getString("key_word");
				
				
				// create new  object 
				keyword tempKeyword = new keyword(id, mykeyword);
				
				
				// add it to the list 
				keywords.add(tempKeyword);
			}
			

		
		return keywords;
		}
		finally {
			// close JDBC objects 
			close(myConn, myStmt, myRs);
		}
	}
		private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
			// TODO Auto-generated method stub
			try{
				if (myRs != null){
					myRs.close();
				}
				if (myStmt != null){
					myStmt.close();
				}
				if (myConn != null){
					myConn.close();  // doesn't really close it ... just puts back in connection pool
				}
		    	}
			    
			
			catch(Exception exc) {
				exc.printStackTrace();
			}
		}

		public void addkeyword(keyword theKeyword) throws SQLException {
			Connection myConn = null;
			PreparedStatement myStmt = null;
			
			try {
				// get db connection
				myConn = dataSource.getConnection();
				// create sql for insert
				String sql = "insert into keyword"
						 	+ "(key_word) "
						 	+ "values (?)";
				myStmt = myConn.prepareStatement(sql);
				// set the param values for the student
				myStmt.setString(1, theKeyword.getKeyWord());
			
				
				// execute sql insert
				myStmt.execute();
			}
			finally {
				// clean up JDBC objects
				close(myConn, myStmt, null);
			}
			
		}
}
