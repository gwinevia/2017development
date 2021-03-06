package sflab;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/log")
public class LogServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	String url = "jdbc:postgresql://localhost/sflab";
	String user = "ユーザ名";
	String password = "パスワード";

	public LogServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを取得.
		HttpSession session = request.getSession( true );

		// ログイン情報を取得.
		Map<String, String> map = (Map<String, String>)session.getAttribute( "login_user" );

		if ( null == map ) {
			// ログインフォームへ遷移(リダイレクト).
			response.sendRedirect( "./login" );
			return;
		}
		
		// 作業ログページへ遷移(フォワード).
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/log.jsp" );
		dispatcher.forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード設定.
		request.setCharacterEncoding( "UTF-8" );
		
		HttpSession session = request.getSession( true );
		Map<String, String> map2 = (Map<String, String>)session.getAttribute( "login_user" );
		String userID = (String)map2.get("id");

		String userTweet = request.getParameter("tweet");
		
		try{
			Class.forName("org.postgresql.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			String sql = "";
				
			if(userTweet != null && !hasXssChars(userTweet)){
				sql = "INSERT INTO Tweet(id,tweet) values(" + userID + ",'" + userTweet + "')";
				stmt.executeUpdate(sql);
				// ログインフォームへ遷移(フォワード).
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/log.jsp" );
				dispatcher.forward( request, response );
				return;
			}
			
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/home.jsp" );
			dispatcher.forward( request, response );
			
			conn.close();
			stmt.close();
	    
		}catch(Exception e){}
			
	}
	
	//XSS対策的なもの
	public boolean hasXssChars(String str) {
		if (str.matches(".*[<>&\"'].*")){
			return true;
		}else {
			return false;
		}
	}
}
