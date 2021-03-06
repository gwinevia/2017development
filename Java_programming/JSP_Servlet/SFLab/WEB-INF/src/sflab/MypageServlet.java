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

@WebServlet("/mypage")
public class MypageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	String url = "jdbc:postgresql://localhost/sflab";
	String user = "ユーザ名";
	String password = "パスワード";

	public MypageServlet() {
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

		// マイページへ遷移(フォワード).
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/mypage.jsp" );
		dispatcher.forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード設定.
		request.setCharacterEncoding( "UTF-8" );
		
		HttpSession session = request.getSession( true );
		Map<String, String> map2 = (Map<String, String>)session.getAttribute( "login_user" );
		String userID = (String)map2.get("id");
		
		String userState = request.getParameter("state");
		String userTweet = request.getParameter("tweet");
		
		try{
			Class.forName("org.postgresql.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			String sql = "";
			
			if(userState != null){
				sql = "INSERT INTO Log(id,state) values(";
				if(userState.equals("in")){
					sql = sql + userID + ",'in')";
					stmt.executeUpdate(sql);
					sql = "INSERT INTO Tweet(id,tweet) values(" + userID + ",'在室')";
					stmt.executeUpdate(sql);
				}else {
					sql = sql + userID + ",'out')";
					stmt.executeUpdate(sql);
					sql = "INSERT INTO Tweet(id,tweet) values(" + userID + ",'退室')";
					stmt.executeUpdate(sql);
				}
				
			}else if(userTweet != null && !hasXssChars(userTweet)){
				sql = "INSERT INTO Tweet(id,tweet) values(" + userID + ",'" + userTweet + "')";
				stmt.executeUpdate(sql);
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/mypage.jsp" );
				dispatcher.forward( request, response );
				return;
			}
			
			response.sendRedirect( "./home" );

			conn.close();
			stmt.close();
	    
		}catch(Exception e){}		
	}

	// XSS対策的なもの
	public boolean hasXssChars(String str) {
		if (str.matches(".*[<>&\"'].*")){
			return true;
		} else {
			return false;
		}
	}
}

