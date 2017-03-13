package sflab;
import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/member")
public class MemberServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	String url = "jdbc:mysql://localhost/sflab";
	String user = "mmk";
	String password = "grqt58yj";

	public MemberServlet() {
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
		
		String userName = (String)map.get("name");
		if(userName.equals("SFLab")){
			// ログインフォームへ遷移(フォワード).
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/member.jsp" );
			dispatcher.forward( request, response );
		}else {
			response.sendRedirect( "./login" );
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エンコード設定.
		request.setCharacterEncoding( "UTF-8" );
		
		String userID = "";

		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			String sql = "";
			ResultSet rs;
			String[] userView = request.getParameterValues("view");
			int i = 0;
			int num = userView.length;
			
			while(i<=num){
				userID = userView[i];
				
				sql = "SELECT * FROM Member where id = " + userID;
				rs = stmt.executeQuery(sql);
				rs.next();
				
				if(rs.getString("view").equals("no")){
					sql = "UPDATE Member set view = 'ok' where id =" + userID;
					stmt.executeUpdate(sql);
				}else {
					sql = "UPDATE Member set view = 'no' where id =" + userID;
					stmt.executeUpdate(sql);									
				}
				
				i++;
			}

			conn.close();
			stmt.close();
	    
		}catch(Exception e){}
		
		// ログインフォームへ遷移(フォワード).
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/" );
		dispatcher.forward( request, response );
	}
}
