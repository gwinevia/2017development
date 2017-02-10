package sessiontest;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CustomAuth2 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String target = request.getRequestURI();

        HttpSession session = request.getSession(false);

        if (session == null){
            /* まだ認証されていない */
            session = request.getSession(true);
            session.setAttribute("target", target);

            response.sendRedirect("/auth/Login");
        }else{
            Object loginCheck = session.getAttribute("login");
            if (loginCheck == null){
                /* まだ認証されていない */
                session.setAttribute("target", target);
                response.sendRedirect("/auth/Login");
            }
        }

        out.println("<html>");
        out.println("<head>");
        out.println("<title>ユーザー認証テスト</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<p>テストページ2</p>");

        out.println("<p><a href=\"/SessionTest/CustomAuth1\">テストページ1へ</a></p>");

        out.println("</body>");
        out.println("</html>");
    }
}
