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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	String url = "jdbc:mysql://localhost/sflab";
	String user = "mmk";
	String password = "grqt58yj";

	public UpdateServlet() {
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
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/update.jsp" );
		dispatcher.forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// エンコード設定.
		request.setCharacterEncoding( "UTF-8" );	
		
		String userName = request.getParameter( "name" );
		if ( null == userName ) {
			userName = "";
		}		
		
		// E-MAIL を取得.
		String userEmail = request.getParameter( "email" );
		if ( null == userEmail ) {
			userEmail = "";
		}
		
		// PASSWORD を取得.
		String userPassword = request.getParameter( "password" );
		if ( null == userPassword ) {
			userPassword = "";
		}
		
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			String sql = "UPDATE Member set ";
			
			// ログイン情報.
			HttpSession session = request.getSession( true );
			Map<String, String> map = (Map<String, String>)session.getAttribute( "login_user" );
			String id = (String)map.get("id");
			
			if (!userName.equals("")) {
				map.put( "name", userName );
				sql = sql + "name='" + userName + "' where id=" + id;
				stmt.executeUpdate(sql);
			}
			
			sql = "UPDATE Member set ";
			
			if (!userEmail.equals("")) {
				map.put( "Email", userEmail );
				sql = sql + "email='" + userEmail + "' where id=" + id;
				stmt.executeUpdate(sql);
			}
			
			sql = "UPDATE Test set ";
			
			if (!userPassword.equals("")) {
				map.put( "Password", userPassword );
				sql = sql + "password='" + userPassword + "' where id=" + id;
				stmt.executeUpdate(sql);
			}
			
			// ログイン情報をセッションに保存.
			session.setAttribute( "login_user", map );
			
		
			conn.close();
			stmt.close();
	    
		}catch(Exception e){}	
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/" );
		dispatcher.forward( request, response );
	}
}

