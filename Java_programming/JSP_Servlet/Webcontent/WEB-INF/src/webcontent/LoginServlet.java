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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String USER_EMAIL = "test@materialize.jp";
	private static final String USER_PASSWORD = "login";

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを取得.
		HttpSession session = request.getSession( true );

		// ログイン情報を取得.
		Map<String, String> map = (Map<String, String>)session.getAttribute( "login_user" );

		// 既にログイン中.
		if ( null != map ) {
			// トップページへ遷移(リダイレクト).
			response.sendRedirect( "./" );
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
		if ( USER_EMAIL.equals( userEmail ) && USER_PASSWORD.equals( userPassword ) ) {
			
			session.setMaxInactiveInterval(60);
			
			// ログイン情報.
			Map<String, String> map = new HashMap<String, String>();
			map.put( "Email", userEmail );
			map.put( "Password", userPassword );

			// ログイン情報をセッションに保存.
			session.setAttribute( "login_user", map );

			// トップページへ遷移(リダイレクト).
			response.sendRedirect( "./" );
		} else {
		// ログインフォームへ遷移(リダイレクト).
			response.sendRedirect( "./login" );
		}
	}
}

