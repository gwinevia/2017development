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

@WebServlet("/newuser")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	String url = "jdbc:postgresql://localhost/sflab";
	String user = "ユーザ名";
	String password = "パスワード";

	public NewUserServlet() {
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

		// 新規登録ページへ遷移(フォワード).
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/newuser.jsp" );
		dispatcher.forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード設定.
		request.setCharacterEncoding( "UTF-8" );
		
		HttpSession session = request.getSession( true );
		
		// 名前 を取得.
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
		
		// ログイン情報.
		Map<String, String> map = new HashMap<String, String>();
		map.put( "Email", userEmail );
		map.put( "Password", userPassword );
		map.put( "name", userName);
		map.put( "image", "sample.jpg");
		
		
		try{
			Class.forName("org.postgresql.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO Member(name,email,password) values('" + userName + "','" + userEmail + "','" + userPassword + "')";
			stmt.executeUpdate(sql);
			
			sql = "SELECT id FROM Member where name='" + userName + "' and password='" + userPassword +  "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			
			map.put("id",rs.getString("id"));
			
			// ログイン情報をセッションに保存.
			session.setAttribute( "login_user", map );
			
			sql = "INSERT INTO Log(id,state) values(" + rs.getString("id") + ",'out')";
			stmt.executeUpdate(sql);
			
			rs.close();
			conn.close();
			stmt.close();
	    
		}catch(Exception e){}		


		response.sendRedirect( "./home" );
	}
}
