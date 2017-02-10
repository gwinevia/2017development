package sessiontest;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CustomAuth1 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        Cookie cookie[] = request.getCookies();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>クッキーを表示</title>");
        out.println("</head>");
        out.println("<body>");

        if (cookie != null){
          for (int i = 0 ; i < cookie.length ; i++){
            if (cookie[i].getName().equals("visited")){
              String val = cookie[i].getValue();

              out.println("<p>");
              out.println(cookie[i].getName());
              out.println("=");
              out.println(val);
              out.println("</p>");
            }
          }
        }else{
          out.println("<p>クッキーが見つかりません</p>");
        }

        out.println("<a href=\"/apache-tomcat-9.0.0.M17/SessionTest/Login\">クッキーを再表示</a>");

        out.println("</body>");
        out.println("</html>");
    }
}