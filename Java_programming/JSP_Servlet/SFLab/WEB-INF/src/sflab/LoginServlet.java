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


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	String url = "jdbc:postgresql://localhost/sflab";
	String user = "mmk";
	String password = "grqt58yj";

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを取得.
		HttpSession session = request.getSession( true );

		// ログイン情報を取得.
		Map<String, String> map = (Map<String, String>)session.getAttribute( "login_user" );

		// 既にログイン中.
		if ( null != map ) {
			// トップページへ遷移(リダイレクト).
			response.sendRedirect( "./home" );
			return;
		}

		// ログインフォームへ遷移(フォワード).
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/login.jsp" );
		dispatcher.forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード設定.
		request.setCharacterEncoding( "UTF-8" );

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

		// セッションを取得.
		HttpSession session = request.getSession( true );
		
		// ログイン認証.
		try{
			Class.forName("org.postgresql.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Member";
			ResultSet rs = stmt.executeQuery(sql);
	    
			while(rs.next()){
				// ログイン認証.
				if(rs.getString("email").equals( userEmail ) && rs.getString("password").equals( userPassword ) ) {
					
					session.setMaxInactiveInterval(600);
					
					// ログイン情報.
					Map<String, String> map = new HashMap<String, String>();
					map.put( "Email", userEmail );
					map.put( "Password", userPassword );
					map.put( "id", rs.getString("id"));
					map.put( "name", rs.getString("name"));
					map.put( "image", rs.getString("image"));
					
					// ログイン情報をセッションに保存.
					session.setAttribute( "login_user", map );
					
					//データベース情報
					Map<String, String> dbinfo = new HashMap<String, String>();
					dbinfo.put( "url", url );
					dbinfo.put( "password", password );
					dbinfo.put("user", user);
					session.setAttribute( "db_info", dbinfo );

					// トップページへ遷移(リダイレクト).
					response.sendRedirect( "./home" );
				}
			}
			response.sendRedirect( "./login" );
			
			conn.close();
			
		}catch(Exception e){}
	}
}


