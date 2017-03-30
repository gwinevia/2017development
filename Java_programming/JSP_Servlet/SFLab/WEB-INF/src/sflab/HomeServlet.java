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

@WebServlet("/home")
public class HomeServlet extends HttpServlet{

		private static final long serialVersionUID = 1L;
		
		Connection conn = null;
		String url = "jdbc:postgresql://localhost/sflab";
		String user = "ユーザ名";
		String password = "パスワード";

		public HomeServlet() {
			super();
		}
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// セッションを取得.
			HttpSession session = request.getSession( true );

			//データベース情報
			Map<String, String> dbinfo = new HashMap<String, String>();
			dbinfo.put( "url", url );
			dbinfo.put( "password", password );
			dbinfo.put("user", user);
			session.setAttribute( "db_info", dbinfo );

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/home.jsp" );
			dispatcher.forward( request, response );
		}
}
