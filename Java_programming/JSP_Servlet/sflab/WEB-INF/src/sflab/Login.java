package sflab;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet {
	
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
	  
	  response.setContentType("text/html; charset=UTF-8");
      String name = new String("Java太郎");
      String id = new String("java");
      String prog = new String("Java-Register");

      //(1)requestスコープのデータを登録
      request.setAttribute("name", name);

      //(2)sessionスコープのデータを登録
      HttpSession session = request.getSession();
      session.setAttribute("id", id);

      //(3)applicationスコープのデータを登録
      ServletContext sc = getServletContext();
      sc.setAttribute("prog", prog);

      //(4)GetDataへフォワード
      RequestDispatcher rd = request.getRequestDispatcher("./CustomAuth");
      rd.forward(request, response);
    }
}