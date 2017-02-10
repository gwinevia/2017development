package sessiontest;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			    throws IOException, ServletException{

		response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();

	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>クッキーテスト</title>");
	    out.println("</head>");
	    out.println("<body>");

	    Cookie cookie[] = request.getCookies();
	    Cookie visitedCookie = null;

	    if (cookie != null){
	      for (int i = 0 ; i < cookie.length ; i++){
	        if (cookie[i].getName().equals("visited")){
	          visitedCookie = cookie[i];
	        }
	      }

	      if (visitedCookie != null){
	        int visited = Integer.parseInt(visitedCookie.getValue()) + 1;

	        out.println("<p>");
	        out.println(visited);
	        out.println("回目の訪問です。</p>");

	        visitedCookie.setValue(Integer.toString(visited));
	        response.addCookie(visitedCookie);
	      }else{
	        out.println("<p>初回の訪問です。</p>");

	        Cookie newCookie = new Cookie("visited", "1");
	        response.addCookie(newCookie);
	      }
	    }else{
	      out.println("<p>初回の訪問です。</p>");

	      Cookie newCookie = new Cookie("visited", "1");
	      response.addCookie(newCookie);
	    }

	    out.println("<a href=\"/apache-tomcat-9.0.0.M17/SessionTest/Login\">再表示</a>");

	    out.println("</body>");
	    out.println("</html>");
	}
}
