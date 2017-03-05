package webcontent;
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
	String url = "jdbc:mysql://localhost/2017development";
	String user = "mmk";
	String password = "grqt58yj";

	public MypageServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを取得.
		HttpSession session = request.getSession( true );

		// ログイン情報を取得.
		Map<String, String> map = (Map<String, String>)session.getAttribute( "login_user" );

		if ( null == map ) {
			// トップページへ遷移(リダイレクト).
			response.sendRedirect( "./login" );
			return;
		}

		// ログインフォームへ遷移(フォワード).
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
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO Log(id,state) values(";
			
			if(userState.equals("in")){
				String status_in = "in";
				sql = sql + userID + ",'in')";
			}else {
				String status_out = "out";
				sql = sql + userID + ",'out')";
			}
			
			int num = stmt.executeUpdate(sql);
			
			// ログインフォームへ遷移(フォワード).
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/" );
			dispatcher.forward( request, response );
			
			conn.close();
			stmt.close();
	    
		}catch(Exception e){}
			
	}
}
