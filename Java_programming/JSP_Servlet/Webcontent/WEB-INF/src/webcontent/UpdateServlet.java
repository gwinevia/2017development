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

@WebServlet("/new")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection conn = null;
	String url = "jdbc:mysql://localhost/2017development";
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

		// 既にログイン中.
		if ( null != map ) {
			response.sendRedirect( "/update" );
			return;
		}

		// ログインフォームへ遷移(フォワード).
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher( "/login.jsp" );
		dispatcher.forward( request, response );
	}

}
