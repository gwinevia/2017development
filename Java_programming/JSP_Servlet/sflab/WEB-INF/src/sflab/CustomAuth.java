package sflab;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CustomAuth extends HttpServlet {
	
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
	  response.setContentType("text/html; charset=UTF-8");
      
      //(1)requestスコープのデータを取得
      String name = (String)request.getAttribute("name");
      
      //(2)sessionスコープのデータを取得
      HttpSession session = request.getSession();
      String id = (String)session.getAttribute("id");
      
      //(3)applicationスコープのデータを取得
      ServletContext sc = getServletContext();
      String prog = (String)sc.getAttribute("prog");

      PrintWriter out = response.getWriter();
      
      //(4)取得したデータの表示
      out.println("<HTML>");
      out.println("<BODY>");
      
      out.println("名前:" + name);
      out.println("<BR>");
      out.println("ID:" + id);
      out.println("<BR>");
      out.println("プログラム:" + prog);
      
      out.println("</BODY>");
      out.println("</HTML>");
      out.flush();
      out.close();
  }
}